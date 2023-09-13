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

    /**
     * <<집은 계란이 깨져있을 경우, 다음 계란으로 넘어간다>> 요구사항을 구현하지 못하여 어려움을 겪었다.
     * 현재 index 의 계란 즉, 현재 집은 계란이 깨져있을 경우 다음계란으로 다시 dfs 를 시작해야한다.
     * 또한, 다음계란으로 dfs 를 하여 오른쪽 끝의 계란까지 끝냈으면 계란깨기가 끝난 상태이므로 dfs 후 return 해주어야 한다.
     * @param index
     * @param cracked
     */
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
