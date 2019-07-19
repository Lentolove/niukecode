package tsp.leetcode.nums;

import java.util.Arrays;

/**
 * 26:删除排序数组中的重复项
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定数组 nums = [1,1,2],
 *
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 */
public class Solution124_26_删除数组的的重复项 {

    /**
     * 只要记住修改原数组，原地写入的时候要定义一个写指针
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length < 2) return nums.length;
        int cp = 0;//可以写入的位置
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[cp] ){
                cp++;
                nums[cp] = nums[i];
            }
        }
        return cp+1;
    }

    public static void main(String[] args) {
        int[] a = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(a));
        System.out.println(Arrays.toString(a));
    }
}
