package tsp.leetcode.nums;

import java.util.Stack;

/**
 * 32:最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 */
public class Solution118_32_最长有效括号 {


    /**
     * 方法一：暴力法
     * 超时  不通过
     */
    public static int longestValidParentheses(String s) {
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j += 2) { //这里去等于是因为字符串的切割包头不包尾
                if (isValid(s.substring(i, j))) {
                    maxLen = Math.max(maxLen, j - i);
                }
            }
        }
        return maxLen;
    }

    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }


    /**
     * 方法二：栈 栈中存储 '(' 的下标索引 当 c 为 ')'时候，弹出索引 inxex_c - index_top 为当前子串的最大长度
     * 这个题的做法思路跟解雨水差不对，接雨水那道题也是存储索引，然后索引对应的值来判断存水量，感觉如出一辙
     * <p>
     * 对于遇到的每个‘(’，我们将它的下标放入栈中。 对于遇到的每个‘)’ ，我们弹出栈顶的元素并将当前元素的
     * 下标与弹出元素下标作差，得出当前有效括号字符串的长度。通过这种方法，我们继续计算有效子字符串的长度，
     * 并最终返回最长有效子字符串的长度。
     */
    public int longestValidParentheses2(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    /**
     * 方法三：动态规划
     * 定义一个dp数组，其中dp[i]表示已下标为i的字符结尾的最长有效子字符串的长度。子字符串一定是
     * 以')'结尾的。所以以'('结尾的子字符串对应dp数组位置上的值必定为0.我们只需要更行')'在dp数组中对应位置的值即可
     * 分情况：
     * 1.如果s[i] =')'且s[i-1] = '(',也就是形成了".....()"，我们可以得到状态方程
     * dp[i] = dp[i-2] + 2;
     * 2.如果s[i] =')'且s[i-1] = ')',也就是形成了".....))"，
     * <p>
     * <p>
     * 这dp过程是在真的难
     */
    public int longestValidParentheses3(String s) {
        int max = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1]) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    /**
     * 方法四：计数器 两个计数器
     */
    public int longestValidParentheses4(String s) {
        int left = 0, right = 0, maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlen = Math.max(maxlen, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlen = Math.max(maxlen, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlen;
    }
}
