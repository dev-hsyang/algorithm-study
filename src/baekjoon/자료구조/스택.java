package baekjoon.자료구조;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 백준 10828
 * 실버 4
 */
public class 스택 {

    public static int N;
    public static Stack<Integer> STACK = new Stack<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for(int tc=0; tc<N; tc++){
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();

            if(type.equals("push"))
                push(Integer.parseInt(st.nextToken()));
            else if(type.equals("pop"))
                pop(bw);
            else if(type.equals("size"))
                size(bw);
            else if(type.equals("empty"))
                empty(bw);
            else if(type.equals("top"))
                top(bw);
        }

        bw.flush();
        bw.close();
    }

    public static void push(int n){
        STACK.push(n);
    }

    public static void pop(BufferedWriter bw) throws IOException {
        if(!STACK.isEmpty())
            bw.append(STACK.pop() + "\n");
        else
            bw.append("-1" + "\n");
    }

    public static void top(BufferedWriter bw) throws IOException {
        if(!STACK.isEmpty())
            bw.append(STACK.peek() + "\n");
        else
            bw.append("-1" + "\n");
    }

    public static void size(BufferedWriter bw) throws IOException {
        bw.append(STACK.size() + "\n");
    }

    public static void empty(BufferedWriter bw) throws IOException {
        if(!STACK.isEmpty())
            bw.append("0" + "\n");
        else
            bw.append("1" + "\n");
    }
}
