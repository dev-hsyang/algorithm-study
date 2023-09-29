package codetree.삼성SW역량테스트;

import java.util.Scanner;

/**
 * 삼성 SW 역량테스트 2019 상반기 오전 1번 문제
 * 골드 4
 * @author didgs
 *
 */
public class 시공의돌풍 {
	
	static int N, M, T, ANS;
	static int[] DX = {0, 1, 0, -1};
	static int[] DY = {1, 0, -1, 0};
	static int[][] ROOM;
	static int[][] CANDI;
	static Storm2019 Counterclock;
	static Storm2019 Clock;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		ROOM = new int[N][M];
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				ROOM[i][j] = sc.nextInt();
				
		findStorm();
		
		for(int i=0; i<T; i++)
			simulate();

		count();
		
		System.out.println(ANS);
	}
	
	public static void findStorm() {
		boolean find = false;
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if(ROOM[i][j] == -1 && !find) {
					Counterclock = new Storm2019(i, j);
					find = true;
				}
		
		for(int i=Counterclock.x; i<N; i++)
			for(int j=Counterclock.y; j<M; j++)
				if((i != Counterclock.x || j != Counterclock.y) && ROOM[i][j] == -1)
					Clock = new Storm2019(i, j);
	}
	
	public static void simulate() {
		spread();
		operateCounterClockwise();
		operateClockwise();
	}
	
	public static void operateCounterClockwise() {
		CANDI = new int[N][M];
		int row = Counterclock.x;
		int col = Counterclock.y;
		CANDI[row][col + 1] = 0;
		for(int i=col+2; i<M; i++)
			CANDI[row][i] = ROOM[row][i - 1];
		for(int i=row-1; i>=0; i--)
			CANDI[i][M - 1] = ROOM[i + 1][M - 1];
		for(int i=M-2; i>=0; i--)
			CANDI[0][i] = ROOM[0][i + 1];
		for(int i=1; i<=row; i++)
			CANDI[i][0] = ROOM[i - 1][0];
		
		CANDI[row][col] = -1;

		for(int i=col; i<M; i++)
			ROOM[row][i] = CANDI[row][i];
		for(int i=row; i>=0; i--)
			ROOM[i][M - 1] = CANDI[i][M - 1];
		for(int i=M-1; i>=0; i--)
			ROOM[0][i] = CANDI[0][i];
		for(int i=0; i<row; i++)
			ROOM[i][0] = CANDI[i][0];
	}
	
	public static void operateClockwise() {
		CANDI = new int[N][M];
		int row = Clock.x;
		int col = Clock.y;
		CANDI[row][col + 1] = 0;
		for(int i=col+2; i<M; i++)
			CANDI[row][i] = ROOM[row][i - 1];
		for(int i=row+1; i<N; i++)
			CANDI[i][M - 1] = ROOM[i - 1][M - 1];
		for(int i=M-2; i>=0; i--)
			CANDI[N - 1][i] = ROOM[N - 1][i + 1];
		for(int i=N-2; i>=row; i--)
			CANDI[i][0] = ROOM[i + 1][0];
		
		CANDI[row][col] = -1;
		
		for(int i=col; i<M; i++)
			ROOM[row][i] = CANDI[row][i];
		for(int i=row; i<N; i++)
			ROOM[i][M - 1] = CANDI[i][M - 1];
		for(int i=M-1; i>=0; i--)
			ROOM[N-1][i] = CANDI[N-1][i];
		for(int i=N-1; i>row; i--) 
			ROOM[i][0] = CANDI[i][0];
	}
	
	
	public static void spread() {
		CANDI = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int cnt = 0;
				for(int m=0; m<4; m++) {
					int nx = i + DX[m];
					int ny = j + DY[m];
					if(canGo(nx, ny)) {
						cnt++;
						CANDI[nx][ny] += ROOM[i][j] / 5;
					}
				}
				CANDI[i][j] -= (ROOM[i][j] / 5) * cnt;
			}
		}
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				ROOM[i][j] += CANDI[i][j];
	}
	
	public static boolean canGo(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M) && ROOM[x][y] != -1; 
	}
	
	public static void count() {
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if(ROOM[i][j] >= 0)
					ANS += ROOM[i][j];
	}
}

class Storm2019{
	public Storm2019(int x, int y) {
		this.x = x;
		this.y = y;
	}
	int x;
	int y;
}
