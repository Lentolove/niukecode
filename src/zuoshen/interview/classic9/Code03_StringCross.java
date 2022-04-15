package zuoshen.interview.classic9;

/**
 * 左神：给定三个字符串 str1,str2 和 aim，如果aim包涵且仅仅包涵来自 str1 和 str2 的所有字符，
 * 而且在 aim 中属于 str1 和 str2 的字符保持原来的顺序，那么称 str1 和 str2 的交错组成。请实现一个函数，
 * 判断 aim 是否是 str1 和 str2 交错组成
 * eg: str1 = "AB", str2 = "12"，那么 "AB12","A1B2","A12B","1A2B"和 "1AB2"等都是 str1 和 str2 的交错组成
 */
public class Code03_StringCross {

    /**
     * 思路：本质上还是动态规划，二维的dp数组分别表示str1的长度和str2的长度
     * 1.定义 dp[i][j] 表示 str1[0,i - 1] 和 str2[0,j - 1] 结尾的字符能交错成 aim[i + j - 1]
     * 2.填写二维 dp 数组，初始化第一行和第一列:
     * 3.分情况：
     * 1.如果 str1[i - 1] 与  aim[i + j - 1] 字符相等，则 dp[i][j] = 前提条件 && dp[i - 1][j]
     * 2.如果 str2[j - 1] 与  aim[i + j - 1] 字符相等，则 dp[i][j] = 前提条件 && dp[i][j - 1]
     */
    public static boolean isCross(String s1, String s2, String aim) {
        if (s1 == null || s2 == null || aim == null) return false;
        if (s1.length() + s2.length() != aim.length()) return false;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] strAim = aim.toCharArray();
        int n = str1.length;
        int m = str2.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;//从str1中长度为0和str2长度为0构成aim长度为0
        //1.初始化第一行
        for (int i = 1; i <= m; i++) {
            if (str2[i - 1] != strAim[i - 1]) {
                break;
            } else {
                dp[0][i] = true;
            }

        }
        //2.初始化第一列
        for (int i = 1; i <= n; i++) {
            if (str1[i - 1] != strAim[i - 1]) {
                break;
            } else {
                dp[i][0] = true;
            }

        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (
                        (str1[i - 1] == strAim[i + j - 1] && dp[i - 1][j]) ||
                                (str2[j - 1] == strAim[i + j - 1] && dp[i][j - 1])
                ) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[n][m];
    }


    public static boolean isCross2(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null) {
            return false;
        }
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        char[] chaim = aim.toCharArray();
        if (chaim.length != ch1.length + ch2.length) {
            return false;
        }
        char[] longs = ch1.length >= ch2.length ? ch1 : ch2;
        char[] shorts = ch1.length < ch2.length ? ch1 : ch2;
        boolean[] dp = new boolean[shorts.length + 1];
        dp[0] = true;
        for (int i = 1; i <= shorts.length; i++) {
            if (shorts[i - 1] != chaim[i - 1]) {
                break;
            }
            dp[i] = true;
        }
        for (int i = 1; i <= longs.length; i++) {
            dp[0] = dp[0] && longs[i - 1] == chaim[i - 1];
            for (int j = 1; j <= shorts.length; j++) {
                if ((longs[i - 1] == chaim[i + j - 1] && dp[j]) || (shorts[j - 1] == chaim[i + j - 1] && dp[j - 1])) {
                    dp[j] = true;
                } else {
                    dp[j] = false;
                }
            }
        }
        return dp[shorts.length];
    }

    public static void main(String[] args) {
        String str1 = "1234";
        String str2 = "abcd";
        String aim = "1a23bcd4";
        System.out.println(isCross(str1, str2, aim));
        System.out.println(isCross2(str1, str2, aim));

    }

}
