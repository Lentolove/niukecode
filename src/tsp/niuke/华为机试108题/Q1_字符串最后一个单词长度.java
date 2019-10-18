package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 题目描述
 * 计算字符串最后一个单词的长度，单词以空格隔开。
 * 输入描述:
 * 一行字符串，非空，长度小于5000。
 * 输出描述:
 * 整数N，最后一个单词的长度。
 * 示例1
 * 输入
 *
 * 复制
 * hello world
 * 输出
 *
 * 复制
 * 5
 */
public class Q1_字符串最后一个单词长度 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        int n = s.length();
        int i = n - 1;
        for (; i >= 0; i--) {
            if (s.charAt(i) == ' ') break;
        }
        System.out.println(n - i-1);
    }
}
