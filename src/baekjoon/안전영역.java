package baekjoon;

import java.util.Scanner;

/**
 * 실버 1
 * DFS BFS 필수문제
 * 백준 2468
 */
public class 안전영역 {

    static int N, ANS, HEIGHT, COUNT;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] MAP;
    static boolean[][] VISITED;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        MAP = new int[N][N];
        VISITED = new boolean[N][N];

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                MAP[i][j] = sc.nextInt();

        for(int n=0; n<=100; n++) {
            HEIGHT = n;
            COUNT = 0;
            VISITED = new boolean[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (isAvailable(i, j)){
                        operate(new int[]{i, j});
                        COUNT += 1;
                    }
            ANS = Math.max(ANS, COUNT);
        }

        System.out.println(ANS);
    }

    public static void operate(int node[]){

        if(!isAvailable(node[0], node[1]))
            return;

        VISITED[node[0]][node[1]] = true;

        for(int i=0; i<4; i++){
            int nx = node[0] + DX[i];
            int ny = node[1] + DY[i];
            if(isInbound(nx, ny) && isAvailable(nx, ny))
                operate(new int[] {nx, ny});
        }
    }

    public static boolean isInbound(int x, int y){
        return (x>=0 && x<N && y>=0 && y<N);
    }

    public static boolean isAvailable(int x, int y){
        return (MAP[x][y] > HEIGHT && !VISITED[x][y]);
    }
}
