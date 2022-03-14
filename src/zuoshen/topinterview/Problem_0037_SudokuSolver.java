package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/11 15:47
 * Desc : 解数独，给定义个数独，把它填好
 */
public class Problem_0037_SudokuSolver {

    /**
     * 暴力递归，一行一行的填写数独，
     * 然后根据我们的isValidSudoku函数提前预处理，可以减掉一些不必要的尝试
     */
    public static void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];
        initBoard(board, row, col, bucket);
        process(board,0,0,row,col,bucket);
    }

    /**
     * i,j 是当前来到的位置，如果不是 '.' 就表明填过了，继续下一个位置，从左往右，从是往下填写。
     * 如果是 '.'就需要根据限定条件剪枝
     */
    private static boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        if (i == 9) {//一行一行的填写，最后一行也填完了，返回true
            return true;
        }
        //计算下一个要填写的位置,从上往下，从左往右,不是最后一列
        int nextI = j != 8 ? i : i + 1;
        int nextJ = j != 8 ? j + 1 : 0;
        //如果已经是数字了，就继续下一个位置,此位置不用填写了
        if (board[i][j] != '.') {
            return process(board, nextI, nextJ, row, col, bucket);
        }
        //当前位置需要填写数字'.',可以尝试1-9
        for (int num = 1; num <= 9; num++) {
            int buckIndex = (i / 3) * 3 + (j / 3);
            //根据row，col，bucket 来剪枝
            if (!row[i][num] && !col[j][num] && !bucket[buckIndex][num]){//之前都没放过该数字，可以放
                row[i][num] = true;
                col[j][num] = true;
                bucket[buckIndex][num] = true;
                board[i][j] = (char)(num + '0');
                //如果能找到填完
                if (process(board,nextI,nextJ,row,col,bucket)){
                    return true;
                }
                //走到这里，说明之前的尝试不行，就需要恢复现场
                row[i][num] = false;
                col[j][num] = false;
                bucket[buckIndex][num] = false;
                board[i][j] = '.';
            }
        }
        return false;
    }

    public static boolean initBoard(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //计算位于哪个格子
                int Index = (i / 3) * 3 + (j / 3);
                if (board[i][j] != '.') {
                    int a = board[i][j] - '0';
                    //判断缓存表中是否出现过了
                    if (row[i][a] || col[j][a] || bucket[Index][a]) return false;
                    //把当前位置标记好，该位置已经放过某元素了
                    row[i][a] = true;
                    col[j][a] = true;
                    bucket[Index][a] = true;
                }
            }
        }
        return true;
    }
}
