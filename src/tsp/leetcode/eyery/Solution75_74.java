package tsp.leetcode.eyery;

/**
 * 74.搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。 (这说明可以二分查找)
 * 示例 1:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 */
class Solution74{
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length < 1||matrix[0].length<1) return false;
        int rows = matrix.length;
        int clos = matrix[0].length;
        int x = 0,y=clos-1;
        while (y>=0&&x<rows){
            if (matrix[x][y]==target){
                return true;
            }else if (matrix[x][y]<target){
                x++;
            }else {
                y--;
            }
        }
        return false;
    }
}


/**
 * 240 搜索二维矩阵II
 *编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 */

class Solution240{

    
}

public class Solution75_74 {

}
