package algorithm_coding._08_2_유니온파인드;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 백준 1717
 * 골드 4
 *
 * 유니온 파인드 알고리즘 적용시, find() 메서드 작성할 때 주의하여야 한다.
 * 경로 압축하는 과정을 재귀적으로 잘 사용할 것
 */
public class 집합표현하기 {

    public static int N, M;
    public static int[] PARENT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        PARENT = new int[N+1];
        for(int i=1; i<N+1; i++)
            PARENT[i] = i;

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(query == 0)
                union(a, b);
            else if(query == 1)
                check(a, b, bw);
        }

        bw.flush();
        bw.close();
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        PARENT[b] = a;
    }

    public static int find(int a){
        if(a == PARENT[a])
            return a;
        else
            return PARENT[a] = find(PARENT[a]);
    }

    public static void check(int a, int b, BufferedWriter bw) throws IOException {
        if(find(a) == find(b))
            bw.append("YES" + "\n");
        else
            bw.append("NO" + "\n");
    }
}
