package baekjoon.자료구조;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 1966
 * 실버 3
 * @author didgs
 *
 */
public class 프린터큐 {

	static int TC, N, M, ANS;
	static Queue<Token1966> QUEUE;
	static PriorityQueue<Integer> PRIORITY;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TC = sc.nextInt();
		for(int tc=0; tc<TC; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			QUEUE = new LinkedList<>();
			PRIORITY = new PriorityQueue<>(Collections.reverseOrder());
			ANS = 0;
			int tokenNum = 0;
			for(int i=0; i<N; i++) {
				int num = sc.nextInt();
				PRIORITY.add(num);
				QUEUE.add(new Token1966(tokenNum++, num));
			}
			
			operatePrinter();
			System.out.print(ANS + "\n");
		}
	}
	
	public static void operatePrinter() {
		int cnt = 0;
		while(true) {
			if(QUEUE.peek().priority == PRIORITY.peek()) {
				PRIORITY.poll();
				Token1966 temp = QUEUE.poll();
				cnt++;
				if(temp.num == M) {
					ANS = cnt;
					break;
				}
			}else {
				Token1966 temp = QUEUE.poll();
				QUEUE.add(temp);
			}
		}
	}

}

class Token1966{
	
	public Token1966(int num, int priority) {
		this.num = num;
		this.priority = priority;
	}
	
	int num, priority;
}