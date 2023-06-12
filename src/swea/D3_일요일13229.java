package swea;

import java.util.Scanner;

public class D3_일요일13229 {

	static int T;
	static int RESULT;
	static String S;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
			S = sc.next();
			getResult(S);
			System.out.println("#" + test_case + " " + RESULT);
		}
	}
	
	public static void getResult(String s) {
		
		if(s.equals("SUN"))
			RESULT = 7;
		if(s.equals("MON"))
			RESULT = 6;
		if(s.equals("TUE"))
			RESULT = 5;
		if(s.equals("WED"))
			RESULT = 4;
		if(s.equals("THU"))
			RESULT = 3;
		if(s.equals("FRI"))
			RESULT = 2;
		if(s.equals("SAT"))
			RESULT = 1;
	}
}
