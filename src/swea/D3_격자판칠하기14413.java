package swea;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class D3_격자판칠하기14413 {

	static int T, N, M;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static String ANS;
	static String[][] BOARD;
	static boolean[][] VISITED;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			BOARD = new String[N][M];
			VISITED = new boolean[N][M];
			boolean search = false;
			
			for(int i=0; i<N; i++) {
				String[] s = sc.next().split("");
				for(int j=0; j<M; j++)
					BOARD[i][j] = s[j];
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(!BOARD[i][j].equals("?")) {
						BFS(i, j);
						search = true;
						break;
					}
				}
				if(search)
					break;
			}
			
			if(!search)
				ANS = "possible";
			
			System.out.println("#" + test_case + " " + ANS);
		}
	}
	
	public static void BFS(int x, int y) { // 탐색 시작칸은 "?" 이면 안된다. 
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		VISITED[x][y] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i=0; i<4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if(inBound(nx, ny)) {
					if(BOARD[nx][ny].equals(BOARD[now[0]][now[1]])) {
						ANS = "impossible";
						return;
					}
					if(BOARD[nx][ny].equals("?")) {
						paint(now[0], now[1], nx, ny);
					}
					if(!VISITED[nx][ny]) {
						queue.add(new int[] {nx, ny});
						VISITED[nx][ny] = true;
					}
				}
			}
		}
		ANS = "possible";
	}
	
	public static void paint(int x, int y, int nx, int ny) {
		if(BOARD[x][y].equals("#"))
			BOARD[nx][ny] = ".";
		if(BOARD[x][y].equals("."))
			BOARD[nx][ny] = "#";
	}
	
	public static boolean inBound(int x, int y) {
		if(x>=0 && x<N && y>=0 && y<M)
			return true;
		return false;
	}

}
