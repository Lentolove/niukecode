package tsp.leetcode.nums;

import java.util.Arrays;

/**
 * 16:最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 */
public class Solution133_16_最接近的三数之和 {

    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) return -1;
        int close = nums[0] + nums[1] + nums[2];
        int diff = Math.abs(close - target);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int new_diff = Math.abs(sum - target);
                if (diff > new_diff) {
                    diff = new_diff;
                    close = sum;
                }
                if (sum == target) return target;
                else if (sum > target) right--;
                else left++;
            }
        }
        return close;
    }

}
