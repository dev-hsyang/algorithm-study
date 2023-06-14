package algorithm_coding._05_2_너비우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2178
// 실버 1
public class 미로탐색하기 {

	static int N, M;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] MAP;
	static int[][] DEPTHMAP;
	static boolean[][] VISITED;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new int[N][M];
		DEPTHMAP = new int[N][M];
		VISITED = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String[] s = st.nextToken().split("");
			for(int j=0; j<M; j++) 
				MAP[i][j] = Integer.parseInt(s[j]);		
		}
		
		BFS(0, 0);
		
		System.out.println(DEPTHMAP[N-1][M-1]);
	}
	
	public static void BFS(int x, int y) {
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		VISITED[x][y] = true;
		DEPTHMAP[x][y] = 1;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i=0; i<4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if(isInbound(nx, ny) && !VISITED[nx][ny] && MAP[nx][ny]==1) {
					int depth = DEPTHMAP[now[0]][now[1]] + 1;
					VISITED[nx][ny] = true;
					DEPTHMAP[nx][ny] = depth;
					queue.add(new int[] {nx, ny});
				}
			}
		}
	}
	
	public static boolean isInbound(int x, int y) {
		if(x>=0 && x<N && y>=0 && y<M)
			return true;
		return false;
	}
}

