package baekjoon.그래프탐색;

import java.util.Scanner;

/**
 * 백준 11403
 * 실버 1
 */
public class 경로찾기 {

    static int N, CUR;
    static int[][] ADJ;
    static int[][] ANS;
    static boolean[] VISITED;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        ADJ = new int[N][N];
        ANS = new int[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                ADJ[i][j] = sc.nextInt();

        for(int i=0; i<N; i++){
            VISITED = new boolean[N];
            CUR = i;
            operate(i);
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++)
                System.out.print(ANS[i][j] + " ");
            System.out.println();
        }
    }

    public static void operate(int num){
        if(VISITED[num])
            return;

        VISITED[num] = true;

        for(int i=0; i<N; i++){
            if(ADJ[num][i]==1){
                ANS[CUR][i] = 1;
                operate(i);
            }
        }
    }
}
