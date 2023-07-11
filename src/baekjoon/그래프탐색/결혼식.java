package baekjoon.그래프탐색;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 5567
 * 실버 2
 */
public class 결혼식 {

    static int N, M, ANS;
    static ArrayList<Integer>[] ADJ;
    static boolean[] VISITED;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        ADJ = new ArrayList[N+1];
        VISITED = new boolean[N+1];
        for(int i=1; i<N+1; i++)
            ADJ[i] = new ArrayList<Integer>();
        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            ADJ[a].add(b);
            ADJ[b].add(a);
        }

        operate(new Marry(1, 0));

        System.out.println(ANS);
    }

    public static void operate(Marry info){
        Queue<Marry> queue = new LinkedList<Marry>();
        queue.add(info);
        VISITED[info.num] = true;
        while (!queue.isEmpty()){
            Marry now = queue.poll();
            if(now.depth <=2 && now.depth != 0)
                ANS += 1;
            for(int num : ADJ[now.num])
                if(!VISITED[num]){
                    VISITED[num] = true;
                    queue.add(new Marry(num, now.depth+1));
                }
        }
    }
}

class Marry{
    public Marry(int num, int depth){
        this.num = num;
        this.depth = depth;

    }
    int num, depth;
}
