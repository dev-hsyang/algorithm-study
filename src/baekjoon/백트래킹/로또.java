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
    static Set<String> LINKED_HASH_SET;
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
            LINKED_HASH_SET = new LinkedHashSet<>();
            for(int i=0; i<K; i++)
                S[i] = sc.nextInt();
            dfs(0, 0, new int[6]);

            Iterator<String> iterator = LINKED_HASH_SET.iterator();
            while(iterator.hasNext())
                BW.append(iterator.next() + "\n");

            BW.append("\n");
        }
        BW.flush();
        BW.close();
    }

    public static void dfs(int depth, int idx, int[] arr) throws IOException {

        if(depth == 6){
            String s = "";
            Arrays.sort(arr);
            for(int i : arr)
                s += String.valueOf(i) + " ";
            LINKED_HASH_SET.add(s);
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
