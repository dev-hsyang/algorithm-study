package baekjoon.자료구조;

import java.util.*;
import java.io.*;

/**
 * 백준 13414
 * 실버 3
 * 
 * 시간초과때문에 고생 많이했던 문제이다.
 * 첫번쨰 시도 :: Set 과 Queue 를 활용해, 다음 학번에 대해 SET에 중복된 값이 있으면, 해당 학번을 맨 뒤로 가게끔 QUEUE를 다시 옮겨 닮았었다.
 * 두번쨰 시도 :: HashMap<String, Integer> 과 LinkedHashMap<Integer, String> 을 이용해서 LinkedHashMap 의 Value 에 중복된 학번이 있다면, HashMap 에서 해당 학번으로 key 값을 찾고, 다시 LinkedHashMap 에서 삭제하고 다시 추가하여 구현하였다.
 * 마지막 시도 :: LinkedHashSet 을 이용하여, 중복을 방지하면서 순서를 지키도록 구현하는데 성공했다.
 * 
 * **** 중요 포인트 ****
 * (1) JAVA 의 Scanner, System.out.print() 는 ** 아주 ** 느리기 때문에, 이 문제와 같이 수십만줄의 입력과 출력을 처리해야할 때는 , BufferedReader 와 BufferedWriter 를 사용할 것.
 * (2) HashMap 에서 ContainsKey() method 는 O(1) 이지만, ** ContainsValue() method 는 worst O(n) 이다 **. 때문에 두번째 시도에서 ContainsValue() 를 사용했을 때, 최종 시간복잡도가 O(n^2) 가 되어 시간초과가 났었던 것.
 * @author didgs
 *
 */
public class 수강신청 {
	
	static int K, L;
	static String[] INPUT;
	static Set<String> SET = new LinkedHashSet<String>();

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		INPUT = new String[L];
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			INPUT[i] = st.nextToken();
		}
		
		for(int i=0; i<L; i++)
			operate(INPUT[i]);
		
		print(bw);
	}
	
	public static void operate(String id) {
		if(!SET.contains(id)) {
			SET.add(id);
		}else {
			SET.remove(id);
			SET.add(id);
		}
	}
	
	public static void print(BufferedWriter bw) throws IOException {
		Iterator<String> iter = SET.iterator();
		int cnt = 0;
		while(iter.hasNext()) {
			if(cnt == K)
				break;
			bw.append(iter.next() + "\n");
			cnt++;
		}
		bw.flush();
		bw.close();
	}
}
