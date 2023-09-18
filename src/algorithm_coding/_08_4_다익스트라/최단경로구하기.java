package algorithm_coding._08_4_다익스트라;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1753
 * 골드 4
 */
public class 최단경로구하기 {

    static int V, E, START;
    static int[] DIST;
    static boolean[] VISITED;
    static ArrayList<Node1753>[] ADJ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        DIST = new int[V + 1];
        for(int i=0; i<V+1; i++)
            DIST[i] = Integer.MAX_VALUE;
        VISITED = new boolean[V + 1];
        ADJ = new ArrayList[V + 1];
        for(int i=1; i<V+1; i++)
            ADJ[i] = new ArrayList<Node1753>();
        st = new StringTokenizer(br.readLine());
        START = Integer.parseInt(st.nextToken());
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            ADJ[u].add(new Node1753(v, w));
        }

        dijkstra(START);

        for(int i =1; i<V+1; i++) {
            if (DIST[i] == Integer.MAX_VALUE)
                bw.append("INF \n");
            else
                bw.append(DIST[i] + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void dijkstra(int node){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        DIST[node] = 0;

        while (!queue.isEmpty()){
            int now = queue.poll();

            if(VISITED[now])
                continue;

            VISITED[now] = true;
            for(Node1753 n : ADJ[now]){
                if(DIST[n.node] > DIST[now] + n.weight){
                    DIST[n.node] = DIST[now] + n.weight;
                    queue.add(n.node);
                }
            }
        }
    }
}

class Node1753{

    public Node1753(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
    int node, weight;
}
