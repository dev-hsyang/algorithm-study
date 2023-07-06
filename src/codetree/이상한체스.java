package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.BufferPoolMXBean;
import java.util.StringTokenizer;

/**
 * 골드 4
 * 삼성 SW 역량테스트 2018 상반기 오전 1번 문제
 */
public class 이상한체스 {

    static int N, M, X1, Y1;
    static int ANS = Integer.MAX_VALUE;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][][] BOARD;
    static boolean[][][] VISITED;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        BOARD = new int[N][M][2];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++)
                BOARD[i][j][0] = Integer.parseInt(st.nextToken());
        }

        findFirstPawn();

        VISITED = new boolean[N][M][4];
        BOARD[X1][Y1][1] = 0;
        operate();

        VISITED = new boolean[N][M][4];
        BOARD[X1][Y1][1] = 1;
        operate();

        VISITED = new boolean[N][M][4];
        BOARD[X1][Y1][1] = 2;
        operate();

        VISITED = new boolean[N][M][4];
        BOARD[X1][Y1][1] = 3;
        operate();

        System.out.println(ANS);
    }

    public static void operate(){
        int[][][] temp = new int[N][M][2];
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                temp[i][j][0] = BOARD[i][j][0];

        simulate();
        ANS = Math.min(countMin(), ANS);

        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                BOARD[i][j][0] = temp[i][j][0];

        for(int i=X1; i<N; i++)
            for(int j=Y1+1; j<M; j++){
                if(isTeam(i, j) && BOARD[i][j][1] < 3 && !VISITED[i][j][BOARD[i][j][1]]){
                    BOARD[i][j][1] += 1;
                    VISITED[i][j][BOARD[i][j][1]] = true;
                    operate();
                    BOARD[i][j][1] -= 1;
                }
            }
    }

    public static void simulate(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(isTeam(i, j)){
                    int num = BOARD[i][j][0];
                    int dir = BOARD[i][j][1];
                    moveChess(i, j, num, dir);
                }
            }
        }
    }

    public static void moveChess(int x, int y, int num, int dir){
        if(num == 1){
            move(x, y, dir);
        }else if(num == 2){
            move(x, y, dir + 1 > 3 ? 0 : dir + 1);
            move(x, y, dir - 1 < 0 ? 3 : dir - 1);
        }else if(num == 3){
            move(x, y, dir);
            move(x, y, dir + 1 > 3 ? 0 : dir + 1);
        }else if(num == 4){
            move(x, y, dir);
            move(x, y, dir + 1 > 3 ? 0 : dir + 1);
            move(x, y, dir - 1 < 0 ? 3 : dir - 1);
        }else if(num == 5){
            move(x, y, 0);
            move(x, y, 1);
            move(x, y, 2);
            move(x, y, 3);
        }
    }

    public static void move(int x, int y, int dir){
        while(true){
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!canMove(nx, ny))
                break;
            if(isTeam(nx, ny)){
                x = nx;
                y = ny;
                continue;
            }
            BOARD[nx][ny][0] = -1;
            x = nx;
            y = ny;
        }
    }

    public static int countMin(){
        int num = 0;
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                if(BOARD[i][j][0] == 0)
                    num += 1;
        return num;
    }

    public static boolean isTeam(int x, int y){
        return BOARD[x][y][0] >= 1 && BOARD[x][y][0] <= 5;
    }

    public static boolean canMove(int x, int y){
        return (x>=0 && x<N && y>=0 && y<M && BOARD[x][y][0] != 6);
    }

    public static void findFirstPawn(){
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                if(isTeam(i, j)){
                    X1 = i;
                    Y1 = j;
                    return;
                }
    }
}
