package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q33_整数与IP地址间的转换 {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            System.out.println(ip2ten(str));
            System.out.println(ten2ip(bf.readLine()));
        }

    }

    //输入IP转10进制
    private static long ip2ten(String s) {
        String[] split = s.split("\\.");
        long result = 0;
        for (int i = 0; i < 4; i++) {
            result += Long.parseLong(split[i]) << ((3 - i) * 8);
        }
        return result;
    }

    //10进制转ip地址
    private static String ten2ip(String s) {
        StringBuilder sb = new StringBuilder();
        long a = Long.parseLong(s);
        for (int i = 3; i >= 0; i--) {
            int b = 1 << (i * 8);
            sb.append(a / b).append(".");
            a %= b;
        }
        return sb.substring(0, sb.length() - 1).toString();
    }
}
