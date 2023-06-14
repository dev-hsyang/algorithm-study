package algorithm_coding._05_2_너비우선탐색;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 1260
// 실버 2
public class DFS와BFS프로그램 {

	static int N, M;
	static int START;
	static boolean[] VISITED;
	static ArrayList<Integer>[] ADJ;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		START = sc.nextInt();
		ADJ = new ArrayList[N+1];
		
		for(int i=0; i<N+1; i++)
			ADJ[i] = new ArrayList<Integer>();
		
		for(int i=0; i<M; i++) {
			int e1 = sc.nextInt();
			int e2 = sc.nextInt();
			ADJ[e1].add(e2);
			ADJ[e2].add(e1);
		}
		
		for(int i=0; i<N+1; i++)
			Collections.sort(ADJ[i]);
		
		initVisited();
		DFS(START);
		System.out.println();
		initVisited();
		BFS(START);	
		
	}
	
	public static void DFS(int start) {
		
		if(VISITED[start])
			return;
		
		VISITED[start] = true;
		
		System.out.print(start + " ");
		
		for(int i=0; i<ADJ[start].size(); i++)
			DFS(ADJ[start].get(i));
		
	}
	
	public static void BFS(int start) {
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		VISITED[start] = true;
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			System.out.print(num + " ");
			for(int i=0; i<ADJ[num].size(); i++) 
				if(!VISITED[ADJ[num].get(i)]) {
					queue.add(ADJ[num].get(i));
					VISITED[ADJ[num].get(i)] = true;
				}
		}
	}
	
	public static void initVisited() {
		VISITED = new boolean[N+1];
	}

}
