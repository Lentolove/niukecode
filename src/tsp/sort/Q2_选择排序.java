package tsp.sort;


/**
 * 选择排序算法
 * 平均时间复杂度： O(n^2)
 * 最好情况： O(n^2)
 * 最坏情况： O(n^2)
 * 空间复杂度：O(1)
 * 不稳定
 * 说明：选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。
 * 所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。
 * <p>
 * 确实如你所说，用数组实现的选择排序是不稳定的，用链表实现的选择排序是稳定的。
 * <p>
 * 不过，一般提到排序算法时，大家往往会默认是数组实现，所以选择排序是不稳定的。
 */
public class Q2_选择排序 {


    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 6, 7, 2, 4, 5, 9, 2, 3, 1, 5};
        select_sort(nums);
        Utils.printNum(nums);
    }


    public static void select_sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;//最小值索引
            for (int j = i; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) Utils.swap(nums,minIndex,i);
        }
    }

}
