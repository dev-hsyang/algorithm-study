package samsung_sw;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class 백준_아기상어 {

	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int N, TOTAL;
	static boolean CALLMOM = false;
	static BabyInfo startInfo = null;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					startInfo = new BabyInfo(i, j, 2, 0, 0);
					map[i][j] = 0;
				}
			}
		}
		
		while(!CALLMOM) {
			BFS(startInfo);			
		}
		
		System.out.println(TOTAL);

	}
	
public static void BFS(BabyInfo info) {
		
		Queue<BabyInfo> queue = new LinkedList<>();
		queue.add(info);
		visited[info.xCord][info.yCord] = true;
		BabyInfo candi = new BabyInfo(20, 20, -1, -1, -1);
		
		while(!queue.isEmpty()) {
			
			BabyInfo now = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nextX = now.xCord + dx[i];
				int nextY = now.yCord + dy[i];
				
				if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
					if(!visited[nextX][nextY]) {
						if(now.size < map[nextX][nextY]) { // 이동할 수 없는칸일때
							continue;
						}else if(now.size == map[nextX][nextY] || map[nextX][nextY] == 0) { // 이동만 가능한 칸일때
							visited[nextX][nextY] = true;
							BabyInfo newInfo = new BabyInfo(nextX, nextY, now.size, now.moved+1, now.eat);
							queue.add(newInfo);							
						}else if(now.size > map[nextX][nextY]) { // 먹을 수 있는 물고기 칸일때
							if(candi.size != -1) {
								// 공간에 먹잇감 1마리 이상
								
								if(now.moved+1 == candi.moved) {
									// 같은 거리의 물고기
									
									if(nextX == candi.xCord) {
										// 둘 다 맨 위에 있을 때
										
										if(nextY < candi.yCord) {
											visited[nextX][nextY] = true;
											BabyInfo newInfo = new BabyInfo(nextX, nextY, now.size, now.moved+1, now.eat);
											candi = newInfo;
											queue.add(newInfo);
										}else if(nextY > candi.yCord) {
											visited[nextX][nextY] = true;
											BabyInfo newInfo = new BabyInfo(nextX, nextY, now.size, now.moved+1, now.eat);
											queue.add(newInfo);
										}
										
									}else if(nextX < candi.xCord) {// 하나만 맨위 일때										
										visited[nextX][nextY] = true;
										BabyInfo newInfo = new BabyInfo(nextX, nextY, now.size, now.moved+1, now.eat);
										candi = newInfo;
										queue.add(newInfo);
									}else if(nextX > candi.xCord) {
										visited[nextX][nextY] = true;
										BabyInfo newInfo = new BabyInfo(nextX, nextY, now.size, now.moved+1, now.eat);
										queue.add(newInfo);
									}
								}else if(now.moved+1 < candi.moved) { // 둘이 거리는 다를때
									visited[nextX][nextY] = true;
									BabyInfo newInfo = new BabyInfo(nextX, nextY, now.size, now.moved+1, now.eat);
									candi = newInfo;
									queue.add(newInfo);
								}else if(now.moved+1 > candi.moved) {
									visited[nextX][nextY] = true;
									BabyInfo newInfo = new BabyInfo(nextX, nextY, now.size, now.moved+1, now.eat);
									queue.add(newInfo);
								}
							}
							else { // 공간에 탐색된 먹잇감이 하나만 있을 떄
								visited[nextX][nextY] = true;
								BabyInfo newInfo = new BabyInfo(nextX, nextY, now.size, now.moved+1, now.eat);
								candi = newInfo;
								queue.add(newInfo);
							}
						}
					}
				}
			}
		}
		if(candi.size == -1) {
			CALLMOM = true;
			return;
		}
		TOTAL += candi.moved;
		startInfo = new BabyInfo(candi.xCord, candi.yCord, candi.size, 0, candi.eat);
		startInfo.eat();
		map[candi.xCord][candi.yCord] = 0;
		visited = new boolean[N][N];
	}

}

class BabyInfo{
	
	public BabyInfo(int xCord, int yCord, int size, int moved, int eat) {
		this.xCord = xCord;
		this.yCord = yCord;
		this.size = size;
		this.moved = moved;
		this.eat = eat;
	}
	
	int xCord;
	int yCord;
	int size;
	int moved;
	int eat;
	
	public void eat() {
		eat++;
		if(eat == size) {
			size++;
			eat = 0;
		}
	}
}
