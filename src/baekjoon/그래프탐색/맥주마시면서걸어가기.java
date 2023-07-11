package baekjoon.그래프탐색;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 9205
 * DFS + BFS 필수문제
 * 골드 5
 */
public class 맥주마시면서걸어가기 {

    static int T, N;
    static String ANS;
    static boolean[] VISITED;
    static ArrayList<Node>[] ADJ;

    /**
     * 핵심아이디어
     * (1) 주어진 좌표들을 node 화하고
     * (2) 맥주가 최대일때 도달할 수 있는 node 끼리는 link 하여 인접리스트를 완성한다.
     * (3) 완성된 그래프를 탐색하며 ** 50m 당 맥주 한병을 소모하고, 맥주를 다 마셔도 갈 수 없는 node 에 대한 조건을 처리한다.
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for(int test_case=0; test_case<T; test_case++){
            N = sc.nextInt();
            ADJ = new ArrayList[N+2];
            VISITED = new boolean[N+2];
            ANS = "sad";

            for(int i=0; i<N+2; i++)
                ADJ[i] = new ArrayList<Node>();

            // (1)
            for(int i=0; i<N+2; i++)
                ADJ[i].add(new Node(sc.nextInt(), sc.nextInt(), i));

            updateAdjList();

            operate(ADJ[0].get(0));

            System.out.println(ANS);
        }
    }

    // (2)
    public static void updateAdjList(){
        for(int i=0; i<N+2; i++){
            for(int j=i+1; j<N+2; j++){
                Node now = ADJ[i].get(0);
                Node next = ADJ[j].get(0);
                if(getDist(now.x, now.y, next.x, next.y) <= 1000){
                    ADJ[i].add(new Node(next.x, next.y, next.num));
                    ADJ[j].add(new Node(now.x, now.y, now.num));
                }
            }
        }
    }

    // (3)
    public static void operate(Node start){
        Queue<Node> queue = new LinkedList<Node>();
        start.beer = 20;
        queue.add(start);
        VISITED[start.num] = true;

        while (!queue.isEmpty()){
            Node now = queue.poll();

            if(now.x == ADJ[ADJ.length-1].get(0).x && now.y == ADJ[ADJ.length-1].get(0).y){
                ANS = "happy";
                return;
            }

            for(int i=1; i<N+1; i++){
                Node store = ADJ[i].get(0);
                if(now.x == store.x && now.y == store.y)
                    now.beer = 20;
            }

            for(Node n : ADJ[now.num]){
                if(!VISITED[n.num] && now.beer - getBeer(getDist(now.x, now.y, n.x, n.y)) >= 0){
                    VISITED[n.num] = true;
                    n.beer = now.beer - getBeer(getDist(now.x, now.y, n.x, n.y));
                    queue.add(n);
                }
            }
        }
    }

    public static int getBeer(int dist){
        return dist % 50 == 0 ? dist / 50 : dist / 50 + 1;
    }

    public static int getDist(int x1, int y1, int x2, int y2){
        return getDiff(x1, x2) + getDiff(y1, y2);
    }

    public static int getDiff(int a, int b){
        return a > b ? a - b : b- a;
    }
}

class Node{

    public Node(int x, int y, int num){
        this.x = x;
        this.y = y;
        this.num = num;
    }
    int num;
    int x;
    int y;
    int beer;
}


