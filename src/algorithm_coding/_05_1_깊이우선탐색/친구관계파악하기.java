package algorithm_coding._05_1_깊이우선탐색;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 백준 13023
 * 골드 5
 */
public class 친구관계파악하기 {

    static int N, M, ANS;
    static boolean[] VISITED;
    static ArrayList<Integer>[] ADJ;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        ADJ = new ArrayList[N];
        for(int i=0; i<N; i++)
            ADJ[i] = new ArrayList<Integer>();
        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            ADJ[a].add(b);
            ADJ[b].add(a);
        }

        for(int i=0; i<N; i++){
            VISITED = new boolean[N];
            operate(i, 0);
            if(ANS == 1)
                break;
        }

        System.out.println(ANS);
    }

    public static void operate(int node, int depth){
        if(VISITED[node])
            return;
        if(depth == 4){
            ANS = 1;
            return;
        }

        VISITED[node] = true;

        for(int i : ADJ[node]){
            if(!VISITED[i]){
                operate(i, depth + 1);
                VISITED[i] = false;
            }
        }
    }
}
