package tsp.leetcode.nums;

/**
 * 给定一个排序整数数组，找出给定目标值的起始和结束位置。
 * 算法的运行时复杂度必须是O(log n)的顺序。
 * 如果数组中没有找到目标，返回[-1,-1]。
 * 例如,
 * 给定[5,7,7,8,8,10]和目标值8，
 * 返回(3、4)。
 * 给定5 返回[0,0]
 */
public class Solution116_搜索目标值在数组中的范围 {

    public int[] searchRange(int[] nums, int target) {
        int start = -1, end = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid]) {
                start = end = mid;
                while (start > 0 && nums[start - 1] == target) start--;
                while (end < nums.length - 1 && nums[end + 1] == target) end++;
                return new int[]{start, end};
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[]{start, end};
    }
}
