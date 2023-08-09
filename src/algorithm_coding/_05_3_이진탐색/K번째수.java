package algorithm_coding._05_3_이진탐색;

import java.util.Scanner;

/**
 * 백준 1300
 * 골드 2
 * @author didgs
 *
 */
public class K번째수 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		long start = 1, end = K;
		long ans = 0;
		
		while(start<=end) {
			long mid = (start + end) / 2;
			long cnt = 0;
			
			for(int i=1; i<=N; i++)
				cnt += Math.min(mid / i, N);
			
			if(cnt < K)
				start = mid + 1;
			else {
				ans = mid;
				end = mid - 1;
			}
		}
		
		System.out.println(ans);
	}

}
