package tsp.leetcode.eyery;

/**
 * 73:矩阵置零
 *给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * 示例 1:
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 进阶:
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes
 */
public class Solution76_73 {

    /**
     * 利用第一行第一列的空间来做记录
     */
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int clos = matrix[0].length;
        boolean row_flag = false,clo_flag = false;
        //判断第一行和第一列是否有零 防止被覆盖
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0]==0){
                clo_flag = true;
                break;
            }
        }
        for (int i = 0; i < clos; i++) {
            if (matrix[0][i]==0){
                row_flag = true;
                break;
            }
        }
        //遍历矩阵，用第一行和第一列的记录0的位置
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < clos; j++) {
                if (matrix[i][j]==0){
                    matrix[i][0] =0;
                    matrix[0][j] =0;
                }
            }
        }
        //根据第一行和第一列的记录清零
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < clos; j++) {
                if (matrix[i][0]==0||matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
        }
        //最后处理第一行，
        if (row_flag){
            for (int i = 0; i < clos; i++) {
                matrix[0][i] = 0;
            }
        }
        if (clo_flag){
            for (int i = 0; i < rows; i++) {
                matrix[i][0]=0;
            }
        }
    }

    /**
     * O(1) 空间的暴力
     * 1.遍历原始矩阵，如果返现某个元素 matrix[i][j]为0，我们将第 i 行和第 j 列的搜索非零元素设成很大的
     * 负虚拟之（-100000）。注意，正确的虚拟值取值依赖于问题的约束，任何允许值范围外的数字都可以作为虚拟之。
     * 2.最后，我们便利整个矩阵将所有等于虚拟值（常量在代码中初始化为 MODIFIO) 的元素设为0；
     */
    public void setZeroes1(int[][] matrix) {
        int MODIFIED = -100000;
        int rows= matrix.length;
        int clos = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < clos; j++) {
                if (matrix[i][j]==0){
                    for (int k = 0; k < clos; k++) {
                        if (matrix[i][k]!=0){
                            matrix[i][k] = MODIFIED;
                        }
                    }
                    for (int k = 0; k < rows; k++) {
                        if (matrix[k][j]!=0){
                            matrix[k][j] = MODIFIED;
                        }
                    }
                }
            }
        }


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < clos; j++) {
                if (matrix[i][j]==MODIFIED){
                    matrix[i][j] =0;
                }
            }
        }
    }


}
