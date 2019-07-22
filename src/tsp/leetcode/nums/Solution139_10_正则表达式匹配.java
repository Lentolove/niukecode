package tsp.leetcode.nums;

/**
 * 10:正则表达式的匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 */
public class Solution139_10_正则表达式匹配 {

    /**
     * 方法一：回溯
     */
    public static boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean first_match = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));
        if (p.length() >= 2 && p.charAt(1) == '*') {
            // isMatch(s,p.substring(2) 表示第一个不匹配 x*匹配s的零个字符
            //(first_match && isMatch(s.substring(1),p)) 表示第一个匹配
            return (isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p)));
        } else {
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }

    /**
     * 方法三：动态规划
     * 自低向上法 从最后向最前进行匹配
     */
    public static boolean isMatch1(String s, String p) {
        int n1 = s.length();
        int n2 = p.length();
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        dp[n1][n2] = true;
        for (int i = n1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                boolean first_match = (i < n1 && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
                if (j + 1 < n2 && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }

            }
        }
        return dp[0][0];
    }

}

/**
 * 方法三：
 * 动态规划 自顶向下
 * 还没理解.....
 */
class Method3 {
    enum Result {
        TRUE, FALSE
    }

    class Solution {
        Result[][] memo;

        public boolean isMatch(String text, String pattern) {
            memo = new Result[text.length() + 1][pattern.length() + 1];
            return dp(0, 0, text, pattern);
        }

        public boolean dp(int i, int j, String text, String pattern) {
            if (memo[i][j] != null) {
                return memo[i][j] == Result.TRUE;
            }
            boolean ans;
            if (j == pattern.length()) {
                ans = i == text.length();
            } else {
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));

                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    ans = (dp(i, j + 2, text, pattern) ||
                            first_match && dp(i + 1, j, text, pattern));
                } else {
                    ans = first_match && dp(i + 1, j + 1, text, pattern);
                }
            }
            memo[i][j] = ans ? Result.TRUE : Result.FALSE;
            return ans;
        }
    }
}
