package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 실버 5
 */
public class 집합 {

    static int M;
    static HashMap<String, String> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();

        for(int i=0; i<M; i++){
            String method = sc.next();
            System.out.println(i);
            if(method.equals("all")) {
                all();
                continue;
            }else if(method.equals("empty")){
                empty();
                continue;
            }else{
                String val = sc.next();
                operate(method, val);
            }
        }
    }

    public static void operate(String method, String val){
        if(method.equals("add")){
            if(!hashMap.containsValue(val))
                hashMap.put(val, val);
        }else if(method.equals("remove")){
            if(hashMap.containsValue(val))
                hashMap.remove(val);
        }else if(method.equals("check")){
            if(hashMap.containsValue(val))
                System.out.println("1");
            else
                System.out.println("0");
        }else if(method.equals("toggle")){
            if(hashMap.containsValue(val))
                hashMap.remove(val);
            else
                hashMap.put(val, val);
        }
    }

    public static void all(){
        hashMap = new HashMap<>();
        for(int i=1; i<=20; i++)
            hashMap.put(String.valueOf(i), String.valueOf(i));
    }

    public static void empty(){
        hashMap = new HashMap<>();
    }
}
