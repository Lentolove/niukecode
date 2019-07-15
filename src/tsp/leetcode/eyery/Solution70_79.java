package tsp.leetcode.eyery;

/**
 * 79 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 */
public class Solution70_79 {

    /**
     * 回溯法 DFS 和 状态重置
     */
    public boolean exist(char[][] board, String word) {
        if (board.length < 1) return false;
        int rows = board.length;
        int clos = board[0].length;
        boolean[][] flag = new boolean[rows][clos];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < clos; j++) {
                if (hashPath(board, i, j, rows, clos, word, 0, flag)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hashPath(char[][] board, int i, int j, int rows, int clos, String word, int k, boolean[][] flag) {
        if (i < 0 || j < 0 || i >= rows || j >= clos || board[i][j] != word.charAt(k) || flag[i][j] == true)
            return false;
        if (k == word.length() - 1) return true;//全部找到
        flag[i][j] = true;
        if (hashPath(board, i - 1, j, rows, clos, word, k + 1, flag) ||
                hashPath(board, i + 1, j, rows, clos, word, k + 1, flag) ||
                hashPath(board, i, j - 1, rows, clos, word, k + 1, flag) ||
                hashPath(board, i, j + 1, rows, clos, word, k + 1, flag)) {
            return true;
        }
        //当前位置没有找到 回溯 重置
        flag[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] a = {{'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        Solution70_79 s = new Solution70_79();
        System.out.println(s.exist(a, "ABCCED"));
    }
}
