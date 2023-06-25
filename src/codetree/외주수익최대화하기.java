package codetree;

import java.util.Scanner;

/**
 * 실버 3
 * 삼성 SW 역량테스트 2017 상반기 오전 2번 문제
 */
public class 외주수익최대화하기 {

    static int N, ANS;
    static int[][] INFO;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        INFO = new int[N][2];

        for(int i=0; i<N; i++)
            for(int j=0; j<2; j++)
                INFO[i][j] = sc.nextInt();

        for(int i=0; i<N; i++)
            findMax(INFO[i][0], INFO[i][1], i);

        System.out.println(ANS);
    }

    public static void findMax(int t, int p, int index){
        if(t > N)
            return;

        if(t == N || index >= N-1 || index+INFO[index][0] >= N){
            ANS = Math.max(p, ANS);
            return;
        }
        for(int i=index+INFO[index][0]; i<N; i++){
            if(t + INFO[i][0] > N)
                ANS = Math.max(p, ANS);

            findMax(t + INFO[i][0], p + INFO[i][1], i);
        }
    }
}
