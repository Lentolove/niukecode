package tsp.leetcode.nums;

/**
 * 29:两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 示例 1:
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 */
public class Solution121_29_两数相除 {

    public int divide(int dividend, int divisor) {
        int flag = 1;//确定结果是否为负数
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) flag = -1;
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        if (ldividend == 0) return 0;//被除数为0  返回0
        if (ldivisor == 0) return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        long res = solve(ldividend, ldivisor);
        int ans;
        if (res > Integer.MAX_VALUE) {
            ans = flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            ans = (int) (res * flag);
        }
        return ans;
    }

    //递归求除法的和
    private long solve(long a, long b) {
        if (a < b) return 0;
        long res = 1;
        long sum = b;
        while ((sum + sum) <= a) {
            res += res;
            sum += sum;
        }
        return res + solve(a - sum, b);
    }
}
