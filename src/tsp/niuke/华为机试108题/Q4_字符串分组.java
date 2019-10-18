package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q4_字符串分组 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str1 = bf.readLine();
        String str2 = bf.readLine();
        int len1 = str1.length();
        int len2 = str1.length();
        StringBuilder sb = new StringBuilder(str1);
        StringBuilder sb1 = new StringBuilder(str2);
        sb.append("0000000");
        int count = sb.length() / 8,start = 0;
        while (start < count) {
            System.out.println(sb.substring(start * 8, (start+1)*8));
            start++;
        }
        count = sb1.append("0000000").length()/8;
        start = 0;
        while (start < count){
            System.out.println(sb1.substring(start * 8, (start+1)*8));
            start++;
        }
    }
}
