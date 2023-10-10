package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2022 상반기 오후 2번 문제
 * 골드 4
 */
public class 나무박멸_2 {

    static int N, M, K, C, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[] DXP = {-1, 1, 1, -1};
    static int[] DYP = {1, 1, -1, -1};
    static int[][] MAP; // 빈칸: 0, 벽: -1
    static int[][] PESTI; // 제초제 맵

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        MAP = new int[N][N];
        PESTI = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++)
                MAP[i][j] = Integer.parseInt(st.nextToken());
        }

        simulate();

        System.out.println(ANS);
    }

    public static void simulate(){
        for(int i=0; i<M; i++){
            treeGrows();
            treeSpreads();
            yearPast();
            pesticide();
        }
    }

    public static void treeGrows(){
        int[][] candi = new int[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                candi[i][j] = MAP[i][j];

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(MAP[i][j] > 0){
                    int grow = 0;
                    for(int k=0; k<4; k++){
                        int nx = i + DX[k];
                        int ny = j + DY[k];
                        if(canGo(nx, ny) && MAP[nx][ny] > 0)
                            grow++;
                    }
                    candi[i][j] = MAP[i][j] + grow;
                }

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                MAP[i][j] = candi[i][j];
    }

    public static void treeSpreads(){
        // 인접 4칸중 벽이 아니고, 다른나무가 없고, 제초제가 없는 칸에 번식진행
        int[][] candi = new int[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                candi[i][j] = MAP[i][j];

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(MAP[i][j] > 0){
                    int cnt = 0;
                    for(int k=0; k<4; k++){
                        int nx = i + DX[k];
                        int ny = j + DY[k];
                        if(canGo(nx, ny) && MAP[nx][ny] == 0 && PESTI[nx][ny] == 0)
                            cnt++;
                    }
                    if(cnt > 0){
                        for(int k=0; k<4; k++){
                            int nx = i + DX[k];
                            int ny = j + DY[k];
                            if(canGo(nx, ny) && MAP[nx][ny] == 0 && PESTI[nx][ny] == 0)
                                candi[nx][ny] += MAP[i][j] / cnt;
                        }
                    }
                }

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                MAP[i][j] = candi[i][j];
    }

    public static void pesticide(){
        int[] maxIndex = new int[2];
        int max = 0;

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(MAP[i][j] > 0){
                    int cnt = MAP[i][j];
                    for(int k=0; k<4; k++){
                        for(int t=1; t<=K; t++){
                            int nx = i + t * DXP[k];
                            int ny = j + t * DYP[k];
                            if(!canGo(nx, ny))
                                break;
                            if(MAP[nx][ny] == 0 || MAP[nx][ny] == -1)
                                break;
                            cnt += MAP[nx][ny];
                        }
                    }
                    if(cnt > max){
                        max = cnt;
                        maxIndex[0] = i;
                        maxIndex[1] = j;
                    }
                }

        MAP[maxIndex[0]][maxIndex[1]] = 0;
        PESTI[maxIndex[0]][maxIndex[1]] = C;
        for(int k=0; k<4; k++){
            for(int t=1; t<=K; t++){
                int nx = maxIndex[0] + t * DXP[k];
                int ny = maxIndex[1] + t * DYP[k];
                if(!canGo(nx, ny))
                    break;
                if(MAP[nx][ny] == 0 || MAP[nx][ny] == -1){
                    PESTI[nx][ny] = C;
                    break;
                }
                MAP[nx][ny] = 0;
                PESTI[nx][ny] = C;
            }
        }
        ANS += max;
    }

    public static void yearPast(){
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(PESTI[i][j] > 0)
                    PESTI[i][j] -= 1;
    }

    public static boolean canGo(int x, int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
}
