package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/17
 * DESC:给你一个非负整数 x ，计算并返回 x 的 算术平方根 。由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * <p>
 * 示例 1：
 * 输入：x = 4
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 提示：
 * 0 <= x <= 2³¹ - 1
 * Related Topics 数学 二分查找 👍 930 👎 0
 */
public class Problem_0069_SqrtX {

    /**
     * 思路：二分法，找到一个元素 m^2 <= x 即可
     * 为了防止 m^2溢出，用 long 来接收
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x < 3) return 1;
        long l = 1, r = x, m, ans = 0;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (m * m <= x) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return (int) ans;
    }

}
