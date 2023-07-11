package baekjoon.브루트포스;

import java.util.Scanner;

/**
 * 백준 2018
 * 실버 5
 */
public class 수들의합5 {

    static int N, ANS;
    static int[] ARR;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        ARR = new int[N+1];
        for(int i=1; i<N+1; i++)
            ARR[i] = i;

        operate();

        System.out.println(ANS);
    }
    public static void operate(){
        for(int i=1; i<N+1; i++){
            int n = 0;
            for(int j=i; j<N+1; j++){
                n += j;
                if(n == N)
                    ANS += 1;
                if(n > N)
                    break;
            }
        }
    }
}
