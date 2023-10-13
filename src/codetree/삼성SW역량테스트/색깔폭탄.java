package codetree.삼성SW역량테스트;

import java.util.*;

/**
 * 삼성 SW 역량테스트 2021 상반기 오전 2번 문제
 * 골드 2
 */
public class 색깔폭탄 {

    static int N, M, ANS;
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};
    static int[][] BOARD;
    static boolean PACKAGE_FOUND;
    static boolean EXPLODE;
    static boolean[][] VISITED;
    static ArrayList<int[]> CANDI;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        BOARD = new int[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                BOARD[i][j] = sc.nextInt();

        simulate();
        System.out.println(ANS);
    }

    public static void simulate(){
        while(true){
            PACKAGE_FOUND = false;
            findPackage();
            if(!PACKAGE_FOUND)
                break;

            explodePackage();
            gravity();
            rotate();
            gravity();
        }
    }

    public static void findPackage(){
        CANDI = new ArrayList<>();
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(BOARD[i][j] > 0)
                    bfs(new Pair2021(i, j));
        Collections.sort(CANDI, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]){
                    if(o1[1] == o2[1]){
                        if(o1[2] == o2[2])
                            return o1[3] - o2[3];
                        return o2[2] - o1[2];
                    }
                    return o2[1] - o1[1];
                }
                return o2[0] - o1[0];
            }
        });
    }

    public static void explodePackage(){
        EXPLODE = false;
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(BOARD[i][j] > 0)
                    bfs2(new Pair2021(i, j));
    }

    public static void gravity(){
        for(int i=0; i<N; i++){
            int cnt = 0;
            for(int j=N-1; j>=0; j--){
                if(BOARD[j][i] == -2)
                    cnt += 1;
                else if(BOARD[j][i] >= 0 && cnt > 0){
                    BOARD[j + cnt][i] = BOARD[j][i];
                    BOARD[j][i] = -2;
                }else if(BOARD[j][i] == -1)
                    cnt = 0;
            }
        }
    }

    public static void rotate(){
        int[][] candi = new int[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                candi[i][j] = BOARD[j][N - i - 1];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                BOARD[i][j] = candi[i][j];
    }

    public static void bfs(Pair2021 cord){
        VISITED = new boolean[N][N];
        int total = 1;
        int noReds = 1;
        int maxRow = cord.x;
        int minCol = cord.y;

        Queue<Pair2021> queue = new LinkedList<>();
        VISITED[cord.x][cord.y] = true;
        queue.add(cord);

        while(!queue.isEmpty()){
            Pair2021 now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = now.x + DX[i];
                int ny = now.y + DY[i];
                if(canGo(nx, ny) && (BOARD[nx][ny] == BOARD[cord.x][cord.y] || BOARD[nx][ny] == 0) && !VISITED[nx][ny]){
                    VISITED[nx][ny] = true;
                    total += 1;
                    if(BOARD[nx][ny] != 0){
                        noReds += 1;
                        maxRow = Math.max(maxRow, nx);
                        minCol = Math.min(minCol, ny);
                    }
                    queue.add(new Pair2021(nx, ny));
                }
            }
        }
        if(total >= 2){
            PACKAGE_FOUND = true;
            CANDI.add(new int[] {total, noReds, maxRow, minCol});
        }
    }

    public static void bfs2(Pair2021 cord){
        if(EXPLODE)
            return;
        VISITED = new boolean[N][N];
        int total = 1;
        int noReds = 1;
        int maxRow = cord.x;
        int minCol = cord.y;

        Queue<Pair2021> queue = new LinkedList<>();
        VISITED[cord.x][cord.y] = true;
        queue.add(cord);

        while(!queue.isEmpty()){
            Pair2021 now = queue.poll();
            for(int i=0; i<4; i++){
                int nx = now.x + DX[i];
                int ny = now.y + DY[i];
                if(canGo(nx, ny) && (BOARD[nx][ny] == BOARD[cord.x][cord.y] || BOARD[nx][ny] == 0) && !VISITED[nx][ny]){
                    VISITED[nx][ny] = true;
                    total += 1;
                    if(BOARD[nx][ny] != 0){
                        noReds += 1;
                        maxRow = Math.max(maxRow, nx);
                        minCol = Math.min(minCol, ny);
                    }
                    queue.add(new Pair2021(nx, ny));
                }
            }
        }

        if(total == CANDI.get(0)[0] && noReds == CANDI.get(0)[1] && maxRow == CANDI.get(0)[2] && minCol == CANDI.get(0)[3]){
            for(int i=0; i<N; i++)
                for(int j=0; j<N; j++)
                    if(VISITED[i][j])
                        BOARD[i][j] = -2;
            ANS += total * total;
            EXPLODE = true;
        }
    }

    public static boolean canGo(int x, int y){
        return (x>=0 && x<N && y>=0 && y<N) && BOARD[x][y] != -1;
    }
}

class Pair2021{

    public Pair2021(int x, int y){
        this.x = x;
        this.y = y;
    }
    int x;
    int y;
}