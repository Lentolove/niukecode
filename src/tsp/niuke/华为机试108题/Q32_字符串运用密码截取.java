package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q32_字符串运用密码截取 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            int maxlen = 0;
            for (int i = 0; i < str.length(); i++) {
                int a = maxLeng(str, i, i);
                int b = maxLeng(str, i, i + 1);
                maxlen = Math.max(maxlen, Math.max(a, b));
            }
            System.out.println(maxlen);
        }
    }
    private static int maxLeng(String s, int i, int j) {
        while (i >=0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }
}
