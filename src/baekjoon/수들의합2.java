package baekjoon;

import java.util.Scanner;

/**
 * 백준 2003
 * 실버 4
 */
public class 수들의합2 {

    static int N, M, ANS;
    static int[] INPUT;
    static int[] S;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        INPUT = new int[N+1];
        S = new int[N+1];
        for(int i=1; i<N+1; i++)
            INPUT[i] = sc.nextInt();
        for(int i=1; i<N+1; i++) // 구간합 연산을 위한 합배열 S 를 구해놓는다.
            S[i] = S[i-1] + INPUT[i];

        operate();

        System.out.println(ANS);
    }

    public static void operate(){
        int left = 1;
        int right = 1;
        int sum = 0;

        // 투포인터 연산을 통해 새롭게 추가되거나, 지워지는 경우에 대해 중복없이 수행한다.
        while(right < N+1){
            sum = S[right] - S[left - 1]; // 합배열 S 를 이용하여 구간합을 구한다.
            if(sum == M){
                ANS += 1;
                right++;
            }else if(sum < M){
                right++;
            }else if(sum > M){
                left++;
            }
        }
    }
}
