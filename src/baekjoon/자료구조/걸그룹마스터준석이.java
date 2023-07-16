package baekjoon.자료구조;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 백준 16165
 * 실버 3
 * @author didgs
 *
 */
	
public class 걸그룹마스터준석이 {
	static int N, M;
	static HashMap<String, String> MEMBERMAP = new HashMap<String, String>();
	static HashMap<String, ArrayList<String>> TEAMMAP = new HashMap<String, ArrayList<String>>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		for(int i=0; i<N; i++) 
			inputGroup(sc);
		
		for(int i=0; i<M; i++)
			ansQuery(sc);
		
	}
	
	public static void inputGroup(Scanner sc) {
		String teamName = sc.next();
		int memberCount = sc.nextInt();
		ArrayList<String> memberList = new ArrayList<String>();
		TEAMMAP.put(teamName, memberList);
		for(int i=0; i<memberCount; i++) {
			String memberName = sc.next();
			MEMBERMAP.put(memberName, teamName);
			ArrayList<String> list = TEAMMAP.get(teamName);
			list.add(memberName);
			TEAMMAP.put(teamName, list);
		}
	}
	
	public static void ansQuery(Scanner sc) {
		String query = sc.next();
		int type = sc.nextInt();
		
		if(type == 0) {
			ArrayList<String> list = TEAMMAP.get(query);
			Collections.sort(list);
			for(String s : list)
				System.out.println(s);
		}else if(type == 1) {
			String team = MEMBERMAP.get(query);
			System.out.println(team);
		}
	}
	
	

}
