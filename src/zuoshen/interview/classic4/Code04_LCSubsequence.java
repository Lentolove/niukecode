package zuoshen.interview.classic4;

/**
 * 左神：求两个字符串的最长公共子序列
 * 子序列：不要求连续
 * 子串：连续
 */
public class Code04_LCSubsequence {

    /**
     * 回溯法1.0
     */
    public static int lcs(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        return process(str1, str2, n - 1, m - 2);
    }

     //str1[0....i1] 与 str2[0......i2]的最长公共子序列长度是多少？
    public static int process(char[] str1, char[] str2, int i1, int i2) {
        //base case:只剩一个字符
        if (i1 == 0 && i2 == 0) return str1[i1] == str2[i2] ? 1 : 0;
        //1. i1 和 i2 不同时为0
        if (i1 == 0) {// str1[0] 与 str2[0,i2] 能否找到一个匹配的
            //2. 两种情况，要么和str2[i2] 位置相同，要么继续比较下一个位置能匹配到
            return ((str1[i1] == str2[i2]) || process(str1, str2, i1, i2 - 1) == 1) ? 1 : 0;
        }
        if (i2 == 0) {
            return ((str1[i1] == str2[i2]) || process(str1, str2, i1 - 1, i2) == 1) ? 1 : 0;
        }
        //3. i1 和 i2 都不为 0
        // 情况一  p1 之前的最长公共子序列,不一 i,j 结尾的情况
        // 情况二  以 i1 结尾，不以 i2 结尾，或者以 i2 结尾，不以 i1 结尾
        // 情况4   以 i1 和 i2 结尾，并且两个相等： p4 = process(str1,str2,i1 - 1,i2 - 1) + 1
        int p1 = process(str1, str2, i1 - 1, i2 - 1);
        int p2 = process(str1, str2, i1, i2 - 1);
        int p3 = process(str1, str2, i1 - 1, i2);
        int p4 = 0;
        if (str1[i1] == str2[i2]) {
            p4 = p1 + 1;
        }
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    /**
     * 回溯法 2.0
     */
    public static int lcs1(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return process2(str1, str2, 0, 0);
    }

    public static int process2(char[] str1, char[] str2, int i1, int i2) {
        if (i1 == str1.length || i2 == str2.length) return 0;
        if (str1[i1] == str2[i2]) {
            return 1 + process2(str1, str2, i1 + 1, i2 + 1);
        }
        //当前位置不相等
        return Math.max(process2(str1, str2, i1 + 1, i2), process2(str1, str2, i1, i2 + 1));
    }


    public static int lcs2(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        int[][] dp = new int[n][m];
        //初始化dp数组
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[0][i] = (str1[0] == str2[i]) ? 1 : dp[0][i - 1];
        }
        //初始化第一列
        for (int i = 1; i < n; i++) {
            dp[i][0] = (str1[i] == str2[0]) ? 1 : dp[i - 1][0];
        }
        //填dp
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                //以str1[0,i]与str2[0,j] 的公共子序列:
                // 这里一定要注意： str1[0,i-1],str2[0,j-1] 一定是 [i - 1][j] 与 dp[i][j - 1] 的子集
                //所以这里可以省略：dp[i][j] = dp[i-1][j-1],再去和其他情况做比较
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                //1.判断当前元素的字符是否相同
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[n - 1][m - 1];
    }


    public static void main(String[] args) {
        String str1 = "A1BC23Z4";
        String str2 = "12O3YU4P";
        System.out.println(lcs(str1, str2));
        System.out.println(lcs1(str1, str2));
        System.out.println(lcs2(str1, str2));
    }

}
