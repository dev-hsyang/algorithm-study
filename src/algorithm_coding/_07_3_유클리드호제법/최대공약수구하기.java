package algorithm_coding._07_3_유클리드호제법;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * 백준 1850
 * 실버 1
 */
public class 최대공약수구하기 {

    static long A, B, ANS;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Scanner sc = new Scanner(System.in);
        A = sc.nextLong();
        B = sc.nextLong();
        long gcd = gcd(Math.max(A, B), Math.min(A, B));
        for(long l=1; l<=gcd; l++)
            bw.append("1");
        bw.flush();
        bw.close();
    }

    public static long gcd(long a, long b){
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
