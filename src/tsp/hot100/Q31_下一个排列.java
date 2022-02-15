package tsp.hot100;

import java.util.Arrays;

/**
 * Author : tsp TODO
 * Time: 2022/2/15 19:00
 * Desc :给你一个整数数组 nums ，找出 nums 的下一个排列。必须 原地 修改，只允许使用额外常数空间。
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 *
 */
public class Q31_下一个排列 {

    /**
     * 必须 原地 修改，只允许使用额外常数空间。
     * 分两种情况看待：
     * 1.末尾一段是升序
     * eg:xxxx1235那下一个排列一定是 xxxx1253,直接交换即可
     * 2.末尾一段是降序
     * 2.1 从右往左找到第一个转折点 i , arr[i - 1] < arr[i] , arr[i] > arr[i+1,.....]
     * 2.2 然后从[i+1,n -1] 中找到第一个大于 arr[ i - 1] 的点 j，交换 (i - 1, j)
     * 2.3 然后将[i,n - 1] 进行逆序，即可得到下一个排列
     */
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        //1.从倒是第二个元素开始看最后一段是否是降序
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            //如果是降序，找到第一个转折点
            i--;
        }
        //2.存在：4321，此时 i = -1,只需要翻转整个数组就行
        //eg: xxxx1235,此时 i = n - 2,直接翻转 [n-2,n-1]数组即可
        if (i >= 0) {
            //3.从[i+1,n - 1] 找到第一个比 arr[ i - 1] 大的位置j,交换 i，j 位置，然后翻转最后的
            int j = n - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                //[i+1,n - 1] 为单调递增区间，可以用二分查找
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public static void reverse(int[] arr, int start) {
        int end = arr.length - 1;
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int a = nums[i];
        nums[i] = nums[j];
        nums[j] = a;
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 2};
        nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
    }


}
