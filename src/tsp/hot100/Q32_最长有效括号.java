package tsp.hot100;

/**
 * Author : tsp TODO 注意 dp[pre - 1] 只有在前面成立的情况下才加上
 * Time: 2022/2/15 20:00
 * Desc :给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q32_最长有效括号 {

    /**
     * 思路：一旦涉及到字符串的问题，子串问题，首先想到：
     * 1.以当前字符开始如果处理，以当前字符结尾如何处理
     * 2.枚举每个位置作为结尾，找到整个过程中的最大值，本质上就是动态规划来实现。
     */
    public static int longestValidParentheses(String s) {
        if (s == null) return 0;
        int n = s.length();
        char[] str = s.toCharArray();
        //1.定义一维dp数组表示[0,i]范围内有效括号的长度,严格以 str[i]结尾
        int[] dp = new int[n];
        //2.当 str[i] == '(',那不可能匹配，直接为0,
        //否则，查找 pre = i - dp[i - 1] - 1 处是否是 '('，是则 dp[i][j] = dp[i - 1] + 2
        //并进一步判断 dp[pre - 1] 是否能组成有效括号：eg: ()((()))
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (str[i] == ')') {
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    //只有在满足的前提下才去考虑pre前有效的括号
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        String s = "()()))))()()(";
        System.out.println(longestValidParentheses(s));
    }
}
