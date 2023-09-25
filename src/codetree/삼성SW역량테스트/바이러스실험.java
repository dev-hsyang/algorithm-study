package codetree.삼성SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 삼성 SW역량테스트 2018 하반기 오후 1번 문제
 * @author didgs
 *
 */
public class 바이러스실험 {
	
	public static int N, M, K, ANS;
	public static int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
	public static int[] DY = {1, 1, 0, -1, -1, -1, 0, 1};
	public static int[][] CANDI;
	public static int[][] ADD;
	public static ArrayList<Dead2018> DEAD_VIRUS;
	public static Virus2018[][] VIRUS;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		CANDI = new int[N][N];
		ADD = new int[N][N];
		VIRUS = new Virus2018[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				ADD[i][j] = Integer.parseInt(st.nextToken());
				CANDI[i][j] = 5;
				VIRUS[i][j] = new Virus2018();
			}
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken());
			VIRUS[r][c].queue.add(a);
		}
		
		for(int i=0; i<K; i++) 
			simulate();
		
		countVirus();
		
		System.out.println(ANS);
	}
	
	public static void simulate() {
		grow();
		update();
		breed();
		addCandi();
	}
	
	public static void grow() {
		DEAD_VIRUS = new ArrayList<Dead2018>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!VIRUS[i][j].queue.isEmpty()) {
					PriorityQueue<Integer> tempQ = new PriorityQueue<>();
					while(!VIRUS[i][j].queue.isEmpty()) {
						int age = VIRUS[i][j].queue.poll();
						if(age <= CANDI[i][j]) {
							tempQ.add(age + 1);
							CANDI[i][j] -= age;
						}else {
							DEAD_VIRUS.add(new Dead2018(i, j, age));
						}
					}
					VIRUS[i][j].queue = tempQ;
				}
			}
		}
	}
	
	public static void update() {
		for(Dead2018 d : DEAD_VIRUS)
			CANDI[d.x][d.y] += d.age / 2;
	}
	
	public static void breed() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!VIRUS[i][j].queue.isEmpty()) {
					PriorityQueue<Integer> tempQ = new PriorityQueue<>();
					while(!VIRUS[i][j].queue.isEmpty()) {
						int age = VIRUS[i][j].queue.poll();
						if(age % 5 == 0) {
							for(int k=0; k<8; k++) {
								int nx = i + DX[k];
								int ny = j + DY[k];
								if(canGo(nx, ny)) {
									VIRUS[nx][ny].queue.add(1);
								}
							}
						}
						tempQ.add(age);
					}
					VIRUS[i][j].queue = tempQ;
				}
			}
		}
	}
	
	public static void addCandi() {
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				CANDI[i][j] += ADD[i][j];
	}
	
	public static void countVirus() {
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				ANS += VIRUS[i][j].queue.size();
	}
	
	public static boolean canGo(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
}


class Virus2018{
	PriorityQueue<Integer> queue = new PriorityQueue<>();
}

class Dead2018{
	
	public Dead2018(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
	}
	int x, y, age;
}

