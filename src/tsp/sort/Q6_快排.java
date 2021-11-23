package tsp.sort;

/**
 * 快速排序算法
 * 平均时间复杂度： O(nlogn)
 * 最好情况： O(nlogn)
 * 最坏情况： O(nlogn)
 * 空间复杂度：O(n)
 * 不稳定 不稳定 不稳定 不稳定 不稳定 不稳定 不稳定 不稳定 不稳定 不稳定
 */
public class Q6_快排 {

    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 6, 7, 2, 4, 5, 9, 2, 3, 1, 5};
        quick_sort(nums);
        Utils.printNum(nums);
    }

    public static void quick_sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int i = l, j = r, key = nums[l];//基准为，每次循环将保证准基的左边的数比它都小，右边的都比它大
        while (i < j) {
            //j先从右边开始走,找到第一个比 key 小的数字
            while (i < j && nums[j] >= key) j--;
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }
            //现在 i 开始走，找到一个比 key 大的数字
            while (i < j && nums[i] < key) i++;
            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
            nums[i] = key;
            quickSort(nums, l, i - 1);
            quickSort(nums, i + 1, r);
        }
    }

}
