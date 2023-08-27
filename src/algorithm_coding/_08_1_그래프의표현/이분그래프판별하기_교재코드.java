package algorithm_coding._08_1_그래프의표현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class 이분그래프판별하기_교재코드 {

    static ArrayList<Integer>[] A;
    static int[] check;
    static boolean visited[];
    static boolean IsEven;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int t = 0; t<N; t++){
            String[] s = br.readLine().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            A = new ArrayList[V+1];
            visited = new boolean[V+1];
            check = new int[V+1];
            IsEven = true;
            for(int i=1; i<=V; i++)
                A[i] = new ArrayList<Integer>();
            for(int i=0; i<E; i++){
                s = br.readLine().split(" ");
                int Start = Integer.parseInt(s[0]);
                int End = Integer.parseInt(s[1]);
                A[Start].add(End);
                A[End].add(Start);
            }

            for(int i=1; i<=V; i++){
                if(IsEven)
                    DFS(i);
                else
                    break;
            }
            if(IsEven)
                System.out.println("YES");
            else
                System.out.println("NO");
        }

    }

    public static void DFS(int node){
        visited[node] = true;
        for(int i : A[node]){
            if(!visited[i]){
                check[i] = (check[node] + 1) % 2;
                DFS(i);
            }
            else if (check[node] == check[i])
                IsEven = false;
        }
    }
}
