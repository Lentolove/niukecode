package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/14
 * DESC:给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 *
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 * 提示：
 *  1 <= nums.length <= 3 * 10⁴ 
 *  0 <= nums[i] <= 10⁵ 
 *  
 *  Related Topics 贪心 数组 动态规划 👍 1709 👎 0
 */
public class Problem_0055_JumpGame {

    /**
     * 跳跃问题一：判断起始位置能否到终点
     * 思路：定义一个变量 max 记录当前位置能跳到的最大距离，遍历数组到 i 位置，如果 i > max，表示之前不可达，直接返回
     */
    public static boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        int n = nums.length;
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            //加过滤条件，如果当前 max 已经可以到末尾了，提前返回true
            if (max >= n) return true;
            if (i > max){
                return false;
            }
            max = Math.max(max,nums[i] + i);
        }
        return true;
    }

}
