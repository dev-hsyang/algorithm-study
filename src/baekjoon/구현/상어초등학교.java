package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 21608
 * 삼성 SW 역량테스트 2021 상반기 오전 1번 문제
 * 골드 5
 */
public class 상어초등학교 {

    static int N, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[] ORDER;
    static int[] SCORE = {0, 1, 10, 100, 1000};
    static int[][] BOARD;
    static HashSet<Integer>[] SETLIST;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ORDER = new int[N * N + 1];
        BOARD = new int[N + 1][N + 1];
        SETLIST = new HashSet[N * N + 1];
        for(int i=1; i<N*N+1; i++)
            SETLIST[i] = new HashSet<>();
        for(int i=1; i<=N*N; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            ORDER[i] = num;
            for(int j=0; j<4; j++)
                SETLIST[num].add(Integer.parseInt(st.nextToken()));
        }

        simulate();
        System.out.println(ANS);
    }

    public static void simulate(){
        for(int i=1; i<ORDER.length; i++) {
            int nowTurn = ORDER[i];
            ride(nowTurn);
        }
        sumScore();
    }

    public static void ride(int idx){
        ArrayList<int[]> candi = new ArrayList<>();
        for(int i=1; i<=N; i++)
            for(int j=1; j<=N; j++)
                if(BOARD[i][j] == 0){
                    int friendCnt = 0;
                    int emptyCnt = 0;
                    for(int k=0; k<4; k++){
                        int nx = i + DX[k];
                        int ny = j + DY[k];
                        if(canGo(nx, ny) && SETLIST[idx].contains(BOARD[nx][ny]))
                            friendCnt += 1;
                        if(canGo(nx, ny) && BOARD[nx][ny] == 0)
                            emptyCnt += 1;
                    }
                    candi.add(new int[] {friendCnt, emptyCnt, i, j});
                }

        Collections.sort(candi, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]){
                    if(o1[1] == o2[1]){
                        if(o1[2] == o2[2])
                            return o1[3] - o2[3];
                        return o1[2] - o2[2];
                    }
                    return o2[1] - o1[1];
                }
                return o2[0] - o1[0];
            }
        });
        BOARD[candi.get(0)[2]][candi.get(0)[3]] = idx;
    }

    public static void sumScore(){
        for(int i=1; i<=N; i++)
            for(int j=1; j<=N; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + DX[k];
                    int ny = j + DY[k];
                    if (canGo(nx, ny) && SETLIST[BOARD[i][j]].contains(BOARD[nx][ny]))
                        cnt++;
                }
                ANS += SCORE[cnt];
            }
    }

    public static boolean canGo(int x, int y){
        return x>=1 && x<=N && y>=1 && y<=N;
    }
}