package tsp.leetcode.dynamic_planning;

/**
 * 给定两个单词Word 1和Word2，找到将word 1转换为Word2所需的最小步骤数。
 * (每个操作都算作1步。)允许对一个单词执行以下3种操作：
 * a)插入字符
 * b)删除字符
 * c)替换字符
 *
 * 动态规划
 * https://leetcode-cn.com/problems/edit-distance/solution/bian-ji-ju-chi-by-leetcode/
 */
public class Solution77 {

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        //如果有一个字符串为0 则需要n+m步操作
        if (n*m==0) return n+m;
        //构建状态方程 dp[i][j] 表示 word1 的前 i 个字母和 word2 的前 j 个字母之间的编辑距离。
        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i < n+1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < m+1; i++) {
            dp[0][i] = i;
        }
        /**
         * 如果两个子串的最后一个字母相同，word1[i] = word2[i] 的情况下：
         * dp[i][j] = 1 + \min(D[i - 1][j], D[i][j - 1], D[i - 1][j - 1] - 1)
         * 否则，word1[i] != word2[i] 我们将考虑替换最后一个字符使得他们相同：
         * dp[i][j]=1+min(D[i−1][j],D[i][j−1],D[i−1][j−1]−1)
         */
        for (int i = 1; i <n+1 ; i++) {
            for (int j = 1; j < m+1 ; j++) {
                int left = dp[i-1][j]+1;
                int down = dp[i][j-1]+1;
                int left_down = dp[i-1][j-1];
                if (word1.charAt(i-1)!=word2.charAt(j-1)) { //
                    left_down += 1;
                }
                dp[i][j] = Math.min(left,Math.min(down,left_down));
            }
        }
        return dp[n][m];
    }
}
