package algorithm_coding._07_3_유클리드호제법;

import java.util.Scanner;

/**
 * 백준 1934
 * 브론즈 1
 */
public class 최소공배수구하기 {

    static int T, A, B;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for(int test_case=0; test_case<T; test_case++){
            A = sc.nextInt();
            B = sc.nextInt();
            // A 와 B 의 최소공배수는 A * B / A 와 B 의 최대공약수 로 구할수 있다.
            int ans = A * B / gcd(Math.max(A, B), Math.min(A, B));
            System.out.println(ans);
        }
    }
    public static int gcd(int A, int B){
        int a = A;
        int b = B;

        while (true){
            int c = a % b;
            if(c == 0)
                break;
            a = b;
            b = c;
        }

        return b;
    }

    public static int gcd_recursive(int a, int b){
        if(b == 0)
            return a;
        else
            return gcd_recursive(b, a % b);
    }
}
