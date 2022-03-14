package zuoshen.interview.classic6;

/**
 * 左神:给定一个整型矩阵，返回子矩阵的最大累加和
 * 思路：矩阵的累加和，转换思想：子矩阵的每一行加起来，变成一维度的数组
 * 又变成了一维矩阵的最大累加和
 */
public class Code02_SubMatrixMaxSum {

    /**
     * 1.按照行来定义：0-0行，0-1行,0-2行，0-3行来遍历
     * 2.1-1行，1-2行，1-3行，1-4行等等。
     * 3.所以求矩阵累加和，比如要0-1一共两行，将两行压缩到一行，就变成求一行的最大累加和问题了。
     * 4.矩阵进行累加和预处理
     */
    public static int maxMatrixSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return 0;
        int row = m.length;
        int col = m[0].length;
        int max = 0;
        int[] s;
        //遍历 i-j行求子矩阵的最大累加和
        for (int i = 0; i < row; i++) {
            //从第i行开始，遍历到第 row行
            s = new int[col];//i-j行累加和
            for (int j = i; j < row; j++) {
                //i-j行，初始化当前值 cur
                int cur = 0;
                //遍历当前 s 数组更新
                for (int k = 0; k < col; k++) {
                    //s中存储的是i-j行的每一列的和
                    s[k] += m[j][k];
                    cur += s[k];
                    max = Math.max(max, cur);
                    cur = Math.max(cur, 0);
                }
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[][] matrix = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
        System.out.println(maxMatrixSum(matrix));
    }

}
