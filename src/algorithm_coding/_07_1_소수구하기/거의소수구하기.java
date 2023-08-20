package algorithm_coding._07_1_소수구하기;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 백준 1456
 * 골드 5
 */
public class 거의소수구하기 {

    public static long A, B, ANS;
    public static int[] ARR;
    public static ArrayList<Long> PRIMES = new ArrayList<Long>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        A = sc.nextLong();
        B = sc.nextLong();
        initPrimeNum();

        for(long l : PRIMES){
            long temp = l;
            while((double)l <= (double)B/ (double)temp){
                if((double)l >= (double)A / (double)temp)
                    ANS++;
                temp *= l;
            }
        }
        System.out.println(ANS);
    }

    public static void initPrimeNum(){
        long[] temp = new long[10000001];
        for(int i=2; i<=10000000; i++)
            temp[i] = i;

        for(int i=2; i<=Math.sqrt(10000000); i++)
            for(int j=i+i; j<=10000000; j+=i)
                temp[j] = 0;

        for(int i=0; i<=10000000; i++)
            if(temp[i] != 0)
                PRIMES.add(temp[i]);
    }
}
