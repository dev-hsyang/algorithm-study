package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 백준 14888
 * 삼성 SW 역량 테스트 기출 문제
 * 실버 1
 * @author didgs
 *
 */
public class 연산자끼워넣기 {

	static int N, MIN, MAX;
	static int[] NUMS;
	static int[] OPERATOR = new int[4];
	static ArrayList<Integer> ARRANGE = new ArrayList<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		NUMS = new int[N];
		MAX = Integer.MIN_VALUE;
		MIN = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++)
			NUMS[i] = sc.nextInt();
		
		for(int i=0; i<4; i++)
			OPERATOR[i] = sc.nextInt();
		
		operate(0);
		
		System.out.println(MAX);
		System.out.println(MIN);
		
	}
	
	/**
	 * DFS 로 백트래킹하여 연산자 조합을 구한다.
	 * 
	 * @param count
	 */
	public static void operate(int count) {
		/**
		 * count 가 N-1이 되면 연산자 갯수만큼 탐색이 완료되었으므로
		 * (1) 현재 구해진 연산자 순서로 계산을 완료하고
		 * (2) 최소 / 최댓값을 최신화한다.
		 */
		if(count == N-1) {
			int sum = getSum(); // (1)
			MAX = Math.max(MAX, sum); // (2)
			MIN = Math.min(MIN, sum); // (2)
			return;
		}
		
		/**
		 * 주어진 연산자 정보를 토대로
		 * (1) 연산자를 배열에서 하나씩 꺼내면서 탐색을 진행한다.
		 * (2) 꺼내진 연산자는 연산자 조합에 순서대로 추가된다.
		 * (3) recursive 하게 탐색을 이어간다.
		 */
		for(int i=0; i<4; i++) {
			if(OPERATOR[i]!=0) {
				OPERATOR[i] -= 1; // (1)
				ARRANGE.add(i); // (2)
				operate(count + 1); // (3)
				OPERATOR[i] += 1;
				ARRANGE.remove(ARRANGE.size()-1);
			}
		}
	}
	
	/**
	 * 탐색한 연산자 조합 arraylist 와 숫자들을 이용해 계산한다.
	 * @return
	 */
	public static int getSum() {
		int sum = NUMS[0];
		
		for(int i=0; i<N-1; i++)
			sum = calculate(sum, NUMS[i+1], ARRANGE.get(i));
		
		return sum;
	}
	
	/**
	 * 두 값을 연산자 인덱스를 이용해 계산한다.
	 * @param a
	 * @param b
	 * @param operator
	 * @return
	 */
	public static int calculate(int a, int b, int operator) {
		if(operator == 0) 
			return a + b;
		else if(operator == 1)
			return a - b;
		else if(operator == 2)
			return a * b;
		else if(operator == 3) {
			if(a<0 && b>0) {
				a = + a;
				return a / b;
			}else
				return a / b;
		}
		return 0;
	}
}