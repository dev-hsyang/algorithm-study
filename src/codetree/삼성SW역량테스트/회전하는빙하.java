package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2020 하반기 오후 2번 문제
 * 골드 3
 * @author didgs
 *
 */
public class 회전하는빙하 {

	static int N, Q, LEN, MAX_PART, SUM;
	static int[] DX = {0, 1, 0, -1};
	static int[] DY = {1, 0, -1, 0};
	static int[] LEVEL;
	static int[][] ICE;
	static boolean[][] VISITED;
	static ArrayList<int[]> PIVOTS;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		LEN = (int)Math.pow(2, N);
		ICE = new int[LEN][LEN];
		LEVEL = new int[Q];
		for(int i=0; i<ICE.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<ICE.length; j++)
				ICE[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<Q; i++)
			LEVEL[i] = Integer.parseInt(st.nextToken());
		
		simulate();
		printAns();
	}
	
	public static void simulate() {
		for(int turn=0; turn<Q; turn++) {
			rotateIceberg(LEVEL[turn]);
			iceBergMelts();
		}
		findPartitions();
		sumIce();
	}
	
	public static void rotateIceberg(int level) {
		if(level == 0)
			return;
		findPivotPoint(level);
		rotatePivots(level);
	}
	
	public static void findPivotPoint(int level) {
		PIVOTS = new ArrayList<>();
		int bias = (int)Math.pow(2, level);
		for(int i=0; i<LEN - bias + 1; i += bias)
			for(int j=0; j<LEN - bias + 1; j+= bias)
				PIVOTS.add(new int[] {i, j});
	}
	
	public static void rotatePivots(int level) {
		int bias = (int)Math.pow(2, level);
		int slice = (int)Math.pow(2, level - 1);
		int[][] temp = new int[bias][bias];
		int[][] candi = new int[bias][bias];
		int[][] sliced = new int[slice][slice];
		for(int[] pair : PIVOTS) {
			int px = pair[0];
			int py = pair[1];
			for(int i=0; i<bias; i++)
				for(int j=0; j<bias; j++)
					temp[i][j] = ICE[px + i][py + j];
			
			VISITED = new boolean[bias][bias];
			for(int i=0; i<bias - slice + 1; i += slice)
				for(int j=0; j<bias - slice + 1; j += slice) 				
					for(int k=i; k<i + slice; k++) 
						for(int l=j; l<j + slice; l++) 
							for(int d=0; d<4; d++) {
								int nx = k + slice * DX[d];
								int ny = l + slice * DY[d];
								if(nx >= 0 && nx < bias && ny >=0 && ny < bias && !VISITED[nx][ny]) {
									candi[nx][ny] = temp[k][l];
									VISITED[nx][ny] = true;
									break;
								}
							}
						
			for(int i=0; i<bias; i++)
				for(int j=0; j<bias; j++)
					ICE[i + px][j + py] = candi[i][j];
		}
	}
	
	public static void iceBergMelts() {
		int[][] candi = new int[LEN][LEN];
		for(int i=0; i<LEN; i++)
			for(int j=0; j<LEN; j++) 
				if(ICE[i][j] != 0) {
					int cnt = 0;
					for(int d=0; d<4; d++) {
						int nx = i + DX[d];
						int ny = j + DY[d];
						if(isInbound(nx, ny) && ICE[nx][ny] > 0)
							cnt++;
					}
					if(cnt < 3)
						candi[i][j] = ICE[i][j] - 1;
					else
						candi[i][j] = ICE[i][j];
				}
		
		for(int i=0; i<LEN; i++)
			for(int j=0; j<LEN; j++)
				ICE[i][j] = candi[i][j];
	}
	
	public static void findPartitions() {
		VISITED = new boolean[LEN][LEN];
		for(int i=0; i<LEN; i++)
			for(int j=0; j<LEN; j++)
				if(ICE[i][j] != 0 && !VISITED[i][j])
					bfs(new int[] {i, j});
	}
	
	public static void bfs(int[] cord) {
		VISITED[cord[0]][cord[1]] = true;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(cord);
		int cnt = 1;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i=0; i<4; i++) {
				int nx = now[0] + DX[i];
				int ny = now[1] + DY[i];
				if(isInbound(nx, ny) && ICE[nx][ny] != 0 && !VISITED[nx][ny]) {
					VISITED[nx][ny] = true;
					cnt += 1;
					queue.add(new int[] {nx, ny});
				}
			}
		}
		MAX_PART = Math.max(cnt, MAX_PART);
	}
	
	public static void sumIce() {
		for(int i=0; i<LEN; i++)
			for(int j=0; j<LEN; j++)
				if(ICE[i][j] > 0)
					SUM += ICE[i][j];
	}
	
	public static boolean isInbound(int x, int y) {
		return x>=0 && x<LEN && y>=0 && y<LEN;
	}
	
	public static void printAns() {
		System.out.println(SUM);
		System.out.println(MAX_PART);
	}
}
