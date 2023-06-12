package swea;

import java.util.Scanner;

public class D2_간단한369게임1926 {

	static int N;
	static String[] S;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		Integer num = 1;
		String token = num.toString();
		
		for(int i=0; i<N; i++) {
			
			S = token.split("");
			String ans = token;
			if(token.contains("3") || token.contains("6") || token.contains("9")){
				ans = "";
				for(String s : S) {
					if(s.equals("3"))
						ans += "-";
					if(s.equals("6"))
						ans += "-";
					if(s.equals("9"))
						ans += "-";
				}				
			}
			
			System.out.print(ans + " ");
			num++;
			token = num.toString();
		}
	}

}
