package baekjoon;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 2667
 * BFS DFS 필수문제
 * 실버 1
 * @author didgs
 *
 */
public class 단지번호붙이기 {

	static int N, CNT;
	static PriorityQueue<Integer> ANS = new PriorityQueue<>();
	static int[] DX = {0, 1, 0, -1};
	static int[] DY = {1, 0, -1, 0};
	static int[][] MAP;
	static boolean[][] VISITED;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		MAP = new int[N][N];
		VISITED = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			String[] s = sc.next().split("");
			for(int j=0; j<N; j++) {
				MAP[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		for(int i=0; i<N; i++) 
			for(int j=0; j<N; j++) 
				if(MAP[i][j] == 1 && !VISITED[i][j]) {
					CNT += 1;
					int total = bfs(new int[] {i, j});
					ANS.add(total);
				}
			
		System.out.println(CNT);
		while(!ANS.isEmpty())
			System.out.println(ANS.poll());
	}
	
	public static int bfs(int[] pair) {
		Queue<int[]> queue = new LinkedList<>();
		int total = 1;
		queue.add(pair);
		VISITED[pair[0]][pair[1]] = true;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i=0; i<4; i++) {
				int nx = now[0] + DX[i];
				int ny = now[1] + DY[i];
				if(canGo(nx, ny))
					if(!VISITED[nx][ny] && MAP[nx][ny] == 1) {
						queue.add(new int[] {nx, ny});
						VISITED[nx][ny] = true;
						total += 1;
					}
			}
		}
		return total;
	}
	
	public static boolean canGo(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}

}
