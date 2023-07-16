package baekjoon.자료구조;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 백준 9375
 * 실버 3
 * @author didgs
 *
 */
public class 패션왕신해빈 {
	
	static int TESTCASE, N, ANS;
	static HashMap<String, Integer> MAP;
	static ArrayList<String> LIST;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TESTCASE = sc.nextInt();
		
		for(int test_case = 0; test_case<TESTCASE; test_case++) {
			N = sc.nextInt();
			MAP = new HashMap<String, Integer>();
			LIST = new ArrayList<String>();
			ANS = 1;
			for(int i=0; i<N; i++) {
				String apparel = sc.next();
				String type = sc.next();
				if(MAP.containsKey(type))
					MAP.put(type, MAP.get(type) + 1);
				else
					MAP.put(type, 1);
			}
			Iterator<String> iter = MAP.keySet().iterator();
			while(iter.hasNext()) {
				ANS *= (MAP.get(iter.next()) + 1);
			}
			ANS -= 1;
			System.out.println(ANS);
		}
	}

}
