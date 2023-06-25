package baekjoon;

import java.util.Scanner;

/**
 * 삼성 SW 역량 테스트 기출 문제
 * 골드 2
 * @author didgs
 *
 */
public class _2048_Easy {

	static int N, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] BOARD;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        BOARD = new int[N][N];

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                BOARD[i][j] = sc.nextInt();

        backtrack(0);

        System.out.println(ANS);
    }

    /**
     * dfs 로 backtracking 하여 값을 찾는다.
     * 정해진 횟수까지 동작을 한 후, 지정 횟수에 도달했을 시 BOARD 에서 최댓값을 찾아 갱신한 후 callback
     * @param count
     */
    public static void backtrack(int count){

        if(count == 5){
            if(findMax() > ANS)
                ANS = findMax();
            return;
        }

        /**
         * 중요 오답 포인트
         * 처음에 dfs 탐색 전에 임시로 BOARD 상태를 저장할 temp 배열을 전역변수로 설정했었다.
         * 각 재귀 depth 마다 고유의 temp 상태를 가지고 있어야 재귀에서 callback 시, 그 이전 상태로 똑바로 돌아갈  수 있다.
         * 전역으로 설정해두어 temp 배열을 초기화하면 다른 depth 상태의 BOARD 상태도 섞이기 때문이다.
         */
        for(int i=0; i<4; i++){
            int[][] temp = new int[N][N];
            for(int m=0; m<N; m++)
                for(int n=0; n<N; n++)
                    temp[m][n] = BOARD[m][n];

            move(i);
            backtrack(count+1);

            for(int m=0; m<N; m++)
                for(int n=0; n<N; n++)
                    BOARD[m][n] = temp[m][n];
        }
    }

    /**
     * 현재 BOARD 에서 가장 큰 값을 찾아 반환한다.
     * @return
     */
    public static int findMax(){
        int max = 0;
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(BOARD[i][j] > max)
                    max = BOARD[i][j];
        return max;
    }

    /**
     * 주어진 방향으로 보드 동작을 취한다.
     * @param dir
     */
    public static void move(int dir){
        combine(dir);
        toEnd(dir);
    }

    /**
     * 주어진 방향으로 숫자들을 이동시킬 때 합해질 숫자를 구한다.
     * 기울일 방향 끝의 벽에서부터 탐색하여 숫자가 발견될 시, 같은 행/열에 합해질 수 있는 숫자가 있는지 찾는다.
     * 합해지면 벽에서부터 가까운 쪽의 숫자가 *2가 되고, 더해진 숫자는 0 으로 바꾸어 상태를 최신화한다.
    * @param dir
     */
    public static void combine(int dir){
        if(dir == 0) {
            for(int i=N-1; i>=0; i--)
                for(int j=0; j<N; j++){
                    if(BOARD[j][i] != 0) {
                        int tx = j;
                        int ty = i;
                        while(true){
                            int nx = tx + DX[2];
                            int ny = ty + DY[2];
                            if(!isInbound(nx, ny))
                                break;
                            tx = nx;
                            ty = ny;
                            if(!canGo(tx, ty))
                                break;
                        }
                        if((tx!=j || ty!=i) && (BOARD[j][i] == BOARD[tx][ty])){
                            BOARD[j][i] *= 2;
                            BOARD[tx][ty] = 0;
                        }
                    }
                }
        }else if(dir == 1){
            for(int i=N-1; i>=0; i--)
                for(int j=0; j<N; j++){
                    if(BOARD[i][j] != 0){
                       int tx = i;
                       int ty = j;
                       while (true){
                           int nx = tx + DX[3];
                           int ny = ty + DY[3];
                           if(!isInbound(nx, ny))
                               break;
                           tx = nx;
                           ty = ny;
                           if(!canGo(tx, ty))
                               break;
                       }
                       if((tx!=i || ty!=j) && (BOARD[i][j] == BOARD[tx][ty])){
                           BOARD[i][j] *= 2;
                           BOARD[tx][ty] = 0;
                       }
                    }
                }
        }else if(dir == 2){
            for(int i=0; i<N; i++)
                for(int j=0; j<N; j++){
                    if(BOARD[j][i] != 0){
                        int tx = j;
                        int ty = i;
                        while(true){
                            int nx = tx + DX[0];
                            int ny = ty + DY[0];
                            if(!isInbound(nx, ny))
                                break;
                            tx = nx;
                            ty = ny;
                            if(!canGo(tx, ty))
                                break;
                        }
                        if((tx!=j || ty!=i) && (BOARD[j][i] == BOARD[tx][ty])){
                            BOARD[j][i] *= 2;
                            BOARD[tx][ty] = 0;
                        }
                    }
                }
        }else if(dir == 3){
            for(int i=0; i<N; i++)
                for(int j=0; j<N; j++){
                    if(BOARD[i][j] != 0){
                        int tx = i;
                        int ty = j;
                        while(true){
                            int nx = tx + DX[1];
                            int ny = ty + DY[1];
                            if(!isInbound(nx, ny))
                                break;
                            tx = nx;
                            ty = ny;
                            if(!canGo(tx, ty))
                                break;
                        }
                        if((tx!=i || ty!=j) && (BOARD[i][j] == BOARD[tx][ty])){
                            BOARD[i][j] *= 2;
                            BOARD[tx][ty] = 0;
                        }
                    }
                }
        }
    }

    /**
     * 주어진 방향으로 숫자들을 끝까지 이동시킨다.
     * 벽이 아니거나, 이미 숫자가 있는 자리가 아닐때까지 해당 방향으로 숫자를 이동시킨다.
     * @param dir
     */
    public static void toEnd(int dir){
        if(dir == 0){
            for(int i=N-1; i>=0; i--)
                for(int j=0; j<N; j++){
                    if(BOARD[j][i] != 0) {
                        int tx = j;
                        int ty = i;
                        while(true){
                            int nx = tx + DX[dir];
                            int ny = ty + DY[dir];
                            if(!isInbound(nx, ny) || !canGo(nx, ny))
                                break;
                            tx = nx;
                            ty = ny;
                        }
                        if(tx!=j || ty!=i){
                            BOARD[tx][ty] = BOARD[j][i];
                            BOARD[j][i] = 0;
                        }
                    }
                }
        }else if(dir == 1){
            for(int i=N-1; i>=0; i--)
                for(int j=0; j<N; j++){
                    if(BOARD[i][j] != 0){
                        int tx = i;
                        int ty = j;
                        while(true){
                            int nx = tx + DX[dir];
                            int ny = ty + DY[dir];
                            if(!isInbound(nx, ny) || !canGo(nx, ny))
                                break;
                            tx = nx;
                            ty = ny;
                        }
                        if(tx!=i || ty!=j){
                            BOARD[tx][ty] = BOARD[i][j];
                            BOARD[i][j] = 0;
                        }
                    }
                }
        }else if(dir == 2){
            for(int i=0; i<N; i++)
                for(int j=0; j<N; j++){
                    if(BOARD[j][i] != 0){
                        int tx = j;
                        int ty = i;
                        while(true){
                            int nx = tx + DX[dir];
                            int ny = ty + DY[dir];
                            if(!isInbound(nx, ny) || !canGo(nx, ny))
                                break;
                            tx = nx;
                            ty = ny;
                        }
                        if(tx!=j || ty!=i){
                            BOARD[tx][ty] = BOARD[j][i];
                            BOARD[j][i] = 0;
                        }
                    }
                }
        }else if(dir == 3){
            for(int i=0; i<N; i++)
                for(int j=0; j<N; j++){
                    if(BOARD[i][j] != 0){
                        int tx = i;
                        int ty = j;
                        while(true){
                            int nx = tx + DX[dir];
                            int ny = ty + DY[dir];
                            if(!isInbound(nx, ny) || !canGo(nx, ny))
                                break;
                            tx = nx;
                            ty = ny;
                        }
                        if(tx!=i || ty!=j){
                            BOARD[tx][ty] = BOARD[i][j];
                            BOARD[i][j] = 0;
                        }
                    }
                }
        }
    }

    /**
     * 좌표가 BOARD 배열 내에 존재하는지 확인한다.
     */
    public static boolean isInbound(int x, int y){
        return (x>=0 && x<N && y>=0 && y<N);
    }

    /**
     * 좌표에 숫자가 있는지 확인한다.
     */
    public static boolean canGo(int x, int y){
        return BOARD[x][y] == 0;
    }
}
