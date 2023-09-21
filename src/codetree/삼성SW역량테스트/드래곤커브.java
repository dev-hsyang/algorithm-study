package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2018 상반기 오후 1번
 * 골드 4
 * @param args
 */
public class 드래곤커브 {
	
	static int N, X, Y, D, G, ANS;
	static int[] DX = {0, -1, 0, 1};
	static int[] DY = {1, 0, -1, 0};
	static int[][] SIMULATED = new int[100][100];
	static int[][] TEMP;
	static int[][] CANDI;
	static ArrayList<Dragon> ARR; // 가장 마지막 점 기록용
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for(int tc=0; tc<N; tc++) {
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			TEMP = new int[100][100];
			ARR = new ArrayList<Dragon>();
			ARR.add(new Dragon(X, Y));
			TEMP[X][Y] = 1;
			ARR.add(new Dragon(X + DX[D], Y + DY[D]));
			TEMP[X + DX[D]][Y + DY[D]] = 1;
			simulate();
		}
		
		countSquare();
		System.out.println(ANS);
	}
	
	public static void simulate() {
		for(int i=0; i<G; i++) { 
			rotateClockwise();
			connectDragon();
			copyDragon();
		}
	}
	
	public static void rotateClockwise() {
		CANDI = new int[100][100];
		for(int i=0; i<100; i++) 
			for(int j=0; j<100; j++) 
				CANDI[j][99 - i] = TEMP[i][j];
	}
	
	public static void connectDragon() {
		int x1 = ARR.get(ARR.size() - 1).x;
		int y1 = ARR.get(ARR.size() - 1).y;
		int x2 = y1;
		int y2 = 99 - x1;
		int dx = x1 - x2;
		int dy = y1 - y2;

		for(int i=0; i<100; i++)
			for(int j=0; j<100; j++)
				if(CANDI[i][j] == 1)
					TEMP[i + dx][j + dy] = 1;
				
		int lx = ARR.get(0).y + dx;
		int ly = 99 - ARR.get(0).x + dy;
		ARR.add(new Dragon(lx, ly));
	}
	
	public static void copyDragon() {
		for(int i=0; i<100; i++)
			for(int j=0; j<100; j++)
				if(TEMP[i][j] == 1)
					SIMULATED[i][j] = 1;
	}
	
	public static void countSquare() {			
		for(int i=0; i<100; i++) 
			for(int j=0; j<100; j++) 
				if(SIMULATED[i][j] == 1 && canGo(i + 1, j + 1)) 
					if(SIMULATED[i+1][j] == 1 && SIMULATED[i+1][j+1] == 1 && SIMULATED[i][j+1] == 1)
						ANS++;
	}
	
	public static boolean canGo(int x, int y) {
		return x>=0 && x<100 && y>=0 && y<100;
	}
}

class Dragon{
	
	public Dragon(int x, int y) {
		this.x = x;
		this.y = y;
	}
	int x, y;
}
