package tsp.leetcode.eyery;

/**
 * 81 搜索旋转排序数组 II
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * <p>
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 * <p>
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
 */
class Solution81 {

    public boolean search(int[] A, int target) {

        return false;
    }
}

/**
 * 33 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 */
class Solution33 {

    /**
     * 考察点： 二分查找
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;
        int rotate_index = find_rotate_index(nums, 0, n - 1); //最小数的索引
        //如果目标数是最小数
        if (nums[rotate_index] == target) return rotate_index;
        //现在开始二分查找
        if (rotate_index == 0) return search_helper(nums, 0, n - 1, target);
        if (target < nums[0]) return search_helper(nums, rotate_index, n - 1, target);
        return search_helper(nums, 0, rotate_index - 1, target);
    }

    //排序数组的二分查找
    private int search_helper(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int p = (left + right) / 2;
            if (nums[p] == target) {
                return p;
            } else {
                if (target < nums[p]) {
                    right = p - 1;
                } else {
                    left = p + 1;
                }
            }
        }
        return -1;
    }

    //找到旋转的起点的下标 4 5 6 7 8 1 2 3 4 找到 1 的下标
    public int find_rotate_index(int[] nums, int left, int right) {
        if (nums[left] < nums[right]) return 0;
        while (left <= right) {
            int p = (left + right) / 2;
            if (nums[p] > nums[p + 1]) {
                return p + 1;
            } else {
                if (nums[p] < nums[left]) { //中点比左起点还小 则左边上升的序列一定存在旋转
                    right = p - 1;
                } else {
                    left = p + 1;
                }
            }
        }
        return 0;
    }

    /**
     * 方法二：
     */
    public int search2(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            /**
             * 异或运算
             * 1.(nums[0] > target)   则 target一定在旋转数组中
             * 2.(nums[0] > nums[mid])则 0 - mid 一定是升序
             * 3.(target > nums[mid]
             * 两项为真时候为真异或结果为假
             * 一项为真时候异或结果为真
             * 三项为真时候异或结果为真
             */
            if ((nums[0] > target) ^ (nums[0] > nums[mid]) ^ (target > nums[mid])) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l == r && nums[l] == target ? l : -1;
    }
}


public class Solution68_81 {

    public static void main(String[] args) {
        Solution33 s33 = new Solution33();
        int[] a = {4, 5, 6, 7, 8, 1, 2, 3};
        System.out.println(s33.search(a, 7));
    }

}
