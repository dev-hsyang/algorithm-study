package codetree.삼성SW역량테스트;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 삼성 SW 역량테스트 2021 하반기 오후 1번 문제
 * 골드 1
 */
public class 팩맨 {

    static int M, T, PACK_MAX, ANS;
    static int[] PDX = {-1, 0, 1, 0};
    static int[] PDY = {0, -1, 0, 1};
    static int[] MDX = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] MDY = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] CANDI = new int[3];
    static int[] CANDI2 = new int[3];
    static int[][] BOARD = new int[5][5];
    static boolean FOUND;
    static Packman2021 PACKMAN;
    static Queue<Monster2021> EGGS = new LinkedList<>();
    static ArrayList<Monster2021> MONSTERS = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        T = sc.nextInt();
        PACKMAN = new Packman2021(sc.nextInt(), sc.nextInt());
        for(int i=0; i<M; i++)
            MONSTERS.add(new Monster2021(sc.nextInt(), sc.nextInt(), sc.nextInt()));

        simulate();
        System.out.println(ANS);
    }

    public static void simulate(){
        for(int i=0; i<T; i++){
            monsterDuplicate();
            monsterMoves();
            packMoves();
            eraseDeads();
            duplicateComplete();
        }
        ANS = MONSTERS.size();
    }

    public static void monsterDuplicate(){
        for(Monster2021 m : MONSTERS)
            EGGS.add(new Monster2021(m.x, m.y, m.d));
    }

    public static void monsterMoves(){
        for(Monster2021 m : MONSTERS){
            for(int i=0; i<8; i++){
                int nx = m.x + MDX[m.d];
                int ny = m.y + MDY[m.d];
                if(canGo(nx, ny)){
                    m.x = nx;
                    m.y = ny;
                    break;
                }
                m.d += 1;
                if(m.d == 9)
                    m.d = 1;
            }
        }
    }

    public static void packMoves(){
        FOUND = false;
        PACK_MAX = 0;
        CANDI = new int[3];
        dfs(0, new int[3]);
        if(PACK_MAX == 0)
            CANDI = CANDI2;

        Monster2021[] candi = new Monster2021[MONSTERS.size()];
        for(int i=0; i<MONSTERS.size(); i++)
            candi[i] = MONSTERS.get(i);

        for(int i=0; i<3; i++){
            int nx = PACKMAN.x + PDX[CANDI[i]];
            int ny = PACKMAN.y + PDY[CANDI[i]];
            for(int j=0; j<candi.length; j++){
                if(candi[j] != null && (candi[j].x == nx && candi[j].y == ny)){
                    candi[j] = null;
                    BOARD[nx][ny] = 3;
                }
            }
            PACKMAN.x = nx;
            PACKMAN.y = ny;
        }
        MONSTERS = new ArrayList<>();
        for(int i=0; i<candi.length; i++)
            if(candi[i] != null)
                MONSTERS.add(candi[i]);
    }

    public static void eraseDeads(){
        for(int i=1; i<=4; i++)
            for(int j=1; j<=4; j++)
                if(BOARD[i][j] > 0)
                    BOARD[i][j] -= 1;
    }

    public static void duplicateComplete(){
        while (!EGGS.isEmpty())
            MONSTERS.add(EGGS.poll());
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
        int x = PACKMAN.x;
        int y = PACKMAN.y;
        int cnt = 0;
        boolean[][] visited = new boolean[5][5];
        for(int i=0; i<3; i++){
            int nx = x + PDX[dir[i]];
            int ny = y + PDY[dir[i]];
            if(!isInbound(nx ,ny))
                return;

            for(Monster2021 m : MONSTERS)
                if(m.x == nx && m.y == ny && !visited[nx][ny])
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
        return isInbound(x, y) && BOARD[x][y] == 0 && !(x == PACKMAN.x && y == PACKMAN.y);
    }
}

class Packman2021{

    public Packman2021(int x, int y){
        this.x = x;
        this.y = y;
    }
    int x;
    int y;
}

class Monster2021{
    public Monster2021(int x, int y, int d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
    int x;
    int y;
    int d;
}
