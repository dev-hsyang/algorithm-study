package baekjoon;

import java.util.Scanner;

/**
 * 백준 14890
 * 삼성 SW 역량 테스트 기출 문제
 * 골드 3
 * @author didgs
 *
 */
public class 경사로 {

    static int N, L, ANS;
    static int[][] ROAD;
    static boolean[][] BLOCKS;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        ROAD = new int[N][N];

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                ROAD[i][j] = sc.nextInt();

        checkRow();
        checkCol();

        System.out.println(ANS);
    }

    /**
     * 행부터 탐색하며 지나갈 수 있는 행을 구한다.
     * (1) 현재칸과 다음칸이 같으면 넘어간다.
     * (2) 현재칸과 다음칸의 차이가 1보다 크면 그 행은 보도블럭을 놓아도 지나갈 수 없다.
     * (3) 보도블럭을 놓을 수 없는 상황이면 그 행은 지나갈 수 없다.
     * (4) 보도블럭을 놓는다.
     */
    public static void checkRow(){
        BLOCKS = new boolean[N][N];
        for(int i=0; i<N; i++) {
            boolean available = true;
            for (int j = 0; j < N - 1; j++) {
                int h = ROAD[i][j];
                int nextH = ROAD[i][j + 1];

                if (h == nextH) // (1)  ** 오답지점 ** 경사로가 놓여져 있는 여부를 이곳에서 체크하고 넘어가게 되면, 경사로가 겹치는 상황을 catch 할 수 없게된다.
                    continue;
                if (getDiff(h, nextH) != 1){ // (2)
                    available = false;
                    break;
                }
                if(!canBlockRow(i, j)){ // (3)
                    available = false;
                    break;
                }
                blockRow(i, j); // (4)
            }
            if (available)
                ANS += 1;
        }
    }

    public static void checkCol(){
        BLOCKS = new boolean[N][N];
        for (int i=0; i<N; i++) {
            boolean available = true;
            for(int j=0; j<N-1; j++){
                int h = ROAD[j][i];
                int nextH = ROAD[j+1][i];

                if(h == nextH)
                    continue;
                if(getDiff(h, nextH) != 1){
                    available = false;
                    break;
                }
                if(!canBlockCol(j, i)){
                    available = false;
                    break;
                }
                blockCol(j, i);
            }
            if (available)
                ANS += 1;
        }
    }

    /**
     * 주어진 좌표를 기준으로 보도블럭을 놓을 수 있는지 확인한다.
     * (1) 현재칸보다 다음칸이 높은 경우
     * (2) 현재칸보다 다음칸이 낮은 경우로 나누어서 체크하고
     * (3) 다음/이전 칸이 배열 내 존재하는지, 경사로 길이만큼 칸들의 높이가 일정한지, 겹치는 경사로가 있는지 확인한다.
     * @param x
     * @param y
     * @return
     */
    public static boolean canBlockRow(int x, int y){
        if (ROAD[x][y] < ROAD[x][y+1]){ // (1)
            for(int i=0; i<L; i++)
                if(!isInbound(y-i) || ROAD[x][y] != ROAD[x][y-i] || BLOCKS[x][y-i]) // (3)
                    return false;
        } else if (ROAD[x][y] > ROAD[x][y+1]) { // (2)
            for(int i=1; i<=L; i++)
                if(!isInbound(y+i) || ROAD[x][y+1] != ROAD[x][y+i] || BLOCKS[x][y+i]) // (3)
                    return false;
        }
        return true;
    }

    /**
     * 보도블럭(경사로를) 놓는다.
     * (1) 현재칸보다 다음칸이 높은 경우
     * (2) 현재칸보다 다음칸이 낮은 경우로 나누고
     * (3) 경사로의 길이만큼 보도블럭을 깔아준다.
     * @param x
     * @param y
     */
    public static void blockRow(int x, int y){
        if(ROAD[x][y] < ROAD[x][y+1]) // (1)
            for(int i=0; i<L; i++) // (3)
                BLOCKS[x][y-i] = true;
        else if (ROAD[x][y] > ROAD[x][y+1]) // (2)
            for(int i=1; i<=L; i++) // (3)
                BLOCKS[x][y+i] = true;
    }

    public static boolean canBlockCol(int x, int y){
        if (ROAD[x][y] < ROAD[x+1][y]) { // 다음칸이 더 높을 경우
            for (int i = 0; i < L; i++)
                if(!isInbound(x-i) || ROAD[x][y] != ROAD[x-i][y] || BLOCKS[x-i][y])
                    return false;
        } else if (ROAD[x][y] > ROAD[x+1][y]) {
            for (int i=1; i<=L; i++)
                if(!isInbound(x+i) || ROAD[x+1][y] != ROAD[x+i][y] || BLOCKS[x+i][y])
                    return false;
        }
        return true;
    }

    public static void blockCol(int x, int y){
        if (ROAD[x][y] < ROAD[x+1][y])
            for(int i=0; i<L; i++)
                BLOCKS[x-i][y] = true;
        else if(ROAD[x][y] > ROAD[x+1][y])
            for (int i=1; i<=L; i++)
                BLOCKS[x+i][y] = true;
    }

    public static boolean isInbound(int n){
        return n>=0 && n<N;
    }

    public static int getDiff(int a, int b){
        if (a>b)
            return a - b;
        else
            return b - a;
    }
}
