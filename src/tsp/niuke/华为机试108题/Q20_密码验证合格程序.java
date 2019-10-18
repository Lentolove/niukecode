package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q20_密码验证合格程序 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            System.out.println(verify(str) ? "OK" : "NG");
        }
    }

    private static boolean verify(String s) {
        if (s.length() <= 8 || s == null) return false;//1.长度超过八位
        int low = 0, hight = 0, num = 0, other = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                low = 1;
            } else if (c >= 'A' && c <= 'Z') {
                hight = 1;
            } else if (c >= '0' && c <= '9') {
                num = 1;
            } else {
                other = 1;
            }
        }
        if (low + hight + num + other < 3) return false;//2.至少三种类型,写代码时习惯性写成hight++，难怪通过不了
        //判断有无重复子串长度超过2的方法，我之前没用contain，而是在加一个循环进行比较，也是可以的，这个更好
        for (int i = 0; i < s.length() - 3; i++) {
            String s1 = s.substring(i, i + 3);
            if (s.substring(i + 3).contains(s1)) return false;
        }
        return true;
    }
}
