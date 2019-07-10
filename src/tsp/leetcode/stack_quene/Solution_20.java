package tsp.leetcode.stack_quene;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.Stack;

/**
 * leetcode: 20 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 */

class Solution{
    // Hash table that takes care of the mappings.
    /**
     * 用hashmap来匹配括号 是真的秒啊
     */
    private HashMap<Character, Character> mappings;
    public Solution(){
        mappings = new HashMap<>();
        mappings.put(')','(');
        mappings.put(']','[');
        mappings.put('}','{');
    }

    public boolean isValid(String s){
        //为空或者为奇数都不可能匹配
        if (s==null||(s.length()&1)==1) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mappings.containsKey(c)) {

                char topElement = stack.isEmpty()?'#': stack.pop();
                if (topElement!=mappings.get(c)){
                    return false;
                }
            }else {
                stack.push(c);
            }

        }

        return stack.isEmpty();
    }
}


public class Solution_20 {


    /**
     * '[' 对应 91 ']' 93
     * '{' 对应 123 '}' 125
     * '(' 对应 40 )' 41
     */
    public static boolean isValid(String s) {
        if (s == null || (s.length() & 1) == 1) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.add(s.charAt(i));
            } else {
                if (stack.isEmpty() || (Math.abs(stack.pop() - s.charAt(i))) > 2) {
                    return false;
                }
            }

        }
        return stack.isEmpty();
    }



    public static void main(String[] args) {
        String s = "((";
        System.out.println(isValid(s));
    }
}
