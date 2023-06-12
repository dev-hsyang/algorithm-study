package swea;

import java.util.Scanner;

public class D3_조별과제13218 {

	static int T, N;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
			N = sc.nextInt();
			System.out.println("#" + test_case + " " + N/3);
		}
	}

}
