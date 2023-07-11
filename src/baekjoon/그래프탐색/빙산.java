package baekjoon.그래프탐색;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 2573
 * DFS BFS 필수문제
 * 골드 4
 */
public class 빙산 {

    static int N, M, ANS, COUNT;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] MAP;
    static int[][] CANDIMAP;
    static boolean CAN_MELT = true;
    static boolean[][] VISITED;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        MAP = new int[N][M];
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                MAP[i][j] = sc.nextInt();

        while(CAN_MELT)
            operate();

        System.out.println(ANS);
    }

    public static void operate(){
        CAN_MELT = false;
        CANDIMAP = new int[N][M];
        COUNT += 1;
        for (int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(MAP[i][j] != 0){
                    CAN_MELT = true;
                    melt(i, j);
                }
            }
        }
        copyMap();
        if(connectedComponent() >= 2){
            ANS = COUNT;
            CAN_MELT = false;
        }
    }

    public static void melt(int x, int y){
        int melt = 0;
        for(int i=0; i<4 ;i++){
            int nx = x + DX[i];
            int ny = y + DY[i];
            if (isInbound(nx, ny) && MAP[nx][ny]==0)
                melt += 1;
        }
        melt = MAP[x][y] - melt;
        if (melt<0)
            CANDIMAP[x][y] = 0;
        else
            CANDIMAP[x][y] = melt;
    }

    public static void copyMap(){
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                MAP[i][j] = CANDIMAP[i][j];
    }

    public static int connectedComponent(){
        int count = 0;
        VISITED = new boolean[N][M];
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                if(MAP[i][j] != 0 && !VISITED[i][j]){
                    count += 1;
                    bfs(new int[] {i, j});
                }
            }
        }
        return count;
    }

    public static void bfs(int[] cord){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(cord);
        VISITED[cord[0]][cord[1]] = true;
        while (!queue.isEmpty()){
            int[] now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if(isInbound(nx, ny) && !VISITED[nx][ny] && MAP[nx][ny] != 0){
                    VISITED[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }

    public static boolean isInbound(int x, int y){
        return (x>=0 && x<N && y>=0 && y<M);
    }
}
