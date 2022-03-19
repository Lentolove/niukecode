package zuoshen.topinterview;

import java.util.Arrays;

/**
 * author : tsp
 * Date : 2022/3/17
 * DESC:给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库的sort函数的情况下解决这个问题。
 * <p>
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * <p>
 * 进阶：
 * <p>
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * Related Topics 数组 双指针 排序 👍 1192 👎 0
 */
public class Problem_0075_SortColors {


    /**
     * 荷兰国旗问题：双指针思想，一趟走完
     */
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int l = -1, r = nums.length;
        int index = 0;
        while (index < r) {
            if (nums[index] < 1) {//当前元素放在左边
                swap(nums,++l,index++);
            } else if (nums[index] == 1) {
                index++;
            } else {
                //当前元素在右边,把右边元素交换过来，交换过来的元素的继续判断
                swap(nums, index, --r);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] num = {2,0,2,1,1,0};
        int[] num1 = {2,0,1};
        sortColors(num);
        System.out.println(Arrays.toString(num));
    }

}
