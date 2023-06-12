package swea;

import java.util.Scanner;

public class D2_달팽이숫자1954 {

	static int T;
	static int SIZE;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] CANDI;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int i=1; i<=T; i++) {
			SIZE = sc.nextInt();
			CANDI = new int[SIZE][SIZE];
			int num = 1;
			int dir = 0;
			int x = 0;
			int y = 0;
			CANDI[x][y] = num;
			
			while(true) {
				if(SIZE == 1)
					break;
				dir = nextDir(dir);
				x += dx[dir];
				y += dy[dir];
				
				if(isInbound(x, y) && CANDI[x][y]==0)
					CANDI[x][y] = ++num;
				else {
					x -= dx[dir];
					y -= dy[dir];
					dir += 1;
					if(CANDI[x + dx[nextDir(dir)]][y + dy[nextDir(dir)]] !=0)
						break;
				}
			}
			
			System.out.println("#" + i);
			for(int j=0; j<SIZE; j++) {
				for(int k=0; k<SIZE; k++)
					System.out.print(CANDI[j][k] + " ");
				System.out.println();
			}
		}
	}
	
	public static boolean isInbound(int x, int y) {
		
		if(x>=0 && y>=0 && x<SIZE && y<SIZE)
			return true;
		
		return false;
	}
	
	public static int nextDir(int dir) {
		if(dir>3)
			return 0;
		
		return dir;
	}

}
