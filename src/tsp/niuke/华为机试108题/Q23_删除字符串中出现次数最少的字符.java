package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q23_删除字符串中出现次数最少的字符 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = bf.readLine()) != null) {
            int[] count = new int[26];
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                count[index]++;
                if (min >= count[index]) {
                    min = count[index];
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (count[s.charAt(i) - 'a'] != min) {
                    sb.append(s.charAt(i));
                }
            }
            System.out.println(sb.toString());
        }
    }
}
