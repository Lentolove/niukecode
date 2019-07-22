package tsp.leetcode.nums;

/**
 * 9:回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 */
public class Solution140_9_回文数 {

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        String s = String.valueOf(x);
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    /**
     * 方法二：数学解法
     * 1221
     * 1221/1000 首位:1
     * 1221%10   末位:1
     * 在进行比较
     */
    public static boolean isPalindrome1(int x) {
        if (x < 0) return false;
        if (x < 9) return true;
        //求整数的位数
        int div = 1;
        while (x / div >= 10) div *= 10;
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10; // 1991%1000 = 991/10 = 99
            div /= 100; //每次取两个数
        }
        return true;
    }

    /**
     * 方法三： 取出后半段进行翻转
     * 1.每次 %10 取末尾数
     */
    public static boolean isPalindrome2(int x) {
        //思考：这里大家可以思考一下，为什么末尾为 0 就可以直接返回 false
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        if (x < 9) return true;
        int reverse = 0;//翻转后一半的数字之后的数
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        return x == reverse || x == reverse / 10;  //奇数位数对应 x == reverse / 10
    }
}
