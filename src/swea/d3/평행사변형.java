package swea.d3;

import java.util.Scanner;

public class 평행사변형 {

	static int NC;
	static int N;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		NC = sc.nextInt();
		
		for(int i=1; i<=NC; i++) {
			N = sc.nextInt();
			
			System.out.println("#" + i + " " + N*N);
		}
	}

}
