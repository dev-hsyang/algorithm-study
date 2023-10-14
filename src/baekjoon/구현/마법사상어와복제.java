package baekjoon.구현;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 23290
 * 삼성 SW 역량테스트 2021 하반기 오후 1번 문제
 * 골드 1
 */
public class 마법사상어와복제 {

    static int M, S, PACK_MAX, ANS;
    static int[] PDX = {-1, 0, 1, 0};
    static int[] PDY = {0, -1, 0, 1};
    static int[] MDX = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] MDY = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] CANDI = new int[3];
    static int[] CANDI2 = new int[3];
    static int[][] BOARD = new int[5][5];
    static boolean FOUND;
    static Shark23290 SHARK;
    static Queue<Fish23290> EGGS = new LinkedList<>();
    static ArrayList<Fish23290> FISHES = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        S = sc.nextInt();
        for(int i=0; i<M; i++)
            FISHES.add(new Fish23290(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        SHARK = new Shark23290(sc.nextInt(), sc.nextInt());

        simulate();
        System.out.println(ANS);
    }

    public static void simulate(){
        for(int i = 0; i< S; i++){
            monsterDuplicate();
            monsterMoves();
            sharkMoves();
            eraseDeads();
            duplicateComplete();
        }
        ANS = FISHES.size();
    }

    public static void monsterDuplicate(){
        for(Fish23290 f : FISHES)
            EGGS.add(new Fish23290(f.x, f.y, f.d));
    }

    public static void monsterMoves(){
        for(Fish23290 f : FISHES){
            for(int i=0; i<8; i++){
                int nx = f.x + MDX[f.d];
                int ny = f.y + MDY[f.d];
                if(canGo(nx, ny)){
                    f.x = nx;
                    f.y = ny;
                    break;
                }
                f.d -= 1;
                if(f.d == 0)
                    f.d = 8;
            }
        }
    }

    public static void sharkMoves(){
        FOUND = false;
        PACK_MAX = 0;
        CANDI = new int[3];
        dfs(0, new int[3]);
        if(PACK_MAX == 0)
            CANDI = CANDI2;

        Fish23290[] candi = new Fish23290[FISHES.size()];
        for(int i = 0; i< FISHES.size(); i++)
            candi[i] = FISHES.get(i);

        for(int i=0; i<3; i++){
            int nx = SHARK.x + PDX[CANDI[i]];
            int ny = SHARK.y + PDY[CANDI[i]];
            for(int j=0; j<candi.length; j++){
                if(candi[j] != null && (candi[j].x == nx && candi[j].y == ny)){
                    candi[j] = null;
                    BOARD[nx][ny] = 3;
                }
            }
            SHARK.x = nx;
            SHARK.y = ny;
        }
        FISHES = new ArrayList<>();
        for(int i=0; i<candi.length; i++)
            if(candi[i] != null)
                FISHES.add(candi[i]);
    }

    public static void eraseDeads(){
        for(int i=1; i<=4; i++)
            for(int j=1; j<=4; j++)
                if(BOARD[i][j] > 0)
                    BOARD[i][j] -= 1;
    }

    public static void duplicateComplete(){
        while (!EGGS.isEmpty())
            FISHES.add(EGGS.poll());
    }

    public static void dfs(int depth, int[] dir){
        if(depth == 3){
            candiMove(dir);
            return;
        }

        for(int i=0; i<4; i++){
            dir[depth] = i;
            dfs(depth + 1, dir);
        }
    }

    public static void candiMove(int[] dir){
        int x = SHARK.x;
        int y = SHARK.y;
        int cnt = 0;
        boolean[][] visited = new boolean[5][5];
        for(int i=0; i<3; i++){
            int nx = x + PDX[dir[i]];
            int ny = y + PDY[dir[i]];
            if(!isInbound(nx ,ny))
                return;

            for(Fish23290 f : FISHES)
                if(f.x == nx && f.y == ny && !visited[nx][ny])
                    cnt += 1;
            visited[nx][ny] = true;
            x = nx;
            y = ny;
        }

        if(!FOUND)
            for(int k=0; k<3; k++)
                CANDI2[k] = dir[k];
        FOUND = true;

        if(cnt > PACK_MAX){
            PACK_MAX = cnt;
            for(int i=0; i<3; i++)
                CANDI[i] = dir[i];
        }
    }

    public static boolean isInbound(int x, int y){
        return x>=1 && x<=4 && y>=1 && y<=4;
    }

    public static boolean canGo(int x, int y){
        return isInbound(x, y) && BOARD[x][y] == 0 && !(x == SHARK.x && y == SHARK.y);
    }
}

class Shark23290{

    public Shark23290(int x, int y){
        this.x = x;
        this.y = y;
    }
    int x;
    int y;
}

class Fish23290{
    public Fish23290(int x, int y, int d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
    int x;
    int y;
    int d;
}
