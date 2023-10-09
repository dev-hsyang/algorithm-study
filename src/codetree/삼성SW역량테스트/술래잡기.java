package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2022 상반기 오전 1번 문제
 * 골드 1
 */
public class 술래잡기 {

    static int N, M, H, K, ANS;
    static int[] DXH = {1, 0, -1, 0};
    static int[] DYH = {0, 1, 0, -1};
    static int[][] DX = {{0, 0}, {0, 0}, {1, -1}};
    static int[][] DY = {{0, 0}, {1, -1}, {0, 0}};
    static int[][] INDEX_MAP;
    static int[][] HUNTER_DIR;
    static int[][] RUNNER_DIR;
    static int[][] DIR1; // 나선형으로 나갈 때 dir 맵
    static int[][] DIR2; // 나선형으로 들어올 때 dir 맵
    static boolean[][] TREE;
    static ArrayList<int[]>[][] RUNNER;
    static ArrayList<int[]> SNAIL_MAP;
    static Hunter HUNTER;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        RUNNER = new ArrayList[N + 1][N + 1];
        RUNNER_DIR = new int[N + 1][N + 1];
        TREE = new boolean[N + 1][N + 1];
        HUNTER = new Hunter(N / 2 + 1, N / 2 + 1, 1, 0);
        for(int i=1; i<=N; i++)
            for(int j=1; j<=N; j++)
                RUNNER[i][j] = new ArrayList<int[]>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            RUNNER[x][y].add(new int[] {d, 0});
        }
        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            TREE[x][y] = true;
        }

        simulate();

        System.out.println(ANS);
    }

    public static void simulate(){
        initSnailMap();
        initDir1();
        HUNTER_DIR = DIR1;
        for(int i=1; i<=K; i++){
            runnerRuns();
            hunterMoves(i);
        }
    }

    public static void runnerRuns(){
        ArrayList<int[]>[][] CANDI = new ArrayList[N + 1][N + 1];
        for(int i=1; i<=N; i++)
            for(int j=1; j<=N; j++)
                CANDI[i][j] = new ArrayList<int[]>();

        for(int i=1; i<=N; i++)
            for(int j=1; j<=N; j++)
                if(!RUNNER[i][j].isEmpty())
                    for(int r=0; r<RUNNER[i][j].size(); r++){
                        int type = RUNNER[i][j].get(r)[0];
                        int dir = RUNNER[i][j].get(r)[1];
                        if(Math.abs(HUNTER.x - i) + Math.abs(HUNTER.y - j) <= 3){
                            for(int k=0; k<2; k++){
                                dir += k;
                                if(dir == 2)
                                    dir = 0;
                                int nx = i + DX[type][dir];
                                int ny = j + DY[type][dir];
                                if(canGo(nx, ny)){
                                    if((nx == HUNTER.x && ny == HUNTER.y)){
                                        CANDI[i][j].add(new int[] {type, dir});
                                        break;
                                    }else{
                                        CANDI[nx][ny].add(new int[] {type, dir});
                                        break;
                                    }
                                }
                            }
                        }else
                            CANDI[i][j].add(new int[] {type, dir});
                }

        for(int i=1; i<=N; i++)
            for(int j=1; j<=N; j++)
                RUNNER[i][j] = CANDI[i][j];
    }

    public static void hunterMoves(int turn){
        if(HUNTER.stat == 0){ // 나선형으로 나가는 상황일 때
            HUNTER.index += 1;
            HUNTER.x = SNAIL_MAP.get(HUNTER.index)[0];
            HUNTER.y = SNAIL_MAP.get(HUNTER.index)[1];

            if(HUNTER.index == N * N){
                HUNTER_DIR = DIR2;
                HUNTER.stat = 1;
            }

            hunt(turn);
        }else if(HUNTER.stat == 1){ // 나선형으로 들어오는 상황일 때
            HUNTER.index -= 1;
            HUNTER.x = SNAIL_MAP.get(HUNTER.index)[0];
            HUNTER.y = SNAIL_MAP.get(HUNTER.index)[1];

            if(HUNTER.index == 1){
                HUNTER_DIR = DIR1;
                HUNTER.stat = 0;
            }

            hunt(turn);
        }
    }

    public static void initDir1(){
        for(int i=1; i<=N; i++)
            for(int j=1; j<=N; j++){
                if(DIR1[i][j] == 0)
                    DIR1[i][j] = 2;
                else if(DIR1[i][j] == 1)
                    DIR1[i][j] = 3;
                else if(DIR1[i][j] == 2)
                    DIR1[i][j] = 0;
                else if(DIR1[i][j] == 3)
                    DIR1[i][j] = 1;
            }
    }

    public static void hunt(int turn){
        for(int i=0; i<3; i++){
            int nx = HUNTER.x + i * DXH[HUNTER_DIR[HUNTER.x][HUNTER.y]];
            int ny = HUNTER.y + i * DYH[HUNTER_DIR[HUNTER.x][HUNTER.y]];
            if(canGo(nx, ny) && !TREE[nx][ny] && !RUNNER[nx][ny].isEmpty()){
                ANS += turn * RUNNER[nx][ny].size();
                RUNNER[nx][ny].clear();
            }
        }
    }

    public static boolean canGo(int x, int y){
        return x>=1 && x<=N && y>=1 && y<=N;
    }

    public static void initSnailMap(){
        INDEX_MAP = new int[N + 1][N + 1];
        HUNTER_DIR = new int[N + 1][N + 1];
        DIR1 = new int[N + 1][N + 1];
        DIR2 = new int[N + 1][N + 1];
        SNAIL_MAP = new ArrayList<>();
        for(int i=0; i<=N*N; i++)
            SNAIL_MAP.add(new int[] {0, 0});
        boolean[][] visited = new boolean[N + 1][N + 1];

        SNAIL_MAP.set(N * N, new int[] {1, 1});
        INDEX_MAP[1][1] = N * N;
        HUNTER_DIR[1][1] = 0;
        visited[1][1] = true;

        int x = 1;
        int y = 1;
        int idx = N * N - 1;
        int d = 0;
        while (true){
            int nx = x + DXH[d];
            int ny = y + DYH[d];
            if(canGo(nx, ny) && !visited[nx][ny]){
                SNAIL_MAP.set(idx, new int[] {nx, ny});
                INDEX_MAP[nx][ny] = idx--;
                DIR1[nx][ny] = d;
                DIR2[x][y] = d;
                visited[nx][ny] = true;
            }else{
                d += 1;
                if(d == 4)
                    d = 0;
                nx = x + DXH[d];
                ny = y + DYH[d];
                SNAIL_MAP.set(idx, new int[] {nx, ny});
                INDEX_MAP[nx][ny] = idx--;
                DIR1[nx][ny] = d;
                DIR2[x][y] = d;
                visited[nx][ny] = true;
            }
            x = nx;
            y = ny;
            if((x == N / 2 + 1 && y == N / 2 + 1))
                break;
        }
    }
}

class Hunter{
    public Hunter(int x, int y, int index, int stat){
        this.x = x;
        this.y = y;
        this.index = index;
        this.stat = stat;
    }
    int x;
    int y;
    int index;
    int stat; // 0이면 나가는 방향, 1이면 들어오는 방향
}
