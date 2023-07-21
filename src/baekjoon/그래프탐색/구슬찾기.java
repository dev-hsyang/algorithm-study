package baekjoon.그래프탐색;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 백준 2617
 * 골드 4
 */
public class 구슬찾기 {


    static int N, M, CNT, MIDVAL, ANS;
    static int[] LIGHT_COUNT, HEAVY_COUNT;
    static boolean[] VISITED;
    static ArrayList<Integer>[] LIGHTER;
    static ArrayList<Integer>[] HEAVER;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        MIDVAL = (N+1) / 2;
        LIGHT_COUNT = new int[N+1];
        HEAVY_COUNT = new int[N+1];
        LIGHTER = new ArrayList[N+1];
        HEAVER = new ArrayList[N+1];
        for(int i=1; i<N+1; i++){
            LIGHTER[i] = new ArrayList<Integer>();
            HEAVER[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            LIGHTER[a].add(b);
            HEAVER[b].add(a);
        }

        operate();

        System.out.println(ANS);
    }

    public static void operate(){
        for(int i=1; i<N+1; i++){
            countLighter(i);
            countHeaver(i);
        }
        countAns();
    }

    public static void countLighter(int then){
        CNT = 0;
        VISITED = new boolean[N+1];
        dfs_LIGHTER(then);
        LIGHT_COUNT[then] = CNT;
    }

    public static void countHeaver(int then){
        CNT = 0;
        VISITED = new boolean[N+1];
        dfs_HEAVER(then);
        HEAVY_COUNT[then] = CNT;
    }

    public static void dfs_LIGHTER(int start){
        if(VISITED[start])
            return;
        VISITED[start] = true;
        for(int i : LIGHTER[start]){
            if(!VISITED[i]){
                CNT += 1;
                dfs_LIGHTER(i);
            }
        }
    }

    public static void dfs_HEAVER(int start){
        if(VISITED[start])
            return;
        VISITED[start] = true;
        for(int i : HEAVER[start]){
            if(!VISITED[i]){
                CNT += 1;
                dfs_HEAVER(i);
            }
        }
    }

    public static void countAns(){
        for(int i=1; i<N+1; i++){
            if(LIGHT_COUNT[i] >= MIDVAL)
                ANS += 1;
            if(HEAVY_COUNT[i] >= MIDVAL)
                ANS += 1;
        }
    }
}
