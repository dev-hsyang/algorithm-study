package baekjoon.백트래킹;

import java.util.Scanner;

public class 부분수열의합_prac {

    static int N, S, ANS;
    static int[] ARR;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();
        ARR = new int[N];
        for(int i=0; i<N; i++)
            ARR[i] = sc.nextInt();

        dfs(0, 0);

        if(S == 0)
            ANS -= 1;

        System.out.println(ANS);
    }

    public static void dfs(int depth, int sum){
        if(depth == N){
            if(sum == S)
                ANS++;
            return;
        }

        dfs(depth + 1, sum + ARR[depth]);
        dfs(depth + 1, sum);
    }
}
