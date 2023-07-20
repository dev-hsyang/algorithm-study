package algorithm_coding._03_5_스택과큐;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 2164
 * 실버 4
 */
public class 카드게임 {

    static int N, ANS;
    static Queue<Integer> QUEUE = new LinkedList<Integer>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for(int i=1; i<N+1; i++)
            QUEUE.add(i);

        while(QUEUE.size() > 1){
            QUEUE.poll();
            int shiftNum = QUEUE.poll();
            QUEUE.add(shiftNum);
        }

        ANS = QUEUE.poll();

        System.out.println(ANS);
    }
}
