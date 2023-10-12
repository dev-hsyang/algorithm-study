package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2023 상반기 오후 1번 문제
 * 골드 3
 * @author didgs
 *
 */
public class 메이즈러너 {

	static int N, M, K, MIN_LEN, ANS;
	static int[] DX = {-1, 1, 0, 0};
	static int[] DY = {0, 0, -1, 1};
	static int[] EXIT;
	static int[] SQUARE_PAIR;
	static int[][] MAZE;
	static Runner2023[] RUNNERS;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		MAZE = new int[N + 1][N + 1];
		RUNNERS = new Runner2023[M + 1];
		EXIT = new int[2];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++)
				MAZE[i][j] = Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			RUNNERS[i] = new Runner2023(x, y, false);
		}
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		EXIT[0] = x;
		EXIT[1] = y;
		
		simulate();
		printAns();
	}
	
	public static void simulate() {
		for(int i=0; i<K; i++) {
			runnerRuns();
			if(allEscaped())
				break;
			mazeRotates();
		}
	}
	
	public static void runnerRuns() {
		for(int runnerIdx=1; runnerIdx<=M; runnerIdx++) {
			Runner2023 r = RUNNERS[runnerIdx];
			if(r.escape)
				continue;
			int minDist = getMinDist(r.x, r.y, EXIT[0], EXIT[1]);
			for(int i=0; i<4; i++) {
				int nx = r.x + DX[i];
				int ny = r.y + DY[i];
				if(canGo(nx, ny) && MAZE[nx][ny] == 0 && getMinDist(nx, ny, EXIT[0], EXIT[1]) < minDist) {
					r.x = nx;
					r.y = ny;
					ANS += 1;
					break;
				}
			}
			if(r.x == EXIT[0] && r.y == EXIT[1]) 
				r.escape = true;
		}
	}
	
	public static void mazeRotates() {
		findMinSquare();
		rotateWall();
		rotateExit();
		rotateRunners();
	}
	
	public static void findMinSquare() {
		SQUARE_PAIR = new int[2];
		MIN_LEN = 0;
		for(int len = 2; len<=N; len++) { // 한변의 길이가 2인 정사각형부터 bruteforce하게 모든 정사각형 탐색
			for(int i=1; i<=N-len+1; i++) {
				for(int j=1; j<=N-len+1; j++) {
					int x1 = i;
					int y1 = j;
					int x2 = x1 + len - 1;
					int y2 = y1 + len - 1;
					// 해당 정사각형 범위 내에 출구가 있는지 확인
					if(!(EXIT[0] >= x1 && EXIT[0] <= x2 && EXIT[1] >= y1 && EXIT[1] <= y2))
						continue;
					for(int idx = 1; idx<=M; idx++) {
						Runner2023 r = RUNNERS[idx];
						// Runner 가 해당 정사각형 범위 내에 있고, 아직 탈출하지 않았는지 확인
						if(!r.escape && r.x >= x1 && r.x <= x2 && r.y >= y1 && r.y <= y2) {
							SQUARE_PAIR[0] = i;
							SQUARE_PAIR[1] = j;
							MIN_LEN = len;
							return;
						}
					}	
				}
			}
		}
	}
	
	public static void rotateWall() {
		int[][] candiMaze = new int[MIN_LEN][MIN_LEN];
		int[][] tempMaze = new int[MIN_LEN][MIN_LEN];
		for(int i=0; i<MIN_LEN; i++)
			for(int j=0; j<MIN_LEN; j++)
				tempMaze[i][j] = MAZE[SQUARE_PAIR[0] + i][SQUARE_PAIR[1] + j];
		for(int i=0; i<MIN_LEN; i++)
			for(int j=0; j<MIN_LEN; j++)
				candiMaze[j][MIN_LEN - i - 1] = tempMaze[i][j];
		for(int i=0; i<MIN_LEN; i++)
			for(int j=0; j<MIN_LEN; j++) {
				int num = candiMaze[i][j] - 1;
				if(num < 0)
					num = 0;
				MAZE[SQUARE_PAIR[0] + i][SQUARE_PAIR[1] + j] = num;
			}
	}
	
	public static void rotateExit() {
		int[] candiExit = new int[2];
		int[] tempExit = new int[2];
		tempExit[0] = EXIT[0] - SQUARE_PAIR[0];
		tempExit[1] = EXIT[1] - SQUARE_PAIR[1];
		candiExit[0] = tempExit[1];
		candiExit[1] = MIN_LEN - tempExit[0] - 1;
		EXIT[0] = candiExit[0] + SQUARE_PAIR[0];
		EXIT[1] = candiExit[1] + SQUARE_PAIR[1];
	}
	
	public static void rotateRunners() {
		for(int i=1; i<=M; i++) {
			Runner2023 r = RUNNERS[i];
			if(r.x >= SQUARE_PAIR[0] && r.x < SQUARE_PAIR[0] + MIN_LEN && r.y >= SQUARE_PAIR[1] && r.y < SQUARE_PAIR[1] + MIN_LEN) {
				int tx = r.x - SQUARE_PAIR[0];
				int ty = r.y - SQUARE_PAIR[1];
				int nx = ty;
				int ny = MIN_LEN - tx - 1;
				r.x = nx + SQUARE_PAIR[0];
				r.y = ny + SQUARE_PAIR[1];
			}
		}
	}
	
	public static boolean allEscaped() {
		for(int i=1; i<=M; i++)
			if(!RUNNERS[i].escape)
				return false;
		return true;
	}
	
	public static int getMinDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	public static boolean canGo(int x, int y) {
		return x>=1 && x<=N && y>=1 && y<=N;
	}
	
	public static void printAns() {
		System.out.println(ANS);
		for(int i : EXIT)
			System.out.print(i + " ");
	}
}

class Runner2023{
	
	public Runner2023(int x, int y, boolean escape) {
		this.x = x;
		this.y = y;
		this.escape = escape;
	}
	
	int x;
	int y;
	boolean escape;
}