package algorithm_coding._06_1_그리디알고리즘;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 백준 1931
 * 실버 1
 */
public class 회의실배정 {

    static int N, ANS;
    static int[][] TIMELINE;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        TIMELINE = new int[N][2];
        for(int i=0; i<N; i++){
            TIMELINE[i][0] = sc.nextInt();
            TIMELINE[i][1] = sc.nextInt();
        }

        Arrays.sort(TIMELINE, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) // 종료시간이 같을 때
                    return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        int end = -1;
        for(int i=0; i<N; i++){
            if(TIMELINE[i][0] >= end){
                end = TIMELINE[i][1];
                ANS++;
            }
        }

        System.out.println(ANS);
    }
}
