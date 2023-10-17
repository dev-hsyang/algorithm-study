package baekjoon.투포인터;

import java.util.Scanner;

/**
 * 백준 2018
 * 실버 5
 */
public class 수들의합5 {

    static int N, ANS;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N  = sc.nextInt();

        int left = 1;
        int right = 2;
        int sum = 3;
        ANS = 1;
        while(right < N) {
            if (sum == N) {
                ANS++;
                right++;
                sum += right;
            } else if (sum < N) {
                right++;
                sum += right;
            } else if (sum > N) {
                sum -= left;
                left++;
            }
        }
        System.out.println(ANS);
    }
}
