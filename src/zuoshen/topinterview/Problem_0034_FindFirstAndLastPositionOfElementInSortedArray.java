package zuoshen.topinterview;

import java.util.Arrays;

/**
 * Author : tsp
 * Time: 2022/3/11 15:02
 * Desc : 给定一个有序数组，包涵重复的值，请找到目标值 target 的左边界和右边界返回
 * eg: [1,1,2,3,3,3,4,4,4,5,5,6,7,7,7,7,8,9] target = 3 返回[3,5],没有则返回-1
 */
public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {


    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int leftBound = findLeftBound(nums, target);
        int rightBound = findRightBound(nums, target);
        return new int[]{leftBound, rightBound};
    }

    public static int findLeftBound(int[] nums, int target) {
        int index = -1;
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (target <= nums[m]) {
                index = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return (index == -1 || nums[index] != target) ? -1 : index;
    }

    public static int findRightBound(int[] nums, int target) {
        int index = -1;
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (target >= nums[m]) {
                index = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return (index == -1 || nums[index] != target) ? -1 : index;
    }


    public static void main(String[] args) {
//        int[] a = {1,1,2,3,3,3,5,5,6,7,7,7,7,8,9};
//        System.out.println(findLeftBound(a,3));
//        System.out.println(findRightBound(a,3));
        int[] b = {1};
        System.out.println(Arrays.toString(searchRange(b, 1)));
    }


}
