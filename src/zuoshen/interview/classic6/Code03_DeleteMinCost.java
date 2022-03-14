package zuoshen.interview.classic6;

import java.util.ArrayList;
import java.util.List;

/**
 * 左神：字符串编辑距离(一)
 * 题目：给定两个字符串s1和s2，问s2最少删除多少个字符可以成为s1的子串？
 * eg: s1 = "abcde" , s2 = "axbc"
 * 返回1，s2删除掉'x'就是s1的子串了
 */
public class Code03_DeleteMinCost {

    /**
     * 思路一：
     * 1.求出s2的所有子序列，然后按照长度从大到小排序。
     * 2.然后考察那个子序列字符与s1的某个子串相等(KMP)
     * 分析：
     * 因为题目原本的样本数据中，有特别说明s2的长度很小。所以这么做也没有太大问题，也几乎不会超时。
     * 但是如果某一次考试给定的s2长度远大于s1，这么做就不合适了。
     */
    public static int minCost1(String s1, String s2) {
        char[] st2 = s2.toCharArray();
        List<String> s2List = new ArrayList<>();
        process1(st2, 0, s2List, "");
        s2List.sort((o1, o2) -> o2.length() - o1.length());
        for (String item : s2List) {
            //判断item是否是s1的子串,底层和KMP算法代价几乎一样，也可以用KMP代替
            if (s1.contains(item)) {
                //长度是经过排序的，找到了直接返回
                return s2.length() - item.length();
            }
        }
        return s2.length();
    }

    //获取一个字符串的所有子序列
    public static void process1(char[] str, int index, List<String> result, String path) {
        if (index == str.length) {
            result.add(path);
            return;
        }
        process1(str, index + 1, result, path);//当前字符不选择
        process1(str, index + 1, result, path + str[index]);//选择当前字符
    }

    /**
     * 左神：找出s1的左右子串 s1Sub，找出s1Sub与str2的编辑距离，记录最小值
     */
    public static int minCost2(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) return 0;
        char[] str2 = s2.toCharArray();
        int ans = Integer.MAX_VALUE;
        //暴力查找s1的左右子串,i-j的长度，左开右闭区间
        for (int i = 0; i < s1.length(); i++) {
            for (int j = i + 1; j <= s1.length(); j++) {
                ans = Math.min(ans, distance(str2, s1.substring(i, j).toCharArray()));
            }
        }
        return ans;
    }

    /**
     * str2转变为s1Sub的代价，只能删除
     */
    public static int distance(char[] str2, char[] s1Sub) {
        int row = str2.length;
        int col = s1Sub.length;
        int[][] dp = new int[row][col];
        // dp[i][j]的含义：
        // str2[0..i]仅通过删除行为变成s1sub[0..j]的最小代价
        // 可能性一：
        // str2[0..i]变的过程中，不保留最后一个字符(str2[i])，
        // 那么就是通过str2[0..i-1]变成s1sub[0..j]之后，再最后删掉str2[i]即可 -> dp[i][j] = dp[i-1][j] + 1
        // 可能性二：
        // str2[0..i]变的过程中，想保留最后一个字符(str2[i])，然后变成s1sub[0..j]，
        // 这要求str2[i] == s1sub[j]才有这种可能, 然后str2[0..i-1]变成s1sub[0..j-1]即可
        // 也就是str2[i] == s1sub[j] 的条件下，dp[i][j] = dp[i-1][j-1]
        dp[0][0] = str2[0] == s1Sub[0] ? 0 : Integer.MAX_VALUE;
        //1.第一行,str2[0] 无法通过删除行为变为s1Sub
        for (int i = 1; i < col; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        //2.初始化第一列，str2变为 s1Sub[0]，只要之前str2[0,i-1]中出现过一次匹配，后面就都是删除，
        for (int i = 1; i < row; i++) {
            dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || str2[i] == s1Sub[0]) ? i : Integer.MAX_VALUE;
        }
        //3.填写dp数组
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                //1.可能性一：dp[i - 1][j]可以转变，剩下的删除str2[i]字符
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                //2.当前str2[i] == s1sub[j] 的条件下，dp[i][j] = dp[i-1][j-1]
                if (str2[i] == s1Sub[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[row - 1][col - 1];
    }


    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "axbc";
        System.out.println(minCost1(s1, s2));
        System.out.println(minCost2(s1, s2));
    }
}
