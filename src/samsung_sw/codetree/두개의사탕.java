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
		if(redFirst(dir)) { // 이동을 할 때 빨간사탕이 먼저 움직여야 하는 경우
			toEnd(RED, dir);
			toEnd(BLUE, dir);
		}else { // 파란사탕이 먼저 움직여야하거나, 상관없는 경우
			toEnd(BLUE,dir);
			toEnd(RED, dir);
		}
	}
	
	/**
	 * 핵심 아이디어 (사탕이 겹칠경우 처리를 위한)
	 * 좌우로 굴릴때 사탕이 같은 행에 있을경우, 혹은 상하로 굴릴때 사탕이 같은 열에 있을 경우
	 * 이동을 완료시키면 사탕은 겹치게 된다
	 * 이에 따라 사탕이 겹쳤을 때 좌표를 처리하기 위해 빨간사탕 파란사탕 중 먼저 움직일 사탕을 찾는다
	 */
	public static boolean redFirst(int dir) {
		if(dir==0) 
			return (RED.x==BLUE.x && RED.y>BLUE.y); // 우로 움직일 때 두 사탕이 같은 행에 위치하고, 빨간 사탕이 더 오른쪽에 있으면 빨간사탕 먼저 이동
		else if(dir==1) 
			return (RED.y==BLUE.y && RED.x>BLUE.x); // 하로 움직일 때 두 사탕이 같은 열에 위치하고, 빨간 사탕이 더 밑에 있으면 빨간사탕 먼저 이동
		else if(dir==2) 
			return (RED.x==BLUE.x && RED.y<BLUE.y);
		else 
			return (RED.y==BLUE.y && RED.x<BLUE.x);
	}
	
	// 주어진 방향으로 사탕을 끝까지 굴리는 동작
	public static void toEnd(Candy candy, int dir) {
		while(true) {
			int nx = candy.x + DX[dir];
			int ny = candy.y + DY[dir];
			if(isCandy(nx, ny)) // 다음칸에 사탕이 있으면 이동 중지
				break;
			if(!isWall(nx, ny)) { // 다음칸이 이동 가능할 때 이동
				candy.x = nx;
				candy.y = ny;
			}
			if(isExit(candy) || isWall(nx, ny)) // 이동한 칸이 구멍이거나, 다음칸이 벽일 경우 이동 중지
				break;
		}
	}
	
	// 이동하려는 칸에 사탕이 있는지 확인하는 함수
	public static boolean isCandy(int x, int y) {
		if(x==BLUE.x && y==BLUE.y && !MAP[x][y].equals("O")) // ************************************* 틀린이유: 사탕이 구멍에 빠지고 났을때, 그 칸에 더이상 사탕이 없는 처리를 하지 않았었다.
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
	
	// 사탕이 구멍에 빠져있는지 확인하는 함수
	public static boolean isExit(Candy candy) {
		if(MAP[candy.x][candy.y].equals("O"))
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

