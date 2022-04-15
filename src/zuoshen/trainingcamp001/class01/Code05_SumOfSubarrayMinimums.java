package zuoshen.trainingcamp001.class01;

import java.util.Stack;

/**
 * Author : tsp
 * Time: 2022/4/15 9:25
 * TODO  LeetCode 84 题:https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * Desc : 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 ，
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 提示：
 * 1 <= heights.length <=10^5
 * 0 <= heights[i] <= 10^4
 */
public class Code05_SumOfSubarrayMinimums {


    /**
     * 题目：求柱状图中，能勾勒出的最大矩形面积。
     * 思路：以每个位置为矩形的高度，找到左边比自己小的位置，右边比自己小的位置，中间的区间就是矩形面积
     * 这不就是典型的单调栈思路么
     */
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int n = heights.length;
        int maxArea = 0;
        //单调栈，找左边最近最小值，右边最近最小值，递增栈
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                //当前要添加的元素比栈顶对应的值小了，结算
                int index = stack.pop();
                int preIndex = stack.isEmpty() ? 0 : stack.peek();
                int width = i - preIndex - 1;
                maxArea = Math.max(maxArea,width * heights[index]);
            }
            stack.add(i);
        }
        //最后结算栈内元素
        while (!stack.isEmpty()){
            int index = stack.pop();
            int preIndex = stack.isEmpty() ? 0 : stack.peek();
            int width = n - preIndex - 1;
            maxArea = Math.max(maxArea,width * heights[index]);
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int[] arr = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(arr));
    }
}
