package algorithm_coding._03_3_투포인터;

import java.util.Scanner;

/**
 * 백준 1940
 * 실버 5
 */
public class 주몽 {

    static int N, M, ANS;
    static int[] NUMS;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        NUMS = new int[N];
        for(int i=0; i<N; i++)
            NUMS[i] = sc.nextInt();

        operate();

        System.out.println(ANS);
    }
    public static void operate(){
        for(int i=0; i<N-1; i++)
            for(int j=i+1; j<N; j++)
                if(NUMS[i] + NUMS[j] == M)
                    ANS += 1;
    }
}
