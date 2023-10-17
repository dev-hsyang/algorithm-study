package baekjoon.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1940
 * 실버 4
 */
public class 주몽 {

    static int N, M, ANS;
    static int[] ARR;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        ARR = new int[N];
        for(int i=0; i<N; i++)
            ARR[i] = Integer.parseInt(st.nextToken());

        twoPointer();

        System.out.println(ANS);
    }

    public static void twoPointer(){
        Arrays.sort(ARR);
        int left = 0;
        int right = N - 1;
        int sum = ARR[left] + ARR[right];
        while(left < right){
            if(sum < M){
                left++;
                sum = ARR[left] + ARR[right];
            }else if(sum > M){
                right--;
                sum = ARR[left] + ARR[right];
            }else if(sum == M){
                ANS++;
                left++;
                sum = ARR[left] + ARR[right];
            }
        }
    }
}
