package baekjoon.백트래킹;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 백준 15666
 * 실버 2
 */
public class N과M_12 {

    static int N, M;
    static int[] ARR;
    static Set<String> LINKED_HASH_SET = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = sc.nextInt();
        M = sc.nextInt();
        ARR = new int[N];
        for(int i=0; i<N; i++)
            ARR[i] = sc.nextInt();
        Arrays.sort(ARR);

        dfs(0, 0, new int[M]);

        Iterator<String> iter = LINKED_HASH_SET.iterator();
        while (iter.hasNext())
            bw.append(iter.next() + "\n");

        bw.flush();
        bw.close();
    }

    public static void dfs(int depth, int now, int[] arr){

        if(depth == M){
            String s = "";
            for(int i : arr)
                s += String.valueOf(i) + " ";
            LINKED_HASH_SET.add(s);
            return;
        }

        for(int i=now; i<N; i++){
            arr[depth] = ARR[i];
            dfs(depth + 1, i, arr);
        }
    }
}
