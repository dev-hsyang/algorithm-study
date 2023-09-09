package codetree.삼성SW역량테스트;

import java.util.*;

/**
 * 실버 2
 * 삼성 SW 역량테스트 2017 하반기 오전 1번 문제
 */
public class 조삼모사 {

    static int N;
    static int ANS = Integer.MAX_VALUE;
    static int[][] BOARD;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N  = sc.nextInt();
        BOARD = new int[N+1][N+1];
        for(int i=1; i<N+1; i++)
            for(int j=1; j<N+1; j++)
                BOARD[i][j] = sc.nextInt();

        dfs(0, 1, new int[N/2]);

        System.out.println(ANS);
    }

    /**
     * 백트래킹을 통해 N 개의 작업 중 N/2 개의 작업 조합을 찾아낸다.
     * @param depth
     * @param num
     * @param arr
     */
    public static void dfs(int depth, int num, int[] arr){
        if(depth == N/2){
            ANS = Math.min(ANS, calculate(arr));
            return;
        }

        for(int i=num; i<N+1; i++){
            arr[depth] = i;
            dfs(depth + 1, i + 1, arr);
        }
    }

    /**
     * 핵심 아이디어
     * dfs 를 통해, N 개 중에서 N/2 개만큼의 조합을 뽑았다.
     * 뽑은 조합은 아침(또는 저녁)에 하는 일의 조합을 의미, 즉 *** 특정 조합에서 뽑히지 않은 숫자는 반대 시간대에 하는 일을 뜻한다. ***
     * 찾아낸 아침 작업 조합을 가지고 저녁 작업 조합을 찾아낼 수 있다.
     *
     * 아침작업, 점심작업을 통해 sum 값을 더해간다.
     * @param arr
     * @return
     */
    public static int calculate(int[] arr){
        int morningSum = 0;
        int nightSum = 0;
        boolean[] morning = new boolean[N+1];
        for(int i : arr)
            morning[i] = true;

        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                if(morning[i] && morning[j]){
                    morningSum += BOARD[i][j];
                }else if(!morning[i] && !morning[j]){
                    nightSum += BOARD[i][j];
                }
            }
        }

        return morningSum > nightSum ? morningSum - nightSum : nightSum - morningSum;
    }
}
