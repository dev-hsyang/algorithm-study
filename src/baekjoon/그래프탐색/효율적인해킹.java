package baekjoon.그래프탐색;

import java.io.*;
import java.util.*;

/**
 * 백준 1325
 * 실버 1
 */
public class 효율적인해킹 {

    static int N, M, MAX;
    static int[] HACKMAP;
    static boolean[] VISITED;
    static ArrayList<Integer> ANS = new ArrayList<Integer>();
    static ArrayList<Integer>[] ADJ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        HACKMAP = new int[N+1];
        ADJ = new ArrayList[N+1];
        for(int i=1; i<N+1; i++)
            ADJ[i] = new ArrayList<Integer>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ADJ[a].add(b);
        }

        operate();
        collectAns();

        for(int i : ANS)
            bw.append(i + " ");

        bw.flush();
        bw.close();
    }

    public static void operate(){
        for(int i=1; i<N+1; i++){
            VISITED = new boolean[N+1];
//            dfs(i);
            bfs(i);
        }
    }

//    public static void dfs(int node){
//        if(VISITED[node])
//            return;
//        VISITED[node] = true;
//        for(int i : ADJ[node])
//            if(!VISITED[i]){
//                HACKMAP[i] += 1;
//                MAX = Math.max(MAX, HACKMAP[i]);
//                dfs(i);
//            }
//    }

    public static void bfs(int node){
        Queue<Integer> queue = new LinkedList<Integer>();
        VISITED[node] = true;
        queue.add(node);
        while (!queue.isEmpty()){
            int now = queue.poll();
            for(int i : ADJ[now]){
                if(!VISITED[i]){
                    VISITED[i] = true;
                    HACKMAP[i] += 1;
                    MAX = Math.max(MAX, HACKMAP[i]);
                    queue.add(i);
                }
            }
        }
    }

    public static void collectAns(){
        for(int i=1; i<N+1; i++)
            if(HACKMAP[i] == MAX)
                ANS.add(i);
    }
}
