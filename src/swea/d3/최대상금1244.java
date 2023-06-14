package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최대상금1244 {

	static int T;
	static int COUNT;
	static int MAX;
	static String[] NUM;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<T+1; i++) {
			st = new StringTokenizer(br.readLine());
			NUM = st.nextToken().split("");
			COUNT = Integer.parseInt(st.nextToken());
			MAX = 0;
			
			if(COUNT > NUM.length)
				COUNT = NUM.length;
			
			dfs(0, 0);
			
			System.out.println("#" + i + " " + MAX);
		}

	}
	
	public static void dfs(int start, int cnt) {
		
		if(cnt == COUNT) {
			String result = "";
			for(String s : NUM)
				result += s;
			MAX = Math.max(MAX, Integer.parseInt(result));
			return;
		}
		
		for(int i=start; i<NUM.length; i++) {
			for(int j=i+1; j<NUM.length; j++) {
				String temp = NUM[i];
				NUM[i] = NUM[j];
				NUM[j] =temp;
				
				dfs(i, cnt+1);
				
				temp = NUM[i];
				NUM[i] = NUM[j];
				NUM[j] = temp;
			}
		}
	}

}
