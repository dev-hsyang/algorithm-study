package algorithm_coding._07_2_오일러피;

import java.util.Scanner;

/**
 * 백준 11689
 * 골드 1
 */
public class 오일러피함수구현하기 {

    static long N, ANS;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextLong();
        ANS = N;

        for(long p=2; p<=Math.sqrt(N); p++){
            if(N % p == 0){
                ANS -= ANS / p;
                while(N % p == 0)
                    N /= p;
            }
        }

        if(N > 1)
            ANS -= ANS / N;

        System.out.println(ANS);
    }
}
