package tsp.leetcode.eyery;

/**
 * leetcode:121 买卖股票的最佳时期
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 */
public class Solution28_121 {

    //暴力法
    public static int maxProfit(int[] prices) {
        if (prices.length < 1) return 0;
        int len = prices.length;
        int max_prive = 0;
        for (int i = len - 1; i > 0; i--) { //卖出
            for (int j = 0; j < i; j++) {
                if (prices[i] > prices[j]) {
                    max_prive = Math.max(max_prive, prices[i] - prices[j]);
                }
            }
        }
        return max_prive;
    }

    //一次遍历
    public int maxProfit1(int[] prices) {
        int minpreice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minpreice) {
                minpreice = prices[i];
            } else if (prices[i] - minpreice > maxprofit) {
                maxprofit = prices[i] - minpreice;
            }
        }
        return maxprofit;
    }


    public static void main(String[] args) {
        int[] nums = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(nums));
    }
}
