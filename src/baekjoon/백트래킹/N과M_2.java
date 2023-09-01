package baekjoon.백트래킹;

import java.util.Scanner;

/**
 * 백준 156550
 * 실버 3
 */
public class N과M_2 {

    static int N, M;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        dfs(1, 0, new int[M]);

    }

    public static void dfs(int num, int depth, int[] arr){
        if(depth == M){
            for(int i : arr)
                System.out.print(i + " ");
            System.out.println();
            return;
        }

        for(int i=num; i<N+1; i++){
            arr[depth] = i;
            dfs(i + 1, depth + 1, arr);
        }
    }
}
