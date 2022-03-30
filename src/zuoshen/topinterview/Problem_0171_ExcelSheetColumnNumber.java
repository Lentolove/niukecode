package zuoshen.topinterview;

/**
 * Author : tsp ： 当你心情浮躁的时候，那就做做算法题，听听宗次郎的歌曲，那时心情一定会好起来的
 * Time: 2022/3/30 19:47
 * Desc :给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。
 *
 * 例如：
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28 
 * ... 
 *
 * 示例 1:
 * 输入: columnTitle = "A"
 * 输出: 1
 *
 * 示例 2:
 * 输入: columnTitle = "AB"
 * 输出: 28
 *
 * 示例 3:
 * 输入: columnTitle = "ZY"
 * 输出: 701 
 *
 * 提示：
 *
 *  1 <= columnTitle.length <= 7 
 *  columnTitle 仅由大写英文组成 
 *  columnTitle 在范围 ["A", "FXSHRXW"] 内 
 *  
 *  Related Topics 数学 字符串 👍 319 👎 0
 */
public class Problem_0171_ExcelSheetColumnNumber {

    /**
     * 题意：A-Z表示1-26,此题目是伪26进制求法
     * ZY = 26 * 26 + 25 = 701
     */
    public static int titleToNumber(String columnTitle) {
        int ans = 0;
        char[] str = columnTitle.toCharArray();
        for (char c : str) {
            ans = ans * 26 + (c - 'A' + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(titleToNumber("ZY"));
    }
}
