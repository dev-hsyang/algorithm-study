package baekjoon.백트래킹;

import java.util.Scanner;

/**
 * 백준 1182
 * 실버 2
 * @author didgs
 *
 */
public class 부분수열의합 {
	
	

	static int N, S, ANS;
	static int[] ARR, SUMA, INDEX;
	static boolean[] VISITED;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt();
		ARR = new int[N+1];
		SUMA = new int[N+1];
		INDEX = new int[2];
		VISITED = new boolean[N+1];
		for(int i=1; i<N+1; i++)
			ARR[i] = sc.nextInt();
		for(int i=1; i<N+1; i++)
			SUMA[i] = SUMA[i-1] + ARR[i];
		
		operate(0);
		
		System.out.println(ANS);
	}
	
	public static void operate(int depth) {
		
		if(depth == 2) {
//			if(INDEX[0] > INDEX[1])
//				return;
			if(calculSum(INDEX[0], INDEX[1]) == S) 
				ANS += 1;
			return;
		}
		
		for(int i=1; i<N+1; i++) {
			if(!VISITED[i]) {
				INDEX[depth] = i;
				VISITED[i] = true;
				operate(depth+1);
				VISITED[i] = false;
			}
		}
	}
	
	public static int calculSum(int i, int j) {
		return SUMA[j] - SUMA[i-1];
	}
}
