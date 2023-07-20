package algorithm_coding._03_5_스택과큐;

import java.io.*;
import java.util.Stack;

/**
 * 백준 17298
 * 골드 4
 */
public class 오큰수구하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        int[] ans = new int[n];
        String[] str = br.readLine().split(" ");
        for(int i=0; i<n; i++)
            A[i] = Integer.parseInt(str[i]);

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for(int i=1; i<n; i++){
            while(!stack.isEmpty() && A[stack.peek()] < A[i])
                ans[stack.pop()] = A[i];
            stack.push(i);
        }

        while(!stack.isEmpty())
            ans[stack.pop()] = -1;

        for(int i=0; i<n; i++)
            bw.write(ans[i] + " ");

        bw.flush();
        bw.close();
    }
}
