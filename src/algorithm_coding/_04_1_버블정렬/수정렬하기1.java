package algorithm_coding._04_1_버블정렬;

import java.util.Scanner;

/**
 * 백준 2750
 * 브론즈 2
 */
public class 수정렬하기1 {

    static int N;
    static int[] ARR;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        ARR = new int[N];
        for(int i=0; i<N; i++)
            ARR[i] = sc.nextInt();

        operate();
        print();
    }

    public static void operate(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N-1; j++){
                if(ARR[j] > ARR[j+1]){
                    int temp = ARR[j];
                    ARR[j] = ARR[j+1];
                    ARR[j+1] = temp;
                }
            }
        }
    }

    public static void print(){
        for(int i=0; i<N; i++)
            System.out.println(ARR[i]);
    }
}
