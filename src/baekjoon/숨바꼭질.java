package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 실버 1
 * DFS BFS 필수문제
 * 백준 1697
 */
public class 숨바꼭질 {

    static int N, K, ANS;
    static boolean[] VISITED = new boolean[100001];

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        operate(new Pair(N, 0));

        System.out.println(ANS);
    }

    public static void operate(Pair pair){
        Queue<Pair> queue = new LinkedList<>();
        queue.add(pair);
        VISITED[pair.num] = true;

        while(!queue.isEmpty()){
            Pair now = queue.poll();
            if(now.num == K){
                ANS = now.count;
                break;
            }
            if(isInbound(now.moveLeft()) && !VISITED[now.moveLeft()]){
                VISITED[now.moveLeft()] = true;
                queue.add(new Pair(now.moveLeft(), now.count + 1));
            }
            if(isInbound(now.moveRight()) && !VISITED[now.moveRight()]){
                VISITED[now.moveRight()] = true;
                queue.add(new Pair(now.moveRight(), now.count + 1));
            }
            if(isInbound(now.jump()) && !VISITED[now.jump()]){
                VISITED[now.jump()] = true;
                queue.add(new Pair(now.jump(), now.count + 1));
            }
        }
    }

    public static boolean isInbound(int n){
        return n>=0 && n<=100000;
    }
}

class Pair{

    public Pair(int num, int count){
        this.num = num;
        this.count = count;
    }
    int num;
    int count;

    public int moveLeft(){
        return num - 1;
    }

    public int moveRight(){
        return num + 1;
    }

    public int jump(){
        return num * 2;
    }
}
