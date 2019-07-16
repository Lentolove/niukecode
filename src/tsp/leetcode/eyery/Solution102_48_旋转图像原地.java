package tsp.leetcode.eyery;

/**
 * 48:旋转图像
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-image
 */
public class Solution102_48_旋转图像原地 {

    /**
     * 方法一：先将矩阵转置 在翻转向对应的列
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //矩阵转置
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        //第一类和最后一列交换 每一行都要交换
        for (int i = 0; i < n; i++) { //代表行
            for (int j = 0; j < n / 2; j++) { //代表列
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];// 第i行的第j列和第i行的di n-j-1列交换
                matrix[i][n - j - 1] = temp;
            }
        }
        return;
    }

    /**
     * 方法二：旋转矩形
     * 我的第一思想也是这样，在实现过程中过于复杂 GG
     * 每次旋转四个值: 1->4->16->13->1 第二次 2->8->15->9->2
     * 1   2   3   4
     * 5   6   7   8
     * 9   10  11  12
     * 13  14  15  16
     * 任意一个  (i,j)         (j,n-i-1)       (n-i-1,n-1-j)      (n-1-j,i)
     *          上行             右列              下行              左列
     *                    上行的列对应右行的行
     *                    上行的行对应 (n-1)-i的列
     */
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n/2; i++) { //圈数 i=0第一圈 i= 1 第二圈
            for (int j = i; j < n - i -1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
        }
    }
}