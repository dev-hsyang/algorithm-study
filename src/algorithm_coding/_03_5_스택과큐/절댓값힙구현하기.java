package algorithm_coding._03_5_스택과큐;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 11286
 * 실버 1
 */
public class 절댓값힙구현하기 {

    static int N;
    static PriorityQueue<Positive> POSITIVE = new PriorityQueue<Positive>();
    static PriorityQueue<Negative> NEGATIVE = new PriorityQueue<Negative>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            operate(num, bw);
        }
        bw.flush();
        bw.close();
    }

    public static void operate(int num, BufferedWriter bw) throws IOException {
        if(num> 0)
            POSITIVE.add(new Positive(num));
        else if(num< 0)
            NEGATIVE.add(new Negative(num));
        else if(num == 0)
            print(bw);
    }

    public static void print(BufferedWriter bw) throws IOException {
        if(!POSITIVE.isEmpty() && !NEGATIVE.isEmpty()){
            int p = POSITIVE.peek().num;
            int n = -NEGATIVE.peek().num;
            if(p >= n)
                bw.append(NEGATIVE.poll().num + "\n");
            else
                bw.append(POSITIVE.poll().num + "\n");
        }else if(POSITIVE.isEmpty() && !NEGATIVE.isEmpty())
            bw.append(NEGATIVE.poll().num + "\n");
        else if(!POSITIVE.isEmpty() && NEGATIVE.isEmpty()){
            bw.append(POSITIVE.poll().num + "\n");
        }else
            bw.append("0" + "\n");
    }
}

class Positive implements Comparable<Positive>{

    public Positive(int num){
        this.num = num;
    }
    int num;
    @Override
    public int compareTo(Positive o) {
        return o.num > num ? -1 : 1;
    }
}
class Negative implements Comparable<Negative>{

    public Negative(int num){
        this.num = num;
    }
    int num;
    @Override
    public int compareTo(Negative o) {
        return o.num > num ? 1 : -1;
    }
}
