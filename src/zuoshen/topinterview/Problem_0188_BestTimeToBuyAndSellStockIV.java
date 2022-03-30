package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/30 20:27
 * Desc :给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * <p>
 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3
 * 。
 * <p>
 * 提示：
 * <p>
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 * <p>
 * Related Topics 数组 动态规划 👍 693 👎 0
 */
public class Problem_0188_BestTimeToBuyAndSellStockIV {

    /**
     * 买卖股票的最好时机，交易次数为k
     * 分情况：k >= n / 2 则等同于无限次交易
     * 如果 k < n / 2，则使用动态规划
     */
    public int maxProfit(int k, int[] prices) {

    }

    /**
     * 区间 1 ~ n / 2 之间
     */
    public int maxProfitK(int[] prices, int k) {
        int n = prices.length;
        //dp[i][j]表示[0..i]天，交易次数为 j 的最大收益
        int[][] dp = new int[n][k + 1];
        for (int i = 1; i < n; i++) {//从第二天开始
            for (int j = 1; j <= k; j++) {

            }
        }
    }

}
