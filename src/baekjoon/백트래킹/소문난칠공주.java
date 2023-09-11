package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1941
 * 골드 3
 */
public class 소문난칠공주 {

    public static int ANS;
    public static int[] DX = {0, 1, 0, -1};
    public static int[] DY = {1, 0, -1 ,0};
    public static boolean[][] VISITED = new boolean[5][5];
    public static String[][] CLASS = new String[5][5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i=0; i<5; i++){
            st = new StringTokenizer(br.readLine());
            String[] s = st.nextToken().split("");
            for(int j=0; j<5; j++)
                CLASS[i][j] = s[j];
        }

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                VISITED = new boolean[5][5];
                //VISITED[i][j] = true;
                backtrack(1, 0, i, j);
            }
        }

        System.out.println(ANS);
    }

    public static void backtrack(int depth, int cnt, int x, int y){
        if(CLASS[x][y].equals("S"))
            cnt += 1;

        //VISITED[x][y] = true;

        if(depth == 7){
            if(cnt >= 4){
                printVisited();
                ANS++;
            }
            return;
        }

        for(int i=0; i<4; i++){
            int nx = x + DX[i];
            int ny = y + DY[i];
            if(canGo(nx, ny) && !VISITED[nx][ny]){
                VISITED[nx][ny] = true;
                backtrack(depth + 1, cnt, nx, ny);
            }
        }
    }

    public static boolean canGo(int x, int y){
        return x>=0 && x<5 && y>=0 && y<5;
    }

    public static void printVisited(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++)
                System.out.print(VISITED[i][j] + " ");
            System.out.println();
        }
        System.out.println("");
    }
}
