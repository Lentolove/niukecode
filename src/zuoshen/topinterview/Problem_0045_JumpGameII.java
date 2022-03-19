package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/14
 * DESC:给你一个非负整数数组 nums ，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。假设你总是可以到达数组的最后一个位置。
 *
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *
 * 提示:
 *  1 <= nums.length <= 10⁴ 
 *  0 <= nums[i] <= 1000 
 *  
 *  Related Topics 贪心 数组 动态规划 👍 1459 👎 0
 * 
 */
public class Problem_0045_JumpGameII {

    /**
     * 题目定义：总是可以跳到最后一个位置，最少的跳跃次数
     * 思路：定义三个变量
     * step 表示当前跳跃的次数
     * cur  在step内当前能达到的最远距离
     * next 表示如果在之前的过程中，多跳一步，能达到的最远距离，用来记录如果当前cur覆盖不到 i 位置，说明在 i 之前我们必须要跳一次
     * 那我们这个 next 必须在每个 i 中更新以下最大值
     */
    public static int jump(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int step = 0;
        int cur = 0;
        int next = nums[0];
        for (int i = 1; i < n; i++) {
            if (i > cur){//之前的cur无法跳到当前完成值，需要在之前的选择中跳一步
                step++;
                cur = next;
            }
            next = Math.max(next,i + nums[i]);
        }
        return step;
    }
    
}
