package tsp.leetcode.nums;

import java.math.BigInteger;

/**
 * 7:整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 * 注意:
 * <p>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 */
public class Solution142_7_整数反转 {

    public int reverse(int x) {
        int flag = 1;
        long rst = x;
        if (x < 0) {
            flag = -1;
            rst = -x;
        }
        long ans = 0;
        while (rst > 0) {
            ans = ans * 10 + rst % 10;
            rst = rst / 10;
        }
        if (ans > Integer.MAX_VALUE) return 0;
        return flag * (int) ans;
    }

    /**
     * 方法二：
     * 虽然很慢，但是也是一种思路
     */
    public int reverse1(int x) {
        int flag = 1;
        long rst = x;
        if (x < 0) {
            flag = -1;
            rst = -x;
        }
        int ans = 0;
        StringBuilder sb = new StringBuilder(String.valueOf(rst));
        try {
            return flag * (int) Integer.valueOf(sb.reverse().toString());
        } catch (Exception e) {
            return 0;
        }
    }
}