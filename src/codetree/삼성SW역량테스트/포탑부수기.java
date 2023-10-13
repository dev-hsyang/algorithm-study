package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 삼성 SW 역량테스트 2023 상반기 오전 1번 문제
 * 골드 1
 */
public class 포탑부수기 {

    static int N, M, K, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[] DX_P = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[] DY_P = {1, 1, 1, 0, -1, -1, -1, 0};
    static int[] ATTACKER;
    static int[] TARGET;
    static int[][] TOWER;
    static int[][] TURN;
    static int[][] DISTMAP;
    static boolean[][] INVOLVED;
    static boolean[][] VISITED;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        TOWER = new int[N + 1][M + 1];
        TURN = new int[N + 1][M + 1];
        ATTACKER = new int[2];
        TARGET = new int[2];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++)
                TOWER[i][j] = Integer.parseInt(st.nextToken());
        }

        simulate();
        System.out.println(ANS);
    }

    public static void simulate(){
        for(int i=1; i<=K; i++){
            if(stop())
                break;
            chooseAttacker(i);
            chooseTarget();
            attack();
            if(stop())
                break;
            repair();
        }
        findAns();
    }

    public static void chooseAttacker(int turn){
        INVOLVED = new boolean[N + 1][M + 1];
        int minStat = Integer.MAX_VALUE;
        int maxTurn = 0;
        for(int i=1; i<=N; i++)
            for(int j=1; j<=M; j++)
                if(TOWER[i][j] != 0)
                    minStat = Math.min(minStat, TOWER[i][j]); // 가장 낮은 공격력 값을 찾는다.
        for(int i=1; i<=N; i++)
            for(int j=1; j<=M; j++)
                if(TOWER[i][j] == minStat)
                    maxTurn = Math.max(maxTurn, TURN[i][j]); // 가장 최근에 공격한 라운드를 찾는다.
        findAttackerIndex(minStat, maxTurn, turn);
    }

    public static void findAttackerIndex(int min, int max, int turn){
        ArrayList<int[]> candi = new ArrayList<int[]>();
        for(int i=1; i<=N; i++)
            for(int j=1; j<=M; j++)
                if(TOWER[i][j] == min && TURN[i][j] == max)
                    candi.add(new int[] {i, j, i + j});

        Collections.sort(candi, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2] == o2[2]) // 행과 열의 합이 같은 포탑이면 열 값이 큰 순서가 앞으로 오도록 정렬
                    return o2[1] - o1[1];
                return o2[2] - o1[2];
            }
        });
        ATTACKER[0] = candi.get(0)[0];
        ATTACKER[1] = candi.get(0)[1];
        TOWER[ATTACKER[0]][ATTACKER[1]] += N + M;
        INVOLVED[ATTACKER[0]][ATTACKER[1]] = true;
        TURN[ATTACKER[0]][ATTACKER[1]] = turn;
    }

    public static void chooseTarget(){
        int maxStat = 0;
        int minTurn = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++)
            for(int j=1; j<=M; j++)
                if(TOWER[i][j] != 0 && !(i == ATTACKER[0] && j == ATTACKER[1]))
                    maxStat = Math.max(maxStat, TOWER[i][j]);
        for(int i=1; i<=N; i++)
            for(int j=1; j<=M; j++)
                if(TOWER[i][j] == maxStat && !(i == ATTACKER[0] && j == ATTACKER[1]))
                    minTurn = Math.min(minTurn, TURN[i][j]);
        findTargetIndex(maxStat, minTurn);
    }

    public static void findTargetIndex(int max, int turn){
        ArrayList<int[]> candi = new ArrayList<int[]>();
        for(int i=1; i<=N; i++)
            for(int j=1; j<=M; j++)
                if(TOWER[i][j] == max && TURN[i][j] == turn)
                    candi.add(new int[] {i, j, i + j});

        Collections.sort(candi, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2] == o2[2])
                    return o1[1] - o2[1];
                return o1[2] - o2[2];
            }
        });

        TARGET[0] = candi.get(0)[0];
        TARGET[1] = candi.get(0)[1];
        INVOLVED[TARGET[0]][TARGET[1]] = true;
    }

    public static void attack(){
        if(laser())
            attackByLaser();
        else
            attackByCanon();
    }

    public static boolean laser(){
        DISTMAP = new int[N + 1][M + 1];
        VISITED = new boolean[N + 1][M + 1];
        Queue<Tower2023> queue = new LinkedList<>();
        queue.add(new Tower2023(TARGET[0], TARGET[1]));
        VISITED[TARGET[0]][TARGET[1]] = true;
        boolean found = false;
        while (!queue.isEmpty()){
            Tower2023 now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = getNextX(now.x + DX[i]);
                int ny = getNextY(now.y + DY[i]);
                if(TOWER[nx][ny] != 0 && !VISITED[nx][ny]){
                    VISITED[nx][ny] = true;
                    DISTMAP[nx][ny] = DISTMAP[now.x][now.y] + 1;
                    if(nx == ATTACKER[0] && ny == ATTACKER[1])
                        found = true;
                    queue.add(new Tower2023(nx, ny));
                }
            }
        }
        return found;
    }

    public static void attackByLaser(){
        int dist = DISTMAP[ATTACKER[0]][ATTACKER[1]];
        int x = ATTACKER[0];
        int y = ATTACKER[1];
        while(true){
            for(int i=0; i<4; i++){
                int nx = getNextX(x + DX[i]);
                int ny = getNextY(y + DY[i]);
                if(TOWER[nx][ny] != 0 && DISTMAP[nx][ny] == dist - 1){
                    x = nx;
                    y = ny;
                    dist -= 1;
                    INVOLVED[x][y] = true;
                    break;
                }
            }
            if(x == TARGET[0] && y == TARGET[1]){
                TOWER[x][y] -= TOWER[ATTACKER[0]][ATTACKER[1]];
                if(TOWER[x][y] < 0)
                    TOWER[x][y] = 0;
                break;
            }else{
                TOWER[x][y] -= TOWER[ATTACKER[0]][ATTACKER[1]] / 2;
                if(TOWER[x][y] < 0)
                    TOWER[x][y] = 0;
            }
        }
    }

    public static void attackByCanon(){
        TOWER[TARGET[0]][TARGET[1]] -= TOWER[ATTACKER[0]][ATTACKER[1]];
        if(TOWER[TARGET[0]][TARGET[1]] < 0)
            TOWER[TARGET[0]][TARGET[1]] = 0;
        for(int i=0; i<8; i++){
            int nx = getNextX(TARGET[0] + DX_P[i]);
            int ny = getNextY(TARGET[1] + DY_P[i]);
            if(TOWER[nx][ny] != 0 && !(nx == ATTACKER[0] && ny == ATTACKER[1])){
                INVOLVED[nx][ny] = true;
                TOWER[nx][ny] -= TOWER[ATTACKER[0]][ATTACKER[1]] / 2;
                if(TOWER[nx][ny] < 0)
                    TOWER[nx][ny] = 0;
            }
        }
    }

    public static void repair(){
        for(int i=1; i<=N; i++)
            for(int j=1; j<=M; j++)
                if(TOWER[i][j] != 0 && !INVOLVED[i][j])
                    TOWER[i][j] += 1;
    }

    public static void findAns(){
        for(int i=1; i<=N; i++)
            for(int j=1; j<=M; j++)
                ANS = Math.max(ANS, TOWER[i][j]);
    }

    public static boolean stop(){
        int cnt = 0;
        for(int i=1; i<=N; i++)
            for(int j=1; j<=M; j++)
                if(TOWER[i][j] > 0)
                    cnt++;
        return cnt == 1 ? true : false;
    }

    public static int getNextX(int x){
        if(x < 1)
            x = N;
        else if(x > N)
            x = 1;
        return x;
    }

    public static int getNextY(int y){
        if(y < 1)
            y = M;
        else if(y > M)
            y = 1;
        return y;
    }
}

class Tower2023{
    public Tower2023(int x, int y){
        this.x = x;
        this.y = y;
    }
    int x;
    int y;
}
