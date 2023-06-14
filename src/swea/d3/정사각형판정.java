package swea.d3;

import java.util.Scanner;

public class 정사각형판정 {

	static int T, N;
	static String ANS;
	static String[][] BOARD;
	static boolean SEARCHED;
	static boolean[][] VISITED;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int test_case = 1; test_case<=T; test_case++) {
			N = sc.nextInt();
			BOARD = new String[N][N];
			VISITED = new boolean[N][N];
			SEARCHED = false;
			
			for(int i=0; i<N; i++) {
				String[] s = sc.next().split("");
				for(int j=0; j<N; j++)
					BOARD[i][j] = s[j];
			}
			
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					if(BOARD[i][j].equals("#") && VISITED[i][j] == false)
						isSquare(i, j);
			
			System.out.println("#" + test_case + " " + ANS);
		} 
	}
	
	public static void isSquare(int x, int y) {
		if(!SEARCHED) {
			int length = 1;
			for(int i=1; i<N; i++) {
				if(isInbound(x, y+i)) {
					if(BOARD[x][y+i].equals("#"))
						length += 1;
					if(BOARD[x][y+i].equals("."))
						break;
				}
			}
			
			for(int i=0; i<length; i++) {
				for(int j=0; j<length; j++) {
					if(BOARD[x+i][y+j].equals("#"))
						VISITED[x+i][y+j] = true;
					if(BOARD[x+i][y+j].equals(".")) {
						ANS = "no";
						return;
					}
				}
			}
			ANS = "yes";
			SEARCHED = true;
		}else if(SEARCHED)
			ANS = "no";
	}
	
	public static boolean isInbound(int x, int y) {
		if(x>=0 && x<N && y>=0 && y<N)
			return true;
		return false;
	}
}
