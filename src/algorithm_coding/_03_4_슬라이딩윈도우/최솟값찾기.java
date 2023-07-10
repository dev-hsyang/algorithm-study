package algorithm_coding._03_4_슬라이딩윈도우;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 백준 11003
 * 플래티넘 5
 */
public class 최솟값찾기 {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Deque<Node> deque = new LinkedList<>();

        for(int i=0; i<N; i++){
            int now = Integer.parseInt(st.nextToken());

            // 새로운 값이 들어올 때마다 정렬 대신 현재 수보다 큰 값을 덱에서 제거해 시간복잡도를 줄임
            while(!deque.isEmpty() && deque.getLast().val > now)
                deque.removeLast();

            deque.addLast(new Node(i, now));

            if(deque.getFirst().index <= i-L)
                deque.removeFirst();

            bw.write(deque.getFirst().val + " ");
        }
        bw.flush();
        bw.close();
    }
}

class Node{
    public int index;
    public int val;

    public Node(int index, int val){
        this.index = index;
        this.val = val;
    }
}
