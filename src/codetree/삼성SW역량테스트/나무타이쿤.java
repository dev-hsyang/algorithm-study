package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2021 상반기 오후 1번 문제
 * 골드 5
 * @author didgs
 *
 */
public class 나무타이쿤 {

	static int N, M, D, P, ANS;
	static int[] DX = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] DY = {0, 1, 1, 0, -1, -1, -1, 0, 1};
	static int[][] TREE;
	static boolean[][] ING;
	static boolean[][] CANDI;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		TREE = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				TREE[i][j] = Integer.parseInt(st.nextToken());
		}
		initIng();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			simulate();
		}
		
		sum();

		System.out.println(ANS);
	}
	
	public static void simulate() {
		move();
		inject();
		growDiagnol();
		buyIng();
	}
	
	public static void move() {
		CANDI = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(ING[i][j]) {
					int nx = i;
					int ny = j;
					for(int k=0; k<P; k++) {
						nx = getNextIndex(nx + DX[D]);
						ny = getNextIndex(ny + DY[D]);		
					}
					CANDI[nx][ny] = true;
				}
			}
		}
	}
	
	public static void inject() {
		for(int i=0; i<N; i++) 
			for(int j=0; j<N; j++)
				if(CANDI[i][j])
					TREE[i][j] += 1;
	}
	
	public static void growDiagnol() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(CANDI[i][j]) {
					int d = 0;
					for(int k=0; k<4; k++) {
						d += 2;
						int nx = i + DX[d];
						int ny = j + DY[d];
						if(canGo(nx, ny) && TREE[nx][ny] >= 1)
							TREE[i][j] += 1;
					}
				}
			}
		}
	}
	
	public static void buyIng() {
		ING = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!CANDI[i][j] && TREE[i][j] >= 2) {
					TREE[i][j] -= 2;
					ING[i][j] = true;
				}
			}
		}
	}
	
	public static void initIng() {
		ING = new boolean[N][N];
		ING[N - 2][0] = true;
		ING[N - 2][1] = true;
		ING[N - 1][0] = true;
		ING[N - 1][1] = true;
	}
	
	public static int getNextIndex(int index) {
		if(index == N)
			return 0;
		else if(index < 0)
			return N - 1;
		else
			return index;
	}
	
	public static void sum() {
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				ANS += TREE[i][j];
	}
	
	public static boolean canGo(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}

}
