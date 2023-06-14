package samsung_sw.codetree;


import java.util.Scanner;

public class 나무박멸 {

	static int[][] Tmap;
	static int[][] Cmap;
	static int[][] Dmap;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[] ddx = {-1, 1, 1, -1};
	static int[] ddy = {1, 1, -1, -1};
	static int N, M, K, C;
	static int TOTAL=0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		C = sc.nextInt();
		Tmap = new int[N][N];
		Cmap = new int[N][N];
		Dmap = new int[N][N];
		
		for(int i=0; i<N; i++) 
			for(int j=0; j<N; j++) 
				Tmap[i][j] = sc.nextInt();
			
		for(int i=0; i<M; i++) {
	
			int[][] candimap = new int[N][N];
			for(int j=0; j<N; j++) 
				for(int n=0; n<N; n++) 
					candimap[j][n] = Tmap[j][n];
			
			// STEP1 나무 성장
			for(int j=0; j<N; j++) {
				for(int n=0; n<N; n++) {
					if(Tmap[j][n]>0) {
						for(int k=0; k<4; k++) {
							int nx = j + dx[k];
							int ny = n + dy[k];
							if(nx>=0 && nx<N && ny>=0 && ny<N) {
								if(Tmap[nx][ny]>0)
									candimap[j][n] += 1;
							}
						}
					}
				}
			}
			
//			System.out.println("STEP2");
			// STEP2 나무 번식
			for(int j=0; j<N; j++) {
				for(int n=0; n<N; n++) {
					if(Tmap[j][n]>0) { // 각 나무가 있는 칸별로
						int count = 0;
						for(int k=0; k<4; k++) {
							int nx = j + dx[k];
							int ny = n + dy[k];
							if(nx>=0 && nx<N && ny>=0 && ny<N) // 격자 안에 있으며
								if(Tmap[nx][ny]==0 && Cmap[nx][ny]==0) // 벽또는 다른 나무가 없는 빈칸이고, 제초제가 0 인칸
									count += 1;
						}
						for(int k=0; k<4; k++) {
							int nx = j + dx[k];
							int ny = n + dy[k];
							if(nx>=0 && nx<N && ny>=0 && ny<N)
								if(Tmap[nx][ny]==0 && Cmap[nx][ny]==0) {
									candimap[nx][ny] += (candimap[j][n] / count); // ==================> 오답포인트: 성장한 트리맵의 값을 나눴어야했다.			
								}
						}				
					}
				}
			}

			// candimap에는 현재 모든 나무의 성장과 번식이 동시에 끝났을때의 나무 상황을 가지고 있어야한다.
			
//			System.out.println("STEP3-1");
			// STEP3-1 제초제 살포위치 선정
			int[][] countmap = new int[N][N];
			for(int j=0; j<N; j++) {
				for(int n=0; n<N; n++) {
					if(candimap[j][n]>0) { // 나무의 성장과 번식이 끝난 맵에서, 나무가 있는 칸이면
						countmap[j][n] += candimap[j][n];
						for(int k=0; k<4; k++) { // 한 대각선 방향을 먼저 k만큼 탐색한다 ==============> 문제해결포인트: 전파중 벽/빈칸이면 그 칸까지만 살포하고 그이후 안하는 구현을 생각못함
							for(int a=1; a<K+1; a++) {
								int nx = j + a*ddx[k];
								int ny = n + a*ddy[k];
								if(nx<0 || nx>=N || ny<0 || ny>=N)
									break;
								if(candimap[nx][ny] == 0 || candimap[nx][ny] == -1)
									break;
								countmap[j][n] += candimap[nx][ny];	
							}
						}				
					}
				}
			}
			int maxX = -1; // 제초제 살포할 x좌표
			int maxY = -1; // 제초제 살포한 y좌표
			int maxVal = -1; // 제초제 살포시 제초되는 나무 수
			for(int j=0; j<N; j++) {
				for(int n=0; n<N; n++) {
					if(countmap[j][n]>maxVal) {
						maxVal = countmap[j][n];
						maxX = j;
						maxY = n;
					}
				}
			}
			
			TOTAL += maxVal;
			
			// 1년이 지났으니 살포된 제초제들의 수명이 1씩 줄어든다
			for(int j=0; j<N; j++)
				for(int n=0; n<N; n++)
					if(Cmap[j][n]>0)
						Cmap[j][n] -= 1;
				
//			System.out.println("STEP3-2");
			// STEP 3-2 제초제 살포
			Cmap[maxX][maxY] = C;
			candimap[maxX][maxY] = 0;
			for(int k=0; k<4; k++)
				for(int a=1; a<K+1; a++) {
					int nx = maxX + a*ddx[k];
					int ny = maxY + a*ddy[k];
					if(nx<0 || nx>=N || ny<0 || ny>=N)
						break;
					if(candimap[nx][ny] == -1) // candimap이 0인데 까지는 제초제가 가야한다....
						break;
					if(candimap[nx][ny] == 0) { // ===================================> 오답포인트. 0인 칸까지만 확산하고 그 뒤로 확산하지 않는 구현을 생각못함
						Cmap[nx][ny] = C;
						break;
					}
					Cmap[nx][ny] = C;
					candimap[nx][ny] = 0;
				}
			
			for(int j=0; j<N; j++)
				for(int n=0; n<N; n++)
					Tmap[j][n] = candimap[j][n];	
			
		}
		
		System.out.println(TOTAL);	
	}

}
