package algorithm_coding._06_1_그리디알고리즘;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 백준 1715
 * 골드 4
 * @author didgs
 *
 */
public class 카드정렬하기 {

	static int N, ANS;
	static PriorityQueue<Integer> PQ = new PriorityQueue<Integer>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		for(int i=0; i<N; i++)
			PQ.add(sc.nextInt());
		
		while(PQ.size()>1) {
			int a = PQ.poll();
			int b = PQ.poll();
			int sum = a + b;
			ANS += sum;
			PQ.add(sum);
		}
		System.out.println(ANS);
	}
}
