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

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        HP = new int[N];
        WEIGHT = new int[N];
        for(int i=0; i<N; i++){
            HP[i] = sc.nextInt();
            WEIGHT[i] = sc.nextInt();
        }

        dfs(0, 0);

        System.out.println(ANS);
    }

    public static void dfs(int index, int cracked){
        if(index == N)
            return;

        if(HP[index] <= 0){
            dfs(index + 1, cracked);
            return;
        }

        for(int i=0; i<N; i++){
            if(i != index && HP[i] > 0){
                int cnt = 0;
                HP[i] -= WEIGHT[index];
                HP[index] -= WEIGHT[i];
                if(HP[i] <= 0)
                    cnt++;
                if(HP[index] <= 0)
                    cnt++;
                ANS = Math.max(ANS, cracked + cnt);
                dfs(index + 1, cracked + cnt);
                HP[i] += WEIGHT[index];
                HP[index] += WEIGHT[i];
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
