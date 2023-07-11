package codetree.삼성SW역량테스트;

import java.util.Scanner;

/**
 * 골드 5
 * 삼성 SW 역량테스트 2017 상반기 오후 1번 문제
 */
public class 자율주행자동차 {

    static int N, M, ANS;
    static int[] DX = {-1, 0, 1, 0};
    static int[] DY = {0, 1, 0, -1};
    static int[][] MAP;
    static boolean done = false;
    static boolean[][] VISITED;
    static Car car;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        MAP = new int[N][M];
        VISITED = new boolean[N][M];

        car = new Car(sc.nextInt(), sc.nextInt(), sc.nextInt());
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                MAP[i][j] = sc.nextInt();

        operate(car);

        System.out.println(ANS);
    }

    /**
     * DFS 하여 탐색한다.
     * @param car
     */
    public static void operate(Car car){

        /**
         * 아직 recursion 이 유효한지 판단한다.
         */
        if(done)
            return;

        if(!VISITED[car.x][car.y])
            ANS += 1;

        VISITED[car.x][car.y] = true;
        Car temp = new Car(car.x, car.y, car.dir);

        /**
         * (1) 좌회전 하면서
         * (2) 이동가능한 칸이면 (아직 방문하지 않았고, 인도가 아닌 칸)
         * (3) 해당 칸으로 이동하여 recursive 하게 반복한다.
         */
        for(int i=0; i<4; i++){
            car.turnLeft(); // (1)
            int nx = car.x + DX[car.dir];
            int ny = car.y + DY[car.dir];
            if(isInbound(nx, ny) && canGo(nx, ny) && !VISITED[nx][ny]){ // (2)
                car.x = nx;
                car.y = ny;

                operate(car); // (3)

                car = new Car(temp.x, temp.y, temp.dir);
            }
        }

        /**
         * 좌회전하며 주변칸을 둘러보아도 이동가능한 칸이 없을 경우
         * (1) 후진이 가능하다면 (방문여부 상관 X, 인도가 아니면 후진가능)
         * (2) 이동방향을 유지한채 후진하여, recursive 하게 반복한다.
         */
        if(canGo(car.x + DX[car.getBackDir()], car.y + DY[car.getBackDir()])){ // (1)
            car.x = car.x + DX[car.getBackDir()];
            car.y = car.y + DY[car.getBackDir()];
            operate(car); // (2)
        }

        /**
         * *** 오답 지점 ***
         * 좌회전하며 주변칸을 둘러보아도 이동가능한 칸이 없고,
         * 후진조차 불가능한 상황이라면 recursion 을 종료하기 위해 flag 처리를 한다.
         */
        done = true;
    }

    /**
     * 좌표가 MAP 내에 존재하는지 확인한다.
     * @param x
     * @param y
     * @return
     */
    public static boolean isInbound(int x, int y){
        return (x>=0 && x<N && y>=0 && y<M);
    }

    /**
     * 좌표가 도로인지 확인한다.
     * @param x
     * @param y
     * @return
     */
    public static boolean canGo(int x, int y){
        return (isInbound(x, y) && MAP[x][y] == 0);
    }
}

class Car{

    public Car(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    int x;
    int y;
    int dir;

    public void turnLeft(){
        if(dir == 0)
            dir = 3;
        else
            dir -= 1;
    }

    public int getBackDir(){
        if(dir == 0)
            return 2;
        else if(dir == 1)
            return 3;
        else if(dir == 2)
            return 0;
        else
            return 1;
    }
}