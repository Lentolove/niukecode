package zuoshen.topinterview;

import java.util.Stack;

/**
 * Author : tsp
 * Time: 2022/3/29 18:27
 * Desc :设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。实现 MinStack 类:
 * <p>
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * <p>
 * 示例 1:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * <p>
 * 提示：
 * -2³¹ <= val <= 2³¹ - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 10⁴ 次
 * <p>
 * Related Topics 栈 设计 👍 1244 👎 0
 */
public class Problem_0155_MinStack {

    private Stack<Integer> min;
    private Stack<Integer> data;

    /**
     * 最小栈，用两个栈实现
     */
    public Problem_0155_MinStack() {
        min = new Stack<>();
        data = new Stack<>();
    }

    public void push(int val) {
        data.add(val);
        if (!min.isEmpty()) {
            val = Math.min(val, min.peek());
        }
        min.add(val);
    }

    public void pop() {
        data.pop();
        min.pop();
    }

    /**
     * 获取堆栈顶部的元素。
     */
    public int top() {
        return data.isEmpty() ? -1 : data.peek();
    }

    public int getMin() {
        return min.isEmpty() ? -1 : min.peek();
    }
}
