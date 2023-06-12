package swea;

import java.util.Scanner;

public class D3_팔씨름13547 {

	static int T;
	static String result;
	static String[] GAME;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
			GAME = sc.next().split("");
			int countWin = 0;
			int left;
			
			for(String s : GAME)
				if(s.equals("o"))
					countWin += 1;
			
			left = 15 - GAME.length;
			
			if(countWin + left >= 8)
				result = "YES";
			else if(countWin + left < 8)
				result = "NO";
			
			System.out.println("#" + test_case + " " + result);
		}
	}

}
