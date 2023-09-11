package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1941
 * 골드 3
 */

/**
 * 일반적인 dfs , bfs 활용한 backtracking 을 시도했으나 어려움을 겪었던 문제이다.
 * 이 문제의 해법은 무작정 dfs, bfs 를 활용하여 delta 탐색을 하는 것이 아닌
 * (1) 25명의 여학생중 7명을 뽑는 조합을 구하고 (25 C 7 을 backtracking 으로 구한다.)
 * (2) 찾아낸 7명 조합이 이다솜파가 4명 이상인지, 근접한지 (여기서 bfs 나 dfs 를 활용하여 delta 탐색을 진행한다.) 확인하면 된다.
 */
public class 소문난칠공주 {

    public static int ANS;
    public static int[] DX = {0, 1, 0, -1};
    public static int[] DY = {1, 0, -1, 0};
    public static boolean[] VISITED = new boolean[25];
    public static String[][] CLASS = new String[5][5];
    public static Seven[] ARR = new Seven[25];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int idx = 0;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            String[] s = st.nextToken().split("");
            for (int j = 0; j < 5; j++){
                CLASS[i][j] = s[j];
                ARR[idx] = new Seven(i, j, CLASS[i][j]);
                idx++;
            }
        }

        findCombination(0, 0, 0);

        System.out.println(ANS);
    }

    /**
     * 25명의 여학생 중 7명을 선택하는 조합을 dfs backtracking 으로 구한다.
     * 7명 조합이고, 이다솜파가 적어도 4명 이상이며, 근접하면 ANS++
     * @param depth
     * @param idx
     * @param cnt
     */
    public static void findCombination(int depth, int idx, int cnt){
        if(depth == 7){
            if(cnt >= 4 && isAdjacent())
                ANS++;
            return;
        }
        for(int i=idx; i<25; i++){
            if(!VISITED[i]){
                VISITED[i] = true;
                if(ARR[i].side.equals("S"))
                    findCombination(depth + 1, i + 1, cnt + 1);
                else
                    findCombination(depth + 1, i + 1, cnt);
                VISITED[i] = false;
            }
        }
    }

    public static boolean isAdjacent(){
        boolean[][] candiClass = new boolean[25][25];
        int[] startPos = new int[2];

        for(int i=0; i<25; i++)
            if(VISITED[i]){
                candiClass[ARR[i].x][ARR[i].y] = true;
                startPos[0] = ARR[i].x;
                startPos[1] = ARR[i].y;
            }

        return bfs(startPos, candiClass) == 7;
    }

    public static int bfs(int[] cord, boolean[][] candiClass){
        int count = 1;
        boolean[][] visited = new boolean[5][5];
        Queue<int[]> queue = new LinkedList<>();
        visited[cord[0]][cord[1]] = true;
        queue.add(cord);
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if(canGo(nx, ny) && candiClass[nx][ny] && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    count++;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
        return count;
    }

    public static boolean canGo(int x, int y){
        return x>=0 && x<5 && y>=0 && y<5;
    }
}

class Seven{

    public Seven(int x, int y, String side){
        this.x = x;
        this.y = y;
        this.side = side;
    }
    String side;
    int x, y;
}