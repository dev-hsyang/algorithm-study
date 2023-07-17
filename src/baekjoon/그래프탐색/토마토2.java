package baekjoon.그래프탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 7576
 * 골드 5
 */
public class 토마토2 {

    static int N, M, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] BOX;
    static Queue<Tomato> QUEUE = new LinkedList<Tomato>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        BOX = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                BOX[i][j] = Integer.parseInt(st.nextToken());
                if(BOX[i][j] == 1)
                    QUEUE.add(new Tomato(i, j));
            }
        }

        operate();

        if(!allDone())
            ANS = -1;
        else
            ANS = countDate() - 1;

        System.out.println(ANS);
    }

    public static void operate(){
        while(!QUEUE.isEmpty()){
            Tomato now = QUEUE.poll();
            for(int i=0; i<4; i++){
                int nx = now.x + DX[i];
                int ny = now.y + DY[i];
                if(isInbound(nx, ny) && BOX[nx][ny] == 0){
                    BOX[nx][ny] = BOX[now.x][now.y] + 1;
                    QUEUE.add(new Tomato(nx, ny));
                }
            }
        }
    }

    public static boolean isInbound(int x, int y){
        return x>=0 && x<N && y>=0 && y<M;
    }

    public static boolean allDone(){
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                if(BOX[i][j] == 0)
                    return false;
        return true;
    }

    public static int countDate(){
        int max = 0;
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                max = Math.max(max, BOX[i][j]);
        return max;
    }
}

class Tomato{
    public Tomato(int x, int y){
        this.x = x;
        this.y = y;
    }
    int x, y;
}
