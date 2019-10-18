package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q37_统计每个月兔子的总数 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            int n = Integer.parseInt(str);
            System.out.println(getTotalCount(n));
        }
    }

    //兔子畜生问题 f(n) = f(n-1)+f(n-2)
    private static int getTotalCount(int n) {
        int one = 1;
        int two = 1;
        int total = 1;
        if (n <= 3) return 1;
        for (int i = 4; i <= n; i++) {
            total = one + two;
            one = two;
            two = total;
        }
        return total;
    }
}
