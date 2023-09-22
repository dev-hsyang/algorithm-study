package baekjoon.구현;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 16234
 * 삼성 SW 역량테스트 2018 하반기 오전 2번 문제
 * 골드 4
 * @author didgs
 *
 */
public class 인구이동 {

	static int N, L, R, PART, ANS;
	static int[] DX = {0, 1, 0, -1};
	static int[] DY = {1, 0, -1, 0};
	static int[][] PLATE;
	static int[][] CANDI;
	static boolean CAN_MERGE;
	static boolean[][] VISITED;
	static ArrayList<Egg>[] PARTITION;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		PLATE = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				PLATE[i][j] = Integer.parseInt(st.nextToken());
		}
		
		simulate();
		System.out.println(ANS);
	}
	
	public static void simulate() {
		CAN_MERGE = true;
		while(CAN_MERGE) {
			combine();
			merge();
		}
	}
	
	public static void combine() {
		VISITED = new boolean[N][N];
		CANDI = new int[N][N];
		PART = 0;
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				if(!VISITED[i][j])
					combinePart(new Egg(i, j));
	}
	
	public static void combinePart(Egg egg) {
		PART++;
		Queue<Egg> queue = new LinkedList<>();
		VISITED[egg.x][egg.y] = true;
		CAN_MERGE = false;
		CANDI[egg.x][egg.y] = PART;
		queue.add(egg);
		while(!queue.isEmpty()) {
			Egg now = queue.poll();
			for(int i=0; i<4; i++) {
				int nx = now.x + DX[i];
				int ny = now.y + DY[i];
				if(canGo(nx, ny) && inRange(Math.abs(PLATE[now.x][now.y] - PLATE[nx][ny])) && !VISITED[nx][ny]) {
					VISITED[nx][ny] = true;
					CANDI[nx][ny] = PART;
					queue.add(new Egg(nx, ny));
				}
			}
		}
	}
	
	public static void merge() {
		PARTITION = new ArrayList[PART + 1];
		for(int i=1; i<PART + 1; i++)
			PARTITION[i] = new ArrayList<Egg>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int part = CANDI[i][j];
				PARTITION[part].add(new Egg(i, j));
			}
		}
		
		CAN_MERGE = false;
		for(int i=1; i<PARTITION.length; i++) {
			if(PARTITION[i].size() > 1)
				CAN_MERGE = true;
			int sum = 0;
			int merged = 0;
			for(Egg e : PARTITION[i])
				sum += PLATE[e.x][e.y];
			merged = sum / PARTITION[i].size();
			for(Egg e : PARTITION[i])
				PLATE[e.x][e.y] = merged;
		}
		
		if(CAN_MERGE)
			ANS++;
	}
	
	
	public static boolean canGo(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	
	public static boolean inRange(int delta) {
		return delta >= L && delta <= R;
	}

}

class Egg{
	
	public Egg(int x, int y) {
		this.x = x;
		this.y = y;
	}
	int x, y;
}
