package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/9 15:23
 * Desc : 正则表达式，两个字符串匹配问题
 */
public class Problem_0010_RegularExpressionMatching {

    /**
     * 递归思路，一定要一步一步分析
     */
    public static boolean isMatch1(String s, String p) {
        if (s == null || p == null) return false;
        char[] str = s.toCharArray();
        char[] par = p.toCharArray();
        return isValid(str, par) && process(str, par, 0, 0);
    }

    /**
     * 其中 si 表示以 si 开的字符 与 pi 开头的字符完全匹配
     * str[si.....] 能否被 pattern[pi...] 变出来
     * 潜台词：pi的位置不能是以 "*"开头，在处理过程中，要避免这个情况出现
     */
    private static boolean process(char[] str, char[] par, int si, int pi) {
        //1.si越界了
        if (si == str.length) {
            //情况1：pi也匹配完了，那说明匹配成功
            if (pi == par.length) return true;
            //情况2：s1到头了，pi 还未到边界，并且 p[pi + 1] = "*"，这样可以把当前位置的字符变为0个，
            //   par = "a*......",".*a*"这样才能将后续的所有字符变为0
            if (pi + 1 < par.length && par[pi + 1] == '*') {//只有这样才有可能匹配成功，后续过程继续
                return process(str, par, si, pi + 2);
            }
            return false;
        }
        //2.s1没越界，pi越界了
        if (pi == par.length) {
            //只有si也到头了才算匹配成功
            return false;
        }
        //3.si 没越界， pi 没越界
        //要避免 p1[i + 1] == "*" 要避免par单独以 "*"开头的情况
        //以下两种情况：1.当前pi来到了最后一个位置，那肯定没有下一个字符了，也就不存在 p1[i + 1] == "*"
        //2.每到最后位置，并且 p1[i + 1] != "*",那次是只有当前两个字符相匹配才可能继续
        if (pi + 1 >= par.length || par[pi + 1] != '*') {
            return (str[si] == par[pi] || par[pi] == '.') && process(str, par, si + 1, pi + 1);
        }
        //-------------这里开始处理 pi + 1 位置为 "*" -------------------------

        // si 没越界 pi 没越界 pi+1 * 该位置无法匹配
        if (par[pi] != '.' && str[si] != par[pi]) {
            return process(str, par, si, pi + 2);
        }

        //1."x*" 匹配 str 的 0 个字符，str[si....] 匹配 par[pi+2....]
        if (process(str, par, si, pi + 2)) return true;

        // 这个 "*" 匹配前面的多个，eg: "aaabcdf"
        // 可匹配的情况： "a*aaabcdf"(a*匹配0个)，"a*aabcdf"()，"a*abcdf"(a*匹配aa) "a*bcdf"(a*变成aaa)
        //所以需要尝试a*匹配多少个a
        while (si < str.length && (str[si] == par[pi] || par[pi] == '.')) {
            //从匹配0个到多个，一直尝试，直到找到一个为true就返回
            if (process(str, par, si + 1, pi + 2)) {
                return true;
            }
            //继续移动s1指针，看能否匹配到
            si++;
        }
        return false;
    }

    /**
     * 递归改动态规划
     * 1.改常规动态规划
     * 2.借助辅助数据，做记忆化搜索
     * 本体采用第二种：定义: int dp[s + 1][p + 1]
     * dp[i][j] 表示以 i,j为起始位置往后的匹配情况
     * dp[i][j] = -1 初始情况：表示该位置为尝试过
     * dp[i][j] = 0 表示该位置往后无法匹配成功
     * dp[i][j] = 1 表示该位置往后匹配成功
     */
    public static boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        int[][] dp = new int[str.length + 1][pattern.length + 1];
        for (int si = 0; si <= str.length; si++) {
            for (int pi = 0; pi <= pattern.length; pi++) {
                dp[si][pi] = -1;
            }
        }
        return isValid(str, pattern) && process2(str, pattern, 0, 0, dp);
    }

    /**
     * 记忆化搜索
     */
    private static boolean process2(char[] str, char[] par, int si, int pi, int[][] dp) {
        if (dp[si][pi] != -1) {
            return dp[si][pi] == 1;
        }
        //si pi 这个参数组合第一次算
        if (si == str.length) {
            //情况1：pi也匹配完了，那说明匹配成功
            if (pi == par.length) {
                dp[si][pi] = 1;
                return true;
            }
            //情况2：s1到头了，pi 还未到边界，并且 p[pi + 1] = "*"，这样可以把当前位置的字符变为0个，
            //   par = "a*......",".*a*"这样才能将后续的所有字符变为0
            if (pi + 1 < par.length && par[pi + 1] == '*') {//只有这样才有可能匹配成功，后续过程继续
                boolean ans = process2(str, par, si, pi + 2, dp);
                dp[si][pi] = ans ? 1 : 0;
                return ans;
            }
            dp[si][pi] = 0;
            return false;
        }
        //2.s1没越界，pi越界了
        if (pi == par.length) {
            boolean ans = si == str.length;
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }
        if (pi + 1 >= par.length || par[pi + 1] != '*') {
            boolean ans = (str[si] == par[pi] || par[pi] == '.') && process2(str, par, si + 1, pi + 1, dp);
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }

        // si 没越界 pi 没越界 pi+1 *
        if (par[pi] != '.' && str[si] != par[pi]) {
            boolean ans = process2(str, par, si, pi + 2, dp);
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }

        //3.到这里还没返回，就说明 par[i + 1] 这个位置是 "*"
        //1."x*" 匹配 str 的 0 个字符，str[si....] 匹配 par[pi+2....]
        if (process2(str, par, si, pi + 2, dp)) {
            dp[si][pi] = 1;
            return true;
        }
        // 这个 "*" 匹配前面的多个，eg: "aaabcdf"
        // 可匹配的情况： "a*aaabcdf"(a*匹配0个)，"a*aabcdf"()，"a*abcdf"(a*匹配aa) "a*bcdf"(a*变成aaa)
        //所以需要尝试a*匹配多少个a
        while (si < str.length && (str[si] == par[pi] || par[pi] == '.')) {
            //从匹配0个到多个，一直尝试，直到找到一个为true就返回
            if (process2(str, par, si + 1, pi + 2, dp)) {
                dp[si][pi] = 1;
                return true;
            }
            //继续移动s1指针，看能否匹配到
            si++;
        }
        dp[si][pi] = 0;
        return false;
    }

    /**
     * 检验输入的合法性
     */
    private static boolean isValid(char[] str, char[] p) {
        //1.检测s中是否包含'.','*' 肯定不满足
        for (char c : str) {
            if (c == '.' || c == '*') return false;
        }
        //2.检测p串中是否有连续的"**"出现，或者第一个字符就是*
        for (int i = 0; i < p.length; i++) {
            if (p[i] == '*' && (i == 0 || p[i - 1] == '*')) return false;
        }
        return true;
    }


    public static boolean isMatch4(String s, String p) {
        if (s == null || p == null) return false;
        char[] str = s.toCharArray();
        char[] par = p.toCharArray();
        if (!isValid(str, par)) return false;
        int N = str.length;
        int M = par.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        return false;

    }


    public static void main(String[] args) {
        String s = "aab";
        String p = "a*aab";
        System.out.println(isMatch1(s, p));
        System.out.println(isMatch2(s, p));
    }


}
