package swea;

import java.util.Scanner;

public class D3_알파벳공부15230 {

	static int T, ANS;
	static String[] INPUT;
	static String[] ALPHA = "abcdefghijklmnopqrstuvwxyz".split("");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int i=1; i<=T; i++) {
			INPUT = sc.next().split("");
			ANS = count(INPUT);
			System.out.println("#" + i + " " + ANS);
		}
	}
	
	public static int count(String[] input) {
		int count = 0;
		for(int i=0; i<input.length; i++) {
			if(!input[i].equals(ALPHA[i]))
				break;
			count += 1;
		}
		return count;
	}
}
