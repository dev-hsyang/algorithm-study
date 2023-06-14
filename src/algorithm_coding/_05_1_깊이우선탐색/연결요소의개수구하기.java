package algorithm_coding._05_1_깊이우선탐색;

import java.util.ArrayList;
import java.util.Scanner;

// 백준 11724
// 실버5
public class 연결요소의개수구하기 {

	static int N, M;
	static boolean[] visited;
	static ArrayList<Integer>[] ADJ;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		ADJ = new ArrayList[N+1];
		visited = new boolean[N+1];
		int count = 0;
		
		for(int i=1; i<N+1; i++) 
			ADJ[i] = new ArrayList<Integer>();
		
		for(int i=0; i<M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			
			ADJ[u].add(v);
			ADJ[v].add(u);
		}
		
		for(int i=1; i<N+1; i++) {
			if(!visited[i]) {
				DFS(i);
				count += 1;
			}
		}
		
		System.out.println(count);
		
	}
	
	public static void DFS(int start) {
		
		if(visited[start])
			return;
		
		visited[start] = true;
		
		for(int i=0; i<ADJ[start].size(); i++)
			DFS(ADJ[start].get(i));
	}

}

