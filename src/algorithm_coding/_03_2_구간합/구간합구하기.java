package algorithm_coding._03_2_구간합;

import java.util.Scanner;

/**
 * 실버 3
 * 백준 11659
 * @author didgs
 *
 */
public class 구간합구하기 {

	static int N, M;
	static int I, J, ANS;
	static int[] A, S;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		A = new int[N+1];
		S = new int[N+1];
		
		for(int i=1; i<N+1; i++) {
			A[i] = sc.nextInt();
			S[i] = S[i-1] + A[i];
		}
		
		for(int i=0; i<M; i++) {
			I = sc.nextInt();
			J = sc.nextInt();
			ANS = S[J] - S[I-1];
			System.out.println(ANS);
		}
		
		
	}

}
