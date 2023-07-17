package baekjoon.자료구조;

import java.util.*;
import java.io.*;

/**
 * 백준 19583
 * 실버 2
 */
public class 싸이버개강총회 {

    static int ANS;
    static String S, E, Q;
    static Set<String> ATTENDANCE = new HashSet<String>();
    static Set<String> EXIT = new HashSet<String>();
    static Set<String> ATTENDED = new HashSet<String>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = st.nextToken();
        E = st.nextToken();
        Q = st.nextToken();
        String str;
        while((str = br.readLine()) != null && !str.isEmpty()){
            st = new StringTokenizer(str);
            String time = st.nextToken();
            String name = st.nextToken();
            if(isBeforeStart(time))
                ATTENDANCE.add(name);
            if(isAfterEnd(time))
                EXIT.add(name);
        }

        check();

        System.out.println(ANS);
    }

    public static boolean isBeforeStart(String time){
        return time.compareTo(S) <= 0;
    }

    public static boolean isAfterEnd(String time){
        return time.compareTo(E) >= 0 && time.compareTo(Q) <= 0;
    }

    public static void check(){
        Iterator<String> iterator = ATTENDANCE.iterator();
        while(iterator.hasNext()){
            String attendee = iterator.next();
            if(EXIT.contains(attendee))
                ATTENDED.add(attendee);
        }
        ANS = ATTENDED.size();
    }

}
