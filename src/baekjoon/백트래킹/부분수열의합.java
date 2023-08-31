package baekjoon.백트래킹;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 1182
 * 실버 2
 *
 */
public class 부분수열의합 {

	static int N, S, ANS;
	static int[] ARR;

	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt();
		ARR = new int[N];
		for(int i=0; i<N; i++)
			ARR[i] = sc.nextInt();

		dfs(0, -1);

		if (S == 0)
			ANS -= 1;

		System.out.println(ANS);
	}

	public static void dfs(int val, int depth){

		if(depth + 1 == N){
			if(val == S)
				ANS++;
			else
				return;
		}else {
			dfs(val + ARR[depth+1], depth+1);
			dfs(val, depth + 1);
		}
	}
}
