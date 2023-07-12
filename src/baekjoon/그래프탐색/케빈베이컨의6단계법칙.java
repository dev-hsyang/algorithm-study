package baekjoon.그래프탐색;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 1389
 * 실버 1
 */
public class 케빈베이컨의6단계법칙 {

    static int N, M, TOTAL, ANS;
    static int MIN_DEPTH = Integer.MAX_VALUE;
    static boolean[] VISITED;
    static ArrayList<Bacon> bacons = new ArrayList<Bacon>();
    static ArrayList<Integer>[] ADJ;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        ADJ = new ArrayList[N+1];
        for(int i=1; i<N+1; i++)
            ADJ[i] = new ArrayList<Integer>();
        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            ADJ[a].add(b);
            ADJ[b].add(a);
        }

        for(int i=1; i<N+1; i++){
            VISITED = new boolean[N+1];
            TOTAL = 0;
            operate(new Bacon(i, 0));
            bacons.add(new Bacon(i, TOTAL));
        }

        for(int i=0; i<bacons.size(); i++){
            if(bacons.get(i).depth < MIN_DEPTH){
                MIN_DEPTH = bacons.get(i).depth;
                ANS = bacons.get(i).num;
            }
        }

        System.out.println(ANS);
    }

    public static void operate(Bacon start){
        Queue<Bacon> queue = new LinkedList<>();
        queue.add(start);
        VISITED[start.num] = true;

        while(!queue.isEmpty()){
            Bacon now = queue.poll();

            for(int i : ADJ[now.num]){
                if(!VISITED[i]){
                    VISITED[i] = true;
                    TOTAL += now.depth + 1;
                    queue.add(new Bacon(i, now.depth+1));
                }
            }
        }
    }
}

class Bacon{
    public Bacon(int num, int depth){
        this.num = num;
        this.depth = depth;
    }
    int num, depth;
}
