package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10_字符个数统计 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        int ans = 0;
        boolean[] flag = new boolean[128];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 0 && s.charAt(i) <= 127) {
                if (!flag[s.charAt(i)]) ans++;
                flag[s.charAt(i)] = true;
            }
        }
        System.out.println(ans);
    }
}
