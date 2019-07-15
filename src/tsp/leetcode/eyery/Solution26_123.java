package tsp.leetcode.eyery;

import java.util.Arrays;

/**
 * leetcode:123 买卖股票的最佳时期 III Hard
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * <p>
 * 题解：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/yi-ge-tong-yong-fang-fa-tuan-mie-6-dao-gu-piao-wen/
 */
public class Solution26_123 {

    public static int maxProfit(int[] prices) {
        /**
         * 初始状态
         */
        int dp_i10 = 0;    //因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0
        int dp_i11 = Integer.MIN_VALUE;//还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能
        int dp_i20 = 0;   //
        int dp_i21 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            //                 rest      sell
            dp_i20 = Math.max(dp_i20,dp_i21+prices[i]);
            //                 rest      buy的时候(k-1)
            dp_i21 = Math.max(dp_i21,dp_i10-prices[i]);
            //                 rest      sell
            dp_i10 = Math.max(dp_i10,dp_i11+prices[i]);
            dp_i11 = Math.max(dp_i11,-prices[i]);

        }
        return dp_i20;
    }

    public static void main(String[] args) {
        int[] a = {3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(a));
    }
}
