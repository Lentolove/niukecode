package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/17
 * DESC:给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * 示例 2：
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -2³¹ <= matrix[i][j] <= 2³¹ - 1
 * <p>
 * 进阶：
 * 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 * <p>
 * Related Topics 数组 哈希表 矩阵 👍 675 👎 0
 */
public class Problem_0073_SetMatrixZeroes {


    public void setZeroes(int[][] matrix) {
        boolean rowZero = false;
        boolean colZero = false;
        int n = matrix.length;
        int m = matrix[0].length;
        //1.首先记录第一行是否有0
        for (int i = 0; i < m; i++) {
            if (matrix[0][i] == 0){
                rowZero = true;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == 0){
                colZero = true;
                break;
            }
        }
        //2.开始遍历(1,1)位置到(n - 1,m - 1)位置出现0的情况
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        //3.现在根据0行和0列的标记位，将数组对应的行和列设置为0；
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        //4.在来修改0行0列
        if (rowZero){
            for (int i = 0; i < m; i++) {
                matrix[0][i] = 0;
            }
        }
        if (colZero){
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }

    }

}
