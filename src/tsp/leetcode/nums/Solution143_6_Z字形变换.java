package tsp.leetcode.nums;

import java.util.ArrayList;
import java.util.List;

/**
 * 6: Z 字形变换
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 */
public class Solution143_6_Z字形变换 {

    /**
     * 方法一：按照行访问
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;// n + n -2 没一个周期有几个字符
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                sb.append(s.charAt(j + i));//这个是第i行第j列的字符，就是 | 的一列
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    sb.append(s.charAt(j + cycleLen - i));

                }
            }
        }
        return sb.toString();
    }

    /**
     * 方法二：按列排序  666666666666！
     * 神仙思想！！！！
     */
    public static String convert1(String s, int numRows) {
        if (numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean goingDown = false;
        //根据Z字形的特点 从左到右将每一行添加到list中对应的行
        // curRow 的控制精，到达低端了 cur就开始回退 到达上端了又开始上升 我的妈呀，真的是66666
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder row : rows) {
            sb.append(row);
        }
        return sb.toString();
    }
}
