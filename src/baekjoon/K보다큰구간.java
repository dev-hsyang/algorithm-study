package baekjoon;

import java.util.Scanner;

/**
 *  백준 14246
 *  실버 2
 */
public class K보다큰구간 {

    static int N;
    static long K, ANS;
    static long[] INPUT;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        INPUT = new long[N+1];
        for(int i=1; i<N+1; i++)
            INPUT[i] = sc.nextLong();
        K = sc.nextInt();

        operate();

        System.out.println(ANS);
    }

    /**
     * 처음에 구간합으로 시도했다가, 이중 for 문을 순회하여 답을 찾아야 했기에 시간초과 문제 발생
     * 이중 for 문 즉 ** 탐색했던 자리를 다시 탐색하는 ** 과정을 최적화하기 위해 투포인터 알고리즘을 적용했다.
     */
    public static void operate(){
        int left = 1;
        int right = 1;
        long sum = INPUT[1];

        while(right <= N){
            if(sum > K){
                // 현재 left 와 right 가 가리키는 구간의 합이 이미 N 을 넘어선다면, 배열 오른쪽의 나머지 값들을 더해도 어차피 N 을 넘어설 것이기에
                // 배열의 오른쪽에 있는 수만큼 ANS 에 더해줘버리고, left 포인터를 한칸 이동
                ANS += 1 + N - right;
                sum -= INPUT[left++];
            }else if(sum <= K){
                if(++right > N)
                    break;
                sum += INPUT[right];
            }
        }
    }
}
