package tsp.sort;

/**
 * 希尔排序算法
 * 平均时间复杂度： O(n(logn)^2)
 * 最好情况： O(n(logn)^2)
 * 最坏情况： O(n(logn)^2)
 * 空间复杂度：O(1)
 * 非稳定
 */
public class Q4_希尔排序 {


    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 6, 7, 2, 4, 5, 9, 2, 3, 1, 5};
        shell_sort(nums);
        Utils.printNum(nums);
    }

    /**
     * 希尔排序，也叫增量排序
     */
    public static void shell_sort(int[] nums) {
        int inCre = nums.length;
        while (true) {
            inCre = inCre / 2;
            for (int i = 0; i < inCre; i++) {
                for (int j = i + inCre; j < nums.length; j += inCre) {
                    for (int k = j; k > i; k -= inCre) {
                        if (nums[k] < nums[k - inCre]) {
                            Utils.swap(nums, k, k - inCre);
                        } else {
                            break;
                        }
                    }
                }
            }
            if (inCre == 1) break;
        }
    }

}
