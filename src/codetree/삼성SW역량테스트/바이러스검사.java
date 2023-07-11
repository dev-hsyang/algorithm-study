package codetree.삼성SW역량테스트;

import java.util.Scanner;

/**
 * 브론즈 2
 * 삼성 SW  역량테스트 2015 하반기 1번 문제
 */
public class 바이러스검사 {

    static int N, LDR, MBR;
    static long ANS;
    static int[] REST;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        REST = new int[N];
        for(int i=0; i<N; i++)
            REST[i] = sc.nextInt();
        LDR = sc.nextInt();
        MBR = sc.nextInt();

        for(int i=0; i<N; i++){
            int total = REST[i];
            total -= LDR;
            ANS += 1;

            /**
             * LDR 단에서 검사가 끝난 인덱스에 대해서는 더이상 계산할 필요가 없다.
             */
            if(total < 0)
                continue;

            if(total % MBR == 0)
                ANS += total / MBR;
            else
                ANS += total / MBR + 1;
        }

        /**
         * ANS 를 long 타입으로 지정하지 않아 오답이 나온 케이스가 있었다.
         */
        System.out.println(ANS);
    }
}
