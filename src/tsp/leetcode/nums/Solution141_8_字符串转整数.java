package tsp.leetcode.nums;

/**
 * 8:字符串转整数(atoi)
 * 你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 说明：
 * <p>
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 */
public class Solution141_8_字符串转整数 {

    public static int myAtoi(String str) {
        if (str.isEmpty()) return 0;
        boolean flag = false;//是否为负数

        //首先找到 能表示数的起始位置
        int i = 0, j = 0;
        for (i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('0' <= c && c <= '9') {//找到第一个数字的起始位置
                break;
            } else if (c == '-' || c == '+') {
                if (flag)
                    flag = (c == '-'); //判断是否为负数
            } else if (c != ' ') {
                return 0;
            }
        }
        for (j = i; j < str.length(); j++) { //找到表示数的字符串的最大长度
            if (str.charAt(j) < '0' || str.charAt(j) > '9') {
                break;
            }
        }
        String num = str.substring(i, j);//把数字部分切割出来
        long ans = 0;
        for (int k = 0; k < num.length(); k++) {
            int a = num.charAt(k) - '0';
            ans = ans * 10 + a;
            if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
                return flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }
        return (int) (flag ? -ans : ans);
    }

}

class Solution141 {

    public static int atoi(String str) {
        if (str == null || str.length() == 0) return 0;
        int pos = 0;
        while (str.charAt(pos) == ' ') pos++;
        int flag = 1;
        if (str.charAt(pos) == '+') pos++;
        if (str.charAt(pos) == '-') {
            flag = -1;
            pos++;
        }
        int res = 0;
        for (int i = pos; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') break;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && c > '7'))
                return Integer.MAX_VALUE; //判断是否溢出 2......7
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && c > '8'))
                return Integer.MIN_VALUE; // -2......8
            res = res * 10 + (c - '0') * flag;
        }
        return res;
    }
}
