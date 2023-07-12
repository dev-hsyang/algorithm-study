package algorithm_coding._03_5_스택과큐;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * 백준 1874
 * 실버 2
 */
public class 스택으로오름차순수열만들기 {

    static int N, PIVOT;
    static boolean VAL;
    static ArrayList<String> ANS = new ArrayList<String>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        PIVOT = 1;
        VAL = true;
        Stack<Integer> stack = new Stack<Integer>();

        for(int i=0; i<N; i++){
            int num = sc.nextInt();
            if(!VAL)
                break;

            while (PIVOT <= num){
                ANS.add("+");
                stack.push(PIVOT++);
            }
            if (!stack.isEmpty() && stack.peek() == num){
                ANS.add("-");
                stack.pop();
            }
            if (!stack.isEmpty() && stack.peek() > num){
                ANS = new ArrayList<String>();
                ANS.add("NO");
                VAL = false;
                break;
            }
        }

        for(String s : ANS)
            System.out.println(s);
    }
}
