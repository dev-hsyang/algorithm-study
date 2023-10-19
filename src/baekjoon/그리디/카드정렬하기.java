package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1715
 * 골드 4
 */
public class 카드정렬하기 {

    static int N, ANS;
    static PriorityQueue<Integer> DECK = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            DECK.add(Integer.parseInt(st.nextToken()));
        }
        while(DECK.size() != 1){
            int n1 = DECK.poll();
            int n2 = DECK.poll();
            ANS += n1 + n2;
            DECK.add(n1 + n2);
        }
        System.out.println(ANS);
    }
}
