package algorithm_coding._08_1_그래프의표현;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1707
 * 골드 4
 *
 * BFS 로직에서 오류가 있었기 때문에 1시간 넘게 고민한 문제이다.
 * ++ 탐색을 두번할 필요 없이, DFS 탐색 한번만에 FLAG 설정과 이분그래프 판별을 할 수 있었다.
 */
public class 이분그래프판별하기 {

    static int K, V, E;
    static int[] FLAG;
    static String ANS;
    static boolean[] VISITED;
    static ArrayList<Integer>[] ADJ;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        for(int test_case=0; test_case<K; test_case++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            ADJ = new ArrayList[V+1];
            VISITED = new boolean[V+1];
            FLAG = new int[V+1];
            ANS = "YES";
            for(int i=1; i<V+1; i++)
                FLAG[i] = 1;
            for(int i=1; i<V+1; i++)
                ADJ[i] = new ArrayList<Integer>();
            for(int i=0; i<E; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                ADJ[a].add(b);
                ADJ[b].add(a);
            }

            for(int i=1; i<V+1; i++)
                dfs(i);

            VISITED = new boolean[V+1];

            for(int i=1; i<V+1; i++)
                if(!VISITED[i])
                    check(i);

            bw.append(ANS + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void dfs(int node){
        if(VISITED[node])
            return;

        VISITED[node] = true;

        for(int i : ADJ[node]){
            if(!VISITED[i]){
                colorFlag(node, i);
                dfs(i);
            }
        }
    }

    public static void colorFlag(int n1, int n2){
        if(FLAG[n1] == 1)
            FLAG[n2] = -1;
        else
            FLAG[n2] = 1;
    }

    public static void check(int node){
        Queue<Integer> queue = new LinkedList<Integer>();
        VISITED[node] = true;
        queue.add(node);

        while (!queue.isEmpty()){
            int now = queue.poll();
            for(int i : ADJ[now]){
                /**
                 * *****************************************************************************
                 * 문제 지점 (2시간 삽질한 지점)
                 * !VISITED[i] 일 경우에만 인접 노드와 FLAG 를 비교하면 안됐었다.
                 * 이미 탐색된 노드이던 아니던간에, 일단 인접한 노드들과 FLAG 비교를 실시해야한다.
                 */
                if(FLAG[i] == FLAG[now]){
                    ANS = "NO";
                    break;
                }
                /**
                 * *****************************************************************************
                 */
                if(!VISITED[i]){

                    VISITED[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
