package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/30 19:59
 * Desc :给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 * <p>
 * 示例 2：
 * 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 * <p>
 * 示例 3：
 * 输入：n = 0
 * 输出：0
 * <p>
 * 提示：
 * 0 <= n <= 10⁴
 * <p>
 * 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
 * Related Topics 数学 👍 662 👎 0
 */
public class Problem_0172_FactorialTrailingZeroes {

    /**
     * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
     * 思路：结果能得到0，结果变成阶乘中能配对多少对 2 和 5
     * eg: 10！ = [2 *( 2 * 2 )* 5 *( 2 * 3 )*( 2 * 2 * 2 )*( 2 * 5)] 末尾两个 0
     * 一个数字进行拆分后 2 的个数肯定是大于 5 的个数的，所以能匹配多少对取决于 5 的个数
     */
    public int trailingZeroes(int n) {
        int count = 0;
        while (n != 0){
            n /= 5;
            count += n;
        }
        return count;
    }
}
