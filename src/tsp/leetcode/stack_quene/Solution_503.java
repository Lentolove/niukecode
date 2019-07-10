package tsp.leetcode.stack_quene;

import java.util.Arrays;
import java.util.Stack;

/**
 * leetcode:503
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * 示例 1:
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 */
public class Solution_503 {

    /**
     * 类似于leetcode ：739 只是现在问题变成了循环数组
     * 通过 index%n 将数组翻倍来模拟循环数组
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] next_max = new int[n];
        Arrays.fill(next_max, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                next_max[stack.pop()] = num;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return next_max;
    }

    //写法二：
    public int[] nextGreaterElements1(int[] nums) {
        int n = nums.length;
        int[] next = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            next[i % n] = stack.isEmpty() ? -1 : stack.pop();
            stack.push(nums[i % n]);
        }
        return next;
    }

}