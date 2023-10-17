package softeer;

import java.util.*;
import java.io.*;


public class _8단변속기
{
    static int[] TRANS;
    static String ans;

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        TRANS = new int[8];
        int prev;

        for(int i=0; i<8; i++)
            TRANS[i] = sc.nextInt();

        prev = TRANS[0];

        for(int i=1; i<8; i++){
            if(TRANS[i]>prev && isContinuos(TRANS[i], prev))
                ans = "ascending";
            else if(TRANS[i]<prev && isContinuos(TRANS[i], prev))
                ans = "descending";
            else{
                ans = "mixed";
                break;
            }
            prev = TRANS[i];
        }

        System.out.println(ans);
    }

    public static boolean isContinuos(int a, int b){
        if(a - b == 1 || b - a == 1)
            return true;
        return false;
    }
}
