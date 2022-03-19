package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/15
 * DESC:实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xⁿ ）。
 * <p>
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * <p>
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * <p>
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * 提示：
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= xⁿ <= 10^4
 * <p>
 * Related Topics 递归 数学 👍 892 👎 0
 */
public class Problem_0050_PowXN {

    /**
     * 思路：快速幂,注意问题：n 为负数的情况，就变成正数去求解，注意最小负数溢出问题
     */
    public static double myPow(double x, int n) {
        //1.0的n次幂为0
        if (x == 0) return 0d;
        if (n == 0) return 1d;
        //2.将次方转成正数去计算，如果是负数，结果为 1.0d/ans
        //因为Integer.MIN_VALUE无法转成正数，-Integer.MIN_VALUE = Integer.MIN_VALUE
        int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        //快速幂的过程
        double t = x;
        double ans = 1.0d;
        while (pow != 0){
            //如果当前pow的二进制位为1，就是有效值
            if ((pow & 1) != 0){
                ans *= t;
            }
            //pow右移一位，计算所有的二进制中1
            pow >>= 1;
            t *= t;
        }
        //如果n是小负数，我们在之前加了一个1，相当于少乘了一个x
        if (n == Integer.MIN_VALUE){
            ans *= x;
        }
        return n < 0 ? (1.0d / ans) : ans;
    }


    public static void main(String[] args) {
        System.out.println(myPow(1.0,Integer.MIN_VALUE));
    }


}
