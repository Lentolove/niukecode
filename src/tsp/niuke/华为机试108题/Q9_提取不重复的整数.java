package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Q9_提取不重复的整数 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        HashSet<Character> set = new HashSet<>();
        for (int i = n - 1; i >= 0; i--) {
            if (!set.contains(s.charAt(i))){
                set.add(s.charAt(i));
                sb.append(s.charAt(i));
            }
        }
        System.out.println(sb.toString());
    }
}
