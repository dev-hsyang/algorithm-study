package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 체스판위의룩배치15612 {
	
	static int T;
	static int[] dx = {0, 1, 0, -1, 0, 2, 0, -2, 0, 3, 0, -3, 0, 4, 0, -4, 0, 5, 0, -5, 0, 6, 0, -6, 0, 7, 0, -7, 0, 8, 0, -8};
	static int[] dy = {1, 0, -1, 0, 2, 0, -2, 0, 3, 0, -3, 0, 4, 0, -4, 0, 5, 0, -5, 0, 6, 0, -6, 0, 7, 0, -7, 0, 8, 0, -8, 0};
	static String[][] board;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=T; i++) {
			board = new String[8][8];
			String ans = "yes";
			int cnt = 0;
			
			for(int j=0; j<8; j++) {
				st = new StringTokenizer(br.readLine());
				String[] s = st.nextToken().split("");
				for(int k=0; k<8; k++)
					board[j][k] = s[k];
			}
			
			for(int j=0; j<8; j++)
				for(int k=0; k<8; k++) {
					if(board[j][k].equals("O")) {
						cnt += 1;
						if(cnt>8) {
							ans = "no";
							break;
						}
						for(int n=0; n<8; n++) {
							int nx = j + dx[n];
							int ny = k + dy[n];
							if(isInbound(nx, ny) && board[nx][ny].equals("O")) {
								ans = "no";
								break;
							}
						}
					}
				}
			
			if(cnt!=8)
				ans = "no";
			
			System.out.println("#" + i + " " + ans);
		}
	}
	
	public static boolean isInbound(int x, int y) {
		
		if(x>=0 && x<8 && y>=0 && y<8)
			return true;
		
		return false;
	}

}
