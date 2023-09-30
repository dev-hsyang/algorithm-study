package baekjoon.구현;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 백준 17140
 * 삼성 SW역량테스트 2019 상반기 오후 1번 문제
 * 골드 4
 * @author didgs
 *
 */
public class 이차원배열과연산 {

	static int R, C, K, ANS;
	static int[][] CANDI;
	static int[][] SORT;
	static int[][] BOARD = new int[3][3];
	static boolean FOUND;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		K = sc.nextInt();
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				BOARD[i][j] = sc.nextInt();
		
		if(isInbound(R - 1, C - 1) && BOARD[R - 1][C - 1] == K)
			FOUND = true;
		while(!FOUND)
			simulate();

		System.out.println(ANS);
	}
	
	public static void simulate() {
		ANS++;
		if(BOARD.length >= BOARD[0].length)
			sortRow();
		else if(BOARD.length < BOARD[0].length)
			sortCol();
		if(BOARD.length > 100 || BOARD[0].length > 100)
			trim();
		if(isInbound(R - 1, C - 1) && BOARD[R - 1][C - 1] == K)
			FOUND = true;
		if(ANS > 100) {
			FOUND = true;
			ANS = -1;
		}
	}
	
	public static void sortRow() {
		int maxCol = 0;
		int row = BOARD.length;
		CANDI = new int[row][BOARD[0].length * 2];
		
		for(int i=0; i<row; i++) {
			SORT = new int[101][2];
			for(int j=1; j<101; j++)
				SORT[j][0] = j;
			
			for(int j=0; j<BOARD[0].length; j++) 
				if(BOARD[i][j] != 0)
					SORT[BOARD[i][j]][1] += 1;
			
			Arrays.sort(SORT, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[1] == o2[1])
						return o1[0] - o2[0];
					return o1[1] - o2[1];
				}
			});
			
			int idx = 0;
			for(int j=1; j<101; j++)
				if(SORT[j][1] > 0) {
					CANDI[i][idx++] = SORT[j][0];
					CANDI[i][idx++] = SORT[j][1];
				}
			maxCol = Math.max(maxCol, idx);
		}
		
		BOARD = new int[row][maxCol];
		for(int i=0; i<row; i++)
			for(int j=0; j<maxCol; j++)
				BOARD[i][j] = CANDI[i][j];
	}
	
	public static void sortCol() {
		int maxRow = 0;
		int col = BOARD[0].length;
		CANDI = new int[BOARD.length * 2][col];
		
		for(int i=0; i<col; i++) {
			SORT = new int[101][2];
			for(int j=1; j<101; j++)
				SORT[j][0] = j;
			
			for(int j=0; j<BOARD.length; j++)
				if(BOARD[j][i] != 0)
					SORT[BOARD[j][i]][1] += 1;
			
			Arrays.sort(SORT, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[1] == o2[1])
						return o1[0] - o2[0];
					return o1[1] - o2[1];
				}
			});
			
			int idx = 0;
			for(int j=1; j<101; j++)
				if(SORT[j][1] > 0) {
					CANDI[idx++][i] = SORT[j][0];
					CANDI[idx++][i] = SORT[j][1];
				}
			maxRow = Math.max(maxRow, idx);
		}
		
		BOARD = new int[maxRow][col];
		for(int i=0; i<maxRow; i++)
			for(int j=0; j<col; j++)
				BOARD[i][j] = CANDI[i][j];
	}
	
	public static void trim() {
		if(BOARD.length > 100) {
			CANDI = new int[100][BOARD[0].length];
			for(int i=0; i<100; i++)
				for(int j=0; j<BOARD[0].length; j++)
					CANDI[i][j] = BOARD[i][j];
			BOARD = new int[100][CANDI[0].length];
			for(int i=0; i<100; i++)
				for(int j=0; j<BOARD[0].length; j++)
					BOARD[i][j] = CANDI[i][j];
		}else if(BOARD[0].length > 100) {
			CANDI = new int[BOARD.length][100];
			for(int i=0; i<BOARD.length; i++)
				for(int j=0; j<100; j++)
					CANDI[i][j] = BOARD[i][j];
			BOARD = new int[CANDI.length][100];
			for(int i=0; i<BOARD.length; i++)
				for(int j=0; j<100; j++)
					BOARD[i][j] = CANDI[i][j];
		}
	}
	
	public static boolean isInbound(int x, int y) {
		return x>=0 && x<BOARD.length && y>=0 && y<BOARD[0].length;
	}
}
