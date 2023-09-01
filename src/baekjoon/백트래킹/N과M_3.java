package baekjoon.백트래킹;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * 백준 15651
 * 실버 3
 */
public class N과M_3 {

    static int N, M;
    static int[] ARR;
    static BufferedWriter BW;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BW = new BufferedWriter(new OutputStreamWriter(System.out));
        N = sc.nextInt();
        M = sc.nextInt();

        dfs(0, new int[M]);

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

        for(int i=1; i<N+1; i++){
            arr[depth] = i;
            dfs(depth + 1, arr);
        }
    }
}
