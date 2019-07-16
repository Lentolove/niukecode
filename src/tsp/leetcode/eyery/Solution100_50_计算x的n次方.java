package tsp.leetcode.eyery;

/**
 * 50:计算 x 的 n 次方
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 */
public class Solution100_50_计算x的n次方 {

    /**
     * 暴力解法超时
     */
    public static double myPow(double x, int n) {
        if (x == 0 && n < 0) {
            throw new RuntimeException("分母不能为零");
        }
        int abs = Math.abs(n);
        double ret = 1;
        for (int i = 1; i <= abs; i++) {
            ret *= x;
        }
        return n > 0 ? ret : 1 / ret;
    }

    /**
     * 快速幂  x^n = (x^(n/2))^2;
     * 分奇偶讨论
     * 可采用递归和循环两种实现方式
     * <p>
     * 递归实现
     */
    public double myPow1(double x, int n) {
        if (n < 0) {
            if (x == 0) {
                throw new RuntimeException("分母不能为0");
            }
            x = 1 / x;
            n = -n;
        }
        return fastPower(x, n);
    }

    private double fastPower(double x, int n) {
        if (n == 0) return 1.0;
        double half = fastPower(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    /**
     * 循环实现
     */
    public static double myPow2(double x, int n) {
        long N = n;
        if (N < 0) {
            if (N == 0) {
                throw new RuntimeException("分母不能为0");
            }
            x = 1 / x;
            N = -N;
        }
        double result = 1;
        double current_product = x;
        for (long i = N; i > 0; i = i / 2) {
            if ((i % 2) == 1) { //偶数
                result = result * current_product;
            }
            current_product = current_product * current_product;
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(myPow2(2.00000, -2147483648));
    }
}
