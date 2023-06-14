package swea.d3;

import java.util.Scanner;

public class View1206 {

	static int N;
	static long[] bInfo;
	static long[][] map;
	static long[] sum = new long[10];
	static int[] dx = {1, 2, -1, -2};
	static int[] dy = {0, 0, 0, 0};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<10; i++) {
			N = sc.nextInt();
			bInfo = new long[N];
			map = new long[N+4][256];
			for(int j=0; j<N; j++)
				bInfo[j] = sc.nextLong();
			
			for(int j=2; j<N+2; j++)
				for(int k=0; k<bInfo[j-2]; k++)
					map[j][k] = 1;
			
			for(int j=0; j<N+4; j++)
				for(int k=0; k<256; k++)
					if(map[j][k] == 1)
						if(hasView(j, k))
							sum[i] += 1;
			}
		
			int idx = 1;
			for(int i=0; i<10; i++)
				System.out.println("#" + idx++ + " " + sum[i]);
		
	}
	
	public static boolean hasView(int x, int y) {
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(map[nx][ny] == 1)
				return false;
		}
		return true;
	}

}
