package algorithm_coding._07_01_소수구하기;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 백준 1929
 * 실버 3
 */

/**
 * '에라토스테네스의 체' 를 활용한 소수 구하는 방식이다.
 * 시간초과를 유발하지 않기 위한 중요한 두가지 포인트는,
 *
 * 1) 구하는 범위의 최대값의 제곱근까지만 탐색을 진행하고,
 * 2) 이중 for 문을 구현할 때, 현재 i 의 배수일 경우만 for 문이 실행되도록 코드를 짜야 O(N^2) 이 아닌 O(Nlog(logN) 의 시간복잡도를 가지게 된다.
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
            for(int j=i+i; j<=N; j+=i) // j 가 i 의 배수인 경우로만 최적화 해두면 O(N^2) 가 되지 않는다.
                if(ARR[j] % i == 0)
                    ARR[j] = 0;

        for(int i=0; i<ARR.length; i++)
            if(ARR[i] != 0 && ARR[i] >= M)
                System.out.println(ARR[i]);

    }
}
