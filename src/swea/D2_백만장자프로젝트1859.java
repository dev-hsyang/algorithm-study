package swea;

import java.util.Scanner;
import java.util.Stack;

public class D2_백만장자프로젝트1859 {

	static int T;
	static int N;
	static long[] ANS;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		ANS = new long[T];
		
		for(int i=0; i<T; i++) {
			N = sc.nextInt();
			long sell = 0;
			long sum = 0;
			Stack<Long> stack = new Stack<>();
			
			for(int j=0; j<N-1; j++)
				stack.push(sc.nextLong());
			
			sell = stack.pop();
			
			for(int j=0; j<N-1; j++) {
				long pop = stack.pop();
				if(pop < sell)
					sum += sell - pop;
				if(pop > sell)
					sell = pop;
			}
			ANS[i] = sum;
		}
		
		int idx = 1;
		for(int i=0; i<T; i++)
			System.out.println("#" + idx++ + " " + ANS[i]);
	}

}
