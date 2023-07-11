package baekjoon.구현;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17144
 * 삼성 SW 역량 테스트 기출 문제
 * 골드 4
 * @author didgs
 *
 */
public class 미세먼지안녕 {

	static int[][] room;
	static AirCon[] airCon;
	static int R, C, T;
	static int[] aDx = {0, -1, 0, 1};
	static int[] aDy = {1, 0, -1, 0};
	static int[] bDx = {0, 1, 0, -1};
	static int[] bDy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		room = new int[R+1][C+1];
		airCon = new AirCon[2];
		
		int no = 0;
		for(int i=1; i<R+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<C+1; j++) {
				room[i][j] = Integer.parseInt(st.nextToken()); 
				if(room[i][j] == -1) {
					airCon[no] = new AirCon(no, i, j);
					no++;
				}
			}
		}
		
		for(int i=0; i<T; i++) {
			
			// 미세먼지가 동시에 이동한 정보를 담을 새로운 임시배열
			int[][] nextRoom = new int[R+1][C+1];
			
			// 임시배열에 공기청정기 좌표 초기화
			for(int j=0; j<2; j++) {
				AirCon ac = airCon[j];
				nextRoom[ac.xCord][ac.yCord] = -1;
			}
			
			// 미세먼지 확산
			for(int j=1; j<R+1; j++) {
				for(int k=1; k<C+1; k++) {
					
					// 미세먼지가 있는 칸일 때 확산.
					if(room[j][k]>0) {
						int count = 0;
						for(int n=0; n<4; n++) {	
							int nx = j + aDx[n];
							int ny = k + aDy[n];
							if(nx>0 && nx<R+1 && ny>0 && ny<C+1) {
								if(room[nx][ny] != -1) { // 확산하려는 칸이 벽이 아니고, 공기청정기가 없을 때 확산
									nextRoom[nx][ny] += room[j][k] / 5;
									count += 1;
								}
							}
						}
						nextRoom[j][k] += room[j][k] - (room[j][k]/5) * count;
					}
				}
			}
			
			// 위쪽 공기청정기 가동, 반시계방향 바람
			Wind topWind = new Wind(airCon[0].xCord, airCon[0].yCord);
			for(int j=1; j<C; j++) {
				topWind.yCord += 1; // 오른쪽 끝까지 이동
				if(nextRoom[topWind.xCord][topWind.yCord] != 0) { // 바람이 있는 칸에 먼지가 있을 때
					int curDust = nextRoom[topWind.xCord][topWind.yCord];
					if(topWind.temp != 0) { // 이전칸에서 이동해온 먼지가 있을 때
						nextRoom[topWind.xCord][topWind.yCord] = topWind.temp;
						topWind.temp = curDust;
					}else { // 이전칸에서 이동해온 먼지가 없을 때					
						nextRoom[topWind.xCord][topWind.yCord] = 0;
						topWind.temp = curDust;
					}
				}else { // 바람이 있는 칸에 먼지가 없을 때
					if(topWind.temp != 0 ) { // 이전칸에서 이동해온 먼지가 있을 때						
						nextRoom[topWind.xCord][topWind.yCord] = topWind.temp;
						topWind.temp = 0;						
					}
				}
			}
			for(int j=1; j<airCon[0].xCord; j++) {
				topWind.xCord -= 1; // 오른쪽 맨 위까지 이동
				if(nextRoom[topWind.xCord][topWind.yCord] != 0) { // 바람이 있는 칸에 먼지가 있을 때
					int curDust = nextRoom[topWind.xCord][topWind.yCord];
					if(topWind.temp != 0) { // 이전칸에서 이동해온 먼지가 있을 때
						nextRoom[topWind.xCord][topWind.yCord] = topWind.temp;
						topWind.temp = curDust;
					}else { // 이전칸에서 이동해온 먼지가 없을 때					
						nextRoom[topWind.xCord][topWind.yCord] = 0;
						topWind.temp = curDust;
					}
				}else { // 바람이 있는 칸에 먼지가 없을 때
					if(topWind.temp != 0 ) { // 이전칸에서 이동해온 먼지가 있을 때						
						nextRoom[topWind.xCord][topWind.yCord] = topWind.temp;
						topWind.temp = 0;						
					}
				}
			}
			for(int j=1; j<C; j++) {
				topWind.yCord -= 1; // 왼쪽 맨 위까지 이동
				if(nextRoom[topWind.xCord][topWind.yCord] != 0) { // 바람이 있는 칸에 먼지가 있을 때
					int curDust = nextRoom[topWind.xCord][topWind.yCord];
					if(topWind.temp != 0) { // 이전칸에서 이동해온 먼지가 있을 때
						nextRoom[topWind.xCord][topWind.yCord] = topWind.temp;
						topWind.temp = curDust;
					}else { // 이전칸에서 이동해온 먼지가 없을 때					
						nextRoom[topWind.xCord][topWind.yCord] = 0;
						topWind.temp = curDust;
					}
				}else { // 바람이 있는 칸에 먼지가 없을 때
					if(topWind.temp != 0 ) { // 이전칸에서 이동해온 먼지가 있을 때						
						nextRoom[topWind.xCord][topWind.yCord] = topWind.temp;
						topWind.temp = 0;						
					}
				}
			}
			for(int j=1; j<airCon[0].xCord; j++) {
				topWind.xCord += 1;
				if(topWind.xCord == airCon[0].xCord) 
					break;		
				if(nextRoom[topWind.xCord][topWind.yCord] != 0) { // 바람이 있는 칸에 먼지가 있을 때
					int curDust = nextRoom[topWind.xCord][topWind.yCord];
					if(topWind.temp != 0) { // 이전칸에서 이동해온 먼지가 있을 때
						nextRoom[topWind.xCord][topWind.yCord] = topWind.temp;
						topWind.temp = curDust;
					}else { // 이전칸에서 이동해온 먼지가 없을 때					
						nextRoom[topWind.xCord][topWind.yCord] = 0;
						topWind.temp = curDust;
					}
				}else { // 바람이 있는 칸에 먼지가 없을 때
					if(topWind.temp != 0 ) { // 이전칸에서 이동해온 먼지가 있을 때						
						nextRoom[topWind.xCord][topWind.yCord] = topWind.temp;
						topWind.temp = 0;						
					}
				}
			}
						
			// 아래쪽 공기청정기 가동, 시계방향 바람
			Wind bottomWind = new Wind(airCon[1].xCord, airCon[1].yCord);
			for(int j=1; j<C; j++) {
				bottomWind.yCord += 1;
				if(nextRoom[bottomWind.xCord][bottomWind.yCord] != 0) { // 바람이 있는 칸에 먼지가 있을 때
					int curDust = nextRoom[bottomWind.xCord][bottomWind.yCord];
					if(bottomWind.temp != 0) { // 이전칸에서 이동해온 먼지가 있을 때
						nextRoom[bottomWind.xCord][bottomWind.yCord] = bottomWind.temp;
						bottomWind.temp = curDust;
					}else { // 이전칸에서 이동해온 먼지가 없을 때					
						nextRoom[bottomWind.xCord][bottomWind.yCord] = 0;
						bottomWind.temp = curDust;
					}
				}else { // 바람이 있는 칸에 먼지가 없을 때
					if(bottomWind.temp != 0 ) { // 이전칸에서 이동해온 먼지가 있을 때
						nextRoom[bottomWind.xCord][bottomWind.yCord] = bottomWind.temp;
						bottomWind.temp = 0;
					}
				}
			}
			for(int j=0; j<R - airCon[1].xCord; j++) {
				bottomWind.xCord += 1;
				if(nextRoom[bottomWind.xCord][bottomWind.yCord] != 0) { // 바람이 있는 칸에 먼지가 있을 때
					int curDust = nextRoom[bottomWind.xCord][bottomWind.yCord];
					if(bottomWind.temp != 0) { // 이전칸에서 이동해온 먼지가 있을 때
						nextRoom[bottomWind.xCord][bottomWind.yCord] = bottomWind.temp;
						bottomWind.temp = curDust;
					}else { // 이전칸에서 이동해온 먼지가 없을 때					
						nextRoom[bottomWind.xCord][bottomWind.yCord] = 0;
						bottomWind.temp = curDust;
					}
				}else { // 바람이 있는 칸에 먼지가 없을 때
					if(bottomWind.temp != 0 ) { // 이전칸에서 이동해온 먼지가 있을 때
						nextRoom[bottomWind.xCord][bottomWind.yCord] = bottomWind.temp;
						bottomWind.temp = 0;
					}
				}
			}
			for(int j=1; j<C; j++) {
				bottomWind.yCord -= 1;
				if(nextRoom[bottomWind.xCord][bottomWind.yCord] != 0) { // 바람이 있는 칸에 먼지가 있을 때
					int curDust = nextRoom[bottomWind.xCord][bottomWind.yCord];
					if(bottomWind.temp != 0) { // 이전칸에서 이동해온 먼지가 있을 때
						nextRoom[bottomWind.xCord][bottomWind.yCord] = bottomWind.temp;
						bottomWind.temp = curDust;
					}else { // 이전칸에서 이동해온 먼지가 없을 때					
						nextRoom[bottomWind.xCord][bottomWind.yCord] = 0;
						bottomWind.temp = curDust;
					}
				}else { // 바람이 있는 칸에 먼지가 없을 때
					if(bottomWind.temp != 0 ) { // 이전칸에서 이동해온 먼지가 있을 때
						nextRoom[bottomWind.xCord][bottomWind.yCord] = bottomWind.temp;
						bottomWind.temp = 0;
					}
				}
			}
			for(int j=0; j<R - airCon[1].xCord; j++) {
				bottomWind.xCord -= 1;
				if(bottomWind.xCord == airCon[1].xCord)
					break;
				if(nextRoom[bottomWind.xCord][bottomWind.yCord] != 0) { // 바람이 있는 칸에 먼지가 있을 때
					int curDust = nextRoom[bottomWind.xCord][bottomWind.yCord];
					if(bottomWind.temp != 0) { // 이전칸에서 이동해온 먼지가 있을 때
						nextRoom[bottomWind.xCord][bottomWind.yCord] = bottomWind.temp;
						bottomWind.temp = curDust;
					}else { // 이전칸에서 이동해온 먼지가 없을 때					
						nextRoom[bottomWind.xCord][bottomWind.yCord] = 0;
						bottomWind.temp = curDust;
					}
				}else { // 바람이 있는 칸에 먼지가 없을 때
					if(bottomWind.temp != 0 ) { // 이전칸에서 이동해온 먼지가 있을 때
						nextRoom[bottomWind.xCord][bottomWind.yCord] = bottomWind.temp;
						bottomWind.temp = 0;
					}
				}
			}
						
			// 미세먼지가 동시에 이동하고, 공기청정기가 작동한 상태를 기존 배열에 초기화
			for(int j=1; j<R+1; j++) {
				for(int k=1; k<C+1; k++) {
					room[j][k] = nextRoom[j][k];
				}
			}
		}
		
		// 방에 남아있는 미세먼지 양 출력
		int sum = 0;
		for(int i=1; i<R+1; i++) {
			for(int j=1; j<C+1; j++) {
				if(room[i][j]>0)
					sum += room[i][j];
			}
		}
		
		System.out.println(sum);

	}

}

class AirCon{
	
	public AirCon(int num, int xCord, int yCord) {
		this.num = num;
		this.xCord = xCord;
		this.yCord = yCord;
	}
	int num;
	int xCord;
	int yCord;
}

class Wind{
	public Wind(int xCord, int yCord) {
		this.xCord = xCord;
		this.yCord = yCord;
	}
	int xCord;
	int yCord;
	int temp = 0;
}
