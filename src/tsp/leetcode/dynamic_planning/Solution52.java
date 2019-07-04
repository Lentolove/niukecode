package tsp.leetcode.dynamic_planning;

/**
 * 题目：交错字符串  分类：动态规划
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * 示例 1:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 */
public class Solution52 {

    /**
     * 方法一： 暴力法 回溯法  时间复杂度 2^(m+n) 空间复杂度 m+n
     * <p>
     * 最基本的想法就就是找到所有 s1 和 s2 能够形成的交错字符串。为了实现这样的想法，我们使用回溯。我们首先
     * 将 s1 中的第一个字符作为开始字符，然后将 s1 字符串剩余部分和 s2 字符串所有可能情况添加在这个字符后面，
     * 每次用完所有字符后检查字符串与 s3 是否一致。类似的，我们可以选择 s2 第一个字符作为开始字符，然后将 s2
     * 剩余字符串和 s1 字符串回溯地添加到该字符后面，看是否能形成 s3.
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        return is_Interleaver(s1, 0, s2, 0, "", s3);
    }

    public boolean is_Interleaver(String s1, int i, String s2, int j, String res, String s3) {
        if (res.equals(s3) && i == s1.length() && j == s2.length()) return true;
        boolean ans = false;
        if (i < s1.length()) {
            ans |= is_Interleaver(s1, i + 1, s2, j, res + s1.charAt(i), s3);
        }
        if (j < s2.length()) {
            ans |= is_Interleaver(s1, i, s2, j + 1, res + s2.charAt(j), s3);
        }
        return ans;
    }

    /**
     * 方法二： 记忆回溯法 减少重复计算
     */
    public boolean isInterleave1(String s1, String s2, String s3) {
        int[][] mark = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                mark[i][j] = -1;
            }
        }
        return is_Interleaver1(s1, 0, s2, 0, s3, 0, mark);
    }

    public boolean is_Interleaver1(String s1, int i, String s2, int j, String s3, int k, int[][] mark) {
        if (i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        }
        if (j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        }
        if (mark[i][j] >= 0) {
            return mark[i][j] == 1 ? true : false;
        }
        boolean ans = false;
        if (s3.charAt(k) == s1.charAt(i) && is_Interleaver1(s1, i + 1, s2, j, s3, k + 1, mark)
                || s3.charAt(k) == s2.charAt(j) && is_Interleaver1(s1, i, s2, j + 1, s3, k + 1, mark)) {
            ans = true;
        }
        mark[i][j] = ans ? 1 : 0;
        return ans;
    }

    /**
     * 方法三 使用二维动态规划
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length() + 1; i++) {
            for (int j = 0; j < s2.length() + 1; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || dp[i][j-1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /**
     *  方法 4：使用一维动态规划
     *
     */
    public boolean isInterleave3(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[] = new boolean[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s2.length()];
    }


    public static void main(String[] args) {
        Solution52 s = new Solution52();
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        System.out.println(s.isInterleave(s1, s2, s3));
    }
}
