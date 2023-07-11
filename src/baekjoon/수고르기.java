package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 2230
 * 골드 5
 */
public class 수고르기 {

    static int N;
    static long M, ANS;
    static long[] ARR;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        ARR = new long[N];
        ANS = Long.MAX_VALUE;
        for(int i=0; i<N; i++)
            ARR[i] = sc.nextLong();
        Arrays.sort(ARR);

        operate();

        System.out.println(ANS);
    }

    /**
     * 투포인터 알고리즘으로 중복경우를 줄여서 시간복잡도 최적화
     */
    public static void operate(){
        int left = 0;
        int right = 0;
        long diff = 0;

        while(left <= right){
            if(right >= N)
                break;
            diff = ARR[right] - ARR[left];
            if(diff >= M){
                ANS = Math.min(ANS, diff);
                left++;
            }else if(diff < M){
                right++;
            }
        }
    }
}
