package zuoshen.topinterview;

import java.util.Stack;

/**
 * Author : tsp
 * Time: 2022/3/10 18:57
 * Desc :
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * <p>
 * 示例 5：
 * <p>
 * 输入：s = "{[]}"
 * 输出：true
 * <p>
 * 提示：
 * 1 <= s.length <= 10⁴
 * s 仅由括号 '()[]{}' 组成
 * <p>
 * Related Topics 栈 字符串 👍 3058 👎 0
 */
public class Problem_0020_ValidParentheses {

    /**
     * 思路：借助栈来实现括号匹配问题,遇到左括号压栈，遇到右括号弹出匹配
     * s = "{[]}"
     */
    public boolean isValid(String s) {
        //1.过滤大条件
        if (s == null || s.length() % 2 != 0) return false;
        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : str) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char left = stack.pop();
                if (!((left == '(' && c == ')') || (left == '[' && c == ']' || (left == '{' && c == '}')))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 拓展问题：判断单一括号的有效性问题
     * 比如：()()()((())) 是否是有效的
     * 用一个变量count记录，遇到左括号++,遇到右括号--，当count<0是否直接返回false
     */
    public static boolean isValid2(String s) {
        if (s == null || s.length() % 2 != 0) return false;
        char[] str = s.toCharArray();
        int count = 0;
        for (char c : str) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) return false;
        }
        return count == 0;
    }

}
