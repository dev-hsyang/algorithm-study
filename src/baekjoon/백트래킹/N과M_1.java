package baekjoon.백트래킹;


import java.util.Scanner;

/**
 * 백준 15649
 * 실버 3
 * @author didgs
 *
 */
public class N과M_1 {

	static int N, M, PIVOT;
	static int[] ANS;
	static boolean[] VISITED;	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		ANS = new int[M+1];
		VISITED = new boolean[N+1];
		
		operate(1);
	}
	
	public static void operate(int depth) {
		if(depth == M+1) {
			print();
			return;
		}
		
		for(int i=1; i<N+1; i++) {
			if(!VISITED[i]) {
				VISITED[i] = true;
				ANS[depth] = i; //** 핵심포인트.. depth 는 자릿수를 표현한다. 배열에서 depth 인덱스의 값은 해당 자릿수에 위치할 탐색된 값인 것이다.
				operate(depth + 1);
				VISITED[i] = false;
			}
		}
	}
	
	public static void print() {
		for(int i=1; i<M+1; i++)
			System.out.print(ANS[i] + " ");
		System.out.println();
	}
}
