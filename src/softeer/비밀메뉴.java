package softeer;

import java.util.*;
import java.io.*;


public class 비밀메뉴
{
    static int M, N, K;
    static int[] INPUT;
    static int[] WINDOW;
    static String SECRET = "secret";
    static String NORMAL = "normal";

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        K = sc.nextInt();
        WINDOW = new int[M];
        INPUT = new int[N];
        for(int i=0; i<M; i++)
            WINDOW[i] = sc.nextInt();
        for(int i=0; i<N; i++)
            INPUT[i] = sc.nextInt();

        if(slideWindow())
            System.out.println(SECRET);
        else
            System.out.println(NORMAL);
    }

    public static boolean slideWindow(){
        int leftIdx = 0;
        int rightIdx = 0;
        while(rightIdx <= N){
            if(rightIdx - leftIdx == M){
                int idx = 0;
                for(int i=leftIdx; i<rightIdx; i++){
                    if(INPUT[i] != WINDOW[idx++])
                        break;
                    if(i == rightIdx - 1)
                        return true;
                }
                leftIdx++;
            }else{
                rightIdx++;
            }
        }
        return false;
    }
}
