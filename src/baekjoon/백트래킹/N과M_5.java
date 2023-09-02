package baekjoon.백트래킹;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 15654
 * 실버 3
 */
public class N과M_5 {

    static int N, M;
    static int[] ARR;
    static boolean[] VISITED;
    static BufferedWriter BW;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BW = new BufferedWriter(new OutputStreamWriter(System.out));
        N = sc.nextInt();
        M = sc.nextInt();
        ARR = new int[N];
        VISITED = new boolean[N];
        for(int i=0; i<N; i++)
            ARR[i] = sc.nextInt();

        Arrays.sort(ARR);

        dfs(0,  new int[M]);

        BW.flush();
        BW.close();
    }

    public static void dfs(int depth, int[] arr) throws IOException {
        if(depth == M){
            for(int i : arr)
                BW.append(i + " ");
            BW.append("\n");
            return;
        }
        for(int i=0; i<N; i++){
            if(!VISITED[i]){
                arr[depth] = ARR[i];
                VISITED[i] = true;
                dfs(depth+1, arr);
                VISITED[i] = false;
            }
        }
    }
}
