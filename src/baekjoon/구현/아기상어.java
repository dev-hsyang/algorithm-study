package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 16236
 * 삼성 SW 역량테스트 2018 하반기 오후 2번 문제
 * 골드 3
 */
public class 아기상어 {

    static int N, MIN_DIST, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] DEPTH;
    static int[][] FISH;
    static boolean CAN_MOVE;
    static boolean[][] VISITED;
    static BabyShark16236 BABY_SHARK;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        FISH = new int[N][N];
        CAN_MOVE = true;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp >= 0 && tmp < 9)
                    FISH[i][j] = tmp;
                else if(tmp == 9)
                    BABY_SHARK = new BabyShark16236(i, j, 2);
            }
        }

        while(CAN_MOVE)
            simulate();

        System.out.println(ANS);
    }

    public static void simulate(){
        search(new int[] {BABY_SHARK.x, BABY_SHARK.y});
        destroy();
    }

    public static void destroy(){
        CAN_MOVE = false;
        for(int i=0; i<N; i++){
            if(!CAN_MOVE){
                for(int j=0; j<N; j++) {
                    if (DEPTH[i][j] == MIN_DIST && BABY_SHARK.level > FISH[i][j] && FISH[i][j] != 0) { // 해당칸이 bfs 를 통해 찾은 먹을 수 있는 물고기가 있는 최소거리이고, 먹을 수 있는 물고기가 있는 칸일 때
                        CAN_MOVE = true;
                        BABY_SHARK.x = i;
                        BABY_SHARK.y = j;
                        BABY_SHARK.buff += 1;
                        BABY_SHARK.updateLevel();
                        ANS += MIN_DIST;
                        FISH[i][j] = 0;
                        break;
                    }
                }
            }
        }
    }

    public static void search(int[] cord){
        VISITED = new boolean[N][N];
        DEPTH = new int[N][N];
        MIN_DIST = Integer.MAX_VALUE;
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
                    if(FISH[nx][ny] > 0 && FISH[nx][ny] < BABY_SHARK.level) // 해당칸이 물고기이고 상어보다 레벨이 낮아 먹을수 있다면, 그 칸까지의 거리가 최소거리인지 확인
                        MIN_DIST = Math.min(MIN_DIST, DEPTH[nx][ny]);
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    public static boolean canGo(int x, int y){
        return isInbound(x, y) && FISH[x][y] <= BABY_SHARK.level;
    }
    public static boolean isInbound(int x, int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
}

class BabyShark16236 {
    public BabyShark16236(int x, int y, int level){
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