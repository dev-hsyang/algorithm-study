package baekjoon.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 4179
 * 골드 4
 *
 * 시간초과가 발생하여 실패
 * 테스트케이스, 반례는 전부 맞으나 시간초과가 발생한다
 */
public class 불 {

    static int R, C, MIN;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static String[][] MAZE;
    static boolean EXIT;
    static boolean POSSIBLE = true;
    static boolean[][] VISITED;
    static ArrayList<int[]> FIRE = new ArrayList<int[]>();
    static ArrayList<John> JOHN = new ArrayList<John>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        MAZE = new String[R][C];
        VISITED = new boolean[R][C];
        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            String[] s = st.nextToken().split("");
            for(int j=0; j<C; j++){
                MAZE[i][j] = s[j];
                if(MAZE[i][j].equals("F"))
                    FIRE.add(new int[] {i, j});
                if(MAZE[i][j].equals("J"))
                    JOHN.add(new John(i, j, 0));
            }
        }

        int cnt = 0;
        while (true){
            cnt++;
            move();
            if(EXIT)
                break;
            fire();
            check();
            if(!POSSIBLE)
                break;
            if(cnt > 100000){
                POSSIBLE = false;
                break;
            }
        }

        if(EXIT)
            System.out.println(MIN);
        else if(!POSSIBLE)
            System.out.println("IMPOSSIBLE");
    }

    public static void check(){
        if(JOHN.size() == 0)
            POSSIBLE = false;
        else
            POSSIBLE = true;
    }

    public static void move(){
        Queue<John> queue = new LinkedList<>(JOHN);
        while (!queue.isEmpty()){
            John now = queue.poll();
            if(MAZE[now.x][now.y].equals("J")){
                if(canExit(now.x, now.y)) {
                    MIN = now.dist + 1;
                    EXIT = true;
                    break;
                }
                for(int i=0; i<4; i++){
                    int nx = now.x + DX[i];
                    int ny = now.y + DY[i];
                    if(canGo(nx, ny)){
                        MAZE[nx][ny] = "J";
                        JOHN.add(new John(nx, ny, now.dist + 1));
                    }
                }
            }else {
                JOHN.remove(now);
            }
        }
    }

    public static void fire(){
        Queue<int[]> queue = new LinkedList<>(FIRE);
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if(canBurn(nx, ny)) {
                    MAZE[nx][ny] = "F";
                    FIRE.add(new int[]{nx, ny});
                }
            }
        }
    }

    public static boolean canExit(int x, int y){
        return (x == R-1 || y == C-1 || x == 0 || y == 0);
    }

    public static boolean canGo(int x, int y){
        return isInbound(x, y) && MAZE[x][y].equals(".");
    }

    public static boolean canBurn(int x, int y){
        return isInbound(x, y) && (MAZE[x][y].equals(".") || MAZE[x][y].equals("J"));
    }

    public static boolean isInbound(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}

class John{
    public John(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
    int x;
    int y;
    int dist;
}
