package algorithm_coding._08_1_그래프의표현;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1325
 * 실버 1
 */
public class 효율적으로해킹하기 {

    static int N, M, A, B, MAX;
    static int[] COUNT;
    static boolean[] VISITED;
    static ArrayList<Integer>[] ADJ;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        COUNT = new int[N+1];
        VISITED = new boolean[N+1];
        ADJ = new ArrayList[N+1];
        for(int i=1; i<N+1; i++)
            ADJ[i] = new ArrayList<Integer>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            ADJ[B].add(A);
        }

        for(int i=1; i<N+1; i++){
            VISITED = new boolean[N+1];
            bfs(i);
            MAX = Math.max(MAX, COUNT[i]);
        }

        for(int i=1; i<N+1; i++)
            if(COUNT[i] == MAX)
                bw.append(i + " " );

        bw.flush();
        bw.close();
    }

    public static void bfs(int node){
        Queue<Integer> queue = new LinkedList<Integer>();
        VISITED[node] = true;
        queue.add(node);
        while (!queue.isEmpty()){
            int now = queue.poll();

            for(int i : ADJ[now]){
                if(!VISITED[i]){
                    COUNT[node]++;
                    VISITED[i] = true;
                    queue.add(i);
                }
            }
        }
    }

}
