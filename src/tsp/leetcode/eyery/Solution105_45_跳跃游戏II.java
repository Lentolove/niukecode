package tsp.leetcode.eyery;

/**
 * 45:跳跃游戏II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 示例:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 */
public class Solution105_45_跳跃游戏II {

    public int jump(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;
        int min_jump = 1;
        int current_max_index = nums[0]; //前一个位置可达到的最远距离
        int pre_max_index = nums[0];     //前一个位置之前的所有位置可达到的最远距离
        for (int i = 1; i < n; i++) {
            //当前位置的索引大于前一个位置所能到达的最远距离，则必须在这个位置之前进行跳跃，选择当中能跳到最远的位置进行一次跳跃
            if (i > current_max_index) {
                min_jump++;
                current_max_index = pre_max_index;
            }
            if (pre_max_index < nums[i] + i) {
                pre_max_index = nums[i] + i;
            }
        }
        return min_jump;
    }

    public int jump1(int[] nums) {
        int end = 0, maxPosition = 0, step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) {
                end = maxPosition;
                step++;
            }
        }
        return step;
    }
}
