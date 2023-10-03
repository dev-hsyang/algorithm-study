package baekjoon.구현;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * 백준 17837
 * 삼성 SW 역량테스트 2019 하반기 오전 2번 문제
 * 골드 2
 */
public class 새로운게임2 {

    static int N, K, ANS;
    static int[] DX = {0, 0, 0, -1, 1};
    static int[] DY = {0, 1, -1, 0, 0};
    static boolean DONE;
    static Cord17837[][] BOARD;
    static ArrayList<int[]> CHESS = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        BOARD = new Cord17837[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                BOARD[i][j] = new Cord17837(sc.nextInt());
        for(int i=0; i<K; i++){
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            int d = sc.nextInt();
            CHESS.add(new int[] {x, y, d});
            BOARD[x][y].stack.push(i);
        }

        while(!DONE)
            simulate();

        System.out.println(ANS);
    }

    public static void simulate(){
        ANS++;
        for(int i=0; i<CHESS.size(); i++)
            move(i, CHESS.get(i));
    }

    public static void move(int num, int[] cord){
        Cord17837 now = BOARD[cord[0]][cord[1]];
        Stack<Integer> temp = new Stack<>();

        while(true){
            int n = now.stack.pop();
            temp.add(n);
            if(n == num)
                break;
        }

        int nx = cord[0] + DX[CHESS.get(num)[2]];
        int ny = cord[1] + DY[CHESS.get(num)[2]];
        if(isInbound(nx, ny)){
            if(BOARD[nx][ny].color == 0)
                white(nx, ny, temp);
            else if(BOARD[nx][ny].color == 1)
                red(nx, ny, temp);
            else if(BOARD[nx][ny].color == 2)
                blue(cord[0], cord[1], temp);
        }else
            blue(cord[0], cord[1], temp);

        check();
    }

    public static void white(int x, int y, Stack<Integer> stack){
        while(!stack.isEmpty()){
            int n = stack.pop();
            BOARD[x][y].stack.push(n);
            CHESS.set(n, new int[] {x, y, CHESS.get(n)[2]});
        }
    }

    public static void red(int x, int y, Stack<Integer> stack){
        Stack<Integer> temp = new Stack<>();
        while(!stack.isEmpty())
            temp.push(stack.pop());
        while(!temp.isEmpty()){
            int n = temp.pop();
            BOARD[x][y].stack.push(n);
            CHESS.set(n, new int[] {x, y, CHESS.get(n)[2]});
        }
    }

    public static void blue(int curX, int curY, Stack<Integer> stack){
        int curDir = CHESS.get(stack.peek())[2];
        int nextDir = reverse(curDir);
        CHESS.set(stack.peek(), new int[] {curX, curY, nextDir});

        int nx = curX + DX[nextDir];
        int ny = curY + DY[nextDir];

        if(isInbound(nx, ny) && BOARD[nx][ny].color == 0)
            white(nx, ny, stack);
        else if(isInbound(nx, ny) && BOARD[nx][ny].color == 1)
            red(nx, ny, stack);
        else
            while(!stack.isEmpty())
                BOARD[curX][curY].stack.push(stack.pop());
    }

    public static boolean isInbound(int x, int y){
        return x>=0 && x<N && y>=0 && y<N;
    }

    public static void check(){
        if(ANS > 1000){
            DONE = true;
            ANS = -1;
            return;
        }
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(BOARD[i][j].stack.size() >= 4)
                    DONE = true;
    }

    public static int reverse(int dir){
        if(dir == 1)
            return 2;
        else if(dir == 2)
            return 1;
        else if(dir == 3)
            return 4;
        else
            return 3;
    }
}

class Cord17837{
    public Cord17837(int color){
        this.color = color;
    }
    int color;
    Stack<Integer> stack = new Stack<Integer>();
}
