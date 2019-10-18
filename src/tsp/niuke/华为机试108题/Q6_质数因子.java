package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 题目描述
 * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质数的因子（如180的质数因子为2 2 3 3 5 ）
 * 最后一个数后面也要有空格
 *
 * 详细描述：
 *
 * 函数接口说明：
 * public String getResult(long ulDataInput)
 * 输入参数：
 * long ulDataInput：输入的正整数
 * 返回值：
 * String
 *
 *
 * 输入描述:
 * 输入一个long型整数
 * 输出描述:
 * 按照从小到大的顺序输出它的所有质数的因子，以空格隔开。最后一个数后面也要有空格。
 * 示例1
 * 输入
 *
 * 复制
 * 180
 * 输出
 *
 * 复制
 * 2 2 3 3 5
 */
public class Q6_质数因子 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long x = Long.parseLong(bf.readLine());
        System.out.println(getResult(x));
    }

    public static String getResult(long x) {
        StringBuilder sb = new StringBuilder();
        int pum = 2;//质数
        while (x != 1) {
            while (x % pum == 0) {
                sb.append(pum).append(" ");
                x /= pum;
            }
            pum++;//如果它调到了4，就在前面就被2分解了
        }
        return sb.toString();
    }
}
