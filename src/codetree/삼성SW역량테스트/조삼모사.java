package codetree.삼성SW역량테스트;

import java.util.*;

/**
 * 실버 2
 * 삼성 SW 역량테스트 2017 하반기 오전 1번 문제
 */
public class 조삼모사 {

    static int N;
    static int ANS = Integer.MAX_VALUE;
    static int[][] P;
    static boolean[] NIGHT_VISITED;
    static ArrayList<Integer> MORNING = new ArrayList<>();
    static ArrayList<Integer> NIGHT = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        P = new int[N][N];
        NIGHT_VISITED = new boolean[N+1];

        for (int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                P[i][j] = sc.nextInt();

        operate(0);

        System.out.println(ANS);
    }

    public static void operate(int count){
        if(count == N){
            System.out.print("MORNING: ");
            for(int i : MORNING)
                System.out.print(i);
            System.out.print(" NIGHT: ");
            for(int i : NIGHT)
                System.out.print(i);
            System.out.println("");

            //if(isAvailable())
                ANS = Math.min(ANS, calculate());
            return;
        }

        for(int i=1; i<N+1; i++){
            if(MORNING.size() < N/2 && !MORNING.contains(i) ){
                MORNING.add(i);
                operate(count + 1);
                MORNING.remove(MORNING.size() - 1);
            }else if(MORNING.size() >= N/2 && !MORNING.contains(i) && !NIGHT.contains(i)){
                NIGHT.add(i);
                operate(count + 1);
                NIGHT.remove(NIGHT.size() - 1);
            }
        }
    }

    public static boolean isAvailable(){
        int[] check = new int[N];
        for(int i=0; i<N; i++){
            if(i<N/2)
                check[i] = MORNING.get(i);
            else if(i>=N/2)
                check[i] = NIGHT.get(i-N/2);
        }
        Arrays.sort(check);
        for(int i=0; i<N-1; i++)
            if(check[i] == check[i+1])
                return false;
        return true;
    }

    public static int calculate(){
        return getDiff(getSum(MORNING), getSum(NIGHT));
    }

    public static int getSum(ArrayList<Integer> time){
        Queue<int[]> queue = new LinkedList<>();
        int sum = 0;
        for(int i=0; i<time.size()-1; i++)
            for(int j=i+1; j<time.size(); j++)
                queue.add(new int[] {time.get(i), time.get(j)});
        while(!queue.isEmpty()){
            int[] pair = queue.poll();
            sum += P[pair[0]-1][pair[1]-1] + P[pair[1]-1][pair[0]-1];
        }
        return sum;
    }

    public static int getDiff(int a, int b){
        if(a > b)
            return a - b;
        else
            return b - a;
    }

    public static int getLast(ArrayList<Integer> arr){
        if(arr.size() == 0)
            return -1;
        else
            return arr.get(arr.size() - 1);
    }
}
