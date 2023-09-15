package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 18809
 * 골드 1
 */

/**
 * (1) 초록색 배양액을 뿌릴 조합
 * (2) 빨간색 배양액을 뿌릴 조합 을 구하고
 * (3) 두 조합을 합친 상태에서 배양액을 퍼뜨려 꽃이 피는 경우 수를 구하면 된다.
 *
 * ArrayList 자료구조를 전역변수로 적절히 활용하여
 * dfs 를 통해 조합을 찾고
 * 조합을 다 찾으면 시뮬레이션을 돌리는 문제다.
 */
public class Gaaaaaaaaaarden {

    static int N, M, G, R, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] GARDEN;
    static int[][] TIMEMAP;
    static String[][] CANDI_GARDEN; // 각 조합별로 시뮬레이션 한 맵
    static boolean[][] ROOT; // 처음 씨가 뿌려진 자리
    static boolean[] VISITED;
    static ArrayList<int[]> CAN_SPREAD = new ArrayList<int[]>();
    static Gcord[] GREENS; // 씨를 뿌리는 조합
    static Gcord[] REDS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        GARDEN = new int[N][M];
        GREENS = new Gcord[G];
        REDS = new Gcord[R];
        ROOT = new boolean[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                GARDEN[i][j] = Integer.parseInt(st.nextToken());
                if(GARDEN[i][j] == 2)
                    CAN_SPREAD.add(new int[] {i, j});
            }
        }
        VISITED = new boolean[CAN_SPREAD.size()];

        spreadGreen(0, 0);

        System.out.println(ANS);
    }

    /**
     * dfs 안에서 for 문의 범위를 잘 생각해야 한다.
     * index 를 파라메터로 받고 i 의 범위를 고려하는 것이 중요.
     * 시간초과를 해결하는데 결정적인 부분이었다.
     * @param depth
     * @param index
     */
    public static void spreadGreen(int depth, int index){
        if(depth == G){
            spreadRed(0, 0);
            return;
        }

        for(int i=index; i<CAN_SPREAD.size(); i++){
            if(!VISITED[i]){
                GREENS[depth] = new Gcord(CAN_SPREAD.get(i)[0], CAN_SPREAD.get(i)[1], 0, "G");
                VISITED[i] = true;
                spreadGreen(depth + 1, i);
                VISITED[i] = false;
            }
        }
    }

    public static void spreadRed(int depth, int index){
        if(depth == R){
            simulate();
            return;
        }

        for(int i=index; i<CAN_SPREAD.size(); i++){
            if(!VISITED[i]){
                REDS[depth] = new Gcord(CAN_SPREAD.get(i)[0], CAN_SPREAD.get(i)[1], 0,"R");
                VISITED[i] = true;
                spreadRed(depth + 1, i);
                VISITED[i] = false;
            }
        }
    }

    public static void simulate(){
        int flowers = 0;
        Queue<Gcord> queue = new LinkedList<>();
        CANDI_GARDEN = new String[N][M];
        ROOT = new boolean[N][M];
        TIMEMAP = new int[N][M];

        for(Gcord c : GREENS){
            CANDI_GARDEN[c.x][c.y] = c.type;
            ROOT[c.x][c.y] = true;
            queue.add(c);
        }
        for(Gcord c : REDS){
            CANDI_GARDEN[c.x][c.y] = c.type;
            ROOT[c.x][c.y] = true;
            queue.add(c);
        }

        while(!queue.isEmpty()){
            Gcord now = queue.poll();
            if(CANDI_GARDEN[now.x][now.y].equals("F"))
                continue;
            for(int i=0; i<4; i++){
                int nx = now.x + DX[i];
                int ny = now.y + DY[i];
                if(canGo(nx, ny) && !ROOT[nx][ny]){ // 격자판 내부이고, 호수가 아니며, 꽃이 아닌 다음 칸에 대해서
                    if(CANDI_GARDEN[nx][ny] == null){ // 아직 배양액이 다다르지 않은 곳
                        CANDI_GARDEN[nx][ny] = now.type;
                        TIMEMAP[nx][ny] = now.cnt + 1;
                        queue.add(new Gcord(nx, ny, now.cnt + 1, now.type));
                    }else{
                        if(!CANDI_GARDEN[nx][ny].equals(now.type) && TIMEMAP[nx][ny] == now.cnt + 1 && !CANDI_GARDEN[nx][ny].equals("F")){ // 배양액이 이미 닿은 곳이고 같은 시간에 만나는 것일 때
                            CANDI_GARDEN[nx][ny] = "F";
                            flowers++;
                        }
                    }
                }
            }
        }
        ANS = Math.max(ANS, flowers);
    }

    public static boolean canGo(int x, int y){
        return (x>=0 && x<N && y>=0 && y<M) && GARDEN[x][y] > 0;
    }
}

class Gcord{
    public Gcord(int x, int y, int cnt, String type){
        this.cnt = cnt;
        this.x = x;
        this.y = y;
        this.type = type;
    }
    int x, y, cnt;
    String type;
}
