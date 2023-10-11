package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 삼성 SW 역량테스트 2022 하반기 오후 1번 문제
 * 골드 2
 * @author didgs
 *
 */
public class 코드트리빵_2 {

	static int N, M, MINDIST, ANS;
	static int[] DX = {-1, 0, 0, 1};
	static int[] DY = {0, -1, 1, 0};
	static int[][] ROAD; // 0: 빈공간, 1: 베이스캠프, -1: 갈수없는 곳
	static int[][] DISTMAP;
	static boolean[] FOUND;
	static boolean[][] VISITED;
	static Pair2022[] STOREINFO;
	static Pair2022[] PLAYERS;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ROAD = new int[N + 1][N + 1];
		STOREINFO = new Pair2022[M + 1];
		PLAYERS = new Pair2022[M + 1];
		FOUND = new boolean[M + 1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++)
				ROAD[i][j] = Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			STOREINFO[i] = new Pair2022(x, y);
			PLAYERS[i] = new Pair2022(0, 0);
		}
		
		simulate();
		
		System.out.println(ANS);
	}
	
	public static void simulate() {
		int time = 1;
		while(true) {
			peopleMove();
			moveToBasecamp(time);
			if(allArrived())
				break;
			time++;
		}
		ANS = time;
	}
	
	public static void peopleMove() {
		int[][] candi = new int[N + 1][N + 1];
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				candi[i][j] = ROAD[i][j];
		
		for(int i=1; i<=M; i++) {
			if(PLAYERS[i].x == 0 && PLAYERS[i].y == 0)
				break;
			if(FOUND[i])
				continue;
			
			// 각 사람들에 대해 가고 싶은 편의점을 향해 1칸씩 이동한다.
			// 각 사람이 원하는 편의점 좌표를 중심으로 DISTMAP과 MINDIST 를 계산하고, 그 사람이 위치한 좌표에서 delta 탐색을 통해 MINDIST 에 해당하는 칸으로 이동. 우선순위는 delta 탐색 순서로 조정한다.
			initDistmap(STOREINFO[i].x, STOREINFO[i].y);
			int minDist = Integer.MAX_VALUE;
			
			for(int k=0; k<4; k++) {
				int nx = PLAYERS[i].x + DX[k];
				int ny = PLAYERS[i].y + DY[k];
				if(canGo(nx, ny) && VISITED[nx][ny])
					minDist = Math.min(minDist, DISTMAP[nx][ny]);
			}
			for(int k=0; k<4; k++) {
				int nx = PLAYERS[i].x + DX[k];
				int ny = PLAYERS[i].y + DY[k];
				if(canGo(nx, ny) && VISITED[nx][ny] && DISTMAP[nx][ny] == minDist) {
					PLAYERS[i].x = nx;
					PLAYERS[i].y = ny;
					break;
				}
			}
			
			// 이동한 사람이 원하는 편의점에 도착했으면 도착표시와 함께 임시지도에 더이상 이동이 불가함을 표시한다.
			if(PLAYERS[i].x == STOREINFO[i].x && PLAYERS[i].y == STOREINFO[i].y) {
				candi[PLAYERS[i].x][PLAYERS[i].y] = -1;
				FOUND[i] = true;
			}
		}
		
		// 격자의 사람들이 모두 이동한 뒤에 이동불가해진 칸을 업데이트해준다.
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				ROAD[i][j] = candi[i][j];
		
	}
	
	public static void moveToBasecamp(int time) {
		if(time > M)
			return;
		// time 번째 사람이 가고자하는 편의점과 가장 가까운 베이스캠프를 찾아서 들어간다.
		// 편의점 위치를 기준으로 bfs 를 통해 distmap 을 구한다.
		initDistmap(STOREINFO[time].x, STOREINFO[time].y);
		
		boolean found = false;
		for(int i=1; i<=N; i++) {
			if(!found) {
				for(int j=1; j<=N; j++) {
					if(ROAD[i][j] == 1 && DISTMAP[i][j] == MINDIST && VISITED[i][j]) {
						PLAYERS[time].x = i;
						PLAYERS[time].y = j;
						ROAD[i][j] = -1;
						found = true;
						break;
					}
				}				
			}
		}
	}
	
	public static void initDistmap(int x, int y) {
		MINDIST = Integer.MAX_VALUE;
		DISTMAP = new int[N + 1][N + 1];
		VISITED = new boolean[N + 1][N + 1];
		Queue<int[]> queue = new LinkedList<>();
		VISITED[x][y] = true;
		queue.add(new int[] {x, y});
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i=0; i<4; i++) {
				int nx = now[0] + DX[i];
				int ny = now[1] + DY[i];
				if(canGo(nx, ny) && !VISITED[nx][ny] && ROAD[nx][ny] != -1) {
					DISTMAP[nx][ny] = DISTMAP[now[0]][now[1]] + 1;
					if(ROAD[nx][ny] == 1)
						MINDIST = Math.min(DISTMAP[nx][ny], MINDIST);
					VISITED[nx][ny] = true;
					queue.add(new int[] {nx, ny});
				}
			}
		}
	}
	
	public static boolean allArrived() {
		for(int i=1; i<=M; i++)
			if(!FOUND[i])
				return false;
		return true;
	}
	
	public static boolean canGo(int x, int y) {
		return x>=1 && x<=N && y>=1 && y<=N;
	}

}

class Pair2022{
	
	public Pair2022(int x, int y) {
		this.x = x;
		this.y = y;
	}
	int x;
	int y;
}
