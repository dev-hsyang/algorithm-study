package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 술래잡기 게임을 진행하려 한다. nxn 격자에서 진행되며 술래는 정중아에 서있다.
 * 술래 잡기에는 m명의 도망자가 있다.
 * 도망자는 처음에 지정된 곳에 서 있다. 도망자는 좌우 혹은 상하로 움직인다.
 * 좌우로 움직이는 사람은 항상 오른쪽을 보고 시작, 상하로 움직이는 사람은 항상 아래를 보고 시작
 * h개의 나무가 있다. 나무와 도망자가 겹쳐져 주어지는 것이 가능하다.
 * 진행 순서
 * 1. m명의 도망자가 먼저 동시에 움직인다.
 * 2. 술래가 움직인다.
 * 3. 1,2의 과정이 1턴으로 총 k턴이 진행된다.
 * [도망자의 턴]
 * 이 때 도망자가 움직일 때 술래와의 거리가 3 이하인 도망자만 움직인다.
 * 거리 = |x1 - x2| + |y1 - y2|로 정의된다.
 * 거리가 3 이하인 도망자들은 1턴동안 다음 규칙에 따라 움직인다.
 * - 현재 바라보는 방향으로 움직였을 때 격자를 벗어나지 않는 경우
 *  - 움직이려는 칸에 술래가 있는 경우라면 움직이지 않는다.
 *  - 움직이려는 칸에 술래가 있지 않다면 해당 칸으로 이동한다.
 * - 벗어나는 경우
 * 	- 방향을 반대로 틀어주고 술래가 없다면 1칸 앞으로 이동합니다.
 * [술래의 턴]
 * 술래는 처음 위 방향으로 시작하여 달팽이 보양으로 움직인다. 끝에 도달하게 되면 다시 거꾸로 중심으로 이동한다.
 * 이동 후 방향이 틀어지는 지점이라면 방향을 바로 틀어준다. 1행 1열이나 중앙에 도착한 경우도 바로 틀어줘야한다. => n이 홀수다
 * 이동 후 턴을 넘기기 전에 시야 내에 있는 도망자를 잡게됩니다. 현재 칸을 포함하여 총 3칸이다.
 * 하지만 나무가 놓여있는 칸이라면 해당 위치의 도망자는 잡히지 않는다.
 * 술래는 현재 턴을 t번째 턴이라고 했을 때 t x 현재 턴에 잡힌 도망자의 수 만큼 점수를 얻게된다.
 * k번에 걸쳐 술래 잡기를 진행하는 동안 술래가 총 얻게된 점수를 출력하는 프로그램을 구하여라 .
 * [입력]
 * n(격자 크기), m(도망자의 수), h(나무의 수), k(진행되는 턴의 수)
 * m개 줄에 걸쳐 도망자의 위치와 이동방법 (1: 좌우 => 오른쪽을 봄 , 2: 상하 => 아래를 봄)
 * h개 줄에 걸쳐 나무의 위치가 주어진다.
 * [출력 ]
 * k 턴 동안 얻은 총 점수
 * [풀이]
 * 도망자 객체
 * 위치정보, 이동방향
 * 이동메소드
 *
 * 술래 객체 전역으로 선언되어야함 (위치를 도망자에서 체크하기 위해)
 * 위치정보 이동정보
 * 잡기수행
 *
 * 나무 위치를 표기할 2차원 배열
 * 도망자가 담길 2차원 배열
 *
 *
 * @author jaeseon.park
 */
public class test {
    static BufferedReader br;
    static StringTokenizer st;

    static final int[] D_ROW = {-1, 0, 1, 0};
    static final int[] D_COL = {0, 1, 0, -1};
    // 술래가 접근할 수 있는 거리
    static final int LENGTH = 3;
    static boolean[][] map;
    static boolean[][] treeMap;
    static int[][] moveList;
    static List<Runner>[][] runnerMap;
    static int size;
    static int numRunner, numTree, numTurn;
    static int totalScore, score;
    static int[] chaserPosition = new int[2];
    static List<Runner> runnerList;

    static class Runner{
        int row, col;
        int direction;

        public Runner(int row, int col, int direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public void move() {
            // 술래와의 거리가 3이상인 경우만 움직인다.
            int distance = Math.abs(chaserPosition[0] - row) + Math.abs(chaserPosition[1] - col);
            // 3이하인 경우 이동 수행
            if (distance <= 3) {
                // 해당방향으로 이동 수행
                int tmpRow = row + D_ROW[this.direction];
                int tmpCol = col + D_COL[this.direction];
                // 범위를 벗어나는 경우
                if (tmpRow <= 0 || tmpCol <= 0 || tmpRow > size || tmpCol > size) {
                    // 방향 변경
                    this.direction += 2;
                    this.direction %= D_ROW.length;
                    tmpRow = row + D_ROW[this.direction];
                    tmpCol = col + D_COL[this.direction];
                }
                if (chaserPosition[0] == tmpRow && chaserPosition[1] == tmpCol) {
                    // 같은 경우 이동을 수행하지 않는다.
                }else {
                    row = tmpRow;
                    col = tmpCol;
                }
            }
        }
    }

    static class Chaser{
        int row, col;
        int[] face;
        int moveIdx;
        int moveDirection;

        public Chaser(int center) {
            this.row = center;
            this.col = center;
            this.moveIdx = 0;
            this.moveDirection = 1;
            this.face = new int[2];
        }

