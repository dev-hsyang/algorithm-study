package baekjoon.자료구조;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 백준 11478
 * 실버 3
 * @author didgs
 *
 */
public class 서로다른부분문자열의개수 {
	
	static int ANS;
	static String[] S;
	static Set<String> SET = new HashSet<String>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		S = sc.next().split("");
		
		operate();
		
		System.out.println(ANS);
	}
	
	public static void operate() {
		for(int i=0; i<S.length; i++) {
			String s = S[i];
			update(s);
			for(int j=i+1; j<S.length; j++) {
				s += S[j];
				update(s);
			}
		}
	}
	
	public static void update(String s) {
		if(!SET.contains(s)) {
			ANS += 1;
			SET.add(s);
		}
	}
}
