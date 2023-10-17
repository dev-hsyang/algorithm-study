package softeer;

import java.util.*;
import java.io.*;


public class 장애물인식프로그램
{
    static int N, CNT;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] ROAD;
    static boolean[][] VISITED;
    static ArrayList<Integer> BLOCKS = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ROAD = new int[N][N];
        VISITED = new boolean[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String[] s = st.nextToken().split("");
            for(int j=0; j<N; j++)
                ROAD[i][j] = Integer.parseInt(s[j]);
        }

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(ROAD[i][j] == 1 && !VISITED[i][j])
                    bfs(new int[] {i, j});

        Collections.sort(BLOCKS);

        System.out.println(CNT);
        for(int i : BLOCKS)
            System.out.println(i);
    }

    public static void bfs(int[] cord){
        Queue<int[]> queue = new LinkedList<>();
        VISITED[cord[0]][cord[1]] = true;
        queue.add(cord);
        int cnt = 1;
        CNT++;
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if(canGo(nx, ny) && ROAD[nx][ny] == 1 && !VISITED[nx][ny]){
                    VISITED[nx][ny] = true;
                    cnt++;
                    queue.add(new int[] {nx ,ny});
                }
            }
        }
        BLOCKS.add(cnt);
    }

    public static boolean canGo(int x, int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
}