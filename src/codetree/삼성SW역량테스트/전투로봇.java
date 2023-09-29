package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2018 하반기 오후 2번 문제
 * 골드 3
 */
public class 전투로봇 {

    static int N, MIN_MON, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] DEPTH;
    static int[][] MONSTER;
    static boolean CAN_PLAY;
    static boolean[][] VISITED;
    static Robot2018 ROBOT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        MONSTER = new int[N][N];
        CAN_PLAY = true;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp >= 0 && tmp < 9)
                    MONSTER[i][j] = tmp;
                else if(tmp == 9)
                    ROBOT = new Robot2018(i, j, 2);
            }
        }

        while(CAN_PLAY)
            simulate();

        System.out.println(ANS);
    }

    public static void simulate(){
        search(new int[] {ROBOT.x, ROBOT.y});
        destroy();
    }

    public static void destroy(){
        CAN_PLAY = false;
        for(int i=0; i<N; i++){
            if(!CAN_PLAY){
                for(int j=0; j<N; j++) {
                    if (DEPTH[i][j] == MIN_MON && ROBOT.level > MONSTER[i][j] && MONSTER[i][j] != 0) {
                        CAN_PLAY = true;
                        ROBOT.x = i;
                        ROBOT.y = j;
                        ROBOT.buff += 1;
                        ROBOT.updateLevel();
                        ANS += MIN_MON;
                        MONSTER[i][j] = 0;
                        break;
                    }
                }
            }
        }
    }

    public static void search(int[] cord){
        VISITED = new boolean[N][N];
        DEPTH = new int[N][N];
        MIN_MON = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        VISITED[cord[0]][cord[1]] = true;
        queue.add(cord);
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if (canGo(nx, ny) && !VISITED[nx][ny]) {
                    VISITED[nx][ny] = true;
                    DEPTH[nx][ny] = DEPTH[now[0]][now[1]] + 1;
                    if(MONSTER[nx][ny] > 0 && MONSTER[nx][ny] < ROBOT.level) // 해당칸이 몬스터이고 로봇보다 레벨이 낮아 없앨 수 있다면, 그 칸까지의 거리가 최소거리인지 확인
                        MIN_MON = Math.min(MIN_MON, DEPTH[nx][ny]);
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    public static boolean canGo(int x, int y){
        return isInbound(x, y) && MONSTER[x][y] <= ROBOT.level;
    }
    public static boolean isInbound(int x, int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
}

class Robot2018{
    public Robot2018(int x, int y, int level){
        this.x = x;
        this.y = y;
        this.level = level;
    }
    int x;
    int y;
    int level;
    int buff = 0;

    public void updateLevel(){
        if(buff == level){
            level += 1;
            buff = 0;
        }
    }
}
