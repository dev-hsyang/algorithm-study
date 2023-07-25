package algorithm_coding._05_2_너비우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1167
 * 골드 2
 */
public class 트리의지름구하기 {

    static int V, ANS;
    static int[] DISTANCE;
    static boolean[] VISITED;
    static ArrayList<Vinfo>[] ADJ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        ADJ = new ArrayList[V+1];
        VISITED = new boolean[V+1];
        DISTANCE = new int[V+1];
        for(int i=1; i<V+1; i++)
            ADJ[i] = new ArrayList<Vinfo>();
        for(int i=0; i<V; i++){
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            while(true){
                int num = Integer.parseInt(st.nextToken());
                if(num == -1)
                    break;
                int weight = Integer.parseInt(st.nextToken());
                ADJ[node].add(new Vinfo(num, weight));
            }
        }

        operate(new Vinfo(1, 0));
        int maxDistIndex = getMaxDistanceIndex();

        VISITED = new boolean[V+1];
        DISTANCE = new int[V+1];

        operate(new Vinfo(maxDistIndex, 0));
        ANS = getMaxDistance();

        System.out.println(ANS);
    }

    public static void operate(Vinfo v){
        Queue<Vinfo> queue = new LinkedList<Vinfo>();
        queue.add(v);
        VISITED[v.node] = true;
        while(!queue.isEmpty()){
            Vinfo now = queue.poll();
            for(Vinfo next : ADJ[now.node]){
                if(!VISITED[next.node]){
                    DISTANCE[next.node] = DISTANCE[now.node] + next.link;
                    VISITED[next.node] = true;
                    queue.add(next);
                }
            }
        }
    }

    public static int getMaxDistanceIndex(){
        int maxVal = getMaxDistance();
        int maxIndex = 0;
        for(int i=1; i<V+1; i++)
            if(DISTANCE[i] == maxVal)
                maxIndex = i;
        return maxIndex;
    }

    public static int getMaxDistance(){
        int maxVal = 0;
        for(int i=1; i<V+1; i++)
            if(DISTANCE[i] > maxVal)
                maxVal = DISTANCE[i];
        return maxVal;
    }
}

class Vinfo{

    public Vinfo(int node, int link){
        this.node = node;
        this.link = link;
    }
    int node;
    int link;
}