package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 21611
 * 삼성 SW 역량테스트 2021 상반기 오후 2번 문제
 * 골드 1
 */
public class 마법사상어와블리자드 {

    static int N, M, D, P, ANS;
    static int[] DX = {0, -1, 1, 0, 0};
    static int[] DY = {0, 0, 0, -1, 1};
    static int[] COUNT = new int[4];
    static int[][] MAP;
    static int[][] INDEX_MAP;
    static boolean[][] VISITED;
    static Cord21611[] INDEX;
    static ArrayList<Integer> PAIR;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAP = new int[N][N];
        INDEX = new Cord21611[N * N - 1];
        INDEX_MAP = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++)
                MAP[i][j] = Integer.parseInt(st.nextToken());
        }
        initIndex();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            simulate();
        }

        for(int i=1; i<COUNT.length; i++)
            ANS += i * COUNT[i];

        System.out.println(ANS);
    }

    public static void simulate(){
        attack();
        move();
        explode();
        change();
    }

    public static void attack(){
        int mx = N / 2;
        int my = N / 2;
        for(int i=0; i<P; i++){
            mx += DX[D];
            my += DY[D];
            MAP[mx][my] = 0;
            INDEX[INDEX_MAP[mx][my]].num = 0;
        }
    }

    public static void move(){
        int cntZero = 0;
        boolean moved = false;
        for(int i=INDEX.length - 1; i>=0; i--){
            if(INDEX[i].num != 0){
                if(cntZero != 0){
                    moved = true;
                    INDEX[i + cntZero].num = INDEX[i].num;
                    INDEX[i].num = 0;
                    MAP[INDEX[i + cntZero].x][INDEX[i + cntZero].y] = INDEX[i + cntZero].num;
                    MAP[INDEX[i].x][INDEX[i].y] = 0;
                }
            }else if(INDEX[i].num == 0)
                cntZero++;
        }
    }

    public static void explode(){
        boolean erased = true;
        while(erased){
            erased = false;
            int cnt = 1;
            int temp = 0;
            for(int i=INDEX.length - 1; i>=0; i--){
                int t =  INDEX[i].num;
                if(t == temp){
                    cnt += 1;
                }else{
                    if(cnt != 1 && cnt >= 4){
                        erased = true;
                        for(int j=i+1; j<=i + cnt; j++){
                            COUNT[INDEX[j].num] += 1;
                            INDEX[j].num = 0;
                            MAP[INDEX[j].x][INDEX[j].y] = 0;
                        }
                    }
                    temp = t;
                    cnt = 1;
                }
            }

            if(erased)
                move();
        }
    }

    public static void change(){
        PAIR = new ArrayList<Integer>();
        int cnt = 1;
        int num = INDEX[INDEX.length - 1].num;
        for(int i = INDEX.length - 2; i>=0; i--) {
            int t = INDEX[i].num;
            if (t == num) {
                cnt++;
            } else {
                PAIR.add(cnt);
                PAIR.add(num);
                num = t;
                cnt = 1;
            }
            if (t == 0)
                break;
        }

        int idx = 0;
        for(int i = INDEX.length - 1; i>=0; i--){
            if(PAIR.size() == 0)
                break;
            INDEX[i].num = PAIR.get(idx);
            MAP[INDEX[i].x][INDEX[i].y] = PAIR.get(idx++);
            if(idx >= PAIR.size())
                break;
        }
    }

    public static void initIndex(){
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        VISITED = new boolean[N][N];
        VISITED[0][0] = true;
        INDEX[0] = new Cord21611(0, 0, MAP[0][0]);
        int x = 0;
        int y = 0;
        int d = 0;
        int idx = 1;
        while(!(x == N / 2 && y == N / 2 - 1)){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(!canGo(nx, ny) || VISITED[nx][ny]){
                d = (d + 1) % 4;
                nx = x + dx[d];
                ny = y + dy[d];
            }
            x = nx;
            y = ny;
            INDEX[idx] = new Cord21611(x, y, MAP[x][y]);
            INDEX_MAP[x][y] = idx++;
            VISITED[x][y] = true;
        }
    }

    public static boolean canGo(int x, int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
}

class Cord21611 {
    public Cord21611(int x, int y, int num){
        this.x = x;
        this.y = y;
        this.num = num;
    }
    int num;
    int x;
    int y;
}
