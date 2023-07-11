package baekjoon.백트래킹;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 14502
 * 삼성 SW 역량 테스트 기출 문제
 * 골드 4
 */
public class 연구소 {

    static int N, M, ANS;
    static ArrayList<int[]> fire = new ArrayList<>();
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] MAP;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        MAP = new int[N][M];

        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++){
                MAP[i][j] = sc.nextInt();
                if(MAP[i][j] == 2)
                    fire.add(new int[] {i, j});
            }

        operate(0);

        System.out.println(ANS);
    }

    /**
     * 이 문제의 핵심 아이디어
     * 백트래킹하여 방화벽 3개를 새롭게 추가하는 방식이다.
     * 2차원 배열을 순회하며 벽을 추가하고, 벽 3개가 추가되었으면
     * updateMaxVal 을 실행하고 callback 한다.
     * @param count
     */
    public static void operate(int count){
        int[][] temp = new int[N][M];
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                temp[i][j] = MAP[i][j];

        if(count == 3){
            updateMaxVal();
            return;
        }

        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++){
                if(MAP[i][j] == 0){
                    MAP[i][j] = 1;
                    operate(count + 1);
                    for(int m=0; m<N; m++)
                        for(int n=0; n<M; n++)
                            MAP[m][n] = temp[m][n];
                }
            }
    }

    /**
     * MAP 에 벽 3개가 모두 추가된 후,
     * (1) 주어진 상태에서 불을 번지게 하고
     * (2) 불이 붙지 않은 칸을 구하여 최대값을 갱신한다.
     */
    public static void updateMaxVal(){
        fire(); // (1)
        ANS = Math.max(ANS, countVal()); // (2)
    }

    /**
     * 불이 번지는 동작을 BFS 로 구현한다.
     * 입력값으로 주어지는 불의 좌표를 큐에 받아오고 (1)
     * 탐색칸이 MAP 범위 내이고, 불이 붙을 수 있는 칸이면 해당 좌표를 큐에 삽입한다. (2)
     */
    public static void fire(){
        Queue<int[]> queue = new LinkedList<>(fire); // (1)

        while(!queue.isEmpty()){
            int[] cord = queue.poll();
            MAP[cord[0]][cord[1]] = 2;
            for(int i=0; i<4; i++){
                int nx = cord[0] + DX[i];
                int ny = cord[1] + DY[i];
                if(isInBound(nx, ny) && canBurn(nx, ny)){ // (2)
                    queue.add(new int[] {nx, ny});
                    MAP[nx][ny] = 2; // ** 오답 포인트 ** : 큐에 좌표를 추가하는 동작은 곧 그 좌표에 불이 번졌음을 의미한다. enqueue 를 할때, 좌표를 2로 최신화해주지 않아 bfs 과정에서 중복된 좌표가 큐에 삽입되어 (canBurn 컨디션에서 0 으로 확인하여 또 번질 수 있다고 판단함), 메모리초과가 발생했다.
                }
            }
        }
    }

    /**
     * 주어진 좌표가 MAP 내에 존재하는지 확인한다.
     * @param x
     * @param y
     * @return
     */
    public static boolean isInBound(int x, int y){
        return (x>=0 && x<N && y>=0 && y<M);
    }

    /**
     * 주어진 좌표에 불이 붙을 수 있는지 확인한다.
     * @param x
     * @param y
     * @return
     */
    public static boolean canBurn(int x, int y){
        return MAP[x][y] == 0;
    }

    /**
     * MAP 에서 0만 count 하여 불이 붙지 않았고, 벽이 아닌 칸의 갯수를 센다.
     * @return
     */
    public static int countVal(){
        int count = 0;
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                if(MAP[i][j] == 0){
                    count += 1;
                }
        return count;
    }
}

