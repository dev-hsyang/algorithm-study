package swea;

import java.util.Scanner;

public class D3_무한문자열15758 {

	static int C;
	static String S;
	static String T;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();
		
		for(int i=1; i<=C; i++) {
			S = sc.next();
			T = sc.next();
			
			if(S.length() == T.length())
				if(S.equals(T))
					System.out.println("#" + i + " " + "yes");
				else
					System.out.println("#" + i + " " + "no");
			
			else if(S.length() != T.length()) {
				int maxL = Math.max(S.length(), T.length());
				
			}
		}
	}

}
