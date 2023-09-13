package baekjoon.구현;

import java.util.*;

/**
 * 백준 15686
 * 골드 5
 * 삼성 SW 역량테스트 2018 상반기 오후 2번 문제
 */
public class 치킨배달 {

    static int N, M, ANS;
    static int[][] CITY;
    static boolean[] CHOSEN;
    static ArrayList<int[]> HOSPITALS = new ArrayList<int[]>();
    static ArrayList<int[]> PERSON = new ArrayList<int[]>();
    static Set<boolean[]> COMBINATIONS = new HashSet<boolean[]>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        ANS = Integer.MAX_VALUE;
        CITY = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                CITY[i][j] = sc.nextInt();
                if(CITY[i][j] == 2)
                    HOSPITALS.add(new int[] {i, j});
                else if(CITY[i][j] == 1)
                    PERSON.add(new int[] {i, j});
            }
        }

        simulate();

        System.out.println(ANS);
    }

    public static void simulate(){
        CHOSEN = new boolean[HOSPITALS.size()];
        getCombination(0, 0);
        Iterator<boolean[]> iter = COMBINATIONS.iterator();

        while(iter.hasNext()){
            int sum = 0;
            ArrayList<Integer> combHospitals = new ArrayList<Integer>();

            boolean[] hospitals = iter.next();
            for(int i=0; i<HOSPITALS.size(); i++)
                if(hospitals[i])
                    combHospitals.add(i);

            for(int i=0; i<PERSON.size(); i++){
                int minDist = Integer.MAX_VALUE;
                int[] now = PERSON.get(i);
                for(int j=0; j<combHospitals.size(); j++){
                    int[] hos = HOSPITALS.get(combHospitals.get(j));
                    minDist = Math.min(calculDist(now[0], now[1], hos[0], hos[1]), minDist);
                }
                sum += minDist;
            }

            ANS = Math.min(ANS, sum);
        }
    }

    /**
     * 전체 병원 중 M 개의 병원을 고르는 조합을 backtracking 으로 찾는다.
     * @param depth
     */
    public static void getCombination(int depth, int index){
        if(depth == M){
            boolean[] temp = new boolean[HOSPITALS.size()];
            for(int i=0; i<HOSPITALS.size(); i++)
                if(CHOSEN[i])
                    temp[i] = true;
            COMBINATIONS.add(temp);
            return;
        }

        for(int i=index; i<HOSPITALS.size(); i++){
            CHOSEN[i] = true;
            getCombination(depth + 1, i + 1);
            CHOSEN[i] = false;
        }
    }

    public static boolean canGo(int x, int y){
        return (x>=0 && x<N && y>=0 && y<N);
    }

    public static int calculDist(int x1, int y1, int x2, int y2){
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }
}

