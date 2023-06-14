package swea.d3;

import java.util.ArrayList;
import java.util.Scanner;

public class 교환학생13038 {

	static int T, N, MIN_ANS;
	static int[] INFO;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
			N = sc.nextInt();
			INFO = new int[7];
			MIN_ANS = Integer.MAX_VALUE;
			ArrayList<Integer> startIndex = new ArrayList<>();
			
			for(int i=0; i<7; i++) {
				INFO[i] = sc.nextInt();
				if(INFO[i] == 1)
					startIndex.add(i);
			}
			
			for(int i=0; i<startIndex.size(); i++) {
				int start = startIndex.get(i);
				int countDate = 0;
				int classDate = 0;
				
				while(true) {
					countDate += 1;
					
					if(start>6)
						start = 0;
					if(INFO[start] == 1)
						classDate += 1;
					if(classDate ==N)
						break;
					
					start++;
				}
				updateANS(countDate);
			}
			System.out.println("#" + test_case + " " + MIN_ANS);
		}
	}
	
	public static void updateANS(int num) {
		if(num<MIN_ANS)
			MIN_ANS = num;
	}
}
