package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/15
 * DESC:给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * <p>
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 * <p>
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * <p>
 * 提示：
 * 1 <= nums.length <= 10⁵
 * -10⁴ <= nums[i] <= 10⁴
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 * Related Topics 数组 分治 动态规划 👍 4525 👎 0
 */
public class Problem_0053_MaximumSubarray {

    /**
     * 常规思路：对于子数组问题，动态规划的思想
     * 一定要甄别以下两种情况
     * 1.dp[0,i]表示一 i 结尾的情况的最值问题
     * 2.dp[0,i]表示在区间[0,i]情况下的最值问题
     * 本题采用以i为结尾思想
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int n = nums.length;
        //dp[i] 表示[0,i]以i结尾的最大和的连续子数组
        int[] dp = new int[n];
        dp[0] = nums[0];
        //抓取过程中的最大值
        int ans = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 从解法一种可知，dp[i] 的值根dp[i - 1]有关，压缩dp数组，用一个变量来记录
     */
    public static int maxSubArray1(int[] nums) {
        if (nums.length == 1) return nums[0];
        int n = nums.length;
        int dp = nums[0];
        //抓取过程中的最大值
        int ans = dp;
        for (int i = 1; i < n; i++) {
//            dp = Math.max(dp + nums[i], nums[i]);
//            ans = Math.max(ans, dp);
            //优化点，如果之前dp值<=0，那就没必要添加了，因为 a + dp < a的
            dp = dp > 0 ? dp + nums[i] : nums[i];
            ans = Math.max(ans, dp);
        }
        return ans;
    }


    //--------------------拓展问题----------------

    /**
     * 题目：给定一个数组，返回累加和最大的子序列，规定：相邻两个元素不能同时选择,至少包含一个元素
     */
    public static int maxSumFollowUp(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        if (n == 1) return arr[0];
        if (n == 2) return Math.max(arr[0], arr[1]);
        //数组长度大于等于三
        //创建dp数组，因为题目是子序列，所以dp[i]表示[0,i]范围内累加和最大的子序列
        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < n; i++) {
            //三种情况
            int p1 = arr[i]; //1.只选择当前元素
            int p2 = dp[i - 1];//2.不选择当前元素
            int p3 = dp[i - 2] + arr[i];//选择当前元素及i-2位置的最大子序列和这个dp[i-2]可能是小于0的
            dp[i] = Math.max(Math.max(p1, p2), p3);
        }
        return dp[n - 1];
    }


    public static void main(String[] args) {
        int[] arr = {-2, -1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(arr));
        System.out.println(maxSubArray1(arr));
    }

}
