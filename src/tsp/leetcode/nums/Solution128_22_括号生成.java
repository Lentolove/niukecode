package tsp.leetcode.nums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 22:括号生成
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 */
public class Solution128_22_括号生成 {

    /**
     * 标准的回溯 求搜索组合的问题时候
     */
    public static List<String> generateParenthesis(int n) {
        List<String> out = new ArrayList<>();
        backtrack(out, "", 0, 0, n);
        return out;
    }

    private static void backtrack(List<String> output, String s, int open, int close, int n) {
        if (s.length() == n * 2) {
            output.add(s);
            return;
        }
        //一定要先添加左括号，右括号的个数必须小于左括号
        //如果我们还剩一个位置，我们可以开始放一个左括号。 如果它不超过左括号的数量，我们可以放一个右括号。
        if (open < n)
            backtrack(output, s + "(", open + 1, close, n);
        if (close < open) //右括号的个数必须要小于左括号的个数
            backtrack(output, s + ")", open, close + 1, n);
    }

    /**
     * 方法二：闭合数  leetcode 官方题解
     * 两个对应的括号 0~index之间的有效括号数字必须是偶数
     */
    public static List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList<>();
        if ( n == 0 ) {
            ans.add("");
        }else {
            for (int i = 0; i < n; i++) {
                for (String left : generateParenthesis(i)) {
                    for (String right : generateParenthesis(n-1-i) ) {
                        //两个对应的括号
                        ans.add("("+left+")"+right);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(generateParenthesis(3)));
    }
}
