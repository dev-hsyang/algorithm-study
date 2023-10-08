package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2021 하반기 오전 1번 문제
 * 골드 3
 */
public class 정육면체한번더굴리기 {

    static int N, M, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] BOARD;
    static boolean[][] VISITED;
    static Dice2021 DICE = new Dice2021();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        BOARD = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++)
                BOARD[i][j] = Integer.parseInt(st.nextToken());
        }

        simulate();

        System.out.println(ANS);
    }

    public static void simulate(){
        DICE.init();
        for(int i=0; i<M; i++){
            if(!canGo(DICE.x + DX[DICE.dir], DICE.y + DY[DICE.dir]))
                DICE.reverse();
            DICE.roll();
            bfs(new int[] {DICE.x, DICE.y});
            updateDir();
        }
    }

    public static void bfs(int[] cord){
        VISITED = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        VISITED[cord[0]][cord[1]] = true;
        ANS += BOARD[cord[0]][cord[1]];
        queue.add(cord);
        while (!queue.isEmpty()){
            int[] now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if(canGo(nx, ny) && !VISITED[nx][ny] && BOARD[nx][ny] == BOARD[cord[0]][cord[1]]){
                    VISITED[nx][ny] = true;
                    ANS += BOARD[nx][ny];
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }

    public static void updateDir(){
        if(DICE.getBottom() > BOARD[DICE.x][DICE.y]){
            DICE.dir = (DICE.dir + 1) % 4;
        } else if(DICE.getBottom() < BOARD[DICE.x][DICE.y]){
            DICE.dir -= 1;
            if(DICE.dir == -1)
                DICE.dir = 3;
        }
    }

    public static boolean canGo(int x, int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
}

class Dice2021{
    int x, y, dir;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int[][] dice = new int[4][3];
    int[][] temp;

    public void roll(){
        switch (dir){
            case 0:
                initTemp();
                x += dx[dir];
                y += dy[dir];
                dice[1][0] = temp[3][1];
                dice[1][1] = temp[1][0];
                dice[1][2] = temp[1][1];
                dice[3][1] = temp[1][2];
                break;
            case 1:
                initTemp();
                x += dx[dir];
                y += dy[dir];
                dice[0][1] = temp[3][1];
                for(int i=1; i<4; i++)
                    dice[i][1] = temp[i - 1][1];
                break;
            case 2:
                initTemp();
                x += dx[dir];
                y += dy[dir];
                dice[1][0] = temp[1][1];
                dice[1][1] = temp[1][2];
                dice[1][2] = temp[3][1];
                dice[3][1] = temp[1][0];
                break;
            case 3:
                initTemp();
                x += dx[dir];
                y += dy[dir];
                dice[3][1] = temp[0][1];
                for(int i = 0; i<3; i++)
                    dice[i][1] = temp[i + 1][1];
                break;
        }
    }
    public void init(){
        x = 0;
        y = 0;
        dir = 0;
        dice[0][1] = 5;
        dice[1][0] = 4;
        dice[1][1] = 1;
        dice[1][2] = 3;
        dice[2][1] = 2;
        dice[3][1] = 6;
    }

    public void initTemp(){
        temp = new int[4][3];
        for(int i=0; i<4; i++)
            for(int j=0; j<3; j++)
                temp[i][j] = dice[i][j];
    }

    public void reverse(){
        if(dir == 0)
            dir = 2;
        else if(dir == 1)
            dir = 3;
        else if(dir == 2)
            dir = 0;
        else if(dir == 3)
            dir = 1;
    }

    public int getBottom(){
        return dice[3][1];
    }
}
