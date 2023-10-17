package softeer;

import java.util.*;
import java.io.*;


public class 회의실예약
{
    static int N, M;
    static String[] ROOMS;
    static HashMap<String, ArrayList<int[]>> INFO = new HashMap<String, ArrayList<int[]>>();

    public static void main(String args[]) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = sc.nextInt();
        M = sc.nextInt();
        ROOMS = new String[N];
        for(int i=0; i<N; i++)
            ROOMS[i] = sc.next();
        for(String s : ROOMS)
            INFO.put(s, new ArrayList<int[]>());
        for(int i=0; i<M; i++){
            String room = sc.next();
            int start = sc.nextInt();
            int end = sc.nextInt();
            INFO.get(room).add(new int[] {start, end});
        }

        operate(bw);
    }

    public static void operate(BufferedWriter bw) throws IOException{
        Arrays.sort(ROOMS);
        for(int i=0; i<ROOMS.length; i++){
            String room = ROOMS[i];
            bw.append("Room " + room + ": " + "\n");
            ArrayList<int[]> arr = INFO.get(room);
            ArrayList<String> val = new ArrayList<String>();
            Collections.sort(arr, new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    return o1[0] - o2[0];
                }
            });

            String start = "09";
            String end = "09";
            for(int j=0; j<arr.size(); j++){
                int s = arr.get(j)[0];
                int e = arr.get(j)[1];
                if(Integer.parseInt(start) < s)
                    val.add(start + "-" + String.valueOf(s));
                start = String.valueOf(e);
            }
            if(!start.equals("18"))
                val.add(start + "-" + "18");

            if(val.size() == 0)
                bw.append("Not available" + "\n");
            else{
                bw.append(val.size() + " available: " + "\n");
                for(int j=0; j<val.size(); j++)
                    bw.append(val.get(j) + "\n");
            }

            if(i < ROOMS.length - 1)
                bw.append("----- " + "\n");
        }

        bw.flush();
        bw.close();
    }
}