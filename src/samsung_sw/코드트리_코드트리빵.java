package samsung_sw;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 코드트리_코드트리빵 {

	static int grid[][]; // 0 이동가능, 1 베이스캠프, -1 이동불능
	static int candiGrid[][];
	static int con[][]; // 편의점 정보 저장한 배열
	static int depth[][];
	static boolean[][] visited;
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	static Person per[];
	static int N, M;
	static int TIME = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		grid = new int[N][N];
		visited = new boolean[N][N];
		con = new int[M][2];
		per = new Person[M];
		
		// 값 입력받기
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<M; i++) {
			con[i][0] = sc.nextInt() - 1;
			con[i][1] = sc.nextInt() - 1;
		}
		
		// per 배열 초기화
		for(int i=0; i<M; i++) {
			per[i] = new Person();
		}
		
		// per별로 가고싶은 편의점의 좌표정보 초기화
		for(int i=0; i<M; i++) { 
			per[i].conX = con[i][0];
			per[i].conY = con[i][1];
		}
		
		
		while(true) {
			TIME++;
			// STEP 1
			// 격자에 있는 모든 사람은 원하는 편의점을 향해 이동
			// 이때 최단거리를 찾아 1분에 한칸씩 이동해야 한다.
			// 모든 사람에 대해 한칸씩 이동을 실시한다.
			for(int i=0; i<M; i++) {
				// 이미 편의점을 찾았거나, 아직 격자에 없는 사람은 이동하지 않는다. ==========================> 오답지점.
				if(per[i].found || per[i].xCord==-1)
					continue;
				Person p = per[i];
				depth = new int[N][N];
				visited = new boolean[N][N];
				int minDist = Integer.MAX_VALUE; // 그리드 크기는 최대 15 * 15지만 depth는 15로 설정해두면 안됨. 맥스값으로 설정 ===========> 오답지점.
				int minX = 0;
				int minY = 0;
				// 역으로, person이 원하는 편의점을 시작으로 완전탐색 진행한다 ===========================================================> 문제해결포인트
				// 그렇다면 depthmap에 편의점에서 갈 수있는 칸의 depth정보가 저장된다
				// 이때, depthmap 에서 person이 있는 좌표의 상하좌우 depth를 확인하면 person과 편의점 사이의 최소값을 찾을 수 있다. ==============> 문제해결포인트
				// person 주위에 depth값이 최소인 값이 2개일시의 처리는, 자연스레 우선순위를 따라 값을 찾느다 ==============> 문제해결포인트
				BFS(new int[] {p.conX, p.conY}); 
				for(int j=0; j<4; j++) { 
					int nextX = p.xCord + dx[j];
					int nextY = p.yCord + dy[j];
					if(nextX>=0 && nextX<N && nextY>=0 && nextY<N && grid[nextX][nextY]!=-1 && minDist>depth[nextX][nextY] && visited[nextX][nextY]) {
						// dx dy 배열을 우선순위에 따라 탐색하도록 했기에, minDist>depth[nextX][nextY] 조건을 통해 우선순위에 따라 최소거리칸을 minX, minY로 찾을 수 있다.
						minDist = depth[nextX][nextY];
						minX = nextX;
						minY = nextY;
					}
				}	
				p.xCord = minX;
				p.yCord = minY;
				// person이 원하는 편의점에 도달하게 됐을 때 처리
				if(p.xCord == p.conX && p.yCord == p.conY) 
					p.found = true;
				
			}
			
			// STEP 2
			// 모든 사람이 이동을 마친 후, 편의점에 도달한 사람이 있는 칸을 -1로 초기화해서 아무도 이동할 수 없는 칸으로 만들어야한다.
			for(int i=0; i<per.length; i++) {
				if(per[i].found) {
					grid[per[i].conX][per[i].conY] = -1;
				}
			}
			
			// STEP 3
			// 격자에 없는 사람이 베이스캠프를 찾아가는 단계
			// person이 원하는 편의점 좌표를 시작으로 완전탐색을 진행한다
			// depthmap에 편의점 좌표를 중심으로 거리 값이 저장된다
			// 모든 베이스캠프의 좌표에 대해, depthmap에서 값이 가장 작은 베이스캠프가 바로 편의점에서 제일 가까운 베이스캠프이다 ====================>문제해결포인트
			// 편의점에서 가까운 베이스캠프가 여러군데인 경우의 우선순위 가장 작은행 -> 가장 작은 열은 i,j가 0부터 탐색을 시작하기에 자동으로 처리된다 =====>문제해결포인트
			if(TIME<=M) {
				Person p = per[TIME-1];
				depth = new int[N][N];
				visited = new boolean[N][N];
				BFS(new int[] {p.conX, p.conY}); // person이 원하는 편의점 좌표를 시작으로 완전탐색
				int minDist = Integer.MAX_VALUE;
				int minX = -1;
				int minY = -1;
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(grid[i][j]==1 && minDist>depth[i][j] && visited[i][j]) {
							// i,j가 0부터 올라가니까 minDist>depth[i][j]에 따라 가장 작은행, 작은열에 minDist값이 초기화된다.
							// minDist>depth[i][j]에 의해 더 작은 depth값이 발견되면 다시 그값이 가장 작은행, 작은열에 저장되는 것이다.
							minDist = depth[i][j];
							minX = i;
							minY = j;
						}
					}
				}
				p.xCord = minX;
				p.yCord = minY;
				// 한 사람에대해 이동하기 때문에 바로 이동불가처리를 해도 된다. 모든사람이 이동했을때를 고려할필요 X
				grid[minX][minY] = -1;
			}			

			// 모든 person의 found가 true면 더이상 시뮬레이션할 필요 없다.
			if(allFound())
				break;

		}
		
		System.out.println(TIME);
	}
	public static boolean allFound() {
		for(int i=0; i<M; i++) {
			if(!per[i].found) {
				return false;
			}
		}
		return true;
	}
	
	public static void BFS(int[] cord) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(cord);
		visited[cord[0]][cord[1]] = true;
		depth[cord[0]][cord[1]] = 0;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i=0; i<4; i++) {
				int nextX = now[0] + dx[i];
				int nextY = now[1] + dy[i];
				
				if(nextX>=0 && nextX<N && nextY>=0 && nextY<N) {
					if(grid[nextX][nextY]!=-1 && !visited[nextX][nextY]) { 
						visited[nextX][nextY] = true;
						depth[nextX][nextY] = depth[now[0]][now[1]] + 1;
						queue.add(new int[] {nextX, nextY});
					}
				}
			}
		}
	}
	
	public static boolean personInGrid() {
		for(Person p : per) {
			if(p.xCord!=-1 && !p.found) 
				return true;
		}
		return false;
	}

}

class Person{
	
	int xCord = -1;
	int yCord = -1;
	int conX;
	int conY;
	boolean found = false;
}