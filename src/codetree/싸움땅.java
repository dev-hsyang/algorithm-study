package codetree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 싸움땅 {

	static ArrayList<Integer>[][] gunMap;
	static Player[] players;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, M, K;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		players = new Player[M];
		gunMap = new ArrayList[20][20];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int num = sc.nextInt();
				gunMap[i][j] = new ArrayList<>();
				if(num>0)
					gunMap[i][j].add(num);
			}
		}
		
		for(int i=0; i<M; i++) {
			int xCord = sc.nextInt() - 1;
			int yCord = sc.nextInt() - 1;
			int dir = sc.nextInt();
			int stat = sc.nextInt();
			players[i] = new Player(i, xCord, yCord, dir, stat, 0, 0);
		}
		
		for(int i=0; i<K; i++) { // 각 라운드마다
			for(int j=0; j<M; j++) { // 첫번째 플레이어부터 순차적으로.
				Player p = players[j];
				// STEP1 본인이 향하는 방향대로 한칸 전진
				int nx = p.xCord + dx[p.dir];
				int ny = p.yCord + dy[p.dir];
				if(nx<0 || nx>=N || ny<0 || ny>=N) { // 본인이 향하는 칸이 격자를 벗어나는 경우 
					p.turnBack();
					nx = p.xCord + dx[p.dir];
					ny = p.yCord + dy[p.dir];
				}
				// STEP2-1 이동할칸에 플레이어가X 
				if(!playerExists(nx, ny)) {
					p.xCord = nx;
					p.yCord = ny;
					if(gunExists(nx, ny)) { // 이동할칸에 총이 O
						if(p.gun != 0) { // 플레이어가 이미 총을 가지고 있을 때
							int pgun = p.gun;
							Collections.sort(gunMap[nx][ny], Collections.reverseOrder()); // 해당 칸에서 가장 높은 공격력의 총 찾고
							pgun = gunMap[nx][ny].get(0); // pgun에 임시 저장
							if(p.gun<gunMap[nx][ny].get(0)) { // 플레이어가 가진 총보다 더 쎄면 교체한다
								gunMap[nx][ny].remove(0); // gunmap에서 해당 총을 빼고
								gunMap[nx][ny].add(p.gun); // 현재 플레이어가 가진 총을 내려놓는다
								p.gun = pgun; // 빼온 총을 플레이어가 갖는다
							}
						}else { // 플레이어가 총을 갖고 있지 않을 때;
							Collections.sort(gunMap[nx][ny], Collections.reverseOrder());
							p.gun = gunMap[nx][ny].get(0);
							gunMap[nx][ny].remove(0);
						}	
					}
				}else { // STEP2-2 이동할칸에 플레이어가O
					// 해당플레이어와 싸운다.
					Player fightplayer = findPlayer(nx, ny);
					Player winPlayer = null;
					Player losePlayer = null;
					int nowPlayerStat = p.stat;
					int nowPlayerGun = p.gun;
					int fightPlayerStat = fightplayer.stat;
					int fightPlayerGun = fightplayer.gun;
					if(nowPlayerStat + nowPlayerGun == fightPlayerStat + fightPlayerGun) { // 해당플레이어와 상대플레이어의 능력치+총합이 같으면
						if(nowPlayerStat>fightPlayerStat) {
							winPlayer = new Player(p.num, nx, ny, p.dir, p.stat, p.gun, p.point);
							losePlayer = new Player(fightplayer.num, fightplayer.xCord, fightplayer.yCord, fightplayer.dir, fightplayer.stat, fightplayer.gun, fightplayer.point);
						}else if(nowPlayerStat<fightPlayerStat) {
							winPlayer = new Player(fightplayer.num, fightplayer.xCord, fightplayer.yCord, fightplayer.dir, fightplayer.stat, fightplayer.gun, fightplayer.point);
							losePlayer = new Player(p.num, nx, ny, p.dir, p.stat, p.gun, p.point);
						}
					}else if(nowPlayerStat + nowPlayerGun > fightPlayerStat + fightPlayerGun) { // 해당플레이어의 능력치+총합이 더 크면
						winPlayer = new Player(p.num, nx, ny, p.dir, p.stat, p.gun, p.point);	
						losePlayer = new Player(fightplayer.num, fightplayer.xCord, fightplayer.yCord, fightplayer.dir, fightplayer.stat, fightplayer.gun, fightplayer.point);
					}else if(nowPlayerStat + nowPlayerGun < fightPlayerStat + fightPlayerGun) { // 해당플레이어의 능력치+총합이 더 작으면
						winPlayer = new Player(fightplayer.num, fightplayer.xCord, fightplayer.yCord, fightplayer.dir, fightplayer.stat, fightplayer.gun, fightplayer.point);
						losePlayer = new Player(p.num, nx, ny, p.dir, p.stat, p.gun, p.point);
					}
					p.xCord = nx;
					p.yCord = ny;
					int gain = -1;
					// 두 플레이어의 능력치+총 합의 차이만큼을 이긴플레이어가 포인트로 가져간다
					gain = (winPlayer.stat + winPlayer.gun) - (losePlayer.stat + losePlayer.gun);					
					winPlayer.point += gain;
					
					// 진 플레이어는 본인총을 해당 칸에 내버려둔다
					int losergun = losePlayer.gun;
					if(losergun!=0)
						gunMap[nx][ny].add(losergun);
					losePlayer.gun = 0;
					
					// 그러고 나서 원래 방향대로 한칸 이동하는데,
					// 이동할칸이 경계거나 플레이어가 있으면 오른쪽으로 90도 회전해서 빈칸을 찾는다
					// 만약 빈칸에 총이 있다면 마찬가지로 처리
					int curDir = losePlayer.dir;
					int loserx = losePlayer.xCord + dx[curDir];
					int losery = losePlayer.yCord + dy[curDir];
					for(int k=0; k<4; k++) {
						if(loserx>=0 && loserx<N && losery>=0 && losery<N) // 이동할칸이 경계가 아니고
							if(!playerExists(loserx, losery)) { // 플레이어가 없다면
								losePlayer.dir = curDir;
								break;								
							}
						// 이동할칸이 경계거나, 플레이어가 있다면 오른쪽방향으로 90도 회전후 다시 확인
						curDir += 1;
						if(curDir>3)
							curDir=0;
						loserx = losePlayer.xCord + dx[curDir];
						losery = losePlayer.yCord + dy[curDir];
					}
					losePlayer.dir = curDir;
					losePlayer.xCord = loserx;
					losePlayer.yCord = losery;								
					// 만약 이동할칸에 총이 있다면 마찬가지로 가장 공격력이 높은 총을 획득한다.
					if(gunExists(loserx, losery)) {
						Collections.sort(gunMap[loserx][losery], Collections.reverseOrder());
						losePlayer.gun = gunMap[loserx][losery].get(0);
						gunMap[loserx][losery].remove(0);
					}
					
					// 이긴 플레이어는 승리한 칸에 떨어져 있는 총들과 원래 들고 있던 총 중 가장 공격력이 높은 총을 획득한다.
					if(gunExists(winPlayer.xCord, winPlayer.yCord)) {
						if(winPlayer.gun != 0) { // 플레이어가 이미 총을 가지고 있을 때
							int pgun = winPlayer.gun;
							Collections.sort(gunMap[winPlayer.xCord][winPlayer.yCord], Collections.reverseOrder()); // 해당 칸에서 가장 높은 공격력의 총 찾고
							pgun = gunMap[winPlayer.xCord][winPlayer.yCord].get(0); // pgun에 임시 저장
							if(winPlayer.gun < gunMap[winPlayer.xCord][winPlayer.yCord].get(0)) { // 플레이어가 가진 총보다 더 쎄면
								gunMap[winPlayer.xCord][winPlayer.yCord].remove(0); // gunmap에서 해당 총을 빼고
								gunMap[winPlayer.xCord][winPlayer.yCord].add(winPlayer.gun); // 현재 플레이어가 가진 총을 내려놓는다
								winPlayer.gun = pgun; // 빼온 총을 플레이어가 갖는다
							}
						}else { // 플레이어가 총을 갖고 있지 않을 때;
							int pgun = winPlayer.gun;
							Collections.sort(gunMap[winPlayer.xCord][winPlayer.yCord], Collections.reverseOrder());
							pgun = gunMap[winPlayer.xCord][winPlayer.yCord].get(0);
							gunMap[winPlayer.xCord][winPlayer.yCord].remove(0);
							winPlayer.gun = pgun;
						}

					}

					players[winPlayer.num] = new Player(winPlayer.num, winPlayer.xCord, winPlayer.yCord, winPlayer.dir, winPlayer.stat, winPlayer.gun, winPlayer.point);
					players[losePlayer.num] = new Player(losePlayer.num, losePlayer.xCord, losePlayer.yCord, losePlayer.dir, losePlayer.stat, losePlayer.gun, losePlayer.point);
				}
			}
		}
		
		for(Player p : players)
			System.out.print(p.point + " ");
	}
	
	public static boolean playerExists(int xCord, int yCord) {
		for(Player p : players) 
			if(p.xCord == xCord && p.yCord == yCord)
				return true;
		return false;
	}
	
	public static Player findPlayer(int xCord, int yCord) {
		for(Player p : players)
			if(p.xCord == xCord && p.yCord == yCord)
				return p;
		return null;
	}
	
	public static boolean gunExists(int xCord, int yCord) {
		if(gunMap[xCord][yCord].size()>0)
			return true;
		return false;
	}

}

class Player{
	
	public Player(int num, int xCord, int yCord, int dir, int stat, int gun, int point) {
		this.num = num;
		this.xCord = xCord;
		this.yCord = yCord;
		this.dir = dir;
		this.stat = stat;
		this.gun = gun;
		this.point = point;
	}
	int num;
	int xCord, yCord;
	int dir;
	int stat;
	int gun;
	int point;
	
	public void turnBack() {
		switch (dir) {
		case 0:
			this.dir = 2;
			break;
		case 1:
			this.dir = 3;
			break;
		case 2:
			this.dir = 0;
			break;
		case 3:
			this.dir = 1;
			break;
		default:
			break;
		}
	}
}
