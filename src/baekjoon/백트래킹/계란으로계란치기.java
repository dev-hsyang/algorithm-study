package baekjoon.백트래킹;

import java.util.Scanner;

/**
 * 백준 16987
 * 골드 5
 */
public class 계란으로계란치기 {

    static int N, ANS;
    static int[] HP;
    static int[] WEIGHT;
    static boolean[] VISITED;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        HP = new int[N];
        WEIGHT = new int[N];
        VISITED = new boolean[N];
        for(int i=0; i<N; i++){
            HP[i] = sc.nextInt();
            WEIGHT[i] = sc.nextInt();
        }

        dfs(0);

        System.out.println(ANS);
    }

    public static void dfs(int index){
        if(index == N)
            return;

        for(int i=0; i<N; i++){
            if(HP[index] > 0 && HP[i] > 0 && i != index){
                HP[index] -= WEIGHT[i];
                HP[i] -= WEIGHT[index];
                ANS = Math.max(ANS, countCracked());
                dfs(index + 1);
                HP[index] += WEIGHT[i];
                HP[i] += WEIGHT[index];
            }
        }
    }

    public static int countCracked(){
        int cnt = 0;
        for(int i=0; i<N; i++)
            if(HP[i] <= 0)
                cnt++;
        return cnt;
    }
}
