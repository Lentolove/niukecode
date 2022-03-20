package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/19
 * DESC:一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * <p>
 * 要解码已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * <p>
 * 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * 示例 1：
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * <p>
 * 示例 2：
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 提示：
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 * <p>
 * Related Topics 字符串 动态规划 👍 1118 👎 0
 */
public class Problem_0091_DecodeWays {


    /**
     * 解码方法，11-26有两种解法，所以需要考虑当前位置及下一个位置一起的情况
     * 1.首先尝试暴力递归
     * 2.然后改动态规划
     * <p>
     * "226"
     */
    public static int numDecodings(String s) {
        return process(s.toCharArray(), 0);
    }

    /**
     * 表示一index为开头的翻译种类
     */
    public static int process(char[] str, int index) {
        if (index == str.length) {
            //有效的一个方案
            return 1;
        }
        //2.如果当index以0开头，那前面的组合不是一个有效的
        if (str[index] == '0') return 0;
        //当前index单个位置计算
        int ways = process(str, index + 1);
        //如果index后没数了，那就直接返回
        if (index == str.length - 1) return ways;
        int num = (str[index] - '0') * 10 + (str[index + 1] - '0');
        if (num <= 26) {
            //说明可以把index 和 index + 1 编译成一个数
            ways += process(str, index + 2);
        }
        return ways;
    }

    /**
     * 暴力递归改动态规划
     */
    public static int numDecodings1(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int n = str.length;
        //dp[i] 表示[i...]结尾的编码方法数
        int[] dp = new int[n + 1];
        dp[n] = 1;
        dp[n - 1] = (str[n - 1] == '0') ? 0 : 1;
        for (int i = n - 2; i >= 0; i--) {
            if (str[i] == '0') continue;
            dp[i] = dp[i + 1];
            int num = (str[i] - '0') * 10 + (str[i + 1] - '0');
            if (num <= 26) {
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        String s = "06";
        System.out.println(numDecodings(s));
        System.out.println(numDecodings1(s));
    }


}
