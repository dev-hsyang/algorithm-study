package baekjoon.백트래킹;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 백준 6603
 * 실버 2
 */
public class 로또 {

    static int K;
    static int[] S;
    static boolean[] VISITED;
    static BufferedWriter BW;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BW = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            K = sc.nextInt();
            if(K == 0)
                break;
            S = new int[K];
            VISITED = new boolean[K];
            for(int i=0; i<K; i++)
                S[i] = sc.nextInt();
            dfs(0, 0, new int[6]);

            BW.append("\n");
        }
        BW.flush();
        BW.close();
    }

    public static void dfs(int depth, int idx, int[] arr) throws IOException {

        if(depth == 6){
            for(int i : arr)
                BW.append(i + " ");
            BW.append("\n");
            return;
        }

        for(int i=idx; i<K; i++){
            if(!VISITED[i]){
                arr[depth] = S[i];
                VISITED[i] = true;
                dfs(depth + 1, i + 1, arr);
                VISITED[i] = false;
            }
        }
    }
}
