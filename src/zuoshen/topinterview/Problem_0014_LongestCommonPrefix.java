package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/9 17:20
 * Desc :
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * <p>
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 * <p>
 * Related Topics 字符串 👍 2088 👎 0
 */
public class Problem_0014_LongestCommonPrefix {

    /**
     * 示例 1：
     * 输入：strs = ["flower","flow","flight"]
     * 就是简单的暴力尝试
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        //既然找最长的公共前缀，那直接以str[0]为初始尝试
        char[] match = strs[0].toCharArray();
        //记录每个字符串中公共的最短
        int min = Integer.MAX_VALUE;
        for (String s : strs) {
            int index = 0;
            while (index < match.length && index < s.length() && s.charAt(index) == match[index]) {
                index++;
            }
            //只要有更短的出现，就直接更新
            min = Math.min(min,index);
        }
        return strs[0].substring(0,min);
    }

    public static void main(String[] args) {
        String[] str = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(str));

    }
}
