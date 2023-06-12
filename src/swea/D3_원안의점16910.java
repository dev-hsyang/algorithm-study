package swea;

import java.util.Scanner;

public class D3_원안의점16910 {

	static int T, N;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int i=1; i<=T; i++) {
			int cnt = 0;
			int ans = 0;
			N = sc.nextInt();
			
			for(int j=0; j<=N; j++)
				for(int k=0; k<=N; k++) 
					if(j*j + k*k <= N*N)
						cnt += 1;	
			ans = cnt * 4 - 4 * N - 3;
			System.out.println("#" + i + " " + ans);
		}

	}

}
