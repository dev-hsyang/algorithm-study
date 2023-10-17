package softeer;

import java.util.*;
import java.io.*;


public class 지도자동구축
{
    static int N, ANS;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        int dotPerLine = 1 + (int)Math.pow(2, N);
        ANS = dotPerLine * dotPerLine;
        System.out.println(ANS);
    }
}
