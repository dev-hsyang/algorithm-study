package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2022 상반기 오후 1번 문제
 * 골드 1
 */
public class 꼬리잡기놀이 {

    static int N, M, K, ROUND, DIR, ANS;
    static int[] THROW_INDEX = new int[2];
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] MAP;
    static boolean[][] VISITED;
    static boolean[][] IS_PLAYER;
    static ArrayList<Player2022>[] TEAMS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        MAP = new int[N + 1][N + 1];
        ROUND = 1;
        TEAMS = new ArrayList[M];
        IS_PLAYER = new boolean[N + 1][N + 1];
        for (int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++)
                MAP[i][j] = Integer.parseInt(st.nextToken());
        }

        simulate();

        System.out.println(ANS);
    }

    public static void simulate(){
        initGame();
        for(int i=0; i<K; i++){
            move();
            throwBall();
            ROUND++;
        }
    }

    public static void move(){
        int[][] candi = new int[N + 1][N + 1];
        for(int i=1; i<N+1; i++)
            for(int j=1; j<N+1; j++)
                candi[i][j] = MAP[i][j];

        for(int i=0; i<TEAMS.length; i++){ // i 번째 팀에 대해 이동
            int x = TEAMS[i].get(0).x;
            int y = TEAMS[i].get(0).y;
            int tailnum = 0;
            boolean isCircle = false;
            for(int k=0; k<4; k++){
                int nx = x + DX[k];
                int ny = y + DY[k];
                if(canGo(nx, ny) && (MAP[nx][ny] != 2 && MAP[nx][ny] != 0)){ // 다음으로 이동할 칸인데
                    if(IS_PLAYER[nx][ny]){ // 다음칸이 이미 꼬리일 경우
                        tailnum = MAP[nx][ny];
                        isCircle = true;
                        TEAMS[i].get(0).x = nx;
                        TEAMS[i].get(0).y = ny;
                        candi[nx][ny] = MAP[x][y];
                    }else {
                        TEAMS[i].get(0).x = nx;
                        TEAMS[i].get(0).y =ny;
                        candi[nx][ny] = MAP[x][y];
                        IS_PLAYER[nx][ny] = true;
                    }
                    break;
                }
            }

            if(!isCircle){
                for(int j=1; j<TEAMS[i].size(); j++){ // x, y 는 이동할 칸, tx, ty 는 이동전 칸
                    int tx = TEAMS[i].get(j).x;
                    int ty = TEAMS[i].get(j).y;
                    candi[x][y] = MAP[tx][ty];
                    candi[tx][ty] = 4;
                    IS_PLAYER[x][y] = true;
                    IS_PLAYER[tx][ty] = false;
                    TEAMS[i].get(j).x = x;
                    TEAMS[i].get(j).y = y;
                    x = tx;
                    y = ty;
                }
            }else{
                for(int j=1; j<TEAMS[i].size(); j++){
                    int tx = TEAMS[i].get(j).x;
                    int ty = TEAMS[i].get(j).y;
                    candi[x][y] = MAP[tx][ty];
                    TEAMS[i].get(j).x = x;
                    TEAMS[i].get(j).y = y;
                    x = tx;
                    y = ty;
                }
            }
        }

        for(int i=1; i<N+1; i++)
            for(int j=1; j<N+1; j++)
                MAP[i][j] = candi[i][j];
//
//        for(int i=1; i<N+1; i++){
//            for(int j=1; j<N+1; j++)
//                System.out.print(MAP[i][j] + " ");
//            System.out.println(" ");
//        }
//
//        System.out.println(" ");
//
//        for(int i=1; i<N+1; i++){
//            for(int j=1; j<N+1; j++)
//                System.out.print(IS_PLAYER[i][j] + " ");
//            System.out.println(" ");
//        }
//
//        System.out.println(" ");
    }

    public static void throwBall(){
        initThrowInfo();
        int x = THROW_INDEX[0];
        int y = THROW_INDEX[1];
        for(int i=0; i<N; i++){
            int nx = x + i * DX[DIR];
            int ny = y + i * DY[DIR];
            if(IS_PLAYER[nx][ny]){
                ANS += MAP[nx][ny] * MAP[nx][ny];
                turn(nx, ny);
                break;
            }
        }
    }

    public static void turn(int x, int y){
        int turnTeam = 0;
        for(int i=0; i<TEAMS.length; i++)
                for(int j=0; j<TEAMS[i].size(); j++)
                    if(TEAMS[i].get(j).x == x && TEAMS[i].get(j).y == y)
                        turnTeam = i;

        ArrayList<Player2022> arr = new ArrayList<Player2022>();
        for(int i=TEAMS[turnTeam].size() - 1; i>=0; i--)
            arr.add(TEAMS[turnTeam].get(i));
        TEAMS[turnTeam] = arr;

        int idx = 1;
        for(int i=0; i<TEAMS[turnTeam].size(); i++)
            MAP[TEAMS[turnTeam].get(i).x][TEAMS[turnTeam].get(i).y] = idx++;
    }

    public static void initThrowInfo() {
        if (ROUND > 4 * N)
            ROUND = 1;
        if (ROUND >= 1 && ROUND <= N) {
            DIR = 0;
            THROW_INDEX[0] = ROUND;
            THROW_INDEX[1] = 1;
        } else if (ROUND >= N + 1 && ROUND <= 2 * N) {
            DIR = 3;
            THROW_INDEX[0] = N;
            THROW_INDEX[1] = ROUND - N;
        } else if (ROUND >= 2 * N + 1 && ROUND <= 3 * N) {
            DIR = 2;
            THROW_INDEX[0] = N - (ROUND - (N * 2)) + 1;
            THROW_INDEX[1] = N;
        } else if (ROUND >= 3 * N + 1 && ROUND <= 4 * N) {
            DIR = 1;
            THROW_INDEX[0] = 1;
            THROW_INDEX[1] = N - (ROUND - (N * 3)) + 1;
        }
    }

    public static void initGame(){
        VISITED = new boolean[N + 1][N + 1];
        int team = 0;
        for(int i=0; i<M; i++)
            TEAMS[i] = new ArrayList<Player2022>();
        for(int i=1; i<N+1; i++)
            for(int j=1; j<N+1; j++)
                if(!VISITED[i][j] && MAP[i][j] == 1)
                    dfs(i, j, team++, 1);
    }

    public static void dfs(int x, int y, int team, int cnt){
        VISITED[x][y] = true;
        TEAMS[team].add(new Player2022(x, y));
        MAP[x][y] = cnt;
        IS_PLAYER[x][y] = true;
        for(int i=0; i<4; i++){
            int nx = x + DX[i];
            int ny = y + DY[i];
            if(canGo(nx ,ny) && MAP[nx][ny] - MAP[x][y] <= 1 && MAP[nx][ny] != 4 && MAP[nx][ny] != 0 && !VISITED[nx][ny])
                dfs(nx ,ny, team, cnt + 1);
        }
    }

    public static boolean canGo(int x, int y){
        return x>=1 && x<=N && y>=1 && y<=N;
    }
}

class Player2022{
    public Player2022(int x, int y){
        this.x = x;
        this.y = y;
    }
    int x;
    int y;
}
