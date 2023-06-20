package samsung_sw.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 골드 1
 * 삼성 SW 역량테스트 2015 하반기 2번 문제
 * @author didgs
 *
 */
public class 두개의사탕 {
	
	static int N, M;
	static int COUNT = Integer.MAX_VALUE;
	static int[] REDBALL;
	static int[] BLUEBALL;
	static int[] CANDIRED = new int[2];
	static int[] CANDIBLUE = new int[2];
	static int[] DX = {0, 1, 0, -1};
	static int[] DY = {1, 0, -1, 0};
	static String[][] MAP;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new String[N][M];

		for(int i=0; i<N ;i++) {
			st = new StringTokenizer(br.readLine());
			String[] s = st.nextToken().split("");
			for(int j=0; j<M; j++) {
				MAP[i][j] = s[j];
				if(MAP[i][j].equals("R"))
					REDBALL = new int[] {i, j};
				if(MAP[i][j].equals("B"))
					BLUEBALL = new int[] {i, j};
			}
		}

		
		backtrack(0);

		if(COUNT > 10)
			COUNT = -1;
		
		System.out.println(COUNT);
	}
	
	public static void backtrack(int count) {
		
		int[] nowRed = new int[] {REDBALL[0], REDBALL[1]};
		int[] nowBlue = new int[] {BLUEBALL[0], BLUEBALL[1]};
		
		if(count > 10)
			return;
		
		if(isExit(nowBlue[0], nowBlue[1]))
			return;
		
		if(isExit(nowRed[0], nowRed[1]) && count < COUNT) {
			COUNT = count;
			System.out.println("FOUND MIN! " + COUNT);
			return;
		}
		
		for(int i=0; i<4; i++) {
			
			if(i==0 || i==2) {
				if(isSameRow(nowRed[0], nowBlue[0])) {
					if((i==0 && nowRed[1]>nowBlue[1]) || (i==2 && nowRed[1]<nowBlue[1]))
						redFirst(nowRed, nowBlue, i);
					else
						blueFirst(nowRed, nowBlue, i);
				}else {
					move(nowRed, nowBlue, i);
				}
			}
			if(i==1 || i==3) {
				if(isSameCol(nowRed[1], nowBlue[1])) {
					if((i==1 && nowRed[0]>nowBlue[0]) || (i==3 && nowRed[0]<nowRed[1]))
						redFirst(nowRed, nowBlue, i);
					else
						blueFirst(nowRed, nowBlue, i);
				}else {
					move(nowRed, nowBlue, i);
				}
			}
			
			if(ballMoved(REDBALL, BLUEBALL, CANDIRED, CANDIBLUE)) {
				count += 1;
				REDBALL = new int[] {CANDIRED[0], CANDIRED[1]};
				BLUEBALL = new int[] {CANDIBLUE[0], CANDIBLUE[1]};
				if(count<10)
				System.out.println("RED: " + REDBALL[0] + ", " + REDBALL[1] + ", BLUE: " + BLUEBALL[0] + ", " + BLUEBALL[1] +", COUNT: " + count + ", DIR: " + i);
				
				backtrack(count);
				
				count -= 1;
				REDBALL = new int[] {nowRed[0], nowRed[1]};
				BLUEBALL = new int[] {nowBlue[0], nowBlue[1]};
			}
		}
	}
	
	public static void redFirst(int[] red, int[] blue, int dir) {
		
		CANDIRED[0] = red[0];
		CANDIRED[1] = red[1];
		CANDIBLUE[0] = blue[0];
		CANDIBLUE[1] = blue[1];
		// 빨간공 끝까지
		while(true) {
			int nrx = CANDIRED[0] + DX[dir];
			int nry = CANDIRED[1] + DY[dir];
			if(!isBlocked(nrx, nry)) {
				CANDIRED[0] = nrx;
				CANDIRED[1] = nry;
				
				if(isExit(nrx, nry))
					break;
			}else
				break;
		}
		// 파란공 끝까지
		while(true) {
			int nbx = CANDIBLUE[0] + DX[dir];
			int nby = CANDIBLUE[1] + DY[dir];
			if(!isBlocked(nbx, nby)) {
				if(isCandy(nbx, nby) && !isExit(CANDIRED[0], CANDIRED[1]))
					break;
				CANDIBLUE[0] = nbx;
				CANDIBLUE[1] = nby;
				
				if(isExit(nbx, nby))
					break;
			}else
				break;
		}
	}
	
	public static void blueFirst(int[] red, int[] blue, int dir) {
		
		CANDIRED[0] = red[0];
		CANDIRED[1] = red[1];
		CANDIBLUE[0] = blue[0];
		CANDIBLUE[1] = blue[1];
		// 파란공 끝까지
		while(true) {
			int nbx = CANDIBLUE[0] + DX[dir];
			int nby = CANDIBLUE[1] + DY[dir];
			if(!isBlocked(nbx, nby)) {
				CANDIBLUE[0] = nbx;
				CANDIBLUE[1] = nby;
				
				if(isExit(nbx, nby))
					break;
			}else
				break;
		}
		// 빨간공 끝까지
		while(true) {
			int nrx = CANDIRED[0] + DX[dir];
			int nry = CANDIRED[1] + DY[dir];
			if(!isBlocked(nrx, nry)) {
				if(isCandy(nrx, nry) && !isExit(CANDIBLUE[0], CANDIBLUE[1]))
					break;
				
				CANDIRED[0] = nrx;
				CANDIRED[1] = nry;
				
				if(isExit(nrx, nry))
					break;
			}else
				break;
		}
	}
	
	public static void move(int[] red, int[] blue, int dir) {
		
		CANDIRED[0] = red[0];
		CANDIRED[1] = red[1];
		CANDIBLUE[0] = blue[0];
		CANDIBLUE[1] = blue[1];
		// 빨간공 끝까지
		while(true) {
			int nrx = CANDIRED[0] + DX[dir];
			int nry = CANDIRED[1] + DY[dir];
			if(!isBlocked(nrx, nry)) {
				CANDIRED[0] = nrx;
				CANDIRED[1] = nry;
				
				if(isExit(nrx, nry))
					break;
			}else
				break;
		}
		//파란공 끝까지
		while(true) {
			int nbx = CANDIBLUE[0] + DX[dir];
			int nby = CANDIBLUE[1] + DY[dir];
			if(!isBlocked(nbx, nby)) {
				CANDIBLUE[0] = nbx;
				CANDIBLUE[1] = nby;
				
				if(isExit(nbx, nby)) 
					break;				
			}else
				break;
		}
	}
	
	public static boolean isBlocked(int x, int y) {
		
		if(x<0 || x>=N || y<0 || y>=M)
			return true;
		if(MAP[x][y].equals("#"))
			return true;
		return false;
	}
	
	public static boolean isCandy(int x, int y) {
		
		if((CANDIRED[0]==x && CANDIRED[1]==y) || (CANDIBLUE[0]==x && CANDIBLUE[1]==y))
			return true;
		return false;
	}
	
	public static boolean isExit(int x, int y) {
		
		if(MAP[x][y].equals("O"))
			return true;
		return false;
	}
	
	public static boolean isSameRow(int rx, int bx) {
		
		if(rx == bx)
			return true;
		return false;
	}
	
	public static boolean isSameCol(int ry, int by) {
		
		if(ry == by)
			return true;
		return false;
	}
	
	public static boolean ballMoved(int[] red, int[] blue, int[] candiRed, int[] candiBlue) {
		
		if(red[0]==candiRed[0] && red[1]==candiRed[1] && blue[0]==candiBlue[0] && blue[1]==candiBlue[1])
			return false;
		return true;
	}
}
