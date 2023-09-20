package baekjoon.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 7562
 * 실버 1
 * @author didgs
 *
 */
public class 나이트의이동 {

	public static int TC, L, ANS;
	public static int[] DX = {-2, -1, 1, 2, 2, 1, -1, -2};
	public static int[] DY = {1, 2, 2, 1, -1, -2, -2, -1};
	public static int[][] BOARD;
	public static boolean[][] VISITED;
	public static Chess NIGHT, GOAL;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());
		
		for(int tc=0; tc<TC; tc++) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			BOARD = new int[L][L];
			VISITED = new boolean[L][L];
			ANS = 0;
			st = new StringTokenizer(br.readLine());
			NIGHT = new Chess(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			st = new StringTokenizer(br.readLine());
			GOAL = new Chess(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			
			bfs(NIGHT);
			
			bw.append(ANS + "\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static void bfs(Chess cord) {
		Queue<Chess> queue = new LinkedList<>();
		VISITED[cord.x][cord.y] = true;
		queue.add(cord);
		while(!queue.isEmpty()) {
			Chess now = queue.poll();
			for(int i=0; i<8; i++) {
				int nx = now.x + DX[i];
				int ny = now.y + DY[i];
				if(canGo(nx, ny) && !VISITED[nx][ny]) {
					VISITED[nx][ny] = true;
					queue.add(new Chess(nx, ny, now.depth + 1));
					if(nx == GOAL.x && ny == GOAL.y) {
						ANS = now.depth + 1;
						break;
					}
				}
			}
		}
	}
	
	public static boolean canGo(int x, int y) {
		return x>=0 && x<L && y>=0 && y<L;
	}

}

class Chess{
	public Chess(int x, int y, int depth) {
		this.x = x;
		this.y = y;
		this.depth = depth;
	}
	int x, y, depth;
}
