package tsp.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author : tsp
 * Time: 2022/2/15 11:14
 * Desc :给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q15_三数之和 {

    /**
     * 思路：从左往右遍历：固定一个数，求剩下的两个数的和问题
     * 1.找到的三元组不能重复，首先对数组进行一个排序，first 如果相同就跳过
     * 2.在[i + 1,n - 1] 的排序数组中查找两个元素的 和为 0 - nums[i]
     * 3.对 [i + 1,n - 1]  采用双指针找答案
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) return ans;
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[0] > 0 || nums[n - 1] < 0) return ans;
        for (int i = 0; i < n - 2; i++) {
            //1.对固定头一个元素的情况去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[l] + nums[r] + nums[i];
                //2.剪枝操作
                if (sum == 0) {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[l]);
                    item.add(nums[r]);
                    ans.add(item);
                    //TODO ☆☆ 去重
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};


    }

}
