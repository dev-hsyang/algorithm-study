package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class test {
    static class Lazer {
        int x;
        int y;
        List<int[]> way; // x, y

        public Lazer(int x, int y, List<int[]> way) {
            super();
            this.x = x;
            this.y = y;
            this.way = way;
        }

        @Override
        public String toString() {
            return "Lazer [x=" + x + ", y=" + y + ", way=" + way + "]";
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] time = new int[N][M]; // 시간을 잴 배열

        for (int i = 0; i < K; i++) {
            boolean[][] nothing = new boolean[N][M]; // 관여했는 지를 체크할 배열

            // 1. 공격자 선정
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { // 공격력, 시점, y, x
                @Override
                public int compare(int[] arr1, int[] arr2) {
                    if (arr1[0] == arr2[0]) {
                        if (arr1[1] == arr2[1]) {
                            if ((arr1[2] + arr1[3]) == (arr2[2] + arr2[3])) {
                                return -(arr1[3] - arr2[3]);
                            }
                            return -((arr1[2] + arr1[3]) - (arr2[2] + arr2[3]));
                        }
                        return -(arr1[1] - arr2[1]);
                    }
                    return arr1[0] - arr2[0];
                }
            });

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[j][k] != 0) {
                        pq.offer(new int[] {map[j][k], time[j][k], j, k});
                    }
                }
            }

            int[] attacker = pq.poll();
            int attackerPower = attacker[0] + N + M;
            nothing[attacker[2]][attacker[3]] = true;
            System.out.println(attacker[2] + ", " + attacker[3]);

//			System.out.println(Arrays.toString(attacker));

            // 2. 수비자 선정
            pq = new PriorityQueue<>(new Comparator<int[]>() { // 공격력, 시점, y, x
                @Override
                public int compare(int[] arr1, int[] arr2) {
                    if (arr1[0] == arr2[0]) {
                        if (arr1[1] == arr2[1]) {
                            if ((arr1[2] + arr1[3]) == (arr2[2] + arr2[3])) {
                                return arr1[3] - arr2[3];
                            }
                            return (arr1[2] + arr1[3]) - (arr2[2] + arr2[3]);
                        }
                        return arr1[1] - arr2[1];
                    }
                    return -(arr1[0] - arr2[0]);
                }
            });

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[j][k] != 0) {
                        pq.offer(new int[] {map[j][k], time[j][k], j, k});
                    }
                }
            }

            int[] defenser = pq.poll();
            nothing[defenser[2]][defenser[3]] = true;

//			System.out.println(Arrays.toString(defenser));

            map[attacker[2]][attacker[3]] = attackerPower;
            time[attacker[2]][attacker[3]] = i + 1;

            // 3. 레이저 공격 시도
            boolean lazer = false;

            boolean[][] check = new boolean[N][M];

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[j][k] == 0) {
                        check[j][k] = true;
                    }
                }
            }

            check[attacker[2]][attacker[3]] = true;

            Queue<Lazer> queue = new ArrayDeque<>();
            queue.offer(new Lazer(attacker[3], attacker[2], new ArrayList<>())); // x, y, way

            // 우, 하, 좌, 상
            int[] dx = {1, 0, -1, 0};
            int[] dy = {0, 1, 0, -1};

            while (!queue.isEmpty()) {
                Lazer output = queue.poll();

                for (int y = 0; y < 4; y++) {
                    int cx = output.x + dx[y];
                    int cy = output.y + dy[y];

                    if (cx == -1) {
                        cx = M - 1;
                    }

                    if (cx == M) {
                        cx = 0;
                    }

                    if (cy == -1) {
                        cy = N - 1;
                    }

                    if (cy == N) {
                        cy = 0;
                    }

                    if (check[cy][cx] == false) {
                        if (cx == defenser[3] && cy == defenser[2]) {
                            List<int[]> destWay = output.way;

                            for (int[] destValue : destWay) {
                                map[destValue[1]][destValue[0]] -= (attackerPower / 2);
                                map[destValue[1]][destValue[0]] = Math.max(map[destValue[1]][destValue[0]], 0);
                                nothing[destValue[1]][destValue[0]] = true;
                            }

                            map[defenser[2]][defenser[3]] -= attackerPower;
                            map[defenser[2]][defenser[3]] = Math.max(map[defenser[2]][defenser[3]], 0);

                            lazer = true;
                            break;
                        }

                        List<int[]> outputWay = output.way;
                        List<int[]> tempWay = new ArrayList<>();

                        for (int[] outputValue : outputWay) {
                            tempWay.add(new int[] {outputValue[0], outputValue[1]});
                        }

                        tempWay.add(new int[] {cx, cy});
                        queue.offer(new Lazer(cx, cy, tempWay));
                        check[cy][cx] = true;
                    }
                }

                if (lazer) {
                    break;
                }
            }

            // 4. 레이저 공격 실패 시 포탄 공격
            if (!lazer) {
                map[defenser[2]][defenser[3]] -= attackerPower;
                map[defenser[2]][defenser[3]] = Math.max(map[defenser[2]][defenser[3]], 0);

                // 좌, 좌상, 상, 상우, 우, 우하, 하, 좌하
                dx = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
                dy = new int[] {0, -1, -1, -1, 0, 1, 1, 1};

                for (int y = 0; y < 8; y++) {
                    int cx = defenser[3] + dx[y];
                    int cy = defenser[2] + dy[y];

                    if (cx == -1) {
                        cx = M - 1;
                    }

                    if (cx == M) {
                        cx = 0;
                    }

                    if (cy == -1) {
                        cy = N - 1;
                    }

                    if (cy == N) {
                        cy = 0;
                    }

                    if (cx == attacker[3] && cy == attacker[2]) {
                        continue;
                    }

                    if (map[cy][cx] != 0) {
                        map[cy][cx] -= (attackerPower / 2);
                        map[cy][cx] = Math.max(map[cy][cx], 0);
                        nothing[cy][cx] = true;
                    }
                }
            }

            // 5. 만약 멀쩡한 거 하나만 남으면 나가기
            int zeroCount = 0;
            int maxValue = -1;

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[j][k] == 0) {
                        zeroCount++;
                    }
                    maxValue = Math.max(maxValue, map[j][k]);
                }
            }

            if (zeroCount == (N * M - 1)) {
                System.out.println(maxValue);
                return;
            }

            // 6. 관여안한 포탑 공격력 올리기
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[j][k] != 0 && nothing[j][k] == false) {
                        map[j][k] += 1;
                    }
                }
            }

//            System.out.println(i + " 턴 후");
//			for (int j = 0; j < N; j++) {
//				for (int k = 0; k < M; k++) {
//					System.out.print(map[j][k] + " ");
//				}
//				System.out.println();
//			}
//            System.out.println(" ");
        }

        int maxValue = -1;

        for (int j = 0; j < N; j++) {
            for (int k = 0; k < M; k++) {
                maxValue = Math.max(maxValue, map[j][k]);
            }
        }

        System.out.println(maxValue);
    }
}