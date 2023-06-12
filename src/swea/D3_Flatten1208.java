package swea;

import java.util.Scanner;

public class D3_Flatten1208 {
	
	static int T = 10;
	static int DUMP, ANS;
	static int[][] MAP;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		for(int i=1; i<=T; i++) {
			DUMP = sc.nextInt();
			MAP = new int[100][100];
			
			for(int j=0; j<100; j++) {
				int height = sc.nextInt();
				for(int k=1; k<=height; k++)
					MAP[100-k][j] = 1;
			}
			for(int j=0; j<DUMP; j++)
				dump();
			ANS = getDiff();
			System.out.println("#" + i + " " + ANS);
		}
	}
	
	public static void dump() {
		int[] maxIndex = getMaxIndex();
		int[] minIndex = getMinIndex();
		MAP[maxIndex[0]][maxIndex[1]] = 0;
		MAP[minIndex[0]][minIndex[1]] = 1;
	}
	
	public static int getDiff() {
		int[] maxIndex = getMaxIndex();
		int[] minIndex = getMinIndex();
		return minIndex[0] - maxIndex[0] + 1;
	}
	
	public static int[] getMaxIndex() {
		for(int i=0; i<100; i++)
			for(int j=0; j<100; j++)
				if(MAP[i][j]==1)
					return new int[] {i, j};
		return null;
	}
	
	public static int[] getMinIndex() {
		for(int i=99; i>=0; i--)
			for(int j=99; j>=0; j--)
				if(MAP[i][j]==0)
					return new int[] {i, j};
		return null;
	}

}
