package zuoshen.trainingcamp004.class02;

/**
 * Author : tsp
 * Time: 2022/3/3 10:54
 * Desc : 股票问题：只交易一次
 */
public class Code01_BestTimeToBuyAndSellStock1 {

    /**
     * 只买卖一次获得的最大利润，遍历数组，记录最低价位，在后续遍历中更新最大收益
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int min = prices[0];
        int ans = 0;
        for (int price : prices) {
            min = Math.min(min, price);
            ans = Math.max(ans, price - min);
        }
        return ans;
    }

}
