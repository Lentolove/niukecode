package tsp.leetcode.nums;

import java.util.*;

/**
 * 18:四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 */
public class Solution132_18_四数之和 {
    /**
     * 方法一：边界去重调试半天
     * 直接上HashSet去重得了..........
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> ans = new HashSet<>();
        if (nums.length < 4) return new ArrayList<>(ans);
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) { //选定第一个数
            for (int j = i + 1; j < n - 2; j++) { //选定第二个数
                int sum = target  - nums[i] - nums[j];
                int left = j + 1;
                int right = n - 1;
                while (left < right){
                    if (nums[left] + nums[right] > sum){
                        right--;
                    }else if (nums[left] + nums[right] < sum){
                        left++;
                    }else {
                        List<Integer> itme = new ArrayList<>();
                        itme.add(nums[i]);
                        itme.add(nums[j]);
                        itme.add(nums[left]);
                        itme.add(nums[right]);
                        ans.add(itme);
                        left++;
                        right--;
                    }
                }
            }
        }
        return new ArrayList<>(ans);
    }
}

class Solution132{
    /**
     * 方法二：双指针 与三数之和的思路写法一模一样
     */
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int n = num.length;
        if (n < 4) return ans;
        Arrays.sort(num);
        for (int i = 0; i < n-3 ; i++) {
            if (i > 0 && num[i] == num[i-1]) continue;
            if (num[i] + num[i+1] + num[i+2] + num[i+3] > target) break;
            if (num[i] + num[n-1] + num[n-2] + num[n-3] < target) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i+1 && num[j] == num[j-1]) continue;;
                if (num[i] + num[j] + num[j+1] + num[j+2] > target) break;
                if (num[i] + num[j] + num[n-1] + num[n-2] < target) continue;;
                int left = j + 1;
                int right = n - 1;
                while (left < right){
                    int sum = num[i] + num[j] + num[left] + num[right];
                    if (sum == target) {
                        ArrayList<Integer> itme = new ArrayList<>();
                        itme.add(num[i]);
                        itme.add(num[j]);
                        itme.add(num[left]);
                        itme.add(num[right]);
                        ans.add(itme);
                        while (left<right && num[left] == num[left+1]) left++;
                        while (left<right && num[right] == num[right-1]) right--;
                        left++;
                        right--;
                    }else if (sum < target){
                        left++;
                    }else {
                        right--;
                    }
                }
            }
        }
        return ans;
    }
}
