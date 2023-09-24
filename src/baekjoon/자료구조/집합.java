package baekjoon.자료구조;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 백준 11723
 * 실버 5
 */
public class 집합 {

    public static int M, INPUT;
    public static String OPERATOR;
    public static Set<Integer> HASHSET = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int tc=0; tc<M; tc++){
            st = new StringTokenizer(br.readLine());
            OPERATOR = st.nextToken();
            operate(OPERATOR, st, bw);
        }
        bw.flush();
        bw.close();
    }

    public static void operate(String operator, StringTokenizer st, BufferedWriter bw) throws IOException {
        if(operator.equals("add")){
            INPUT = Integer.parseInt(st.nextToken());
            HASHSET.add(INPUT);
        }else if(operator.equals("remove")){
            INPUT = Integer.parseInt(st.nextToken());
            HASHSET.remove(INPUT);
        }else if(operator.equals("check")){
            INPUT = Integer.parseInt(st.nextToken());
            if(HASHSET.contains(INPUT))
                bw.append("1" + "\n");
            else
                bw.append("0" + "\n");
        }else if(operator.equals("toggle")){
            INPUT = Integer.parseInt(st.nextToken());
            if(HASHSET.contains(INPUT))
                HASHSET.remove(INPUT);
            else
                HASHSET.add(INPUT);
        }else if(operator.equals("all")){
            HASHSET.clear();
            for(int i=1; i<=20; i++)
                HASHSET.add(i);
        }else if(operator.equals("empty")){
            HASHSET = new HashSet<Integer>();
        }
    }
}
