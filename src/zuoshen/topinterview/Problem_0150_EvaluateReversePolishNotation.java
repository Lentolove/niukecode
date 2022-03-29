package zuoshen.topinterview;

import java.util.Stack;

/**
 * author : tsp
 * Date : 2022/3/27
 * DESC:根据 逆波兰表示法，求表达式的值。有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 注意 两个整数之间的除法只保留整数部分。可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * <p>
 * 示例 1：
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * <p>
 * 示例 2：
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * <p>
 * 提示：
 * 1 <= tokens.length <= 10⁴
 * tokens[i] 是一个算符（"+"、"-"、"*" 或 "/"），或是在范围 [-200, 200] 内的一个整数
 * <p>
 * 逆波兰表达式：逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
 * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
 * 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
 * <p>
 * 逆波兰表达式主要有以下两个优点：
 * <p>
 * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中
 * <p>
 * Related Topics 栈 数组 数学 👍 496 👎 0
 */
public class Problem_0150_EvaluateReversePolishNotation {

    /**
     * 题目：逆波兰表达式计算
     * ["2","1","+","3","*"]
     * 借助栈来实现，遇到符号弹出计算
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                int b = stack.pop();
                int a = stack.pop();
                int ans = compute(a, b, s);
                stack.add(ans);
            }else {
                stack.add(Integer.parseInt(s));
            }
        }
        return stack.peek();
    }

    /**
     * 计算
     */
    private int compute(int a, int b, String op) {
        int ans = 0;
        switch (op) {
            case "+":
                ans = a + b;
                break;
            case "-":
                ans = a - b;
                break;
            case "*":
                ans = a * b;
                break;
            case "/":
                ans = a / b;
                break;
        }
        return ans;
    }
}
