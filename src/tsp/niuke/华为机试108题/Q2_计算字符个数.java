package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Q2_计算字符个数 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = bf.readLine().toCharArray();
        String s = bf.readLine();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (s.equalsIgnoreCase(String.valueOf(chars[i]))) count++;
        }
        System.out.println(count);
    }
}
