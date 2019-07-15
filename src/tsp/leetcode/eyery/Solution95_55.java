package tsp.leetcode.eyery;

/**
 * 55: 跳跃游戏 动态规划
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 */
public class Solution95_55 {




    //从后往前遍历
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n<1) return false;
        int dc = 1;
        int i = n-2;//从倒数第二个位置开始判断
        for (; i >=0; i--) {
            if (nums[i]>=dc){////该位置下可以跳的最远距离大于等于1，那一定可以跳过去
                dc = 1;
            }else {////该位置可跳距离比1还小，0 则下一个点至少可跳的距离大于等于2 否则不可达
                dc++;
            }
        }
        if (dc>1) return false;
        return true;
    }

    /**
     * 方法一：回溯法
     * 这是一个低效的解决方法。我们模拟从第一个位置跳到最后位置的所有方案。
     * 从第一个位置开始，模拟所有可以跳到的位置，然后从当前位置重复上述操作，
     * 当没有办法继续跳的时候，就回溯。
     */
    public boolean canJump1(int[] nums) {
        return helper(0,nums);
    }

    private boolean helper(int position,int[] nums){
        if (position==nums.length-1){
            return true;
        }
        int furthestJump = Math.min(position+nums[position], nums.length-1);
        for (int i = position+1; i <=furthestJump ; i++) {
            if (helper(i,nums)){
                return true;
            }
        }
        return false;
    }


    /**
     * 方法二 自顶向下的动态规划
     */
    enum Index{
        GOOD,BAD,UNKNOWN
    }

    class Method2{
        Index[] memo;
        public boolean canJump(int[] nums){
            memo = new Index[nums.length];
            for (int i = 0; i < memo.length; i++) {
                memo[i] = Index.UNKNOWN;
            }
            memo[memo.length-1] = Index.GOOD;
            return helper(nums,0);
        }

        private boolean helper(int[] nums,int position){
            if (memo[position]!=Index.UNKNOWN){
                return memo[position] == Index.GOOD?true:false;
            }
            int furthestJump = Math.min(position+nums[position],nums.length-1);
            for (int i = position+1; i <= furthestJump; i++) {
                if (helper(nums,i)){
                    memo[position] = Index.GOOD;
                    return true;
                }
            }
            //回溯
            memo[position] = Index.BAD;
            return false;
        }
    }

    /**
     * 方法三：自底向上的动态规划
     */
    class Method3{
        public boolean canJump(int[] nums){
            Index[] memo = new Index[nums.length];
            for (int i = 0; i < memo.length ;i++) {
                memo[i] = Index.UNKNOWN;
            }
            memo[memo.length-1] = Index.GOOD;
            for (int i = nums.length-2; i >=0 ; i--) {
                int furtJump = Math.min(i+nums[i],nums.length-1);
                for (int j = i+1; j < furtJump; j++) {
                    if (memo[j]==Index.GOOD){
                        memo[i] = Index.GOOD;
                        break;
                    }
                }
            }
            return memo[0] == Index.GOOD;
        }
    }

    /**
     * 贪心  好方法
     */
    class Method4 {
        public boolean canJump(int[] nums) {
            int lastPos = nums.length-1;
            for (int i = nums.length-2; i >=0 ; i--) {
                if (i+nums[i]>=lastPos){
                    lastPos = i;
                }
            }
            return lastPos==0;
        }
    }
}
