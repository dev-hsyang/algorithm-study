package baekjoon;

import java.util.Scanner;

/**
 * 백준 12891
 * 실버 2
 */
public class DNA비밀번호 {

    static int S, P, ANS;
    static int[] CHECK;
    static int[] VERIFY;
    static String INPUT;
    static String[] DNA;

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();
        P = sc.nextInt();
        INPUT = sc.next();
        CHECK = new int[4];
        DNA = INPUT.split("");
        for(int i=0; i<4; i++)
            CHECK[i] = sc.nextInt();

        operate();

        System.out.println(ANS);
    }

    public static void operate(){
        int window_start = 1;
        int window_end = 1;
        int window_previous = 0;
        VERIFY = new int[4];
        for(int i=0; i<P; i++)
            add(i);

        if(verify())
            ANS += 1;

        while(window_end < S-1){
            window_end = window_start + P - 1;

            add(window_end);
            delete(window_previous);
            if(verify())
                ANS += 1;

            window_previous += 1;
            window_start += 1;
        }
    }

    public static boolean verify(){
        for(int i=0; i<4; i++)
            if(VERIFY[i] < CHECK[i])
                return false;
        return true;
    }

    public static void add(int i){
        if(DNA[i].equals("A"))
            VERIFY[0] += 1;
        else if(DNA[i].equals("C"))
            VERIFY[1] += 1;
        else if(DNA[i].equals("G"))
            VERIFY[2] += 1;
        else if(DNA[i].equals("T"))
            VERIFY[3] += 1;
    }

    public static void delete(int i){
        if(DNA[i].equals("A"))
            VERIFY[0] -= 1;
        else if(DNA[i].equals("C"))
            VERIFY[1] -= 1;
        else if(DNA[i].equals("G"))
            VERIFY[2] -= 1;
        else if(DNA[i].equals("T"))
            VERIFY[3] -= 1;
    }

}
