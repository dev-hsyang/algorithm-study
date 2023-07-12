package baekjoon.그래프탐색;

import java.util.*;

/**
 * 백준 2660
 * 골드 5
 */
public class 회장뽑기 {

    static int N, CANDI_SCORE, CANDI_NUM, DEPTH;
    static int[] CANDI;
    static ArrayList<Integer> FINAL = new ArrayList<Integer>();
    static ArrayList<Integer>[] ADJ;
    static boolean[] VISITED;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        ADJ = new ArrayList[N+1];
        CANDI = new int[N+1];
        for(int i=1; i<N+1; i++)
            ADJ[i] = new ArrayList<Integer>();

        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(a == -1 && b == -1)
                break;
            ADJ[a].add(b);
            ADJ[b].add(a);
        }

        operate();
        updateCandi();

        System.out.println(CANDI_SCORE + " " + CANDI_NUM);

        for(int i : FINAL)
            System.out.print(i + " ");
    }

    public static void operate(){
        for(int i=1; i<N+1; i++){
            DEPTH = 0;
            VISITED = new boolean[N+1];
            bfs(new Candidate(i, 0));
            CANDI[i] = DEPTH;
        }
    }

    public static void bfs(Candidate candi){
        Queue<Candidate> queue = new LinkedList<>();
        queue.add(candi);
        VISITED[candi.num] = true;

        while(!queue.isEmpty()){
            Candidate now = queue.poll();
            DEPTH = Math.max(DEPTH, now.depth);

            for(int i : ADJ[now.num])
                if(!VISITED[i]){
                    VISITED[i] = true;
                    queue.add(new Candidate(i, now.depth+1));
                }
        }
    }

    public static void updateCandi(){
        int[] temp = new int[N+1];
        for(int i=1; i<N+1; i++)
            temp[i] = CANDI[i];
        Arrays.sort(temp);
        CANDI_SCORE = temp[1];
        for(int i=1; i<N+1; i++)
            if(temp[i] == CANDI_SCORE)
                CANDI_NUM += 1;
        for(int i=1; i<N+1; i++)
            if(CANDI[i] == CANDI_SCORE)
                FINAL.add(i);
    }
}

class Candidate{
    public Candidate(int num, int depth){
        this.num = num;
        this.depth = depth;
    }
    int num, depth;
}