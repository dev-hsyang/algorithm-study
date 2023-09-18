package algorithm_coding._08_4_다익스트라;

import java.io.*;
import java.util.*;

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

        dijkstra(new Node1753(START, 0));

        for(int i =1; i<V+1; i++) {
            if (DIST[i] == Integer.MAX_VALUE)
                bw.append("INF \n");
            else
                bw.append(DIST[i] + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void dijkstra(Node1753 node){
        PriorityQueue<Node1753> queue = new PriorityQueue<Node1753>(); // 탐색을 하는 노드를 기준으로 **가장 작은 값의 거리**를 우선적으로 찾아야 하기 때문에 우선순위큐를 사용해야한다.
        DIST[node.node] = 0;
        queue.add(node);
        while(!queue.isEmpty()){
            Node1753 now = queue.poll();
            if(VISITED[now.node])
                continue;
            VISITED[now.node] = true;
            for(int i=0; i<ADJ[now.node].size(); i++){
                Node1753 next = ADJ[now.node].get(i);
                if(DIST[next.node] > DIST[now.node] + next.weight){
                    DIST[next.node] = DIST[now.node] + next.weight;
                    queue.add(new Node1753(next.node, DIST[next.node])); // 큐에 삽입할때 최신화된 DIST 정보를 담아서 삽입해야한다.
                }
            }
        }
    }
}

class Node1753 implements Comparable<Node1753>{

    public Node1753(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
    int node, weight;

    @Override
    public int compareTo(Node1753 o) {
        return this.weight > o.weight ? 1 : -1; // 새로 큐에 삽입되는 값보다 기존 큐의 값이 더 크면 그 순서 그대로. 반대 경우 기존큐 값이랑 새로운 값이랑 교체.
    }
}
