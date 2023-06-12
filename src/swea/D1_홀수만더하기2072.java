package swea;

import java.util.Scanner;

public class D1_홀수만더하기2072 {

	static int T;
	static int[] ARR;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		ARR = new int[T];
		
		for(int i=0; i<T; i++) {
			int sum = 0;
			for(int j=0; j<10; j++) {
				int num = sc.nextInt();
				if(num % 2 != 0)
					sum += num;
			}
			ARR[i] = sum;
		}
		
		int index = 1;
		for(int i : ARR)
			System.out.println("#" + index++ + " " + i);

	}

}
