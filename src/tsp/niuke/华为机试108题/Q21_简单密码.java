package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q21_简单密码 {

    static int[] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        map = new int[]{2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,8,8,8,9,9,9,9};
        String str;
        while ((str = bf.readLine()) != null) {
            System.out.println(getPw(str));
        }
    }

    private static String getPw(String s) {
        if (s == null || s.equals(" ")) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
//            boolean upperCase = Character.isUpperCase(c);
            if (c >= 'A' && c <= 'Z') {
                if (c == 'Z') {
                    sb.append('a');
                } else {
                    sb.append((char) (c + 33));
                }
            } else if (c >= 'a' && c <= 'z') {
                sb.append(map[c-'a']);
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
