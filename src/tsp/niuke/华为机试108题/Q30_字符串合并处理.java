package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * String s = "0123456789abcdefABCDEF";
 * s.indexOf()中传入的字符，传 'a'返回索引10，只能传字符 '0' 而不能穿数字 0 进去，要主要呀！！！
 */

public class Q30_字符串合并处理 {

    static String helper1 = "0123456789abcdefABCDEF";//原始字符
    static String helper2 = "084C2A6E195D3B7F5D3B7F";//相应的转换后的字符

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            String[] split = str.split(" ");//两个字符串
            String s = split[0] + split[1];
            int len = s.length();
            char[] odd = new char[(len + 1) / 2];//存储偶数下边的字符
            char[] even = new char[len / 2];
            int p1 = 0, p2 = 0;
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (i % 2 == 0) {
                    odd[p1++] = c;
                } else {
                    even[p2++] = c;
                }
            }
            Arrays.sort(odd);//对索引为偶数的进行排序
            Arrays.sort(even);//对索引为奇数下边的进行排序
            p1 = 0;//odd的指针
            p2 = 0;//even的指针
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                char c = i % 2 == 0 ? odd[p1++] : even[p2++];
                if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) {
                    c = helper2.charAt(helper1.indexOf(c));
                }
                sb.append(c);
            }
            System.out.println(sb.toString());
        }
    }
}
