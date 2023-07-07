package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 백준 15683
 * 삼성 SW 역량 테스트 기출 문제
 * 골드 4
 */

public class 감시 {

    static int N, M;
    static int ANS = Integer.MAX_VALUE;
    static int[] DX = {-1, 0, 1, 0};
    static int[] DY = {0, 1, 0, -1};
    static int[][] BOARD;
    static int[][] PIECE_DIR;
    static ArrayList<Cord> PIECES = new ArrayList<Cord>(); // ** 핵심 ** ArrayList 를 사용하여 탐색할 체스말 정보 (좌표)를 동적으로 추가

    /**
     * 이 문제를 특히 어려워했던 이유
     * 체스말의 동작과정 시뮬레이션은 구현하였으나,
     * 각 체스말별로 독립적인 방향을 가지는 상황을 구현하지 못하였다.
     * DFS 를 이용한 backtracking 알고리즘을 구현하는데 시행착오가 많았고, 어려움이 컸었다.
     * DFS 에서 RECURSION 을 이용한 탐색을 진행할 때, 파라메터로 COUNT (또는 NUM)을 통해 return 컨디션을 정해야 함을 유념하고,
     * COUNT 로 사용할 조건/변수/상황을 무엇으로 할지 고민해야한다.
     *
     * 이 문제의 경우에는 '각각 체스말' 별로 가지는 방향의 조합을 RECURSIVE 하게 탐색한다.
     * 즉, '각각 체스말'이 DFS 의 파라메터가 된다.
     * ArrayList 자료구조를 사용하여 각 체스말 좌표를 add 해놓고, DFS 파라메터의 COUNT(NUM)가 체스말의 수(arraylist.size)만큼 탐색이 끝나면 return 조건이 된다.
     * 다시말해 DFS 에서 한번 탐색할 때 마다 한 체스말의 한 방향을 탐색하고, 재귀적으로 다음 체스말을 탐색해나가는 것이다.
     * 전체 체스말 수 만큼 탐색되면 최솟값을 찾고, return 하여 다음 방향으로 다시 재귀탐색한다.
     * @param args
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        BOARD = new int[N][M];
        PIECE_DIR = new int[N][M];
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++){
                BOARD[i][j] = sc.nextInt();
                if(isTeam(i, j))
                    PIECES.add(new Cord(i, j));
            }

        operate(0);

        System.out.println(ANS);
    }

    /**
     * DFS Backtracking
     * 각 체스말별로 북, 동, 남, 서 방향대로 재귀탐색한다. (1)
     * 체스말 수만큼 깊이 탐색이 완료되었으면 더이상 탐색할 체스말이 없는것이기에 (2)
     * 현재 체스말의 방향 상태에 따라 simulate 하여 (3)
     * 갈수없는 칸의 최솟값을 갱신한다. (4)
     * @param num
     */
    public static void operate(int num){
        if(num == PIECES.size()){ // (2)
            int[][] temp = new int[N][M];
            for(int i=0; i<N; i++)
                for(int j=0; j<M; j++)
                    temp[i][j] = BOARD[i][j];
            simulate(); // (3)
            updateMin(); //(4)
            for(int i=0; i<N; i++)
                for(int j=0; j<M; j++)
                    BOARD[i][j] = temp[i][j];
            return;
        }

        Cord piece = PIECES.get(num);
        int x = piece.x;
        int y = piece.y;

        for(int i=0; i<4; i++){ // (1)
            PIECE_DIR[x][y] = i;
            operate(num + 1);
        }
    }

    /**
     * 체스말들을 시뮬레이션한다.
     */
    public static void simulate(){
        for(Cord piece : PIECES)
            move(piece);
    }

    /**
     * ArrayList 상의 체스말 좌표를 통해
     * 체스말 번호 (1)
     * 현재 체스말 방향 (2)
     * 을 찾는다.
     * 주어진 체스말과 방향을 기준으로 채울수 있는 칸을 찾는다. (3)
     * @param piece
     */
    public static void move(Cord piece){
        int pieceNum = BOARD[piece.x][piece.y]; // (1)
        int curDir = PIECE_DIR[piece.x][piece.y]; // (2)

        if(pieceNum== 1){
            fill(piece, curDir); // (3)
        }else if(pieceNum == 2){
            fill(piece, curDir + 1 > 3 ? 0 : curDir + 1);
            fill(piece, curDir - 1 < 0 ? 3 : curDir - 1);
        }else if(pieceNum == 3){
            fill(piece, curDir);
            fill(piece, curDir + 1 > 3 ? 0 : curDir + 1);
        }else if(pieceNum == 4){
            fill(piece, curDir);
            fill(piece, curDir + 1 > 3 ? 0 : curDir + 1);
            fill(piece, curDir - 1 < 0 ? 3 : curDir - 1);
        }else if(pieceNum == 5){
            fill(piece, 0);
            fill(piece, 1);
            fill(piece, 2);
            fill(piece, 3);
        }
    }

    /**
     * 해당 칸에서 해당 방향을 기준으로 BOARD 의 끝까지 이동한다.
     * BOARD 의 밖이거나, 다른팀의 말이 있어 이동이 불가능한 경우를 처리한다. (1)
     * 이동하는 칸이 같은팀일 경우 BOARD 를 칠하는 과정을 생략하고 계속한다. (2)
     * @param piece
     * @param dir
     */
    public static void fill(Cord piece, int dir){
        int x = piece.x;
        int y = piece.y;
        while (true){
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if (!canGo(nx, ny)) // (1)
                break;
            if (isTeam(nx, ny)){ // (2)
                x = nx;
                y = ny;
                continue;
            }
            BOARD[nx][ny] = -1;
            x = nx;
            y = ny;
        }
    }

    /**
     * 체스말들을 이동시켜 갈 수 없는 칸의 최솟값을 갱신한다.
     */
    public static void updateMin(){
        int count = 0;
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                if(BOARD[i][j] == 0)
                    count += 1;
        ANS = Math.min(count, ANS);
    }

    public static boolean isTeam(int x, int y){
        return BOARD[x][y] >= 1 && BOARD[x][y] <= 5;
    }

    public static boolean canGo(int x, int y){
        return x>=0 && x<N && y>=0 && y<M && BOARD[x][y] != 6;
    }
}

class Cord{
    public Cord(int x, int y){
        this.x = x;
        this.y = y;
    }
    int x, y;
}

