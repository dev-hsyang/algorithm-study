package algorithm_coding._07_1_소수구하기;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class 소수_팰린드롬 {

	static int N;
	static long ANS;
	static Set<Integer> HASHSET = new HashSet<Integer>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		initPrimeNumbers();
			
		for(int i=N; i<=Integer.MAX_VALUE; i++) {
			if(isPalindrome(Integer.toString(i)) && HASHSET.contains(i)) {
				ANS = i;
				break;
			}
		}
		
		System.out.println(ANS);
	}
	
	public static boolean isPalindrome(String args) {
		String[] arr = args.split("");
		Stack<String> lefthand = new Stack<>();
		Stack<String> righthand = new Stack<>();
		
		for(int i=0; i<arr.length/2; i++) 
			lefthand.push(arr[i]);
		
		if(arr.length % 2 == 0) 
			for(int i=arr.length-1; i>=arr.length/2; i--) 
				righthand.push(arr[i]);
		else
			for(int i=arr.length-1; i>arr.length/2; i--) 
				righthand.push(arr[i]);
		
		while(!lefthand.isEmpty()) {
			String left = lefthand.pop();
			String right = righthand.pop();
			if(!left.equals(right))
				return false;
		}
		return true;
	}
	
	public static void initPrimeNumbers() {
		int[] temp = new int[10000001];
		for(int i=2; i<=10000000; i++)
			temp[i] = i;
		
		for(int i=2; i<=Math.sqrt(10000000); i++)
			for(int j=i+i; j<=10000000; j+=i)
				temp[j] = 0;
		
		for(int i=0; i<=10000000; i++)
			if(temp[i] != 0)
				HASHSET.add(i);
	}
}
