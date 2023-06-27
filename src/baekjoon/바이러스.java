package baekjoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 2606
 * DFS BFS 필수문제
 * 실버 3
 * @author didgs
 *
 */
public class 바이러스 {

	static int NODE, LINK, ANS;
	static boolean[] VISITED;
	static ArrayList[] ADJ;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		NODE = sc.nextInt();
		LINK = sc.nextInt();
		VISITED = new boolean[NODE + 1];
		ADJ = new ArrayList[NODE + 1];
		
		for(int i=1; i<NODE+1; i++)
			ADJ[i] = new ArrayList<Integer>();
		
		for(int i=0; i<LINK; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			ADJ[a].add(b);
			ADJ[b].add(a);
		}
		
		bfs(1);
		
		System.out.println(ANS);
	}
	
	public static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		VISITED[start] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			for(int i=0; i<ADJ[node].size(); i++) {
				int next = (int)ADJ[node].get(i);
				if(!VISITED[next]) {
					VISITED[next] = true;
					queue.add(next);
					ANS += 1;
				}
			}
		}
	}
}
