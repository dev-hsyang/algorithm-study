package baekjoon.수학;

import java.util.Scanner;

/**
 * 백준 9655
 * 실버 5
 * @author didgs
 *
 */
public class 돌게임 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		if(N % 2 == 0)
			System.out.println("CY");
		else
			System.out.println("SK");
	}
}
