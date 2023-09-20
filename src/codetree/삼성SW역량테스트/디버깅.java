package codetree.삼성SW역량테스트;

import java.util.Scanner;

/**
 * 삼성 SW 역량테스트 2018 상반기 오전 2번 문제
 * 골드 3
 * @author didgs
 *
 */
public class 디버깅 {

	static int N, M, H, ANS;
	static int FAIL = -1;
	static boolean SUCCESS;
	static boolean[][] NET;
	
 	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		NET = new boolean[H + 1][N + 1];
		ANS = Integer.MAX_VALUE;
		for(int i=0; i<M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			NET[a][b] = true;
		}
		backtrack(0, 1, 1);
		if(SUCCESS)
			System.out.println(ANS);
		else
			System.out.println(FAIL);
	}
 	
 	public static void backtrack(int depth, int x, int y) {
 		if(depth > 3)
 			return;
 		
 		if(test())
 			ANS = Math.min(ANS, depth);
 		
 		for(int i=x; i<H+1; i++) {
 			for(int j=1; j<N+1; j++) {
 				if(NET[i][j])
 					continue;
 				if(canDeploy(i, j)) {
 					NET[i][j] = true;
 					backtrack(depth + 1, i, j);
 					NET[i][j] = false; 					
 				}
 			}
 		}
 	}
 	
 	public static boolean test() {
 		for(int i=1; i<N+1; i++) {
 			int nx = 1;
 			int ny = i;
 			boolean crossed = false;
 			while(true) {
 				if(NET[nx][ny] && !crossed) {
 					if(canGo(nx, ny + 1)) {
 						ny += 1;
 						crossed = true; 						
 					}else {
 						nx += 1;
 						crossed = false;
 					}
 				} else if(canGo(nx, ny - 1) && NET[nx][ny - 1] && !crossed) {
 					ny -= 1;
 					crossed = true;
 				} else {
 					nx += 1;
 					crossed = false;
 				}
 				if(nx == H + 1) 
 					if(ny != i)
 						return false;
 					else
 						break;
 			}
 		}
 		SUCCESS = true;
 		return true;
 	}
 	
 	public static boolean canGo(int x, int y) {
 		return x>=0 && x<H+1 && y>=0 && y<N+1;
 	}
 	
 	public static boolean canDeploy(int x, int y) {
 		if(canGo(x, y + 1) && NET[x][y + 1])
 			return false;
 		if(canGo(x, y - 1) && NET[x][y - 1])
 			return false;
 		return true;
 	}
}
