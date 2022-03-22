package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author : tsp
 * Time: 2022/3/22 17:23
 * Desc :给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * <p>
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 * <p>
 * Related Topics 字符串 动态规划 回溯 👍 1044 👎 0
 */
public class Problem_0131_PalindromePartitioning {

    /**
     * 给定一个字符串 s,请你将 s 分割成一些子串，使每个子串都是回文串,返回 s 所有可能的分割方案。
     * 思路：求解字符串分解，通常应用递归来解决： f(index,)
     * 1.当前 index 表示从 index 位置出发，能分割的方案
     * 2.以[index...n-1]为头，剩下的继续调用递归，所有的可能情况为 [index....n-1]中
     * 3.当前只有在[index..i]为回文的前提下，才继续这个递归的过程。
     * 4.每次都要判断[index,j]是否为回文，这个代价是O(N)的，所以采用预处理的思想，
     * 提前记录，dp[i][j] 表示 [i,j] 是否为回文
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.isEmpty()) return ans;
        char[] str = s.toCharArray();
        int n = str.length;
        boolean[][] dp = getDp(str);
        process(s,ans,new ArrayList<>(),0,dp);
        return ans;
    }

    public static void process(String s,List<List<String>> ans,List<String> item,int index,boolean[][] dp){
        if (index == s.length()){
            ans.add(new ArrayList<>(item));
            return;
        }
        //从index触发，以[index,i]为开头
        for (int end = index; end < s.length(); end++) {
            if (dp[index][end]){
                item.add(s.substring(index, end + 1));
                process(s,ans,item,end + 1,dp);
                item.remove(item.size() - 1);
            }
        }
    }

    /**
     * 返回回文dp矩阵
     */
    public static boolean[][] getDp(char[] str) {
        int n = str.length;
        boolean[][] dp = new boolean[n][n];
        //回文串填写dp矩阵，矩阵的左下不分是无用的
        //1.首先对角线表示单个字符，都为true
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        //2.填写第二条对角线，(0,1)->(1,2)->(2,3)
        for (int i = 0; i < n - 1; i++) {
            if (str[i] == str[i + 1]) dp[i][i + 1] = true;
        }
        //3.通过dp矩阵图可推导，dp[i][j] 位置跟它的左下角有关机 dp[i + 1][j-1]
        //从左往右，从下往上填写dp
        for (int row = n - 2; row >= 0; row--) {
            for (int col = row + 1; col < n; col++) {
                if (str[row] == str[col] && dp[row + 1][col - 1]){
                    dp[row][col] = true;
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String s = "abccba";
        boolean[][] dp = getDp(s.toCharArray());
        for (boolean[] line : dp) {
            System.out.println(Arrays.toString(line));
        }
    }

}
