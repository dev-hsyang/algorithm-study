package baekjoon.그래프탐색;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1012
 * 실버 2
 */
public class 유기농배추 {

    static int T, M, N, K, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] MAP;
    static boolean[][] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            MAP = new int[N][M];
            VISITED = new boolean[N][M];
            ANS = 0;

            for(int k=0; k<K; k++){
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                MAP[x][y] = 1;
            }

            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(!VISITED[i][j] && MAP[i][j] == 1){
                        ANS++;
                        bfs(new int[] {i, j});
                    }
                }
            }

            bw.append(ANS + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void bfs(int[] cord){
        Queue<int[]> queue = new LinkedList<>();
        VISITED[cord[0]][cord[1]] = true;
        queue.add(new int[] {cord[0], cord[1]});

        while (!queue.isEmpty()){
            int[] now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if(canGo(nx, ny) && MAP[nx][ny] == 1 && !VISITED[nx][ny]){
                    VISITED[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }

    public static boolean canGo(int x, int y){
        return x>=0 && x<N && y>=0 && y<M;
    }
}


