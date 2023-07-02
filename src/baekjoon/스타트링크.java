package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 실버 1
 * DFS BFS 필수문제
 * 백준 5014
 */
public class 스타트링크 {

    static int F, S, G, U, D, ANS;
    static boolean ELEVATOR;
    static boolean[] VISITED;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        F = sc.nextInt();
        S = sc.nextInt();
        G = sc.nextInt();
        U = sc.nextInt();
        D = sc.nextInt();
        VISITED = new boolean[F+1];

        operate(new int[] {S, 0});


        if(ELEVATOR)
            System.out.println(ANS);
        else
            System.out.println("use the stairs");
    }

    public static void operate(int[] pair){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(pair);
        VISITED[pair[0]] = true;
        while (!queue.isEmpty()){
            int[] now = queue.poll();
            if(now[0] == G){
                ANS = now[1];
                ELEVATOR = true;
                break;
            }
            if(isInbound(now[0] + U) && !VISITED[now[0] + U]){
                VISITED[now[0] + U] = true;
                queue.add(new int[] {now[0] + U, now[1] + 1});
            }
            if(isInbound(now[0] - D) && !VISITED[now[0] - D]){
                VISITED[now[0] - D] = true;
                queue.add(new int[] {now[0] - D, now[1] + 1});
            }
        }
    }

    public static boolean isInbound(int n){
        return n>=1 && n<= F;
    }
}