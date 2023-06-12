package swea;


import java.util.Scanner;

public class D3_1차원정원14178 {

	static int T, N, D, ANS;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			D = sc.nextInt();
			ANS = 0;
			int filter = (D * 2) + 1;
			for(int i=1; i<=N; i++) {
				ANS += 1;
				if(i*filter>=N)
					break;
			}
			System.out.println("#" + test_case + " " + ANS);
		}
	}
}
