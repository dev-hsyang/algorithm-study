package swea;

import java.util.Scanner;
import java.util.Stack;

public class D3_구구단걷기16800 {
	
	static int TC;
	static long NUM, ANS;
	static long[] DIVISORS;
	static Stack<Integer> XSTACK;
	static Stack<Long> YSTACK;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		TC = sc.nextInt();
		
		for(int test_case = 1; test_case<=TC; test_case++) {
			NUM = sc.nextLong();
			ANS = 0;
			XSTACK = new Stack<>();
			YSTACK = new Stack<>();
			
			for(int i=1; i<=Math.sqrt(NUM); i++) {
				if(NUM%i==0) {
					XSTACK.push(i);
					YSTACK.push(NUM/i);
				}
			}
			
			ANS = XSTACK.pop() - 1 + YSTACK.pop() - 1;
			System.out.println("#" + test_case + " " + ANS);
		}
		
	}
}


