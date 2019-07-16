package tsp.leetcode.eyery;

/**
 * 53:最大子序和 动态规划
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 */
public class Solution97_63_最大子序和 {

    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int cur_sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (cur_sum < 0) {
                cur_sum = nums[i];
            } else {
                cur_sum += nums[i];
            }
            max = Math.max(cur_sum, max);
        }
        return max;
    }

    /**
     * 动态规划
     */
    public static int maxSubArray1(int[] nums) {
        if (nums.length < 1) return 0;
        int curmax = nums[0], ret = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curmax = Math.max(curmax + nums[i], nums[i]);
            ret = Math.max(curmax, ret);
        }
        return ret;
    }

    /**
     * 分治法
     */
    public int maxSubArray2(int[] nums) {
        return find(nums, 0, nums.length - 1);
    }

    private int find(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        if (start > end) {
            return Integer.MIN_VALUE;
        }
        int mid = (start + end) / 2;
        int left = find(nums, start, mid - 1);
        int right = find(nums, mid + 1, end);
        int ml = 0;
        for (int i = mid - 1, sum = 0; i >= start; i--) {
            sum += nums[i];
            ml = Math.max(ml, sum);
        }
        int mr = 0;
        for (int i = mid + 1, sum = 0; i <= end; i++) {
            sum += nums[i];
            mr = Math.max(mr, sum);
        }
        return Math.max(Math.max(left, right), ml + mr + nums[mid]);
    }
}
