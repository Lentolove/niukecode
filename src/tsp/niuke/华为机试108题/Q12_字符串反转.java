package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q12_字符串反转 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(bf.readLine());
        System.out.println(sb.reverse().toString());
    }
}
