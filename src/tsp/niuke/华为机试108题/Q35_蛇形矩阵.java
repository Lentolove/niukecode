package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q35_蛇形矩阵 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            int n = Integer.parseInt(str);
            System.out.print(printMatrix(n));//我也是醉了，这里打成println就case为0，输出格式不符合
        }
    }

    private static String printMatrix(int n) {
        StringBuilder sb = new StringBuilder();
        int begin = 1;
        for (int i = 1; i <= n; i++) {//一共n层
            sb.append(begin);
            int temp = begin;
            for (int j = i + 1; j <= n; j++) {//横着没两个数之间的差值为j的
                temp += j;
                sb.append(" ").append(temp);
            }
            sb.append("\n");//换行
            begin += i;//每一行的起始位置
        }
        return sb.toString();
    }


    private static String getResult(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            //j为每行元素个数的迭代,每一行的第一个数是个等差数列 1 2 4 7 11
            // an - a1 = 1+2+3+..n-1 -> an = (1+n-1)(n)/2
            for (int j = 0, start = (i - 1) * i / 2 + 1, step = i + 1; j <= n - i; j++, start += step, step++) {
                sb.append(start).append(" ");
            }
            sb.setCharAt(sb.length() - 1, '\n');
        }
        return sb.toString();
    }
}
