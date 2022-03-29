package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/29 18:43
 * Desc :峰值元素是指其值严格大于左右相邻值的元素。给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * <p>
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2,或者返回索引 5， 其峰值元素为 6。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * -2³¹ <= nums[i] <= 2³¹ - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * <p>
 * Related Topics 数组 二分查找 👍 765 👎 0
 */
public class Problem_0162_FindPeakElement {

    /**
     * 寻找峰值，即凸位置
     * label：二分查找，判断 m 的左边和右边
     * 1.如果 num[0] > num[1] 则直接返回，num[n-2] < num[n-1] 直接返回
     * 2.否则二分查找
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        if (n == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[n - 2] < nums[n - 1]) return n - 1;
        int l = 1, r = n - 2;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] > nums[m - 1] && nums[m] > nums[m + 1]) {
                return m;
            } else if (nums[m] < nums[m - 1]) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}
