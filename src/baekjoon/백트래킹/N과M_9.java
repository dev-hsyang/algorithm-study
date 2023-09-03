package baekjoon.백트래킹;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 백준 15663
 * 실버 2
 */
public class N과M_9 {

    static int N, M;
    static int[] ARR;
    static boolean[] VISITED;
    static Set<String> SET = new HashSet<String>();
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

        dfs(0, new int[M]);

        BW.flush();
        BW.close();
    }

    public static void dfs(int depth, int[] arr) throws IOException {

        if(depth == M){
            String s = "";
            for(int i : arr)
                s += String.valueOf(i) + " "; // ** 오답 지점 ** 각 값에 대해서 간격을 두고 더하지 않아서 틀렸다.
            if(!SET.contains(s)){
                for(int i : arr)
                    BW.append(i + " ");
                BW.append("\n");
            }
            SET.add(s);
            return;
        }

        for(int i=0; i<N; i++){
            if(!VISITED[i]){
                VISITED[i] = true;
                arr[depth] = ARR[i];
                dfs(depth + 1, arr);
                VISITED[i] = false;
            }
        }
    }
}
