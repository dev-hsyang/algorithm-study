package swea.d3;

import java.util.Scanner;

public class 등차수열18662 {

    static int TC, A, B, C;
    static double ANS;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TC = sc.nextInt();
        for(int test_case = 1; test_case<=TC; test_case++){
            A = sc.nextInt();
            B = sc.nextInt();
            C = sc.nextInt();
            ANS = Double.MAX_VALUE;
            if(B - A == C - B) {
                ANS = 0.0;
            } else {
                ANS = Math.abs((double) B - (double) (A + C) / 2);
            }

            System.out.println("#" + test_case + " " + ANS);
        }
    }
}
