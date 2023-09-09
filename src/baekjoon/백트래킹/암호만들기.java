package baekjoon.백트래킹;

import java.beans.Visibility;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 1759
 * 골드 5
 */
public class 암호만들기 {

    static int L, C;
    static String[] ARR;
    static boolean[] VISITED;
    static BufferedWriter BW;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BW = new BufferedWriter(new OutputStreamWriter(System.out));
        L = sc.nextInt();
        C = sc.nextInt();
        ARR = new String[C];
        VISITED = new boolean[C];
        for(int i=0; i<C; i++)
            ARR[i] = sc.next();
        Arrays.sort(ARR);

        dfs(0, 0, 0);

        BW.flush();
        BW.close();
    }

    public static void dfs(int depth, int idx, int consonant) throws IOException {
        if(depth == L){
            String s = "";
            for(int i=0; i<C; i++)
                if(VISITED[i])
                    s += ARR[i];
            if(containsVowel(s) && consonant >= 2)
                BW.append(s + "\n");
            return;
        }

        for(int i=idx; i<C; i++){
            int cnt = consonant;
            if(!isVowel(ARR[i]))
                cnt += 1;
            VISITED[i] = true;
            dfs(depth + 1, i + 1, cnt);
            VISITED[i] = false;
        }
    }

    public static boolean containsVowel(String s){
        return (s.contains("a") || s.contains("e") || s.contains("i") || s.contains("o") || s.contains("u"));
    }

    public static boolean isVowel(String s){
        return s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u");
    }
}
