package baekjoon.그래프탐색;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 6118
 * 실버 1
 */
public class 숨바꼭질2 {

    static int N, M, ANS, DISTANCE, CNT;
    static int[] DIST;
    static boolean[] VISITED;
    static ArrayList<Integer>[] ADJ;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        DIST = new int[N+1];
        VISITED = new boolean[N+1];
        ADJ = new ArrayList[N+1];
        for(int i=1 ;i<N+1; i++)
            ADJ[i] = new ArrayList<Integer>();
        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            ADJ[a].add(b);
            ADJ[b].add(a);
        }

        initDist();
        operate();

        System.out.println(ANS + " " + DISTANCE + " " + CNT);
    }

    public static void operate(){
        for(int i=1; i<N+1; i++){
            VISITED = new boolean[N+1];
        }
        bfs(1);
        findMaxDistance();
        findAns();
        countDist();
    }

    public static void bfs(int start){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        VISITED[start] = true;
        while (!queue.isEmpty()){
            int now = queue.poll();
            for(int i : ADJ[now]){
                if(!VISITED[i]){
                    DIST[i] = DIST[now] + 1;
                    VISITED[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    public static void findMaxDistance(){
        for(int i=1; i<N+1; i++)
            DISTANCE = Math.max(DISTANCE, DIST[i]);
    }

    public static void findAns(){
        for(int i=1; i<N+1; i++)
            if(DIST[i] == DISTANCE){
                ANS = i;
                break;
            }
    }

    public static void countDist(){
        for(int i=1; i<N+1; i++)
            if(DIST[i] == DISTANCE)
                CNT += 1;
    }

    public static void initDist(){
        for(int i=2; i<N+1; i++)
            DIST[i] = Integer.MAX_VALUE;
    }
}
