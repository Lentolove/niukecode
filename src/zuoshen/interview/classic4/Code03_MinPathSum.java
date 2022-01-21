package zuoshen.interview.classic4;

/**
 * 左神：给定一个二维数组 matrix,其中每个数都是正数，要求从左上到右下，每一步只能向右或者向下，
 * 沿途经过的数字要累加起来，最后返回最小路径和
 */
public class Code03_MinPathSum {

    /**
     * 动态规划1.0 版本
     * dp[i,j] = Math.min(dp[i - 1][j],dp[i][j - 1]) + m[i][j]
     * 思路2：可以直接在原始 m 数组上更改
     */
    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return 0;
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        //1.初始化第一行，初始化第一列
        dp[0][0] = m[0][0];
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + m[0][i];
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * 动态规划：2.0 版本
     * 空间压缩，将二维dp改成一维dp。矩阵
     * 小优化点：dp数组的递推方向根据矩阵的规格来定
     *         1.如果 row > col,则 dp 表示一行，从上往下更新dp
     *         2.如果 row < col，则 dp 表示第一列，从左往右更新 dp
     */
    public static int minPathSum2(int[][] m){
        if (m == null || m.length == 0 || m[0].length == 0) return 0;
        int row = m.length;
        int col = m[0].length;
        //1.定义 dp 数组为行，从第一行往最后行迭代
        int[] dp = new int[col];
        dp[0] = m[0][0];
        //2.初始化第一行
        for (int i = 1; i < col; i++){
            dp[i] = dp[i - 1] + m[0][i];
        }
        //3.填写dp数组
        for (int i = 1; i < row; i++){
            //更新dp数组
            dp[0] += m[i][0];
            //3.1开始填写当前行的其它列
            for (int j = 1; j < col; j++){
                //当前位置要么从左边过来，要么从上面下来
                dp[j] = Math.min(dp[j - 1],dp[j]) + m[i][j];
            }
        }
        return dp[col - 1];
    }





    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1},
                {8, 8, 4, 0}};
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));
    }

}
