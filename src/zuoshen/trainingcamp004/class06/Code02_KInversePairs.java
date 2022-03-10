package zuoshen.trainingcamp004.class06;

/**
 * Author : tsp
 * Time: 2022/3/8 13:47
 * Desc :给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
 * 逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i<j 且 a[i]>a[j]，则其为一个逆序对；否则不是。
 * 由于答案可能很大，只需要返回 答案 mod 109+ 7 的值。
 * 链接：https://leetcode-cn.com/problems/k-inverse-pairs-array
 */
public class Code02_KInversePairs {

    public static int dp1(int N, int K) {
        if (N < 1 || K < 0) {
            return 0;
        }
        if (K == 0) {
            return 1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[1][0] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = 1;
        }
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                for (int s = j; s >= Math.max(0, j - i + 1); s--) {
                    dp[i][j] += dp[i - 1][s];
                }
            }
        }
        return dp[N][K];
    }
}
