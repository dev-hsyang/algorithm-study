package algorithm_coding._05_3_이진탐색;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 1920
 * 실버 4
 */
public class 원하는정수찾기 {

    static int N, M;
    static int[] ARR;
    static boolean FIND;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        ARR = new int[N];
        for(int i=0; i<N; i++)
            ARR[i] = sc.nextInt();
        Arrays.sort(ARR);
        M = sc.nextInt();

        for(int i=0; i<M; i++){
            FIND = false;
            int target = sc.nextInt();

            int start = 0;
            int end = ARR.length - 1;
            while (start <= end){
                int midi = (start + end) / 2;
                int midV = ARR[midi];

                if(midV > target)
                    end = midi - 1;
                else if(midV < target)
                    start = midi + 1;
                else{
                    FIND = true;
                    break;
                }
            }
            if(FIND)
                System.out.println(1);
            else
                System.out.println(0);
        }
    }
}
