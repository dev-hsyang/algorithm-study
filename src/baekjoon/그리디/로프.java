package baekjoon.그리디;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 2217
 * 실버 4
 */
public class 로프 {

    static int N, ANS;
    static int[] ROPE;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        ROPE = new int[N];
        for(int i=0; i<N; i++)
            ROPE[i] = sc.nextInt();
        Arrays.sort(ROPE);

        for(int i=0; i<N; i++)
            ANS = Math.max(ANS, ROPE[i] * (N - i));

        System.out.println(ANS);
    }
}
