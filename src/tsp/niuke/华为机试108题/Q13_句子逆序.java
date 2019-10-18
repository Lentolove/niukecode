package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q13_句子逆序 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        System.out.println(reverse(s));
    }

    public static String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        String[] split = s.split(" ");
        for (int i = split.length-1; i >=0 ; i--) {
            sb.append(split[i]).append(" ");
        }
        return sb.substring(0,sb.length()-1).toString();
    }

}
