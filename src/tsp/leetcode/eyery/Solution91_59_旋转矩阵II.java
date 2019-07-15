package tsp.leetcode.eyery;

import java.util.Arrays;

/**
 * 59: 旋转矩阵II
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * 示例:
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 */
public class Solution91_59_旋转矩阵II {

    /**
     * 自己写的 真不容易
     */
    public static int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        int level = (n+1)/2; //矩阵转圈层数
        int num = 1;
        for (int i = 0; i < level; i++) {
            //从左往右填充
            for (int j = i; j < n-i; j++) ret[i][j] = num++;
            //从上往下填充
            for (int j = i+1; j < n-i; j++) ret[j][n-i-1] = num++;
            //从右往左填充
            for (int j = n-i-2; j >=i ; j--) ret[n-1-i][j] = num++;
            //从下往上填充
            for (int j = n-i-2; j > i ; j--) ret[j][i] = num++;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] ints = generateMatrix(1);

    }
}
