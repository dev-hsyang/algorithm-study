package algorithm_coding._03_1_배열과리스트;

import java.util.Scanner;

/**
 * 브론즈 3
 * 백준 11720
 * @author didgs
 *
 */
public class 숫자의합구하기 {

	static String S;
	static int N, ANS;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		S = sc.next();
		String[] given = S.split("");
		
		for(int i=0; i<N; i++) 
			ANS += Integer.parseInt(given[i]);
		
		System.out.println(ANS);
		

	}

}
