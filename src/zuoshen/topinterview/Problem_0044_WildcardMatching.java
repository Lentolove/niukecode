package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/12 16:15
 * Desc :给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * <p>
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释:'*' 可以匹配任意字符串。
 * <p>
 * 示例 3:
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释:'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * <p>
 * 示例 4:
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释： 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * <p>
 * 示例 5:
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 * Related Topics 贪心 递归 字符串 动态规划 👍 831 👎 0
 */
public class Problem_0044_WildcardMatching {

    /**
     * 字符串匹配问题：s 只包含a-z，p包含 a-z,'?','*'
     * *  '?' 可以匹配任何单个字符。
     * * '*' 可以匹配任意字符串（包括空字符串）。
     * TODO 暴力递归转动态规划，然后斜率优化，哇哇哇，这可能是我写出的最6的代码了。。。。。。
     */
    public static boolean isMatch1(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null || p == null) return false;
        char[] str = s.toCharArray();
        char[] par = p.toCharArray();
        return process(str, par, 0, 0);
    }

    /**
     * 暴力递归套路：分情况讨论即可
     * 递归含义：str[si.....] 与 p[pi.....]完全匹配
     */
    public static boolean process(char[] str, char[] p, int si, int pi) {
        //1.当前si已经遍历完了
        if (si == str.length) {
            //pi也遍历完了
            if (pi == p.length) return true;
            //pi没有遍历完，那当前字符必须是 '*',匹配0个，后续继续去递归吧，当然，只有在后续全是 "***"这种才能完全匹配
            return (p[pi] == '*') && process(str, p, si, pi + 1);
        }
        //2.当前 pi 遍历完了
        if (pi == p.length) {
            return si == str.length;//只有si也遍历完了才满足
        }
        //3.str[si....] 与 p[pi....] 比较
        //p[pi]当前字符不是？和 *
        if (p[pi] != '?' && p[pi] != '*') {
            return (p[pi] == str[si]) && process(str, p, si + 1, pi + 1);
        }
        if (p[pi] == '?') {
            return process(str, p, si + 1, pi + 1);
        }
        //4.当前str[si] 是 * 了，分情况讨论 str[si...] = abcd , p[pi..] = *abcd, *bcd,*cd,*d,* 等才能匹配成功，
        //p[pi..] = **abcde不可能
        //str[si...]的后续匹配问题：len = str.length - si
        for (int len = 0; len <= str.length - si; len++) {
            //后续过程只要有一种情况匹配成功，就算成功,pi匹配str的si+len个字符
            if (process(str, p, si + len, pi + 1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 暴力递归改动态规划：两个可变参数 si,pi，构建二维表。
     * si[0,n],pi[0,m]
     */
    public static boolean isMatch2(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null || p == null) return false;
        char[] str = s.toCharArray();
        char[] par = p.toCharArray();
        int N = str.length;
        int M = par.length;
        //有暴力递归可知道si,pi能到N的，dp[i][j] 表示str[i...]与par[j...]是否完全匹配
        boolean[][] dp = new boolean[N + 1][M + 1];
        //对应 2.当前 pi 遍历完了
        dp[N][M] = true;
        //dp方向：从右往左，从下往上
        //初始化最后一行：str = "" 与 par[i...]是否匹配，只有par全是’*"才可能
        //对应： 1.当前si已经遍历完了
        for (int i = M - 1; i >= 0; i--) {
            if (par[i] == '*' && dp[N][i + 1]) {
                dp[N][i] = true;
            }
        }
        //开始填写表格了,普通位置怎么填写
        for (int i = N - 1; i >= 0; i--){
            for (int j = M - 1; j >= 0; j--){
                //3.str[si....] 与 p[pi....] 比较
                //p[pi]当前字符不是？和 *
//                if (p[pi] != '?' && p[pi] != '*') {
//                    return (p[pi] == str[si]) && process(str, p, si + 1, pi + 1);
//                }
//                if (p[pi] == '?') {
//                    return process(str, p, si + 1, pi + 1);
//                }
//                for (int len = 0; len < str.length - si; len++) {
//                    //后续过程只要有一种情况匹配成功，就算成功,pi匹配str的si+len个字符
//                    if (process(str, p, si + len, pi + 1)) {
//                        return true;
//                    }
//                }
                if (par[j] != '*' && par[j] != '?'){
                    dp[i][j] = (str[i] == par[j]) && dp[i + 1][j + 1];
                }else if (par[j] == '?'){
                    dp[i][j] = dp[i + 1][j + 1];
                }else {
                    //pi当前位置是 *
                    for (int len = 0; len <= N - i;len++){
                        if (dp[i + len][j + 1]) {
                            dp[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }
        //返回从0,0完全匹配
        return dp[0][0];
    }

    //--------------------------------优化递归--------------------------------------
    /**
     * 暴力递归改动态规划：两个可变参数 si,pi，构建二维表。
     * si[0,n],pi[0,m]
     */
    public static boolean isMatch3(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null || p == null) return false;
        char[] str = s.toCharArray();
        char[] par = p.toCharArray();
        int N = str.length;
        int M = par.length;
        //有暴力递归可知道si,pi能到N的，dp[i][j] 表示str[i...]与par[j...]是否完全匹配
        boolean[][] dp = new boolean[N + 1][M + 1];
        //对应 2.当前 pi 遍历完了
        dp[N][M] = true;
        //dp方向：从右往左，从下往上
        //初始化最后一行：str = "" 与 par[i...]是否匹配，只有par全是’*"才可能
        //对应： 1.当前si已经遍历完了
        for (int i = M - 1; i >= 0; i--) {
            if (par[i] == '*' && dp[N][i + 1]) {
                dp[N][i] = true;
            }
        }
        //开始填写表格了,普通位置怎么填写
        for (int i = N - 1; i >= 0; i--){
            for (int j = M - 1; j >= 0; j--){
                if (par[j] != '*'){
                    dp[i][j] = (str[i] == par[j] || par[j] == '?') && dp[i + 1][j + 1];
                } else {
                    //pi当前位置是 *
//                    for (int len = 0; len <= N - i;len++){
//                        if (dp[i + len][j + 1]) {
//                            dp[i][j] = true;
//                            break;
//                        }
//                    }
                    /**
                     * 优化过程思路： dp[2][3] = dp[2][4] | dp[3][4] | dp[4][4] | ....
                     * dp[1][3] = dp[1][4] | dp[2][4] | dp[3][4] | dp[4][4]....
                     * dp[1][3] = dp[1][4] + dp[2][3]
                     */
                    dp[i][j] = dp[i][j + 1] | dp[i+ 1][j];
                }
            }
        }
        //返回从0,0完全匹配
        return dp[0][0];
    }


    //--------------------------------优化递归--------------------------------------
    /**
     * 暴力递归改动态规划：两个可变参数 si,pi，构建二维表。
     * si[0,n],pi[0,m]
     */
    public static boolean isMatch4(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null || p == null) return false;
        char[] str = s.toCharArray();
        char[] par = p.toCharArray();
        int N = str.length;
        int M = par.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;
        for (int i = M - 1; i >= 0; i--) {
            dp[N][i] = par[i] == '*' && dp[N][i + 1];
        }
        for (int i = N - 1; i >= 0; i--){
            for (int j = M - 1; j >= 0; j--){
                if (par[j] != '*'){
                    dp[i][j] = (str[i] == par[j] || par[j] == '?') && dp[i + 1][j + 1];
                } else {
                    dp[i][j] = dp[i][j + 1] | dp[i+ 1][j];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        String s = "aa";
        String p = "*";
        System.out.println(isMatch1(s, p));
        System.out.println(isMatch2(s, p));
        System.out.println(isMatch3(s, p));
    }

}
