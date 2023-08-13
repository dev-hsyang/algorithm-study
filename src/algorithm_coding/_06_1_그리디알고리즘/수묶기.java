package algorithm_coding._06_1_그리디알고리즘;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 백준 1744
 * 골드 4
 */
public class 수묶기 {

    static int N, CNT_1, CNT_0, ANS;
    static PriorityQueue<Integer> PLUS_PQ = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> MINUS_PQ = new PriorityQueue<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for(int i=0; i<N; i++) {
            int num = sc.nextInt();
            if (num > 1)
                PLUS_PQ.add(num);
            else if (num == 1)
                CNT_1 += 1;
            else if (num == 0)
                CNT_0 += 1;
            else if (num < 0)
                MINUS_PQ.add(num);
        }

        while(PLUS_PQ.size() > 1){
            int a = PLUS_PQ.poll();
            int b = PLUS_PQ.poll();
            ANS += a * b;
        }
        if(!PLUS_PQ.isEmpty())
            ANS += PLUS_PQ.poll();

        while(MINUS_PQ.size() > 1){
            int a = MINUS_PQ.poll();
            int b = MINUS_PQ.poll();
            ANS += a * b;
        }
        if(!MINUS_PQ.isEmpty())
            if(CNT_0 == 0)
                ANS += MINUS_PQ.poll();

        ANS += CNT_1;
        System.out.println(ANS);
    }
}
