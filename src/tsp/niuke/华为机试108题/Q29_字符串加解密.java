package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q29_字符串加解密 {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            String s2 = bf.readLine();
            System.out.println(Encrypt(str));
            System.out.println(unEncrypt(s2));
        }
    }

    //加密
    private static String Encrypt(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isLetter(c)) {//是字母
                if (Character.isLowerCase(c)) {
                    if (c == 'z') {
                        sb.append('A');
                    } else {
                        sb.append((char) (c - 31));
                    }
                } else {
                    if (c == 'Z') {
                        sb.append('a');
                    } else {
                        sb.append((char) (c + 33));
                    }
                }
            } else {//是数字
                sb.append((c - '0' + 1) % 10);
            }
        }
        return sb.toString();
    }

    //解密
    private static String unEncrypt(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isLetter(c)) {//是字母
                if (Character.isLowerCase(c)) {
                    if (c == 'a') {
                        sb.append('Z');
                    } else {
                        sb.append((char) (c - 33));
                    }
                } else {
                    if (c == 'A') {
                        sb.append('z');
                    } else {
                        sb.append((char) (c + 31));
                    }
                }
            } else {//是数字
                int a = (c - '0');
                sb.append(a == 0 ? 9 : a - 1);
            }
        }
        return sb.toString();
    }
}

//class Mehtod2 {
//    static String helper1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//    static String helper2 = "BCDEFGHIJKLMNOPQRSTUVWXYZAbcdefghijklmnopqrstuvwxyza1234567890";
//}