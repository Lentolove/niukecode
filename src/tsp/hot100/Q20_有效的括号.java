package tsp.hot100;

import java.util.Stack;

/**
 * Author : tsp  √√√√√√√√√√√√√√√√√√√√√√√√√√√√√√
 * Time: 2022/2/15 14:43
 * Desc : 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q20_有效的括号 {

    /**
     * 思路：借助栈来完成匹配过程，遇到左括号入栈，遇到右括号出栈
     */
    public static boolean isValid1(String s) {
        if (s.length() % 2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char a : chars) {
            if (a == '(' || a == '[' || a == '{') {
                stack.push(a);
            } else {
                if (!stack.isEmpty()) {
                    char pre = stack.pop();
                    if (Math.abs(pre - a) > 2) return false;
                }else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }




    public static void main(String[] args) {
        String s = "()[]{}";
        String s2 = "({{{{}}}))";
        String s3 = "()";
        System.out.println(isValid1(s3));
    }
}
