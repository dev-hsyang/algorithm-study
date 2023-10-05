package baekjoon.구현;

import java.util.Scanner;

/**
 * 백준 17825
 * 삼성 SW 역량테스트 2019 하반기 오후 2번 문제
 * 골드 2
 */
public class 주사위윷놀이 {

    static int ANS;
    static int[] TURN = new int[10];
    static int[] ORDER = new int[10];
    static int[] BOARD = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0
            , 10, 13, 16, 19, 25, 30, 35, 40, 0
            , 20, 22, 24, 25, 30, 35, 40, 0
            , 30, 28, 27, 26, 25, 30, 35, 40, 0};
    static boolean[] PLACED;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++)
            TURN[i] = sc.nextInt();

        simulate();

        System.out.println(ANS);
    }

    public static void simulate(){
        findOrder(0);
    }

    public static void findOrder(int cnt){
        if(cnt == 10){
            play();
            return;
        }

        for(int i=0; i<4; i++){
            ORDER[cnt] = i;
            findOrder(cnt + 1);
        }
    }

    public static void play(){
        PLACED = new boolean[BOARD.length];
        int sum = 0;
        int[] index = new int[4]; // 매 턴 각 말들의 위치

        for(int i=0; i<10; i++){
            int nowTurn = TURN[i]; // i 번째에 이동할 크기
            int nowChess = ORDER[i]; // i 번째에 굴릴 말
            if(done(index[nowChess]))
                return;

            int next = nextIndex(index[nowChess], nowTurn);
            if(done(next)){
                placeChess(index[nowChess], false);
                index[nowChess] = next;
                continue;
            }

            if(PLACED[next])
                return;

            placeChess(index[nowChess], false);
            placeChess(next, true);

            index[nowChess] = next;
            sum += BOARD[next];
        }
        ANS = Math.max(ANS, sum);
    }

    public static int nextIndex(int now, int val){
        int next = now + val;
        if(now < 21){
            if(next >= 21)
                next = 21;
        }else if(now >= 21 && now < 30){
            if(next >= 30)
                next = 30;
        }else if(now >= 30 && now < 38){
            if(next >= 38)
                next = 38;
        }else if(now >= 38 && now < 47){
            if(next >= 47)
                next = 47;
        }

        if(next == 5)
            next = 22;
        if(next == 10)
            next = 31;
        if(next == 15)
            next = 39;
        return next;
    }

    public static void placeChess(int index, boolean place){
        if(index == 20 || index == 29 || index == 37 || index == 46){ // 말이 40점에 위치할경우 index 가 다를 수 있으니, 40점인 모든 index 에 대해 표시
            PLACED[20] = place;
            PLACED[29] = place;
            PLACED[37] = place;
            PLACED[46] = place;
        }else if(index == 26 || index == 34 || index == 43){ // 말이 25점에 위치한경우
            PLACED[26] = place;
            PLACED[34] = place;
            PLACED[43] = place;
        }else if(index == 27 || index == 35 || index == 44){ // 30
            PLACED[27] = place;
            PLACED[35] = place;
            PLACED[44] = place;
        }else if(index == 28 || index == 36 || index == 45){
            PLACED[28] = place;
            PLACED[36] = place;
            PLACED[45] = place;
        }else
            PLACED[index] = place;
    }

    public static boolean done(int index){
        return index == 21 || index == 30 || index == 38 || index == 47;
    }
}
