package baekjoon.구현;

import java.util.Scanner;

/**
 * 백준 14499
 * 삼성 SW 역량 테스트 기출 문제
 * 골드 4
 * @author didgs
 *
 */
public class 주사위굴리기 {

	static int N, M, X, Y, K;
	static int[] DIR;
	static int[] CORD;
	static int[] DX = {0, 0, 0, -1, 1};
	static int[] DY = {0, 1, -1, 0, 0};
	static int[][] MAP;
	static Dice dice;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt();
		Y = sc.nextInt();
		K = sc.nextInt();
		MAP = new int[N][M];
		DIR = new int[K];
		CORD = new int[] {X, Y};
		dice = new Dice();
		
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				MAP[i][j] = sc.nextInt();
		MAP[X][Y] = 0;

		for(int i=0; i<K; i++)
			DIR[i] = sc.nextInt();
		
		/**
		 * 순서대로 주어진 방향에 따라 동작을 취한다.
		 * (구분동작)
		 * 1. 주어진 방향으로 칸을 이동했을 때 격자판 내에 위치한지 확인하고
		 * 2. 주어진 방향으로 주사위를 굴린다.
		 * 3. 굴린뒤에 주사위 바닥면과 칸의 숫자를 조건에 맞게 복사하는 과정을 취한다.
		 * 4. 주사위 윗면의 숫자를 출력한다.
		 */
		for(int dir : DIR) {
			int nx = CORD[0] + DX[dir];
			int ny = CORD[1] + DY[dir];
			
			if(!canGo(nx, ny))
				continue;
			
			CORD[0] = nx;
			CORD[1] = ny;
			
			roleDice(dir);
			copyNum();
			printTopNum();
		}
	}
	
	/**
	 * 방향에 맞게 주사위를 굴린다.
	 * @param dir
	 */
	public static void roleDice(int dir) {
		if(dir == 1)
			dice.moveEast();
		else if(dir == 2)
			dice.moveWest();
		else if(dir == 3)
			dice.moveNorth();
		else if(dir == 4)
			dice.moveSouth();
	}
	
	/**
	 * 조건에 맞게 주사위 바닥면과 칸의 숫자를 복사한다.
	 */
	public static void copyNum() {
		if(MAP[CORD[0]][CORD[1]] == 0)
			MAP[CORD[0]][CORD[1]] = dice.getFloor();
		else if(MAP[CORD[0]][CORD[1]] != 0) {
			dice.setFloor(MAP[CORD[0]][CORD[1]]);
			MAP[CORD[0]][CORD[1]] = 0;
		}
	}
	
	/**
	 * 주사위 상단면의 숫자를 출력한다.
	 */
	public static void printTopNum() {
		System.out.println(dice.getTop());
	}
	
	/**
	 * 이동하려는 칸이 격자판 내에 위치한지 확인한다.
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean canGo(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
}

/**
 * 2차원 배열로 주사위 전개도를 표현한다.
 * [0][1], [1][0], [1][1], [1][2], [2][1], [3][1] 인덱스만 사용하여 전개도를 표시한다.
 * 동 서 남 북으로 굴렸을 시 전개도에 맞게 인덱스를 교체해주어 상태를 최신화한다.
 * @author didgs
 *
 */
class Dice{
	
	int[][] map = new int[4][3];
	
	public void moveNorth() {
		int[][] newMap = new int[4][3];
		newMap[0][1] = map[3][1];
		newMap[1][0] = map[1][0];
		newMap[1][1] = map[0][1];
		newMap[1][2] = map[1][2];
		newMap[2][1] = map[1][1];
		newMap[3][1] = map[2][1];
		map = newMap;
	}
	
	public void moveEast() {
		int[][] newMap = new int[4][3];
		newMap[0][1] = map[0][1];
		newMap[1][0] = map[1][1];
		newMap[1][1] = map[1][2];
		newMap[1][2] = map[3][1];
		newMap[2][1] = map[2][1];
		newMap[3][1] = map[1][0];
		map = newMap;
	}
	
	public void moveSouth() {
		int[][] newMap = new int[4][3];
		newMap[0][1] = map[1][1];
		newMap[1][0] = map[1][0];
		newMap[1][1] = map[2][1];
		newMap[1][2] = map[1][2];
		newMap[2][1] = map[3][1];
		newMap[3][1] = map[0][1];
		map = newMap;
	}
	
	public void moveWest() {
		int[][] newMap = new int[4][3];
		newMap[0][1] = map[0][1];
		newMap[1][0] = map[3][1];
		newMap[1][1] = map[1][0];
		newMap[1][2] = map[1][1];
		newMap[2][1] = map[2][1];
		newMap[3][1] = map[1][2];
		map = newMap;
	}
	
	public int getFloor() {
		return map[1][1];
	}
	
	public int getTop() {
		return map[3][1];
	}
	
	public void setFloor(int n) {
		map[1][1] = n;
	}
}
