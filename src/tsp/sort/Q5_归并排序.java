package tsp.sort;

/**
 * 归并排序算法
 * 平均时间复杂度： O(nlogn)
 * 最好情况： O(nlogn)
 * 最坏情况： O(nlogn)
 * 空间复杂度：O(n)
 * 稳定 稳定 稳定 稳定 稳定 稳定 稳定 稳定 稳定 稳定
 */
public class Q5_归并排序 {

    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 6, 7, 2, 4, 5, 9, 2, 3, 1, 5};
        merge_sort(nums);
        Utils.printNum(nums);
    }

    /**
     * 归并排序
     */
    public static void merge_sort(int[] nums) {
        int[] temp = new int[nums.length];
        divide_sort(nums, 0, nums.length - 1, temp);
    }

    public static void divide_sort(int[] nums, int first, int last, int[] temp) {
        if (first < last) {
            int mid = (first + last) / 2;
            divide_sort(nums, first, mid, temp);
            divide_sort(nums, mid + 1, last, temp);
            mergeToNum(nums, first, mid, last, temp);
        }
    }

    //合并两个排序数组，最终放回原始数据
    public static void mergeToNum(int[] nums, int first, int mid, int end, int[] temp) {
        int p1 = first, m = mid, p2 = mid + 1, n = end, p = 0;
        while (p1 <= m && p2 <= n) {
            temp[p++] = nums[p1] < nums[p2] ? nums[p1++] : nums[p2++];
        }
        while (p1 <= m) temp[p++] = nums[p1++];
        while (p2 <= n) temp[p++] = nums[p2++];
        //将排序好的数组放入到 nums 中
        for (int i = 0; i < p; i++) {
            nums[first + i] = temp[i];
        }
    }
}
