package zuoshen.topinterview;

import java.util.Stack;

/**
 * author : tsp
 * Date : 2022/3/18
 * DESC:给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 示例 1:
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * <p>
 * 示例 2：
 * 输入： heights = [2,4]
 * 输出： 4
 * <p>
 * 提示：
 * 1 <= heights.length <=10⁵
 * 0 <= heights[i] <= 10⁴
 * <p>
 * Related Topics 栈 数组 单调栈 👍 1817 👎 0
 */
public class Problem_0084_LargestRectangleInHistogram {

    /**
     * 单调栈解决问题：栈中存放下标，保持栈内下标对应的元素递增
     * 题目数据规模为 10^5，直接不考虑 O(N^2) 解法
     */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 1) return heights[0];
        //stack存的是下标，单调栈
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            //1.如果当前栈不为null，并且当前 i 元素比栈顶元素对应的值小于等于，则弹出栈顶元素，结算当前栈顶元素 j
            //因为 j 的下方都是比它小，i 也是不大于它，那以 j 为高的最大面积就可以结算了。
            //相等时也结算的原因：i 位置的元素计算的结果一定会覆盖j位置计算的结果，虽然j位置计算的不是最优解
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                //结算当前栈顶元素
                int curIndex = stack.pop();
                int width = i - (stack.isEmpty() ? -1 : stack.peek()) - 1;
                maxArea = Math.max(maxArea, width * heights[curIndex]);
            }
            stack.add(i);
        }
        //最后单独结算栈内元素,栈内元素是递增的，所以它的右边界是可以来到长度n的，因为之前右边有比它晓得元素，则已经将它弹出了
        //而它的左边界就是栈顶元素，因为在之前的判断中，小于等于我们也做出栈操作了
        while (!stack.isEmpty()) {
            int index = stack.pop();
            int preIndex = stack.isEmpty() ? -1 : stack.peek();
            //TODO 特别注意，右边能到的宽度，左边能到index,
            int width = n - preIndex - 1;
            maxArea = Math.max(width * heights[index], maxArea);
        }
        return maxArea;
    }


    public static void main(String[] args) {
//        int[] heights = {2,1,5,6,2,3};
        int[] heights = {2,3};
        System.out.println(largestRectangleArea(heights));
    }

}
