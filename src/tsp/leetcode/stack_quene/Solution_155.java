package tsp.leetcode.stack_quene;

import java.util.Stack;

/**
 * 最小栈
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 */

class MinStack {


    private Stack<Integer> dataStack,minStack;

    /** initialize your data structure here. */
    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        dataStack.add(x);
        if (minStack.isEmpty()||x<minStack.peek()){
            minStack.add(x);
        }else {
            minStack.add(minStack.peek());
        }

    }

    public void pop() {
        if (!dataStack.isEmpty()&&!minStack.isEmpty()){
            dataStack.pop();
            minStack.pop();
        }
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
public class Solution_155 {


}
