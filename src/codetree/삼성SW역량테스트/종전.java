package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2019 하반기 오전 1번 문제
 * 골드 3
 */
public class 종전 {

    static int N, TOTAL, ANS;
    static int[] DX = {-1, -1, 1, 1};
    static int[] DY = {1, -1, -1, 1};
    static int[] POPULATION;
    static int[][] MAP;
    static boolean[][] BORDER;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        MAP = new int[N][N];
        ANS = Integer.MAX_VALUE;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                MAP[i][j] = Integer.parseInt(st.nextToken());
                TOTAL += MAP[i][j];
            }
        }

        simulate();

        System.out.println(ANS);
    }

    public static void simulate(){
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                for(int k=1; k<N; k++)
                    for(int l=1; l<N; l++)
                        if(canDraw(i, j, k, l))
                            divide(i, j, k, l);
    }

    public static void divide(int x, int y, int k, int l){
        POPULATION = new int[5];
        drawBorder(x, y, k, l);
        for(int i=0; i<x-l; i++)
            for(int j = 0; j<=y-l+k && !BORDER[i][j]; j++)
                POPULATION[0] += MAP[i][j]; // 2
        for(int i=x-l; i<N; i++)
            for(int j=0; j<y && !BORDER[i][j]; j++)
                POPULATION[1] += MAP[i][j]; // 4
        for(int i=0; i<=x-k; i++)
            for(int j=N-1; j>y+k-l&& !BORDER[i][j]; j--)
                POPULATION[2] += MAP[i][j]; // 3
        for(int i=x-k+1; i<N; i++)
            for(int j = N-1; j>=y && !BORDER[i][j]; j--)
                POPULATION[3] += MAP[i][j]; // 5

        POPULATION[4] = TOTAL - POPULATION[0] - POPULATION[1] - POPULATION[2] - POPULATION[3];
        Arrays.sort(POPULATION);
        ANS = Math.min(POPULATION[4] - POPULATION[0], ANS);
    }

    public static void drawBorder(int x, int y, int k, int l){
        BORDER = new boolean[N][N];
        int[] cm = {k, l, k, l};
        for(int i=0; i<4; i++){
            for(int j=0; j<cm[i]; j++){
                x += DX[i];
                y += DY[i];
                BORDER[x][y] = true;
            }
        }
    }

    public static boolean canDraw(int x, int y, int k, int l){
        return isInbound(x - k, y + k) && isInbound(x - l , y - l) && isInbound(x - k - l, y + k - l);
    }

    public static boolean isInbound(int x, int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
}