        public void move() {
            if (moveDirection == 1) {
                // moveindex를 증가하는 방향
                moveIdx++;
                this.row = moveList[moveIdx][0];
                this.col = moveList[moveIdx][1];
                // 바라보는 방향 결정
                if (moveIdx == size * size - 1) {
                    // 만약 1, 1에 도착한 경우라면 이제 뒤로 이동해야함
                    moveDirection = -1;
                }
                this.face[0] = moveList[moveIdx + moveDirection][0] - this.row ;
                this.face[1] = moveList[moveIdx + moveDirection][1] - this.col;
            }else if(moveDirection == -1){
                // moveindex를 감소하는 방향
                moveIdx--;
                this.row = moveList[moveIdx][0];
                this.col = moveList[moveIdx][1];
                // 바라보는 방향 결정
                if (moveIdx == 0) {
                    // 만약 중심에 도착한 경우라면 다시 뒤집어준다.
                    moveDirection = 1;
                }
                this.face[0] = moveList[moveIdx + moveDirection][0] - this.row ;
                this.face[1] = moveList[moveIdx + moveDirection][1] - this.col;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 나선형 이동 만들기
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        map = new boolean[size + 1][size + 1];
        treeMap = new boolean[size + 1][size + 1];
        moveList = new int[size*size][2];
        numRunner = Integer.parseInt(st.nextToken());
        numTree= Integer.parseInt(st.nextToken());
        numTurn = Integer.parseInt(st.nextToken());

        runnerList = new ArrayList<>();
        // 도망자 정보 받기
        for (int idx = 0; idx < numRunner; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            Runner runner = new Runner(row, col, direction);
            runnerList.add(runner);
        }

        // 나무 정보 받기
        for (int idx =0; idx < numTree; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            treeMap[row][col] = true;
        }
        // 달팽이 방향으로 움직이는 moveList 만들기
        int center = size/2 + 1;
        int row = center;
        int col = center;
        int distance = 1;
        map[row][col] = true;
        int moveIdx = 0;
        moveList[moveIdx++] = new int[] {row, col};
        while(true) {
            // 위, 오른쪽 아래 왼쪽 순서로 진행
            for (int dist = 1; dist <= distance; dist++) {
                row += D_ROW[0];
                map[row][col] = true;
                moveList[moveIdx++] = new int[] {row, col};
            }
            if(row == 1 && col ==1) {
                break;
            }
            // 오른쪽 탐색 오른쪽이 이미 채워졌다면? 위쪽으로 한칸 탐색 후 오른쪽 탐색
            if (map[row][col + 1]) {
                row += D_ROW[0];
                map[row][col] = true;
                moveList[moveIdx++] = new int[] {row, col};
                distance++;
            }

            for (int dist = 1; dist <= distance; dist++) {
                col += D_COL[1];
                map[row][col] = true;
                moveList[moveIdx++] = new int[] {row, col};
            }
            // 아래 탐색
            for (int dist = 1; dist <= distance; dist++) {
                row += D_ROW[2];
                map[row][col] = true;
                moveList[moveIdx++] = new int[] {row, col};
            }
            // 왼쪽 탐색 시도
            if (map[row][col - 1]) {
                row += D_ROW[2];
                map[row][col] = true;
                moveList[moveIdx++] = new int[] {row, col};
                distance++;
            }

            for (int dist = 1; dist <= distance; dist++) {
                col += D_COL[3];
                map[row][col] = true;
                moveList[moveIdx++] = new int[] {row, col};
            }
        }
        chaserPosition = new int[] {center, center};
        totalScore = 0;
        score = 0;
        Chaser chaser = new Chaser(center);
        for (int turnIdx = 1; turnIdx <= numTurn; turnIdx++) {
            runnerMap = new List[size + 1][size + 1];
            // 도망자들의 이동 수행
            for (int idx = 0; idx < runnerList.size(); idx++) {
                Runner curRunner = runnerList.get(idx);
                // 이동 수행
                curRunner.move();
                int curRow = curRunner.row;
                int curCol = curRunner.col;
                if (runnerMap[curRow][curCol] == null) {
                    runnerMap[curRow][curCol] = new ArrayList<Runner>();
                }
                runnerMap[curRow][curCol].add(curRunner);
            }

            // 술래의 이동 수행
            chaser.move();
            chaserPosition[0] = chaser.row;
            chaserPosition[1] = chaser.col;
            // 술래가 바라보는 방향
            int[] face = chaser.face;
            // 술래의 잡기 수행
            int numCatch = 0;
            for (int dist = 0; dist < LENGTH; dist++) {
                int tmpRow = chaser.row + (dist * face[0]);
                int tmpCol = chaser.col + (dist * face[1]);
                // 영역안에 있는 경우 잡기 수행
                if (tmpRow > 0 && tmpCol > 0 && tmpRow <= size && tmpCol <=size) {
                    // 나무가 존재하면 잡기 패스
                    if (treeMap[tmpRow][tmpCol]) continue;
                    // 해당 위치에 도망자가 없어도 패스
                    if (runnerMap[tmpRow][tmpCol] == null) continue;
                    numCatch += runnerMap[tmpRow][tmpCol].size();
                    runnerMap[tmpRow][tmpCol] = null;
                }
            }
            totalScore += numCatch * turnIdx;
            runnerList = new ArrayList<>();
            for (int rowIdx = 1; rowIdx <= size; rowIdx++) {
                for (int colIdx = 1; colIdx <= size; colIdx++) {
                    if (runnerMap[rowIdx][colIdx] != null) {
                        for (Runner runner: runnerMap[rowIdx][colIdx]) {
                            runnerList.add(runner);
                        }
                    }
                }
            }
        }

        System.out.println(totalScore);
    }
}