package tsp.leetcode.dynamic_planning;

import java.util.Arrays;

/**
 * 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 示例:
 * <p>
 * 输入:
 * [
 *   [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum。
 */
public class Solution86 {

    public static int minPathSum1(int[][] grid) {
        if (grid == null || grid.length < 0) return 0;
        int rows = grid.length;
        int clos = grid[0].length;
        int[][] dp = new int[rows][clos];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < clos; i++) {
            dp[0][i] = grid[0][i]+dp[0][i-1];
        }
        for (int i = 1; i < rows; i++) {
            dp[i][0] = grid[i][0]+dp[i-1][0];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < clos; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows-1][clos-1];
    }

    //直接改变原始矩阵
    public static int minPathSum(int[][] grid) {
        int rows = grid.length;
        int clos = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < clos; j++) {
                if (i==0&&j!=0) grid[i][j] += grid[i][j-1];
                if (i!=0&&j==0) grid[i][j] += grid[i-1][j];
                if (i*j!=0){
                    grid[i][j] += Math.min(grid[i-1][j],grid[i][j-1]);
                }
            }
        }
        return grid[rows-1][clos-1];
    }

    //方法三：
    public static int minPathSum2(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int[] res = new int[colLen + 1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[1] = 0;
        for (int i = 1; i <= rowLen; i++) {
            for (int j = 1; j <= colLen; j++) {
                //当前点的最小路径和为 : 从左边和上边选择最小的路径和再加上当前点的值
                //res[j]没更新之前就表示i-1行到第j个元素的最小路径和
                //因为是从左往右更新,res[j-1]表示i行第j-1个元素的最小路径和
                res[j] = Math.min(res[j], res[j - 1]) + grid[i - 1][j - 1];
            }
        }
        return res[colLen];
    }


    public static void main(String[] args) {
        int[][] a = {{1, 3, 1},{1, 5, 1},{4, 2, 1}};
        System.out.println(minPathSum2(a));
    }
}
