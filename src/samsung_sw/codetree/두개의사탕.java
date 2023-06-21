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
	static int[] DX = {0, 1, 0, -1};
	static int[] DY = {1, 0, -1, 0};
	static String[][] MAP;
	static Candy RED, BLUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new String[N][M];
		
		// 주어진 정보로 2차원 배열을 초기화
		// 빨간사탕과 파란사탕의 좌표 초기화
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String[] s = st.nextToken().split("");
			for(int j=0; j<M; j++) {
				MAP[i][j] = s[j];
				if(MAP[i][j].equals("R"))
					RED = new Candy(i, j);
				if(MAP[i][j].equals("B"))
					BLUE = new Candy(i, j);
			}
		}
		
		dfs(0);
		
		if(COUNT>10)
			COUNT = -1;
		
		System.out.println(COUNT);
	}
	
	// 백트래킹
	// 파란공이 구멍에 빠져있거나, 10회 이상 탐색할 경우 pruning 처리
	// 파란공이 구멍에 빠지지 않은 채로 빨간공이 구멍에 빠지면 답을 찾은 것
	public static void dfs(int count) {
		if(isExit(BLUE))
			return;
		if(isExit(RED)) {
			COUNT = Math.min(COUNT, count);
			return;
		}
		if(count>=10)
			return;
		
		for(int i=0; i<4; i++) {
			Candy nowRed = new Candy(RED.x, RED.y);
			Candy nowBlue = new Candy(BLUE.x, BLUE.y);
			
			move(i);
			dfs(count+1);
			
			RED = new Candy(nowRed.x, nowRed.y);
			BLUE = new Candy(nowBlue.x, nowBlue.y);
		}
	}

	// MAP 을 한 방향에 대해 완전히 기울이는 동작
	public static void move(int dir) {
		if(redFirst(dir)) {
			toEnd(RED, dir);
			toEnd(BLUE, dir);
		}else {
			toEnd(BLUE,dir);
			toEnd(RED, dir);
		}
	}
	
	// 주어진 방향으로 사탕을 끝까지 굴리는 동작
	public static void toEnd(Candy candy, int dir) {
		while(true) {
			int nx = candy.x + DX[dir];
			int ny = candy.y + DY[dir];
			if(isCandy(nx, ny))
				break;
			if(!isWall(nx, ny)) {
				candy.x = nx;
				candy.y = ny;
			}
			if(isExit(candy) || isWall(nx, ny)) 
				break;
		}
	}
	
	// 기울일 때 빨간색 공이 반드시 먼저 움직여야할 때를 처리
	public static boolean redFirst(int dir) {
		if(dir==0) 
			return (RED.x==BLUE.x && RED.y>BLUE.y);
		else if(dir==1) 
			return (RED.y==BLUE.y && RED.x>BLUE.x);
		else if(dir==2) 
			return (RED.x==BLUE.x && RED.y<BLUE.y);
		else 
			return (RED.y==BLUE.y && RED.x<BLUE.x);
	}
	
	// 사탕이 구멍에 빠져있는지 확인하는 함수
	public static boolean isExit(Candy candy) {
		if(MAP[candy.x][candy.y].equals("O"))
			return true;
		return false;
	}
	
	// 이동하려는 칸에 사탕이 있는지 확인하는 함수
	public static boolean isCandy(int x, int y) {
		if(x==BLUE.x && y==BLUE.y && !MAP[x][y].equals("O")) // ************************************* 틀린이유: 사탕이 구멍에 빠지고 났을때, 그 칸에 사탕이 겹치지 않는 처리를 하지 않았었다.
			return true;
		if(x==RED.x && y==RED.y && !MAP[x][y].equals("O"))
			return true;
		return false;
	}
	
	// 이동하려는 칸이 벽이거나 MAP 밖인지 확인하는 함수
	public static boolean isWall(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=M)
			return true;
		if(MAP[x][y].equals("#"))
			return true;
		return false;
	}
}

class Candy{
	
	public Candy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	int x;
	int y;
}

