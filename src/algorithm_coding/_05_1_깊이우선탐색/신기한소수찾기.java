package algorithm_coding._05_1_깊이우선탐색;

import java.util.Scanner;

// 백준 2023
// 골드 5
public class 신기한소수찾기 {

	public static int N;
	public static int[] PRIME = {1, 3, 5, 7, 9};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		DFS(2);
		DFS(3);
		DFS(5);
		DFS(7);
	}
	
	public static void DFS(int num) {
		
		String val = String.valueOf(num);
		
		if(val.length()==N && isPrime(Integer.parseInt(val))) {
			System.out.println(val);
			return;
		}
		
		if(!isPrime(num))
			return;
		
		for(int i=0; i<5; i++)
			if(val.length()<N)
				DFS(Integer.parseInt(val + String.valueOf(PRIME[i])));
	}
	
	
	public static boolean isPrime(int num) {
		
		for(int i=2; i<num-1; i++)
			if(num%i==0)
				return false;
		
		return true;
	}
}
