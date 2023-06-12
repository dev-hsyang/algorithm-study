package swea;

import java.util.Scanner;

public class D3_통나무자르기14692 {

	static int TC, N;
	static String WINNER;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		TC = sc.nextInt();
		
		for(int i=1; i<=TC; i++) {
			N = sc.nextInt();
			if(N%2==0)
				WINNER = "Alice";
			else if(N%2!=0)
				WINNER = "Bob";
			System.out.println("#" + i + " " + WINNER);
		}

	}

}
