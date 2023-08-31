package baekjoon.백트래킹;

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

		/**
		 * 부분 수열을 전부 구하고, 부분수열의 합과 S 를 비교할려고 한다.
		 * depth + 1 == N 이면 tree 에서 부분수열을 전부 구한 단계이고, 부분수열의 합인 val 이 S 와 같으면 ANS++
		 * depth 를 -1에서 시작하여 depth + 1 을 N 과 비교하는 이유는, ARR[depth + 1] 할때 INDEX OUT OF BOUND 에러를 방지하기 위함이다.
		 */
		if(depth + 1 == N){ // 부분수열을 전부 구하기 때문에, depth 가 N 이 되면 부분수열의 합을 S 와 대조해본다.
			if(val == S)
				ANS++;
			return;
		}

		dfs(val + ARR[depth + 1], depth + 1); // TREE 에서 왼쪽 경로로 탐색하는 경우
		dfs(val, depth + 1); // TREE 에서 오른쪽 경로로 탐색하는 경우
	}
}
