package algorithm_coding._03_1_배열과리스트;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * 브론즈 1
 * 백준 1546
 * @author didgs
 *
 */
public class 평균구하기 {

	static int N;
	static double total;
	static double[] ARR;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		ARR = new double[N];
		
		for(int i=0; i<N; i++)
			ARR[i] = sc.nextInt();
		
		Arrays.sort(ARR);
		
		double M = ARR[N-1];
		
		for(int i=0; i<N; i++) 
			total += ARR[i] / M * 100;
		
		total /= N;
		
		System.out.println(total);
		
		
		

	}

}
