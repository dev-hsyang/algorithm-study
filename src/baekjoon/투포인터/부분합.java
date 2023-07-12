package baekjoon.투포인터;

import java.util.Scanner;

/**
 * 백준 1806
 * 골드 4
 */
public class 부분합 {

    static int N;
    static long S;
    static long ANS = Long.MAX_VALUE;
    static long[] A, H;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextLong();
        A = new long[N+1];
        H = new long[N+1];
        for(int i=1; i<N+1; i++)
            A[i] = sc.nextLong();
        for(int i=1; i<N+1; i++)
            H[i] = H[i-1] + A[i];

        operate();
        System.out.println(ANS);
    }

    public static void operate(){
        int left = 1;
        int right = 1;
        long sum = 0;

        while(left <= right){
            if(right > N)
                break;
            sum = H[right] - H[left-1];
            if(sum < S)
                right++;
            else if(sum >= S){
                ANS = Math.min(ANS, right - left + 1);
                left++;
            }
        }

        if(ANS == Long.MAX_VALUE)
            ANS = 0;
    }
}
