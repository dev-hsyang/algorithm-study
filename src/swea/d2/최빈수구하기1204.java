package swea.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 최빈수구하기1204 {

	static int T;
	static int VAL;
	static int COUNT;
	static int[] SCORE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken());
			SCORE = new int[101];
			VAL = 0;
			COUNT = 0;
			Stack<Integer> stack = new Stack<>();
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<1000; j++)
				stack.push(Integer.parseInt(st.nextToken()));
			
			for(int j=0; j<1000; j++) {
				int num = stack.pop();
				SCORE[num] += 1;
			}
			
			for(int j=0; j<101; j++) {
				if(SCORE[j]>=COUNT) {
					COUNT = SCORE[j];
					VAL = j;
				}
			}
			
			System.out.println("#" + no + " " + VAL);
		}

	}

}
