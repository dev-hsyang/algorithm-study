package baekjoon.백트래킹;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * 백준 15652
 * 실버 3
 */
public class N과M_4 {

    static int N, M;
    static BufferedWriter BW;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BW = new BufferedWriter(new OutputStreamWriter(System.out));
        N = sc.nextInt();
        M = sc.nextInt();

        dfs(1, 0, new int[M]);

        BW.flush();
        BW.close();
    }

    public static void dfs(int num, int depth, int[] arr) throws IOException {
        if(depth == M){
            for(int i : arr)
                BW.append(i + " ");
            BW.append("\n");
            return;
        }

        for(int i=num; i<N+1; i++){
            arr[depth] = i;
            dfs(i, depth + 1, arr);
        }
    }
}
