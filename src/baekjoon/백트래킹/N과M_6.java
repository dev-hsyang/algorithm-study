package baekjoon.백트래킹;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 15655
 * 실버 3
 */
public class N과M_6 {

    static int N, M;
    static int[] ARR;
    static BufferedWriter BW;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BW = new BufferedWriter(new OutputStreamWriter(System.out));
        N = sc.nextInt();
        M = sc.nextInt();
        ARR = new int[N];
        for(int i=0; i<N; i++)
            ARR[i] = sc.nextInt();
        Arrays.sort(ARR);

        dfs(0, 0, new int[M]);

        BW.flush();
        BW.close();
    }

    public static void dfs(int depth, int num, int[] arr) throws IOException {
        if(depth == M){
            for(int i : arr)
                BW.append(i + " ");
            BW.append("\n");
            return;
        }

        for(int i=num; i<N; i++){
            arr[depth] = ARR[i];
            dfs(depth + 1, i + 1, arr);
        }
    }
}
