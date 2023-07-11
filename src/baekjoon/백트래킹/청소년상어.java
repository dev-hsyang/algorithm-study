package baekjoon.백트래킹;

import java.util.Scanner;

/**
 * 백준 19236
 * 삼성 SW 역량 테스트 기출 문제
 * 골드 2
 * @author didgs
 *
 */
public class 청소년상어 {

	static int[][] map = new int[4][4];
	static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static Fish2[] fishArr = new Fish2[17];
	static int MAX=0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				int fishNum = sc.nextInt();
				int fishDir = sc.nextInt();
				map[i][j] = fishNum;
				fishArr[fishNum] = new Fish2(i, j, fishDir, true);
			}
		}		
		Shark2 shark = new Shark2(0, 0, fishArr[map[0][0]].dir, map[0][0]);
		fishArr[map[0][0]].alive=false;
		map[0][0] = -1; // -1 은 상어, 0 은 빈칸
		
		DFS(shark);	
		System.out.println(MAX);

	}
	
	public static void DFS(Shark2 shark) {
		
		if(shark.sum > MAX)
			MAX = shark.sum;
		
		int[][] candiMap = new int[4][4];
		Fish2[] candiFishArr = new Fish2[17];
		// 물고기 이동 전의 배열 상태를 복사해놓는다
		for(int i=0; i<4; i++) 
			for(int j=0; j<4; j++) 
				candiMap[i][j] = map[i][j];		
		for(int i=1; i<17; i++)
			candiFishArr[i] = new Fish2(fishArr[i].xCord, fishArr[i].yCord, fishArr[i].dir, fishArr[i].alive);		
		// 물고기 이동
		fishMove();	
		// 상어이동 = 백트래킹
		for(int i=1; i<4; i++) {
			int nextX = shark.xCord + i*dx[shark.dir];
			int nextY = shark.yCord + i*dy[shark.dir];			
			if((nextX>=0 && nextX<4 && nextY>=0 && nextY<4) && map[nextX][nextY]!=0) {
				Fish2 candiFish = fishArr[map[nextX][nextY]];
				int candiFishNum = map[nextX][nextY];
				Shark2 candiShark = new Shark2(nextX, nextY, candiFish.dir, shark.sum + map[candiFish.xCord][candiFish.yCord]);
				map[shark.xCord][shark.yCord] = 0;
				map[candiShark.xCord][candiShark.yCord] = -1;
				fishArr[candiFishNum].alive = false;
				DFS(candiShark);
				// DFS 콜백시 원상태 복귀
				map[shark.xCord][shark.yCord] = -1;
				map[candiShark.xCord][candiShark.yCord] = candiFishNum;
				fishArr[candiFishNum].alive = true;
			}
		}		
		// 물고기 이동하기 전으로 복귀
		for(int i=0; i<4; i++) 
			for(int j=0; j<4; j++) 
				map[i][j] = candiMap[i][j];			
		for(int i=1; i<17; i++)
			fishArr[i] = new Fish2(candiFishArr[i].xCord, candiFishArr[i].yCord, candiFishArr[i].dir, candiFishArr[i].alive);
	}
	
	public static void fishMove() {
		
		for(int i=1; i<17; i++) {
			// 그 칸이 상어가 아니고, 빈칸이 아닐때 ******************* << 틀린원인.
			// i에 해당하는 물고기가 살아있을때만 이동해야한다.
			if(fishArr[i].alive) {				
				int nextX = fishArr[i].xCord + dx[fishArr[i].dir];
				int nextY = fishArr[i].yCord + dy[fishArr[i].dir];
				// 이동 불가능 = 상어나 경계면일때 -> 이동 가능할때까지 반시계방향으로 회전
				while(true) {			
					if(nextX>=0 && nextX<4 && nextY>=0 && nextY<4)
						if(map[nextX][nextY] != -1)
							break;
					fishArr[i].rotate();
					nextX = fishArr[i].xCord + dx[fishArr[i].dir];
					nextY = fishArr[i].yCord + dy[fishArr[i].dir];
				}
				// 이동가능 = 빈칸이거나 다른물고기가 있을 떄		
				if(nextX>=0 && nextX<4 && nextY>=0 && nextY<4) {
					int targetFish = map[nextX][nextY];
					int curFish = map[fishArr[i].xCord][fishArr[i].yCord];
					int curX = fishArr[i].xCord;
					int curY = fishArr[i].yCord;					
					if(map[nextX][nextY]!=0) {// 빈칸이 아닐때
						// 스왑			
						fishArr[targetFish].xCord = curX;
						fishArr[targetFish].yCord = curY;
						fishArr[curFish].xCord = nextX;
						fishArr[curFish].yCord = nextY;					
						map[curX][curY] = targetFish;
						map[nextX][nextY] = curFish;						
					}else if(map[nextX][nextY]==0) { // 빈칸일떄
						map[nextX][nextY] = curFish;
						map[curX][curY] = 0;
						fishArr[curFish].xCord = nextX;
						fishArr[curFish].yCord = nextY;						
					}
				}
			}				
		}
	}

}
class Fish2{
	
	public Fish2(int xCord, int yCord, int dir, boolean isAlive) {
		this.xCord = xCord;
		this.yCord = yCord;
		this.dir = dir;
		this.alive = isAlive;
	}
	int xCord;
	int yCord;
	int dir;
	boolean alive;
	public void rotate() {
		this.dir++;
		if(dir>8)
			this.dir = 1;
	}
}

class Shark2{
	
	public Shark2(int xCord, int yCord, int dir, int sum) {
		this.xCord = xCord;
		this.yCord = yCord;
		this.dir = dir;
		this.sum = sum;
	}
	int xCord;
	int yCord;
	int dir;
	int sum;
}