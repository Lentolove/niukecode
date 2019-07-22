package tsp.leetcode.nums;

/**
 * 4:寻找两个有序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 */
public class Solution147_4_寻找两个有序数组的中位数 {

    /**
     * num1[a1,a2,a3....,an]  num2[b1,b2,b3,...bm];
     * [[a1],[b1,b2,b3] | [a2,....][b4....]
     * 只需要比较b3和a2的关系大小，就可以知道改分法的正确性
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2) {
            return findMedianSortedArrays(nums2, nums1); //确保在较短的数组里去二分查找
        }
        int k = (n1 + n2 + 1) / 2; // 3 ，4 的中位数在第四位 3+4+1/2 = 8，这里值的是位置，索引不是从0开始的
        int left = 0;
        int right = n1;
        while (left < right) {
            int m1 = left + (right - left) / 2;
            int m2 = k - m1;
            if (nums1[m1] < nums2[m2 - 1]) left = m1 + 1;
            else right = m1;
        }
        int m1 = left;
        int m2 = k - left;
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1], m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);
        if ((n1 + n2) % 2 == 1) return c1;
        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1], m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
        return (c1 + c2) * 0.5;
    }

}