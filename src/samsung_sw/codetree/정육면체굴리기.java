package samsung_sw.codetree;

import java.util.Scanner;

/**
 * 골드 4
 * 삼성 SW 역량테스트 2016 하반기 1번문제
 * @author didgs
 *
 */
public class 정육면체굴리기 {

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
	
	public static void copyNum() {
		if(MAP[CORD[0]][CORD[1]] == 0)
			MAP[CORD[0]][CORD[1]] = dice.getFloor();
		else if(MAP[CORD[0]][CORD[1]] != 0) {
			dice.setFloor(MAP[CORD[0]][CORD[1]]);
			MAP[CORD[0]][CORD[1]] = 0;
		}
	}
	
	public static void printTopNum() {
		System.out.println(dice.getTop());
	}
	
	public static boolean canGo(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}

}

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