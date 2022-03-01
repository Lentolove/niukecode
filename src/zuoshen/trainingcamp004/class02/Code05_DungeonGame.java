package zuoshen.trainingcamp004.class02;

/**
 * Author : tsp
 * Time: 2022/3/1 20:07
 * Desc : 174 https://leetcode-cn.com/problems/dungeon-game/
 */
public class Code05_DungeonGame {


    public static int needMin(int[][] matrix) {
        return process(matrix, matrix.length, matrix[0].length, 0, 0);
    }

    public static int process(int[][] matrix, int N, int M, int row, int col) {
        //1.如果到达了左下角
        if (row == N - 1 && col == M - 1) {
            //如果当前格子的hp值是正的，直接返回-1,否则需要返回当前格子的好血量 + 1,至少保证一格血通关
            return matrix[row][col] < 0 ? (-matrix[row][col] + 1) : 1;
        }
        //2.到达最后一行,那只能往后走
        if (row == N - 1) {
            //2.1 右边需要的hp
            int rightNeed = process(matrix, N, M, row, col + 1);
            if (matrix[row][col] < 0) {//当前位置耗血，那这个位置至少需要 -matrix[row][col] + rightNeed
                return -matrix[row][col] + rightNeed;
            } else if (matrix[row][col] >= rightNeed) {
                //当前格子的hp可以用于后面通关了，那至少需要保证之前是1格血过来的
                return 1;
            } else {
                //当前的格子的hp>0，但是达不到rightNeed的通关条件，当前是格子1，rightNeed = 3，则至少需要2格血，到达当前格子前都是保证至少血量是1
                return rightNeed - matrix[row][col];
            }
        }
        //3.来到最后一列
        if (col == M - 1) {
            int downNeed = process(matrix, N, M, row + 1, col);
            if (matrix[row][col] < downNeed) {
                return -matrix[row][col] + downNeed;
            } else if (matrix[row][col] >= downNeed) {
                return 1;
            } else {
                return downNeed - matrix[row][col];
            }
        }
        //4.来到中间位置，可以往下也可以往右走,取较小值
        int minNeed = Math.min(process(matrix, N, M, row + 1, col), process(matrix, N, M, row, col + 1));
        if (matrix[row][col] < 0) {
            return -matrix[row][col] + minNeed;
        } else if (matrix[row][col] >= minNeed) {
            return 1;
        } else {
            return minNeed - matrix[row][col];
        }
    }


    public static int needMin2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) return 1;
        int row = m.length;
        int col = m[0].length;
        //dp[i][j] 表示达到该位置需要的hp，自底向上，从右往左
        int[][] dp = new int[row][col];
        //到达右下角需要的hp
        dp[row - 1][col - 1] = m[row - 1][col - 1] < 0 ? (-m[row - 1][col - 1] + 1) : 1;
        //2.初始化最后一行
        for (int i = col - 2; i >= 0; i--) {
            //当前位置的hp为它的右边需要的血量 - 当前位置，如果当前位置为 -2，右边为3，则该位置需要5，如果当前位置为2,则需要的hp为1
            dp[row - 1][i] = Math.max(1, dp[row - 1][i + 1] - m[row - 1][i]);
        }
        //3.开始填写dp数组
        for (int i = row - 2; i >= 0; i--) {
            //最后一列单独计算，只依赖它的下方
            dp[i][col - 1] = Math.max(1, dp[i + 1][col - 1] - m[i][col - 1]);
            for (int j = col - 2; j >= 0; j--) {
                //中间位置，右边需要的hp
                int right = Math.max(dp[i][j + 1] - m[i][j], 1);
                int down = Math.max(dp[i + 1][j] - m[i][j], 1);
                dp[i][j] = Math.min(right, down);
            }
        }
        return dp[0][0];
    }


    //[[0,0,0],[1,1,-1]]
    public static void main(String[] args) {
        int[][] map = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5},};

        int[][] map2 = {{0, 0, 0}, {1, 1, -1}};
        System.out.println(needMin(map));
        System.out.println(needMin2(map));


    }
}
