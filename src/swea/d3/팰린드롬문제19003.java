package swea.d3;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class 팰린드롬문제19003 {

    static int TC, N, M, PAL, ANS;
    static boolean[] PICK;
    static String[][] WORDS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        TC = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case<=TC; test_case++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ANS = 0;
            PAL = 0;
            PICK = new boolean[N];
            WORDS = new String[N][M];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                String[] s = st.nextToken().split("");
                for(int j=0; j<M; j++)
                    WORDS[i][j] = s[j];
            }

            for(int i=0; i<N; i++)
                if(isPalindrome(WORDS[i]))
                    PAL = M;

            for(int i=0; i<N; i++) {
                PICK[i] = true;
                String[] oppo = new String[M];
                for (int j = 0; j < M; j++)
                    oppo[j] = WORDS[i][M - 1 - j];
                for (int j = 0; j < N; j++)
                    if (!PICK[j] && equals(oppo, j))
                        ANS += M;
                PICK[i] = false;
            }
            ANS += PAL;
            bw.append("#" + test_case + " " + ANS  + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static boolean equals(String[] word, int index){
        for(int i=0; i<word.length; i++)
            if(!word[i].equals(WORDS[index][i]))
                return false;
        return true;
    }

    public static boolean isPalindrome(String[] word){
        Stack<String> stack = new Stack<>();
        for(int i=0; i<word.length/2; i++)
            stack.push(word[i]);
        for(int i=word.length % 2 == 0 ? word.length / 2 : word.length / 2 + 1; i<word.length; i++){
            String s = stack.pop();
            if(!word[i].equals(s))
                return false;
        }
        return true;
    }
}