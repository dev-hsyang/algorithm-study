package algorithm_coding._03_3_투포인터;

import java.util.Scanner;

public class 연속된자연수의합구하기 {

    static int N, ANS;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        operate();

        System.out.println(ANS);
    }

    public static void operate(){
        ANS = 1;
        int start_idx = 1;
        int end_idx = 1;
        int sum = 1;

        while (end_idx != N){
            if(sum == N){
                ANS += 1;
            }else if(sum > N){
                start_idx += 1;
                end_idx = start_idx;
                sum = start_idx;
                continue;
            }
            end_idx += 1;
            sum += end_idx;
        }
    }
}
