package algorithm_coding._07_01_소수구하기;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 백준 1929
 * 실버 3
 */
public class 소수구하기 {

    static int N, M;
    static int[] ARR;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        ARR = new int[N+1];

        for(int i=2; i<=N; i++)
            ARR[i] = i;

        for(int i=2; i<=Math.sqrt(N); i++)
            for(int j=i+i; j<=N; j+=i)
                if(ARR[j] % i == 0)
                    ARR[j] = 0;

        for(int i=0; i<ARR.length; i++)
            if(ARR[i] != 0 && ARR[i] >= M)
                System.out.println(ARR[i]);

    }
}
