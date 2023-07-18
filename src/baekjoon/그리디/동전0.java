package baekjoon.그리디;

import java.util.Scanner;

/**
 * 백준 11047
 * 실버 4
 */
public class 동전0 {

    static int N, K, ANS;
    static int[] ARR;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        ARR = new int[N];
        for(int i=0; i<N; i++)
            ARR[i] = sc.nextInt();

        operate();

        System.out.println(ANS);
    }

    public static void operate(){
        for(int i=N-1; i>=0; i--){
            if(ARR[i]>K)
                continue;
            int val = ARR[i];
            int cnt = 1;
            while(val*cnt <= K)
                cnt++;
            ANS += cnt-1;
            K -= ARR[i] * (cnt-1);
        }
    }
}
