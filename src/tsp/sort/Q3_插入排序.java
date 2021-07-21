package tsp.sort;

/**
 * 插入排序算法
 * 平均时间复杂度： O(n^2)
 * 最好情况： O(n)
 * 最坏情况： O(n^2)
 * 空间复杂度：O(1)
 * 稳定
 * 说明：
 */
public class Q3_插入排序 {

    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 6, 7, 2, 4, 5, 9, 2, 3, 1, 5};
        insert_sort(nums);
        Utils.printNum(nums);
    }


    /**
     * 插入排序
     */
    public static void insert_sort(int[] nums) {
        //从下标为1的位置开始选择合适的位置插入，因为下标为0的第一个元素默认是有序的
        for (int i = 1; i < nums.length; i++) {
            //2.记录要插入的数据，从前面排序好的最后开始往前遍历，找到合适的位置插入
            int temp = nums[i];
            int j = i;
            while (j > 0 && temp < nums[j - 1]) {
                nums[j] = nums[j - 1];//将比 temp 大的元素往后移动
                j--;
            }
            if (j != i) nums[j] = temp;
        }
    }

}
