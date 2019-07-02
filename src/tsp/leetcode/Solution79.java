package tsp.leetcode;

/**
 * 你在爬楼梯。达到顶峰需要n步。每次你可以爬1或2步。
 * 你能以多少种不同的方式爬上山顶？
 */
public class Solution79 {


    public static int climbStairs(int n) {
        if (n<2) return n;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <n+1; i++) {
            /**
             * 第一次爬1步剩下的dp[i-1] 步
             * 第一次爬2步 剩下的dp[i-2] 步
             */
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }
}
