package baekjoon;

import java.util.Scanner;

/**
 * 백준 14500
 * 삼성 SW 역량 테스트 기출 문제
 * 골드 4
 */
public class 테트로도미노 {
	
	static int N, M, ANS;
	static int[][] MAP;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		MAP = new int[N][M];
		
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				MAP[i][j] = sc.nextInt();
		
		doFilter1();
		doFilter2();
		doFilter3();
		doFilter4();
		doFilter5();
		System.out.println(ANS);
	}
	
	public static void doFilter1() {
		int sum = 0;
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++) {
				if(j+4 <= M) 
					sum = MAP[i][j] + MAP[i][j+1] + MAP[i][j+2] + MAP[i][j+3];
				if(sum > ANS)
					ANS = sum;
			}
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++) {
				if(i+4 <= N)
					sum = MAP[i][j] + MAP[i+1][j] + MAP[i+2][j] + MAP[i+3][j];
				if(sum > ANS)
					ANS = sum;
			}
	}
	
	public static void doFilter2() {
		Filter2 filter2 = new Filter2();
		filter2.init();
		filtering(filter2.getFilter());
	}
	
	public static void doFilter3() {
		Filter3 filter3 = new Filter3();
		filter3.init();
		filtering(filter3.getFilter());
		filter3.state1();
		filtering(filter3.getFilter());
		filter3.state2();
		filtering(filter3.getFilter());
		filter3.state3();
		filtering(filter3.getFilter());
	}
	
	public static void doFilter4() {
		Filter4 filter4 = new Filter4();
		filter4.init();
		filtering(filter4.getFilter());
		filter4.state1();
		filtering(filter4.getFilter());
		filter4.state2();
		filtering(filter4.getFilter());
		filter4.state3();
		filtering(filter4.getFilter());
		filter4.state4();
		filtering(filter4.getFilter());
		filter4.state5();
		filtering(filter4.getFilter());
		filter4.state6();
		filtering(filter4.getFilter());
		filter4.state7();
		filtering(filter4.getFilter());

	}
	
	public static void doFilter5() {
		Filter5 filter5 = new Filter5();
		filter5.init();
		filtering(filter5.getFilter());
		filter5.state1();
		filtering(filter5.getFilter());
		filter5.state2();
		filtering(filter5.getFilter());
		filter5.state3();
		filtering(filter5.getFilter());
	}
	
	public static void filtering(int[][] filter) {
		int sum;
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++) {
				if(i+filter.length <= N && j+filter[0].length <= M) {
					sum = 0;
					for(int m=0; m<filter.length; m++)
						for(int n=0; n<filter[0].length; n++)
							if(filter[m][n] == 1)
								sum += MAP[i+m][j+n];
					if(sum > ANS)
						ANS = sum;
				}
			}
	}
}

class Filter2{
	int[][] filter;
	
	public void init() {
		filter = new int[2][2];
		for(int i=0; i<2; i++)
			for(int j=0; j<2; j++)
				filter[i][j] = 1;
	}
	
	public int[][] getFilter(){
		return filter;
	}
}

class Filter3{
	int[][] filter;
	
	public void init() {
		filter = new int[3][2];
		filter[0][0] = 1;
		filter[1][0] = 1;
		filter[1][1] = 1;
		filter[2][1] = 1;
	}
	
	public void state1() {
		filter = new int[2][3];
		filter[0][1] = 1;
		filter[0][2] = 1;
		filter[1][0] = 1;
		filter[1][1] = 1;
	}
	
	public void state2() {
		filter = new int[2][3];
		filter[0][0] = 1;
		filter[0][1] = 1;
		filter[1][1] = 1;
		filter[1][2] = 1;
	}
	
	public void state3() {
		filter = new int[3][2];
		filter[0][1] = 1;
		filter[1][0] = 1;
		filter[1][1] = 1;
		filter[2][0] = 1;
	}
	
	public int[][] getFilter(){
		return filter;
	}
	
}

class Filter4{
	int[][] filter;
	
	public void init() {
		filter = new int[3][2];
		filter[0][0] = 1;
		filter[1][0] = 1;
		filter[2][0] = 1;
		filter[2][1] = 1;
	}
	
	public void state1() {
		filter = new int[3][2];
		filter[0][1] = 1;
		filter[1][1] = 1;
		filter[2][1] = 1;
		filter[2][0] = 1;
	}
	
	public void state2() {
		filter = new int[3][2];
		filter[0][0] = 1;
		filter[0][1] = 1;
		filter[1][0] = 1;
		filter[2][0] = 1;
	}
	
	public void state3() {
		filter = new int[3][2];
		filter[0][0] = 1;
		filter[0][1] = 1;
		filter[1][1] = 1;
		filter[2][1] = 1;
	}
	
	public void state4() {
		filter = new int[2][3];
		filter[0][0] = 1;
		filter[1][0] = 1;
		filter[1][1] = 1;
		filter[1][2] = 1;
	}
	
	public void state5() {
		filter = new int[2][3];
		filter[0][0] = 1;
		filter[0][1] = 1;
		filter[0][2] = 1;
		filter[1][2] = 1;
	}
	
	public void state6() {
		filter = new int[2][3];
		filter[0][2] = 1;
		filter[1][0] = 1;
		filter[1][1] = 1;
		filter[1][2] = 1;
	}
	
	public void state7() {
		filter = new int[2][3];
		filter[0][0] = 1;
		filter[0][1] = 1;
		filter[0][2] = 1;
		filter[1][0] = 1;
	}
	
	public int[][] getFilter(){
		return filter;
	}
}

class Filter5{
	int[][] filter;
	
	public void init() {
		filter = new int[3][2];
		filter[0][0] = 1;
		filter[1][0] = 1;
		filter[1][1] = 1;
		filter[2][0] = 1;
	}
	
	public void state1() {
		filter = new int[3][2];
		filter[0][1] = 1;
		filter[1][0] = 1;
		filter[1][1] = 1;
		filter[2][1] = 1;
	}
	
	public void state2() {
		filter = new int[2][3];
		filter[0][0] = 1;
		filter[0][1] = 1;
		filter[0][2] = 1;
		filter[1][1] = 1;
	}
	
	public void state3() {
		filter = new int[2][3];
		filter[0][1] = 1;
		filter[1][0] = 1;
		filter[1][1] = 1;
		filter[1][2] = 1;
	}
	
	public int[][] getFilter() {
		return filter;
	}
}
