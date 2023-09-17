package baekjoon.그래프탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 10026
 * 골드 5
 */
public class 적록색약 {

    static int N, ANS1, ANS2;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static String[][] PIC;
    static String[][] PIC_DIS;
    static boolean[][] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        PIC = new String[N][N];
        PIC_DIS = new String[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String[] s = st.nextToken().split("");
            for(int j=0; j<N; j++){
                PIC[i][j] = s[j];
                if(s[j].equals("R") || s[j].equals("G"))
                    PIC_DIS[i][j] = "R";
                else
                    PIC_DIS[i][j] = s[j];
            }
        }

        VISITED = new boolean[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(!VISITED[i][j]){
                    ANS1++;
                    bfs_normal(new int[] {i, j});
                }

        VISITED = new boolean[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(!VISITED[i][j]){
                    ANS2++;
                    bfs_disabled(new int[] {i, j});
                }

        System.out.println(ANS1 + " " + ANS2);
    }

    public static void bfs_normal(int[] cord){
        Queue<int[]> queue = new LinkedList<>();
        VISITED[cord[0]][cord[1]] = true;
        queue.add(cord);
        while (!queue.isEmpty()){
            int[] now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if(canGo(nx, ny) && !VISITED[nx][ny] && PIC[now[0]][now[1]].equals(PIC[nx][ny])){
                    VISITED[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }

    public static void bfs_disabled(int[] cord){
        Queue<int[]> queue = new LinkedList<>();
        VISITED[cord[0]][cord[1]] = true;
        queue.add(cord);
        while (!queue.isEmpty()){
            int[] now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if(canGo(nx, ny) && !VISITED[nx][ny] && PIC_DIS[now[0]][now[1]].equals(PIC_DIS[nx][ny])){
                    VISITED[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }


    public static boolean canGo(int x, int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
}
