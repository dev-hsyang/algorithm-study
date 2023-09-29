package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17143
 * 삼성 SW역량테스트 2019 상반기 오전 2번 문제
 * 골드 1
 * @author didgs
 *
 */
public class 낚시왕 {
	
	static int N, M, K, ANS;
	static int[] DX = {0, -1, 1, 0, 0};
	static int[] DY = {0, 0, 0, 1, -1};
	static int[][] MAP;
	static int[][] CANDI;
	static boolean[] ALIVE;
	static Shark17143[] SHARKS;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		MAP = new int[N][M];
		SHARKS = new Shark17143[K + 1];
		ALIVE = new boolean[K + 1];
		for(int i=1; i<K + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			SHARKS[i] = new Shark17143(x, y, s, b, d);
			ALIVE[i] = true;
			MAP[x][y] = i;
		}
		
		simulate();
		
		System.out.println(ANS);
	}
	
	public static void simulate() {
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(MAP[j][i] != 0) {					
					ANS += SHARKS[MAP[j][i]].size;
					ALIVE[MAP[j][i]] = false;
					break;
				}
			}
			move();
		}
	}
	
	public static void move() {
		CANDI = new int[N][M];
		for(int i=1; i<K+1; i++) {
			if(ALIVE[i]) {
				Shark17143 now = SHARKS[i];
				int nx = now.x;
				int ny = now.y;
				for(int t=0; t<now.dist; t++) {
					if(canGo(nx + DX[now.dir], ny + DY[now.dir])) {
						nx += DX[now.dir];
						ny += DY[now.dir];
					}else {
						now.reverse();
						nx += DX[now.dir];
						ny += DY[now.dir];
					}
				}
				
				SHARKS[i].x = nx;
				SHARKS[i].y = ny;
				
				if(CANDI[nx][ny] != 0) {
					if(SHARKS[CANDI[nx][ny]].size > SHARKS[i].size) {
						ALIVE[i] = false;
					}else {
						ALIVE[CANDI[nx][ny]] = false;
						CANDI[nx][ny] = i;
					}
				}else
					CANDI[nx][ny] = i;
			}
		}
		
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				MAP[i][j] = CANDI[i][j];
	}
	
	public static boolean canGo(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}

}

class Shark17143{
	
	public Shark17143(int x, int y, int dist, int size, int dir) {
		this.x = x;
		this.y = y;
		this.dist = dist;
		this.size = size;
		this.dir = dir;
	}
	
	int x;
	int y;
	int dist;
	int size;
	int dir;

	public void reverse() {
		if(dir == 1)
			dir = 2;
		else if(dir == 2)
			dir = 1;
		else if(dir == 3)
			dir = 4;
		else if(dir == 4)
			dir = 3;
	}
}
