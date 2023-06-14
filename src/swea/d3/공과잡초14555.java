package swea.d3;

import java.util.Scanner;
import java.util.Stack;

public class 공과잡초14555 {
	
	static int T, COUNT;
	static String[] INPUT;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int i=1; i<=T; i++) {
			INPUT = sc.next().split("");
			COUNT = 0;
			
			for(int j=0; j<INPUT.length; j++) {
				if(INPUT[j].equals("(")) 
					if(INPUT[j+1].equals("|")) 
						COUNT += 1;
				if(INPUT[j].equals(")"))
					if(INPUT[j-1].equals("|") || INPUT[j-1].equals("("))
						COUNT += 1;
			}		
			System.out.println("#" + i + " " + COUNT);	
		}
	}
}
