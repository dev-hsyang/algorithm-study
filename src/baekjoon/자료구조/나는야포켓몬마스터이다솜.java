package baekjoon.자료구조;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 백준 1620
 * 실버 4
 */
public class 나는야포켓몬마스터이다솜 {

    static int N, M;
    static HashMap<String, String> NUM_MAP = new HashMap<String, String >();
    static HashMap<String, String> NAME_MAP = new HashMap<String, String>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        for(int i=1; i<N+1;i ++){
            String name = sc.next();
            NUM_MAP.put(Integer.toString(i), name);
            NAME_MAP.put(name, Integer.toString(i));
        }

        for(int i=0; i<M; i++){
            String query = sc.next();
            if(NUM_MAP.containsKey(query))
                System.out.println(NUM_MAP.get(query));
            else if(NAME_MAP.containsKey(query))
                System.out.println(NAME_MAP.get(query));
        }
    }
}
