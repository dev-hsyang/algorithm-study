package samsung_sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_로봇청소기 {

	private static boolean[][] visited;
	private static int N, M;
	private static int[][] room;
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	private static int count = 0;
	private static boolean operating = true;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		visited = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		RobotInfo startInfo = new RobotInfo(st.nextToken(), st.nextToken(), st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(startInfo);
		System.out.println(count);
	}
	
public static void DFS(RobotInfo info) {
		
		if(!operating)
			return;
		
		if(!visited[info.xCord][info.yCord]) {
			visited[info.xCord][info.yCord] = true;
			count++;
		}		
		//System.out.println(info.xCord + ", " + info.yCord + ", " + info.direction + ", " + count);
		for(int i=0; i<4; i++) {
			int nearX = info.xCord + dx[i];
			int nearY = info.yCord + dy[i];
			if(!visited[nearX][nearY] && room[nearX][nearY]!=1) { // 현재 칸의 주변 4칸중 청소되지 않은 빈칸이 있는경우
				for(int j=0; j<4; j++) {
					info.rotate();
					if(!visited[info.xCord + dx[info.direction]][info.yCord + dy[info.direction]] && room[info.xCord + dx[info.direction]][info.yCord + dy[info.direction]] != 1) {
						// 반시계 방향으로 회전한 방향의 앞쪽 칸이 청소되지 않은 빈 칸인 경우 전진해서 다시 1번부터
						DFS(new RobotInfo(info.xCord + dx[info.direction], info.yCord + dy[info.direction], info.direction));
					}					
				}
			}
		}
		
		// 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
		
		
		// 현재 로봇의 반대 방향으로 후진할 수 있다면 다시 1번부터
		if(room[info.xCord + dx[info.getBackDirection()]][info.yCord + dy[info.getBackDirection()]] == 0) {
			info.xCord += dx[info.getBackDirection()];
			info.yCord += dy[info.getBackDirection()];
			DFS(new RobotInfo(info.xCord, info.yCord, info.direction));
		}else		
		// 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면
		if(room[info.xCord + dx[info.getBackDirection()]][info.yCord + dy[info.getBackDirection()]] == 1) {
			operating = false;
			return;
		}		
	}

}

class RobotInfo {
	
	public RobotInfo(String xCord, String yCord, String direction) {
		this.xCord = Integer.parseInt(xCord);
		this.yCord = Integer.parseInt(yCord);
		this.direction = Integer.parseInt(direction);
	}
	
	public RobotInfo(int xCord, int yCord, int direction) {
		this.xCord = xCord;
		this.yCord = yCord;
		this.direction = direction;
	}
	public int xCord;
	public int yCord;
	public int direction;

	public void rotate() {
		
		if(direction == 0)
			direction = 3;
		else
			direction--;
	}
	
	public int getBackDirection() {
		
		switch (this.direction) {
		case 0:
			return 2;
		case 1:
			return 3;
		case 2:
			return 0;
		case 3:
			return 1;
		default:
			return -1;
		}
	}
}

