package samsung_sw.baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class 구슬탈출2 {

	private static String[][] map;
	private static boolean[][][][] visited;
	private static int[] dx = {0, 1, 0, -1};
	private static int[] dy = {1, 0, -1, 0};
	private static int N, M;
	private static int[] redBall;
	private static int[] blueBall;
	private static Info startInfo;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new String[N][M];
		visited = new boolean[N][M][N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j=0; j<M; j++) {
				map[i][j] = s.substring(j, j+1);
				if(map[i][j].equals("R"))
					redBall = new int[] {i, j};
				if(map[i][j].equals("B"))
					blueBall = new int[] {i, j};
			}
		}
		
		startInfo = new Info(redBall, blueBall, 0);
		int num = BFS(startInfo);
		
		if(num>10) {
			System.out.println(-1);
		}else {
			System.out.println(num);
		}

	}

private static int BFS(Info startInfo) {
		
		int num = -1;
		Queue<Info> queue = new LinkedList<>();
		queue.add(startInfo);
		visited[startInfo.redCord[0]][startInfo.redCord[1]][startInfo.blueCord[0]][startInfo.blueCord[1]] = true;
		
		while(!queue.isEmpty()) {
			
			Info info = queue.poll();
//			System.out.println("DQ: " + info.redCord[0] + ", " + info.redCord[1] + ", " + info.count);		
			if(map[info.redCord[0]][info.redCord[1]].equals("O")) {
				if(map[info.blueCord[0]][info.blueCord[1]].equals("O")) {
					num = -1;
					continue; // 반례) 빨간공과 파란공이 모두 구멍에 빠지는 경우는 안세고 넘어감
				}
				else {
					num = info.count; 
					break; // 빨간공만 빠졌을 경우 이때 값을 반환하고 끝냄
				}
			}
			
			if(map[info.blueCord[0]][info.blueCord[1]].equals("O"))
				continue; // 반례) 파란공이 먼저 구멍에 빠지는 경우는 안세고 넘어감
			
			for(int i=0; i<4; i++) {
				
				// 각각 한 방향에 대해서
				int redD = 0;
				int blueD = 0;
				int[] nextRed = new int[] {info.redCord[0] + dx[i], info.redCord[1] + dy[i]};
				int[] nextBlue = new int[] {info.blueCord[0] + dx[i], info.blueCord[1] + dy[i]};
				
				// 빨간공을 벽 또는 O까지 이동
				while(true) {
					if(!map[nextRed[0]][nextRed[1]].equals("#") && !map[nextRed[0]][nextRed[1]].equals("O")) {
						nextRed[0] += dx[i];
						nextRed[1] += dy[i];
						redD++;
					}else {
						if(map[nextRed[0]][nextRed[1]].equals("#")) {
							nextRed[0] -= dx[i];
							nextRed[1] -= dy[i];
						}
						break;
						// #을 만나거나 O를 만나면 이동 중지
					}
				}
				
				// 파란공을 벽 또는 O까지 이동
				while(true) {
					if(!map[nextBlue[0]][nextBlue[1]].equals("#") && !map[nextBlue[0]][nextBlue[1]].equals("O")) {
						nextBlue[0] += dx[i];
						nextBlue[1] += dy[i];
						blueD++;
					}else {
						if(map[nextBlue[0]][nextBlue[1]].equals("#")) {
							nextBlue[0] -= dx[i];
							nextBlue[1] -= dy[i];
						}
						break;
					}
				}
				
				// 두 공이 겹칠 시 처리
				if(nextRed[0] == nextBlue[0] && nextRed[1] == nextBlue[1]) {
					if(!map[nextRed[0]][nextRed[1]].equals("O")) { // 빨간공이 구멍에 빠졌을경우 처리해줄 필요 없다.
						if(redD>blueD) {
							nextRed[0] -= dx[i];
							nextRed[1] -= dy[i];
						}else if(redD<blueD) {
							nextBlue[0] -= dx[i];
							nextBlue[1] -= dy[i];
						}
					}
				}
				
				// 방문한 칸이 아니면 큐에 삽입
				if(!visited[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]]) {
					visited[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]] = true;
					queue.add(new Info(nextRed, nextBlue, info.count+1));
//					System.out.println("EQ: " + nextRed[0] + ", " + nextRed[1] + ", " + info.count+1);
				}				
			}
		}		
		return num;
	}
}

class Info{
	
	public Info(int[] redCord, int[] blueCord, int count) {
		
		this.redCord = redCord;
		this.blueCord = blueCord;
		this.count = count;
	}
	
	public int[] redCord;
	public int[] blueCord;
	public int count;
}
