package zuoshen.trainingcamp004.class02;

/**
 * Author : tsp
 * Time: 2022/3/3 11:05
 * Desc :股票交易问题，可以无限制买入卖出
 */
public class Code02_BestTimeToBuyAndSellStock2 {

    /**
     * 可以无限制买入卖出，就相当于我需要找到这个这线上所有的上升阶段的总和
     * 如果 prices[i] > prices[i -1]，则上升，能获得收益
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

}
