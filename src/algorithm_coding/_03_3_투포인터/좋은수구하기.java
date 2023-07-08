package algorithm_coding._03_3_투포인터;

import java.util.Arrays;
import java.util.Scanner;

public class 좋은수구하기 {

    static int N, ANS;
    static int[] ARR;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        ARR = new int[N];
        for(int i=0; i<N; i++)
            ARR[i] = sc.nextInt();
        Arrays.sort(ARR);

        operate();

        System.out.println(ANS);
    }

    public static void operate(){
        for(int i=0; i<N; i++){
            int n = ARR[i];
            int sum = 0;
            int start_idx = 0;
            int end_idx = N-1;
            while (start_idx < end_idx){
                sum = ARR[start_idx] + ARR[end_idx];

                if(sum == n){
                    if(start_idx != i && end_idx != i){
                        ANS += 1;
                        break;
                    }
                    if (start_idx == i)
                        start_idx += 1;
                    if (end_idx == i)
                        end_idx -= 1;
                }
                else if(sum > n){
                    end_idx -= 1;
                }
                else if(sum < n){
                    start_idx += 1;
                }
            }
        }
    }
}