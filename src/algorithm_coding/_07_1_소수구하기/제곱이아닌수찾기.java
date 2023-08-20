package algorithm_coding._07_1_소수구하기;

import java.util.Scanner;

/**
 * 백준 1016
 * 골드 1
 */
public class 제곱이아닌수찾기 {

    static long MIN, MAX, ANS;
    static boolean[] CHECK;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        MIN = sc.nextLong();
        MAX = sc.nextLong();
        CHECK = new boolean[(int)(MAX - MIN + 1)];

        for(long l=2; l*l <= MAX; l++){
            long pow = l * l;
            long start_index = MIN / pow;
            if((MIN % pow) != 0)
                start_index++;
            for(long j=start_index; pow*j<=MAX; j++)
                CHECK[(int)((j * pow) - MIN)] = true;
        }

        for(int i=0; i<=MAX - MIN; i++)
            if(!CHECK[i])
                ANS++;

        System.out.println(ANS);

    }
}
