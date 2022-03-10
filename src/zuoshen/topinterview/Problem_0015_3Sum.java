package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author : tsp
 * Time: 2022/3/10 16:58
 * Desc :
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
 * 复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[]
 * <p>
 * 提示：
 * 0 <= nums.length <= 3000
 * -10⁵ <= nums[i] <= 10⁵
 * <p>
 * Related Topics 数组 双指针 排序 👍 4447 👎 0
 */
public class Problem_0015_3Sum {

    /**
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 三数之和问题，先对数组进行排序,
     * 1.先解决两数之和问题
     * 2.然后固定第一个数，从后续数组中查找两个元素的和为 0 - nums[i]
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[0] > 0 || nums[n - 1] < 0) return ans;
        for (int i = 0; i < n - 2; i++) {//对多移动到倒数第三位
            //去重
            if (i == 0 || nums[i] != nums[i - 1]) {
                List<List<Integer>> lists = twoSum(nums, i + 1, -nums[i]);
                for (List<Integer> item : lists) {
                    //这里涉及到一点优化，每次将第一个元素添加到0位置，涉及到list的拷贝，不过题目没有要求顺序
                    item.add(nums[i]);
                    ans.add(item);
                }
            }
        }
        return ans;
    }

    //默认数组是已经排序了，在有序数组中查找两个元素的和为 target
    public static List<List<Integer>> twoSum(int[] nums, int begin, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        int l = begin, r = n - 1;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum > target) {
                r--;
            } else if (sum < target) {
                l++;
            } else {
                //去重，如果是第一个元素，或者跟前一个元素不相同，才添加
                if (l == begin || nums[l] != nums[l - 1]) {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[l]);
                    item.add(nums[r]);
                    ans.add(item);
                }
                l++;
            }
        }
        return ans;
    }

}
