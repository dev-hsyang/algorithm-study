package algorithm_coding._03_2_구간합;

import java.util.Scanner;

/**
 * 실버 1
 * 백준 11660
 * @author didgs
 *
 */
public class 구간합구하기2 {

	static int N, M, X1, X2, Y1, Y2, ANS;
	static int[][] A, D;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		A = new int[N+1][N+1];
		D = new int[N+1][N+1];
		
		for(int i=1; i<N+1; i++)
			for(int j=1; j<N+1; j++) {
				A[i][j] = sc.nextInt();
				D[i][j] = D[i-1][j] + D[i][j-1] - D[i-1][j-1] + A[i][j];
			}
		
		for(int i=0; i<M; i++) {
			X1 = sc.nextInt();
			Y1 = sc.nextInt();
			X2 = sc.nextInt();
			Y2 = sc.nextInt();
			
			ANS = D[X2][Y2] - D[X1-1][Y2] - D[X2][Y1-1] + D[X1-1][Y1-1];
			System.out.println(ANS);
		}
		
	}

}
