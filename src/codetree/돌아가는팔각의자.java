package codetree;

import java.util.Scanner;

/**
 * 골드 5
 * 삼성 SW 역량테스트 2017 하반기 오후 1번 문제
 */
public class 돌아가는팔각의자 {

    static int K, N, D, ANS;
    static int[][] CHAIRS = new int[5][8];
    static boolean[] ROTATED;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        for (int i=1; i<5; i++){
            String[] s = sc.next().split("");
            for (int j=0; j<8; j++)
                CHAIRS[i][j] = Integer.parseInt(s[j]);
        }

        K = sc.nextInt();
        for (int i=0; i<K; i++){
            ROTATED = new boolean[5];
            N = sc.nextInt();
            D = sc.nextInt();
            operate(N, D);
        }

        calculate();

        System.out.println(ANS);
    }

    /**
     * 핵심 아이디어
     * recursive 하게 회전시킬 대상을 먼저 찾아낸다. (DFS)
     * 선택된 대상과 맞물려있으면서, 조건을 맞족시키면 recursive 하게 찾아내는 동작을 반복한다.
     * @param n
     * @param d
     */
    public static void operate(int n, int d){

        if (ROTATED[n] || !isInbound(n))
            return;

        ROTATED[n] = true;

        if (isInbound(n-1) && (CHAIRS[n-1][2] != CHAIRS[n][6]) && !ROTATED[n-1]){
            operate(n-1, opposite(d));
        }
        if (isInbound(n+1) && (CHAIRS[n+1][6] != CHAIRS[n][2]) && !ROTATED[n+1]){
            operate(n+1, opposite(d));
        }

        if (d == 1)
            clock(CHAIRS[n]);
        else if (d == -1)
            counterClock(CHAIRS[n]);
    }

    /**
     * 시계방향으로 회전
     * @param chair
     */
    public static void clock(int[] chair){
        int[] temp = new int[8];
        temp[0] = chair[7];
        for (int i=1; i<8; i++)
            temp[i] = chair[i-1];

        for (int i=0; i<8; i++)
            chair[i] = temp[i];
    }

    /**
     * 반시계 방향으로 회전
     * @param chair
     */
    public static void counterClock(int[] chair){
        int[] temp = new int[8];
        temp[7] = chair[0];
        for (int i=1; i<8; i++)
            temp[i-1] = chair[i];

        for (int i=0; i<8; i++)
            chair[i] = temp[i];
    }

    public static void calculate(){
        ANS += (1 * CHAIRS[1][0]) + (2 * CHAIRS[2][0]) + (4 * CHAIRS[3][0]) + (8 * CHAIRS[4][0]);
    }

    public static boolean isInbound(int x){
        return x>=1 && x<5;
    }

    public static int opposite(int a){
        if (a == 1)
            return -1;
        else
            return 1;
    }
}
