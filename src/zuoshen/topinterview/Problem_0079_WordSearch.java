package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/18
 * DESC:给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
 * "ABCCED"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
 * "SEE"
 * 输出：true
 * <p>
 * 提示：
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * <p>
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 * Related Topics 数组 回溯 矩阵 👍 1224 👎 0
 */
public class Problem_0079_WordSearch {

    /**
     * 深度优先遍历，四个方向都可以走，走过的位置不能走
     * 1.可以将走过的位置改为0，返回之前再恢复，这样可以节省一个二维的flag标记矩阵空间
     */
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        char[] words = word.toCharArray();
        //每个位置都可以走
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (process(board,words,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean process(char[][] board,char[] word,int i,int j,int index){
        //1.找到
        if (index == word.length) return true;
        //2.越界
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        //3.当前字符与word[index]不匹配，包括我们手动改为0，也表示当前位置不可走
        if (board[i][j] != word[index]) return false;
        //4.来到这里，当前位置匹配成功，记录当前字符  temp ，然后改为0，表示当前字符已经选择过了，不可以重复走，temp用于恢复现场
        char temp = board[i][j];
        board[i][j] = '0';
        //四个方向走，有一个成功就算成功
        boolean result = process(board,word,i + 1,j,index + 1)
                | process(board,word,i - 1,j,index + 1)
                | process(board,word,i,j + 1,index + 1)
                | process(board,word,i,j - 1,index + 1);
        //返回之前恢复现场
        board[i][j] = temp;
        return result;
    }

}
