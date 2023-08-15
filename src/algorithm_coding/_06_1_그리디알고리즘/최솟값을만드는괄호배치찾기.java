package algorithm_coding._06_1_그리디알고리즘;

import java.util.Scanner;

/**
 * 백준 1541
 * 실버 2
 * @author didgs
 *
 */
public class 최솟값을만드는괄호배치찾기 {

	static int ANS;
	static String INPUT;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		INPUT = sc.next();
		String[] s = INPUT.split("-");
		for(int i=0; i<s.length; i++) {
			if(i == 0)
				ANS += mySum(s[i]);
			else
				ANS -= mySum(s[i]);
		}
		
		System.out.println(ANS);
	}
	
	public static int mySum(String s) {
		String[] nums = s.split("[+]");
		int sum = 0;
		for(int i=0; i<nums.length; i++)
			sum += Integer.parseInt(nums[i]);
		return sum;
	}

}
