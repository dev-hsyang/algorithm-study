package algorithm_coding._08_1_그래프의표현;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 18352
 * 실버 2
 */
public class 특정거리의도시찾기 {

    static int N, M, K, X, A, B;
    static int[] DIST;
    static ArrayList<Integer>[] ADJ;
    static boolean[] VISITED;
    static Queue<Integer> ANS_QUEUE = new LinkedList<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        ADJ = new ArrayList[N+1];
        VISITED = new boolean[N+1];
        DIST = new int[N+1];
        for(int i=1; i<N+1; i++)
            ADJ[i] = new ArrayList<Integer>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            ADJ[A].add(B);
        }

        bfs(X);

        for(int i=1; i<N+1; i++)
            if(DIST[i] == K)
                ANS_QUEUE.add(i);

        if(ANS_QUEUE.isEmpty()){
            bw.append("-1");
        }else {
            while (!ANS_QUEUE.isEmpty()){
                bw.append(ANS_QUEUE.poll() + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static void bfs(int node){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        VISITED[node] = true;

        while (!queue.isEmpty()){
            int now = queue.poll();
            for(int n : ADJ[now]){
                if(!VISITED[n]){
                    VISITED[n] = true;
                    DIST[n] = DIST[now] + 1;
                    queue.add(n);
                }
            }
        }
    }
}
