package baekjoon.그래프탐색;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 백준 2644
 * DFS BFS 필수문제
 * 실버 2
 */
public class 촌수계산 {

    static int N, M, START, FIN;
    static int ANS = -1;
    static ArrayList[] ADJ;
    static boolean[] VISITED;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        START = sc.nextInt();
        FIN  = sc.nextInt();
        M = sc.nextInt();
        ADJ = new ArrayList[N+1];
        VISITED = new boolean[N+1];

        for(int i=1; i<N+1; i++)
            ADJ[i] = new ArrayList<Integer>();

        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            ADJ[a].add(b);
            ADJ[b].add(a);
        }

        dfs(START, 0);

        System.out.println(ANS);
    }

    public static void dfs(int node, int depth){
        if(node == FIN){
            ANS = depth;
            return;
        }
        if(VISITED[node])
            return;

        VISITED[node] = true;

        for(int i=0; i<ADJ[node].size(); i++){
            int nextNode = (int)ADJ[node].get(i);
            dfs(nextNode, depth + 1);
        }
    }
}
