package baekjoon.그래프탐색;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 17142
 * 삼성 SW 역량테스트 2019 상반기 오후 2번 문제
 * 골드 3
 */
public class 연구소3 {

    static int N, M, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] MAP;
    static int[][] DEPTH_MAP;
    static boolean DONE;
    static boolean[] SELECTED;
    static boolean[][] VISITED;
    static ArrayList<int[]> LABS = new ArrayList<int[]>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAP = new int[N][N];
        ANS = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
                if (MAP[i][j] == 2)
                    LABS.add(new int[]{i, j});
            }
        }

        simulate();

        System.out.println(ANS);
    }

    public static void simulate(){
        SELECTED = new boolean[LABS.size()];
        dfs(0, 0);
        if(!DONE)
            ANS = -1;
    }

    public static void dfs(int depth, int index){
        if(depth == M){
            bfs();
            return;
        }

        for(int i = index; i< LABS.size(); i++){
            SELECTED[i] = true;
            dfs(depth + 1, i + 1);
            SELECTED[i] = false;
        }
    }

    public static void bfs(){
        DEPTH_MAP = new int[N][N];
        VISITED = new boolean[N][N];
        int maxDepth = 0;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i< LABS.size(); i++)
            if(SELECTED[i]){
                int[] cord = LABS.get(i);
                queue.add(cord);
                VISITED[cord[0]][cord[1]] = true;
            }
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if(canGo(nx, ny) && !VISITED[nx][ny]){
                    VISITED[nx][ny] = true;
                    DEPTH_MAP[nx][ny] = DEPTH_MAP[now[0]][now[1]] + 1;
                    queue.add(new int[] {nx, ny});
                }
            }
        }

        if(checkVirus()){
            for(int i=0; i<N; i++)
                for(int j=0; j<N; j++)
                    if(DEPTH_MAP[i][j] > 0 && MAP[i][j] == 0)
                        maxDepth = Math.max(maxDepth, DEPTH_MAP[i][j]);
            ANS = Math.min(maxDepth, ANS);
            DONE = true;
        }
    }

    public static boolean checkVirus(){
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(MAP[i][j] == 0 && !VISITED[i][j])
                    return false;
        return true;
    }

    public static boolean canGo(int x, int y){
        return (x>=0 && x<N && y>=0 && y<N) && MAP[x][y] != 1;
    }
}
