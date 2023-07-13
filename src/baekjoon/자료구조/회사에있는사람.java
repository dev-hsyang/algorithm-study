package baekjoon.자료구조;

import java.util.*;

/**
 * 백준 7785
 * 실버 5
 */
public class 회사에있는사람 {

    static int N;
    static ArrayList<String> ANS = new ArrayList<String>();
    static Set<String> SET = new HashSet<String>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for(int i=0; i<N; i++){
            String name = sc.next();
            String stat = sc.next();
            if(stat.equals("enter"))
                SET.add(name);
            else if(stat.equals("leave"))
                SET.remove(name);
        }

        Iterator<String> iter = SET.iterator();
        while(iter.hasNext()){
            ANS.add(iter.next());
        }

        Collections.sort(ANS, Collections.reverseOrder());

        for(String s : ANS)
            System.out.println(s);
    }
}
