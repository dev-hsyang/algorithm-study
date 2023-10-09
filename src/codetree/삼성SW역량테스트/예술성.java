package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2022 상반기 오전 2번 문제
 * 골드 3
 */
public class 예술성 {

    static int N, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[] COMBI;
    static int[][] PAINTING;
    static int[][] GROUP;
    static boolean[][] VISITED;
    static ArrayList<Integer> G_CNT; // 리스트의 인덱스는 그룹번호, 인덱스의 값은 그룹에 속한 칸 수
    static ArrayList<Integer> G_NUM; // 리스트의 인덱스는 그룹번호, 인덱스의 값은 그룹을 이루는 숫자 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        PAINTING = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++)
                PAINTING[i][j] = Integer.parseInt(st.nextToken());
        }

        simulate();

        System.out.println(ANS);
    }

    public static void simulate(){
            findGroup();
            getScore();
        for(int i=0; i<3; i++){
            rotateCross();
            rotateSquare();
            findGroup();
            getScore();
        }
    }

    public static void findGroup(){
        VISITED = new boolean[N][N];
        GROUP = new int[N][N];
        G_CNT = new ArrayList<>();
        G_NUM = new ArrayList<>();
        G_CNT.add(0);
        G_NUM.add(0);
        int groupNum = 1;
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(!VISITED[i][j])
                    bfs(new int[] {i, j}, groupNum++);
    }

    public static void bfs(int[] cord, int groupNum){
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();
        VISITED[cord[0]][cord[1]] = true;
        GROUP[cord[0]][cord[1]] = groupNum;
        G_NUM.add(PAINTING[cord[0]][cord[1]]);
        G_CNT.add(1);
        queue.add(cord);
        while (!queue.isEmpty()){
            int[] now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if(canGo(nx, ny) && PAINTING[nx][ny] == PAINTING[cord[0]][cord[1]] && !VISITED[nx][ny]){
                    VISITED[nx][ny] = true;
                    cnt += 1;
                    G_CNT.set(groupNum, cnt);
                    GROUP[nx][ny] = groupNum;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }

    public static void getScore(){
        COMBI = new int[2];
        dfs(0, 1);
    }

    public static void dfs(int depth, int index){
        if(depth == 2){
            sumScore();
            return;
        }
        for(int i=index; i<G_CNT.size(); i++){
            COMBI[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }

    public static void sumScore(){
        int adj = getAdj();
        int g1 = COMBI[0];
        int g2 = COMBI[1];
        ANS += (G_CNT.get(g1) + G_CNT.get(g2)) * G_NUM.get(g1) * G_NUM.get(g2) * adj;
    }

    public static int getAdj(){
        int g1 = COMBI[0];
        int g2 = COMBI[1];
        int cnt = 0;
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(GROUP[i][j] == g1)
                    for(int k=0; k<4; k++){
                        int nx = i + DX[k];
                        int ny = j + DY[k];
                        if(canGo(nx, ny) && GROUP[nx][ny] == g2)
                            cnt++;
                    }
        return cnt;
    }

    public static void rotateCross(){
        int[][] candi = new int[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                candi[i][j] = PAINTING[i][j];

        for(int i=0; i<N/2; i++) // 왼쪽 -
            candi[N / 2][i] = PAINTING[i][N / 2];
        for(int i=N/2+1; i<N; i++) // 오른쪽 -
            candi[N / 2][i] = PAINTING[i][N / 2];
        for(int i=0; i<N/2; i++) // 위쪽 |
            candi[i][N / 2] = PAINTING[N / 2][N - 1 - i];
        for(int i=N/2+1; i<N; i++) // 아래쪽 |
            candi[i][N / 2] = PAINTING[N / 2][N - 1 - i];

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                PAINTING[i][j] = candi[i][j];
    }

    public static void rotateSquare(){
        int[][] candi = new int[N / 2][N / 2];
        int[][] rotated = new int[N / 2][N / 2];

        for(int i=0; i<N/2; i++)
            for(int j=0; j<N/2; j++)
                candi[i][j] = PAINTING[i][j];
        for(int i=0; i<N/2; i++)
            for(int j=0; j<N/2; j++){
                rotated[i][j] = candi[N/2 - 1 - j][i];
                PAINTING[i][j] = rotated[i][j];
            }

        for(int i=0; i<N/2; i++)
            for(int j=N/2+1; j<N; j++)
                candi[i][j - (N/2 + 1)] = PAINTING[i][j];
        for(int i=0; i<N/2; i++)
            for(int j=N/2+1; j<N; j++){
                rotated[i][j - (N/2 + 1)] = candi[N/2 - 1 - (j - (N/2 + 1))][i];
                PAINTING[i][j] = rotated[i][j - (N/2 + 1)];
            }

        for(int i=N/2+1; i<N; i++)
            for(int j=0; j<N/2; j++)
                candi[i - (N/2+1)][j] = PAINTING[i][j];
        for(int i=N/2+1; i<N; i++)
            for(int j=0; j<N/2; j++){
                rotated[i - (N/2+1)][j] = candi[N/2 - 1 - j][i - (N/2+1)];
                PAINTING[i][j] = rotated[i - (N/2+1)][j];
            }

        for(int i=N/2+1; i<N; i++)
            for(int j=N/2+1; j<N; j++)
                candi[i - (N/2+1)][j - (N/2+1)] = PAINTING[i][j];
        for(int i=N/2+1; i<N; i++)
            for(int j=N/2+1; j<N; j++){
                rotated[i - (N/2+1)][j - (N/2+1)] = candi[N/2 - 1 - (j - (N/2 + 1))][i - (N/2+1)];
                PAINTING[i][j] = rotated[i - (N/2+1)][j - (N/2+1)];
            }
    }

    public static boolean canGo(int x, int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
}
