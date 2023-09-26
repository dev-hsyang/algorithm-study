package baekjoon.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 백준 10773
 * 실버 4
 */
public class 제로 {

    static int K, ANS;
    static Stack<Integer> STACK = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(num == 0)
                STACK.pop();
            else
                STACK.push(num);
        }

        while(!STACK.isEmpty())
            ANS += STACK.pop();

        System.out.println(ANS);
    }
}
