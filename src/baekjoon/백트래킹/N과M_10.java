package baekjoon.백트래킹;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 백준 15664
 * 실버 2
 */
public class N과M_10 {

    static int N, M;
    static int[] ARR;
    static boolean[] VISITED;
    static Set<String> LINKED_HASH_SET = new LinkedHashSet<String>();
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

        dfs(0, 0, new int[M]);

        Iterator<String> iter = LINKED_HASH_SET.iterator();
        while(iter.hasNext()){
            BW.append(iter.next());
            BW.append("\n");
        }

        BW.flush();
        BW.close();
    }

    public static void dfs(int depth, int idx, int[] arr){

        if(depth == M){
            String s = "";
            for(int i : arr)
                s += String.valueOf(i) + " ";
            LINKED_HASH_SET.add(s);
            return;
        }

        for(int i=idx; i<N; i++){
            if(!VISITED[i]){
                VISITED[i] = true;
                arr[depth] = ARR[i];
                dfs(depth + 1, i, arr);
                VISITED[i] = false;
            }
        }
    }
}
