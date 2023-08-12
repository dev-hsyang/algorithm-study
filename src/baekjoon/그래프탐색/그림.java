package baekjoon.그래프탐색;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 1926
 * 실버 1
 */
public class 그림 {

    static int N, M, CNT, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] BOARD;
    static boolean[][] VISITED;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        BOARD = new int[N][M];
        VISITED = new boolean[N][M];

        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                BOARD[i][j] = sc.nextInt();

        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                if(BOARD[i][j] == 1 && !VISITED[i][j])
                    operate(i, j);


        System.out.println(CNT);
        System.out.println(ANS);
    }

    public static void operate(int x, int y){
        CNT += 1;
        int count = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        VISITED[x][y] = true;
        while(!queue.isEmpty()){
            int[] next = queue.poll();
            for(int i=0; i<4; i++){
                int nx = next[0] + DX[i];
                int ny = next[1] + DY[i];
                if(isInbound(nx, ny) && isPainted(nx, ny) && !VISITED[nx][ny]){
                    VISITED[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                    count += 1;
                }
            }
        }
        ANS = Math.max(ANS, count);
    }

    public static boolean isInbound(int x, int y){
        return (x>=0 && x<N && y>=0 && y<M);
    }

    public static boolean isPainted(int x, int y){
        return BOARD[x][y] == 1;
    }
}
