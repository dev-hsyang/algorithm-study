package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2022 하반기 오전 1번 문제
 * 골드 2
 * @author didgs
 *
 */
public class 싸움땅_2 {
	
	static int N, M, K, ANS;
	static int[] DX = {-1, 0, 1, 0};
	static int[] DY = {0, 1, 0, -1};
	static int[] POINTS;
	static int[][] PLAYERMAP;
	static PriorityQueue<Integer>[][] GUNMAP;
	
	static ArrayList<Info2022> PLAYERS = new ArrayList<Info2022>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		GUNMAP = new PriorityQueue[N + 1][N + 1];
		PLAYERMAP = new int[N + 1][N + 1];
		POINTS = new int[M];
		for(int i=1; i<N+1; i++)
			for(int j=1; j<N+1; j++)
				GUNMAP[i][j] = new PriorityQueue<Integer>(Collections.reverseOrder());
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n > 0)
					GUNMAP[i][j].add(n);
			}
		}
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			PLAYERS.add(new Info2022(x, y, d, s));
			PLAYERMAP[x][y] = i;
		}
		
		simulate();
		printAns();
	}
	
	public static void simulate() {
		for(int i=0; i<K; i++) 
			playerMoves();
	}
	
	public static void printAns() {
		for(int i : POINTS)
			System.out.print(i + " ");
	}
	
	public static void playerMoves() {
		for(int i=0; i<PLAYERS.size(); i++) {
			int nx = PLAYERS.get(i).x + DX[PLAYERS.get(i).dir];
			int ny = PLAYERS.get(i).y + DY[PLAYERS.get(i).dir];
			if(!canGo(nx, ny)) {
				PLAYERS.get(i).reverse();
				nx = PLAYERS.get(i).x + DX[PLAYERS.get(i).dir];
				ny = PLAYERS.get(i).y + DY[PLAYERS.get(i).dir];
			}
			
			if(PLAYERMAP[nx][ny] == 0) {
				pickGun(i, nx, ny);
				PLAYERMAP[PLAYERS.get(i).x][PLAYERS.get(i).y] = 0;
				PLAYERMAP[nx][ny] = i + 1;
				PLAYERS.get(i).x = nx;
				PLAYERS.get(i).y = ny;
			}else {
				fight(i, nx, ny);
			}
		}
	}
	
	public static void pickGun(int player, int x, int y) {
		if(GUNMAP[x][y].size() == 0)
			return;
		
		int playerGun = PLAYERS.get(player).gun;
		int droppedGun = GUNMAP[x][y].peek();
		if(playerGun == 0) {
			PLAYERS.get(player).gun = GUNMAP[x][y].poll();
		}else if(droppedGun > playerGun) {
			PLAYERS.get(player).gun = GUNMAP[x][y].poll();
			GUNMAP[x][y].add(playerGun);
		}
	}
	
	public static void fight(int player, int x, int y) {
		int t = PLAYERMAP[x][y] - 1;
		int movedPlayerPoint = PLAYERS.get(player).stat + PLAYERS.get(player).gun;
		int existPlayerPoint = PLAYERS.get(PLAYERMAP[x][y] - 1).stat + PLAYERS.get(PLAYERMAP[x][y] - 1).gun;
		int winnerIdx = 0 ;
		int loserIdx = 0;
		if(movedPlayerPoint > existPlayerPoint) {
			winnerIdx = player;
			loserIdx = PLAYERMAP[x][y] - 1;
		}else if(movedPlayerPoint < existPlayerPoint) {
			winnerIdx = PLAYERMAP[x][y] - 1;
			loserIdx = player;
		}else if(movedPlayerPoint == existPlayerPoint) {
			if(PLAYERS.get(player).stat > PLAYERS.get(PLAYERMAP[x][y] - 1).stat) {
				winnerIdx = player;
				loserIdx = PLAYERMAP[x][y] - 1;
			}else {
				winnerIdx = PLAYERMAP[x][y] - 1;
				loserIdx = player;
			}
		}
		PLAYERMAP[PLAYERS.get(player).x][PLAYERS.get(player).y] = 0;
		PLAYERS.get(player).x = x;
		PLAYERS.get(player).y = y;
		
		POINTS[winnerIdx] += Math.abs(movedPlayerPoint - existPlayerPoint);
		
		// 진 플레이어는 본인이 가진 총을 내려놓습니다.
		if(PLAYERS.get(loserIdx).gun > 0) {
			GUNMAP[x][y].add(PLAYERS.get(loserIdx).gun);
			PLAYERS.get(loserIdx).gun = 0;
		}

		// 진 플레이어가 이동합니다.
		for(int i=0; i<4; i++) {
			int nx = x + DX[PLAYERS.get(loserIdx).dir];
			int ny = y + DY[PLAYERS.get(loserIdx).dir];
			if(canGo(nx ,ny) && PLAYERMAP[nx][ny] == 0) {
				pickGun(loserIdx, nx, ny);
				PLAYERMAP[nx][ny] = loserIdx + 1;
				PLAYERS.get(loserIdx).x = nx;
				PLAYERS.get(loserIdx).y = ny;
				break;
			}
			PLAYERS.get(loserIdx).rotateClock();
		}
		
		// 이긴 플레이어는 승리한 칸에 떨어져 있는 총들과 원래 들고 있는 총 중 높은 총을 획득하고, 나머지는 내려놓습니다.
		PLAYERMAP[PLAYERS.get(winnerIdx).x][PLAYERS.get(winnerIdx).y] = 0;
		PLAYERMAP[x][y] = winnerIdx + 1;
		PLAYERS.get(winnerIdx).x = x;
		PLAYERS.get(winnerIdx).y = y;
		pickGun(winnerIdx, x, y);
	}
	
	public static boolean canGo(int x, int y) {
		return x>=1 && x<=N && y>=1 && y<=N;
	}
}


class Info2022{
	
	public Info2022(int x, int y, int dir, int stat) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.stat = stat;
	}
	
	int x;
	int y;
	int dir;
	int stat;
	int gun;
	
	public void reverse() {
		if(this.dir == 0)
			dir = 2;
		else if(this.dir == 1)
			dir = 3;
		else if(this.dir == 2)
			dir = 0;
		else if(this.dir == 3)
			dir = 1;
	}
	
	public void rotateClock() {
		this.dir += 1;
		if(dir == 4)
			dir = 0;
	}
}
