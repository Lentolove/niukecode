package tsp.leetcode.backtract;

public class Q79_单词搜 {


    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}
        };
        Solution solution = new Solution();
        System.out.println(solution.exist(board,"ABCCED"));
    }

    static class Solution {

        private char[][] board;
        private int row, col;
        private String word;
        public boolean exist(char[][] board, String word) {
            this.row = board.length;
            this.col = board[0].length;
            this.board = board;
            this.word = word;
            for(int i = 0; i < row; i++){
                for(int j = 0;j < col; j++){
                    if(backtrack(new boolean[row][col],i,j,0)) return true;
                }
            }
            return false;
        }

        public boolean backtrack(boolean[][] flag,int x,int y,int count){
            if(x < 0 || x >= row || y < 0 || y >= col || board[x][y] != word.charAt(count) ||  flag[x][y]) return false;
            if(count == word.length() - 1) return true;
            flag[x][y] = true;
            boolean result = backtrack(flag,x - 1,y,count + 1) ||
                    backtrack(flag,x + 1,y,count + 1) ||
                    backtrack(flag,x,y + 1,count + 1) ||
                    backtrack(flag,x,y - 1,count + 1);
            if(result){
                return true;
            }else{
                flag[x][y] = false;
                return false;
            }
        }
    }
}
