package softeer;

import java.util.*;
import java.io.*;

/**
 * 소프티어 레벨 3
 */
public class 강의실배정
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] info = new int[N][2];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            info[i][0] = startTime;
            info[i][1] = endTime;
        }

        Arrays.sort(info, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[1] == o2[1])
                    return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        int endTime = info[0][1];
        int ans = 1;
        for(int i=1; i<N; i++){
            if(info[i][0] >= endTime){
                endTime = info[i][1];
                ans++;
            }
        }
        System.out.println(ans);
    }
}
