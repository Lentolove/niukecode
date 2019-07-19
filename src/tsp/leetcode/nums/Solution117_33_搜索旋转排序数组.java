package tsp.leetcode.nums;

/**
 * 33:搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 */
public class Solution117_33_搜索旋转排序数组 {

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;
        //找到旋转数组的起始点
        int rotate_index = find_rotate_index(nums,0,nums.length -1);
        if (rotate_index == 0) return seracH_Helper(nums,0,nums.length-1, target);
        if (target < nums[0]) return seracH_Helper(nums,rotate_index,nums.length - 1,target);
        return seracH_Helper(nums,0,rotate_index-1,target);
    }

    private int seracH_Helper(int[] nums, int left, int rigth, int target) {
        while (left <= rigth) {
            int mid = (left + rigth) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                rigth = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    //找到旋转排序数组的起点旋转点
    private int find_rotate_index(int[] nums, int left, int right) {
        if (nums[left] < nums[right]) return 0;//说明没有旋转
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) { //前面比后面还大 找到翻转点了 即下坡点
                return mid + 1;
            } else {
                if (nums[mid] < nums[left]) { //
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution117_33_搜索旋转排序数组 s = new Solution117_33_搜索旋转排序数组();
        int[] a = {8,9,2,3,4};
        System.out.println(s.search(a,9));
    }
}
