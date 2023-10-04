package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 17822
 * 삼성 SW 역량테스트 2019 하반기 오후 1번 문제
 * 골드 2
 * @author didgs
 *
 */
public class 원판돌리기 {

	static int N, M, Q, ANS;
	static int[][] BOARD;
	static boolean ERASED;
	static ArrayList<int[]> QUERY = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		BOARD = new int[N + 1][M];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++)
				BOARD[i][j] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			QUERY.add(new int[] {x, d, k});
		}
		
		simulate();

		System.out.println(ANS);
	}
	
	public static void simulate() {
		for(int[] q : QUERY) {
			rotateBoard(q[0], q[1], q[2]);
			eliminateAdjacent();			
		}
		sum();
	}
	
	public static void rotateBoard(int x, int d, int k) {
		for(int i=1; i<=N; i++) 
			if(i % x == 0) {
				if(d == 0)
					rotateClockwise(i, k);
				else if(d == 1)
					rotateCounterClockwise(i, k);
			}
	}
	
	public static void rotateClockwise(int index, int delta) {
		int[] temp = new int[M];
		for(int i=0; i<M; i++)
			temp[i] = BOARD[index][i];
		
		for(int i=0; i<M; i++) {
			if(i + delta < M)
				BOARD[index][i + delta] = temp[i];
			else
				BOARD[index][i + delta - M] = temp[i];
		}
	}
	
	public static void rotateCounterClockwise(int index, int delta) {
		int[] temp = new int[M];
		for(int i=0; i<M; i++)
			temp[i] = BOARD[index][i];
		
		for(int i=M-1; i>=0; i--) {
			if(i - delta >= 0)
				BOARD[index][i - delta] = temp[i];
			else
				BOARD[index][i - delta + M] = temp[i];
		}
	}
	
	public static void eliminateAdjacent() {
		int[][] newRow = new int[N + 1][M];
		int[][] newCol = new int[N + 1][M];
		ERASED = false;
		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				int t = BOARD[i][j];
				if(t != 0 && j + 1 < M  && BOARD[i][j + 1] == t) {
					ERASED = true;
					newRow[i][j] = 0;
					newRow[i][j + 1] = 0;
				}else if(t != 0 && j - 1 >= 0 && BOARD[i][j - 1] == t) {
					ERASED = true;
					newRow[i][j] = 0;
					newRow[i][j - 1] = 0;
				}else if(t != 0 && j + 1 >= M && BOARD[i][0] == t) {
					ERASED = true;
					newRow[i][j] = 0;
					newRow[i][0] = 0;
				}else if(t != 0 && j == 0 && BOARD[i][M - 1] == t) {
					ERASED = true;
					newRow[i][0] = 0;
					newRow[i][M - 1] = 0;
				}
				else
					newRow[i][j] = BOARD[i][j];
			}
		}
		
		for(int i=0; i<M; i++){
			for(int j=1; j<=N; j++) {
				int t = BOARD[j][i];
				if(t != 0 && j + 1 < N && BOARD[j + 1][i] == t) {
					ERASED = true;
					newCol[j][i] = 0;
					newCol[j + 1][i] = 0;
				}else if(t != 0 && j - 1 >= 0 && BOARD[j - 1][i] == t) {
					ERASED = true;
					newCol[j][i] = 0;
					newCol[j - 1][i] = 0;
				}else
					newCol[j][i] = BOARD[j][i];
			}
		}
		
		for(int i=1; i<=N; i++)
			for(int j=0; j<M; j++)
				if(newRow[i][j] == 0 || newCol[i][j] == 0)
					BOARD[i][j] = 0;
		
		if(!ERASED) 
			normalization();
	}
	
	public static void normalization() {
		double cnt = 0;
		double sum = 0;
		double avg = 0;
		for(int i=1; i<=N; i++)
			for(int j=0; j<M; j++)
				if(BOARD[i][j] != 0) {
					cnt++;
					sum += BOARD[i][j];
				}
		
		avg = (double) (sum / cnt);
		for(int i=1; i<=N; i++)
			for(int j=0; j<M; j++)
				if(BOARD[i][j] > avg && BOARD[i][j] != 0)
					BOARD[i][j] -= 1;
				else if(BOARD[i][j] < avg && BOARD[i][j] != 0)
					BOARD[i][j] += 1;
	}
	
	public static void sum() {
		for(int i=1; i<=N; i++)
			for(int j=0; j<M; j++)
				ANS += BOARD[i][j];
	}
}