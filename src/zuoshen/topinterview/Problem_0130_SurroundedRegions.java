package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/26
 * DESC:给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充
 * <p>
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O",
 * "X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都
 * 会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 示例 2：
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 * <p>
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 * <p>
 * Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 754 👎 0
 */
public class Problem_0130_SurroundedRegions {

    /**
     * 题目：被围绕的区域，围棋的思想，中间的区域被杀，边界区域保持原状
     * 1.从边界开始感染，与边界相连的 'O' 是不会被杀，先通过深度优先遍历找到边界相连的所有位置
     * 2.将 'O' 改为 'F'，表示这个位置不会改为 'X'，第二次遍历的时候将所有的 'O' 改为 'X'
     * 3.将所有的'F'改为'O'
     */
    public void solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        if (row <= 2 || col <= 2) return;
        //1.先处理边界处的 'O'
        for (int i = 0; i < row; i++) {
            //1.第一列，和最后一列
            if (board[i][0] == 'O') {
                process(board, i, 0);
            }
            if (board[i][col - 1] == 'O') {
                process(board, i, col - 1);
            }
        }
        for (int i = 1; i < col - 1; i++) {
            //1.第一行，最后一行
            if (board[0][i] == 'O') {
                process(board, 0, i);
            }
            if (board[row - 1][i] == 'O') {
                process(board, row - 1, i);
            }
        }
        //开始感染
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'F'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void process(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
            return;
        }
        //朝四个方向去感染，当前位置为 '0' 先改为 'F'
        board[i][j] = 'F';
        process(board, i + 1, j);
        process(board, i - 1, j);
        process(board, i, j + 1);
        process(board, i, j - 1);
    }

}
