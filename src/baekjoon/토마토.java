package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 7569
 * DFS BFS 필수문제
 * 골드 5
 */
public class 토마토 {

    static int N, M, H, ANS;
    static int[] DZ = {1, 0, 0, -1, 0, 0};
    static int[] DX = {0, -1, 0, 0, 1, 0};
    static int[] DY = {0, 0, 1, 0, 0, -1};
    static int[][][] BOX;
    static boolean[][][] VISITED;
    static Queue<int[]> TOMATOES = new LinkedList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        H = sc.nextInt();
        BOX = new int[H][N][M];
        VISITED = new boolean[H][N][M];

        for(int i=0; i<H; i++)
            for(int j=0; j<N; j++)
                for(int k=0; k<M; k++){
                    BOX[i][j][k] = sc.nextInt();
                    if(BOX[i][j][k] == 1)
                        TOMATOES.add(new int[] {i, j, k});
                }

        operate();
        findMin();

        System.out.println(ANS);
    }

    public static void operate(){
        while(!TOMATOES.isEmpty()){
            int[] now = TOMATOES.poll();
            for(int i=0; i<6; i++){
                int nz = now[0] + DZ[i];
                int nx = now[1] + DX[i];
                int ny = now[2] + DY[i];
                if(isInbound(nz, nx, ny) && !VISITED[nz][nx][ny] && BOX[nz][nx][ny] == 0){
                    BOX[nz][nx][ny] = BOX[now[0]][now[1]][now[2]] + 1;
                    VISITED[nz][nx][ny] = true;
                    TOMATOES.add(new int[] {nz, nx, ny});
                }
            }
        }
    }

    public static boolean isInbound(int z, int x, int y){
        return (z>=0 && z<H && x>=0 && x<N && y>=0 && y<M);
    }

    public static void findMin(){
        for(int i=0; i<H; i++)
            for(int j=0; j<N; j++)
                for (int k=0; k<M; k++ ){
                    if(BOX[i][j][k]==0){
                        ANS = -1;
                        return;
                    }
                    ANS = Math.max(ANS, BOX[i][j][k]);
                }
        ANS -= 1;
    }
}
