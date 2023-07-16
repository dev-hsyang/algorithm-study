package baekjoon.자료구조;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 백준 17219
 * 실버 4
 * @author didgs
 *
 */
public class 비밀번호찾기 {
	
	static int N, M;
	static HashMap<String, String> HASHMAP = new HashMap<String, String>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			HASHMAP.put(st.nextToken(), st.nextToken());
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			bw.append(HASHMAP.get(st.nextToken()) + "\n");
		}
		
		bw.flush();
		bw.close();
	}

}
