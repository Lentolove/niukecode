package tsp.leetcode.nums;

/**
 * 5:最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 */
public class Solution144_5_最长回文子串 {

    /**
     * 方法一：中心扩展法
     * 1.既然是回文字符串，本来联想的是滑动窗口，双指针这样的思想，发现并不行，
     * 无法确保 abcd...这一段是否是回文串，万一继续后面是dcba，总不能全部遍历吧，那就等于暴力算法了。
     * 2.回文串的特点，由中间到两边扩散，aba 或者 bb这样的形式，这就需要我们分情况讨论了。
     * for循环以每一个点为中心，求最长文回串，从中心点向两边延伸，记录最长子串
     */

    private int start, maxLen;

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        for (int i = 0; i < s.length(); i++) {
            //考虑两种情况1:aba  和 2: bb
            findMaxPalindrome(s, i, i);
            findMaxPalindrome(s, i, i + 1);
        }
        return s.substring(start, start + maxLen);
    }

    private void findMaxPalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;//向左延伸
            j++;//向右延伸
        }
        //记录每个起始点扩展的回文串的最大长度
        if (maxLen < j - i - 1) {
            start = i + 1;
            maxLen = j - i - 1;
        }
    }

    /**
     * 方法二：动态规划
     * 求解 "最优子结构" 问题，可以考虑用 "动态规划"
     */
    public String longestPalindrome1(String s) {
        int n = s.length();
        if (n < 2) return s;
        int maxLen = 1;
        String res = s.substring(0, 1);
        boolean[][] dp = new boolean[n][n];
        //左边界一定小于右边界，因此从右边界开始
        for (int r = 1; r < n; r++) { //表示右边界
            for (int l = 0; l < r; l++) { //表示左边界
                // 区间应该慢慢放大
                // 状态转移方程：如果头尾字符相等并且中间也是回文
                // 在头尾字符相等的前提下，如果收缩以后不构成区间（最多只有 1 个元素），直接返回 True 即可
                // 否则要继续看收缩以后的区间的回文性
                if (s.charAt(l) == s.charAt(r) && ((r - l) <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        res = s.substring(l, r + 1);
                    }
                }
            }
        }
        return res;
    }
}
