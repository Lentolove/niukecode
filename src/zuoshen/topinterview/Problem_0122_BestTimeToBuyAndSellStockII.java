package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/21
 * DESC:给定一个数组 prices ，其中 prices[i] 表示股票第 i 天的价格。
 * 在每一天，你可能会决定购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以购买它，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 * <p>
 * 示例 1:
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * <p>
 * 示例 2:
 * 输入: prices = [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 示例 3:
 * 输入: prices = [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 提示：
 * 1 <= prices.length <= 3 * 10⁴
 * 0 <= prices[i] <= 10⁴
 * <p>
 * Related Topics 贪心 数组 动态规划 👍 1606 👎 0
 */
public class Problem_0122_BestTimeToBuyAndSellStockII {

    /**
     * 买卖股票问题二：无限次交易
     * 1.既然是无限次交易，就是抓取每个上坡点。
     * 2.只要 prices[i] > prices[i  1] 那我就选择交易
     * 注意：[1,2,3,4,5,6] 最大收益是 5
     * 1 买入，2卖出，2再买入，3再卖出.....
     * 两个过程上升的总的高度是相同的。
     * 所以步骤二是能涵盖所有的情况的
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

}