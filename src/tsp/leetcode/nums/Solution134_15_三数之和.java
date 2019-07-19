package tsp.leetcode.nums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15:三数之和
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 */
public class Solution134_15_三数之和 {

    /**
     * 三数之和  固定一个数 移动双指针
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) return ans;
        Arrays.sort(nums);
        if (nums[0] > 0 || nums[nums.length - 1] < 0) return ans;
        //固定一个数个数
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue; //固定相同的数字就没必要了
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                //当前一个数字和后一个数字相等时候我们只需要选一个就行
                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[l]);
                    item.add(nums[r]);
                    ans.add(item);
                    while (l < r && nums[r - 1] == nums[r]) r--; //右指针去重
                    while (l < r && nums[l] == nums[l + 1]) l++; //左指针去重
                    r--;
                    l++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {1, -1, 0};
        Solution134 s = new Solution134();

        System.out.println(Arrays.asList(s.threeSum(a)));
    }
}

class Solution134 {

    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (num.length < 3) return ans;
        Arrays.sort(num);
        int n = num.length;
        if (num[0] > 0 || num[n - 1] < 0) return ans;
        //固定一个数字 求三数之和 所以要一直跑到单数第三个位置
        for (int i = 0; i < n - 2; i++) {
            if (num[i] > 0 || num[i] + num[i + 1] > 0) break;
            if (i > 0 && num[i] == num[i - 1]) continue;//去重
            int left = i + 1; //选定元素的后一位为左指针
            int right = n - 1;
            while (left < right) {
                int sum = num[i] + num[left] + num[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    ArrayList<Integer> item = new ArrayList<>();
                    item.add(num[i]);
                    item.add(num[left]);
                    item.add(num[right]);
                    ans.add(item);
                    //去重不会添加重复的组合到ans中
                    while (left < right && num[left] == num[left + 1]) left++;
                    while (left < right && num[right] == num[right - 1]) right--;
                    left++;
                    right--;
                }
            }
        }
        return ans;
    }
}

