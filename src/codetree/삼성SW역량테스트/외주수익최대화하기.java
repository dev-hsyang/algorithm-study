package codetree.삼성SW역량테스트;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 실버 3
 * 삼성 SW 역량테스트 2017 상반기 오전 2번 문제
 */
public class 외주수익최대화하기 {

    static int N, ANS;
    static ArrayList<Time> TIMELINE = new ArrayList<Time>();
    static ArrayList<Integer> PROFITS = new ArrayList<>();
    static ArrayList<Integer> SELECTED_WORK = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for(int i=1; i<=N; i++){
            int time = sc.nextInt();
            int profit = sc.nextInt();
            TIMELINE.add(new Time(i, i + time - 1));
            PROFITS.add(profit);
        }

        findMax(0);

        System.out.println(ANS);
    }

    /**
     * DFS 로 모든 경우의 수를 탐색한다.
     * @param index
     */
    public static void findMax(int index){
        /**
         * 더이상 탐색할 외주작업이 없으면,
         * 지금까지 추가한 외주작업들이 조건에 맞게 수행이 가능한지 판단하고
         * 가능하면 최댓값을 갱신한다.
         */
        if(index == N){
            if(isAvailable())
                ANS = Math.max(ANS, getProfit());
            return;
        }

        /**
         * 현재 index 의 외주작업을 수행하지 않는 경우에 대해 recursive 하게 탐색
         */
        findMax(index + 1);

        /**
         * 현재 index 의 외주작업을 수행하는 경우에 대해 recursive 하게 탐색
         */
        SELECTED_WORK.add(index);
        findMax(index + 1);
        SELECTED_WORK.remove(SELECTED_WORK.size() - 1);
    }

    /**
     * 지금까지 추가한 외주작업들이 수행 가능한 조건에 맞는지 판단
     * (1) 외주작업 기간이 겹치거나 (이전 외주작업이 안끝났는데 다음 외주작업이 시작되는 경우)
     * (2) 외주작업의 종료일이 휴가기간을 초과하는 경우
     * 외주작업 수행이 불가능하다
     * @return
     */
    public static boolean isAvailable(){
        for(int i = 0; i< SELECTED_WORK.size() - 1; i++){ // (1)
            if(TIMELINE.get(SELECTED_WORK.get(i)).end >= TIMELINE.get(SELECTED_WORK.get(i+1)).start)
                return false;
        }

        for(int i = 0; i< SELECTED_WORK.size(); i++){ // (2)
            if(TIMELINE.get(SELECTED_WORK.get(i)).end > N)
                return false;
        }
        return true;
    }

    public static int getProfit(){
        int val = 0;
        for(int i = 0; i< SELECTED_WORK.size(); i++)
            val += PROFITS.get(SELECTED_WORK.get(i));
        return val;
    }
}

/**
 * 외주작업의 시작일과 종료일
 */
class Time{
    public Time(int start, int end) {
        this.start = start;
        this.end = end;
    }
    int start;
    int end;
}

