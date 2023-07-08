package algorithm_coding._03_3_투포인터;

import java.util.Arrays;
import java.util.Scanner;

public class 주몽의명령 {

    static int N, M, ANS;
    static int[] ARR;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        ARR = new int[N];
        for(int i=0; i<N; i++)
            ARR[i] = sc.nextInt();
        Arrays.sort(ARR);

        operate();

        System.out.println(ANS);
    }

    public static void operate(){
        int sum = 0;
        int start_idx = 0;
        int end_idx = N-1;

        while (start_idx < end_idx){
            sum = ARR[start_idx] + ARR[end_idx];
            if(sum == M){
                ANS += 1;
                end_idx -= 1;
            }else if(sum < M){
                start_idx += 1;
            }else if(sum > M){
                end_idx -= 1;
            }
        }
    }

}
