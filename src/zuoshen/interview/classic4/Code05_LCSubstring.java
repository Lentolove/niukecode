package zuoshen.interview.classic4;

/**
 * 左神：求两个字符串 str1 与 str2 的最长公共子串
 * 子串：连续
 */
public class Code05_LCSubstring {

    /**
     * 最长公共子串问题,区别：dp[i][j] 必须是 str1 以 i 位置结尾， str2以 j 位置结尾
     */
    public static String lcs1(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        //分别计算以每个[i][j]位置结尾的最长公共子串
        int[][] dp = new int[n][m];
        //1.初始化第一行
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < m; i++) {
            //str1[0] 与 str2[i] 相同的位置为1
            if (str1[0] == str2[i]) {
                dp[0][i] = 1;
            }
        }
        //2.初始化第一列
        for (int i = 1; i < n; i++) {
            if (str1[i] == str2[0]) {
                dp[i][0] = 1;
            }
        }
        //3.开始填充数组，记录最大值，记录结束位置
        int max = 0;
        int end = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        end = i;
                    }
                }
            }
        }
        return String.copyValueOf(str1, end - max + 1, max);
    }


    /**
     * 动态规划二：脑海里构建一张二维表。从右上角开始斜向遍历 \
     * 1.因为只有当以 [i,j] 结尾：str2[i] = str2[j] 时候，往前找 str1[i-1] , str2[j-1]
     * 往后找 str1[i+1] , str2[j+1].
     * 2. 以为要找最长子串，所有可能的情况一定是验证\方向去递增的。
     */
    public static String lcs2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) return "";
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        //1.初始位置,在脑海中去填这张表
        int row = 0;// 出发点的行号
        int col = m - 1;// 出发的列号
        int max = 0;//记录最长子串长度
        int end = 0;//记录结尾的字符索引位置
        while (row < n) {  //2.\ 遍历，左上角开始
            int i = row;
            int j = col;
            int len = 0;
            while (i < n && j < m) {
                if (str1[i] != str2[j]) {
                    len = 0;//以当前字符即为的最长公共子串为0
                } else {
                    len++;
                }
                //更新最值
                if (len > max) {
                    max = len;
                    end = i;
                }
                i++;
                j++;
            }
            //遍历 \ 向左移动，如果到达[0,0]位置，row开始向下移动，否则col向左移动
            if (col > 0) {
                col--;
            } else {
                row++;
            }
        }
        return String.copyValueOf(str1, end - max + 1, max);
    }


    public static void main(String[] args) {
        String str1 = "ABC1234567DEFG";
        String str2 = "HIJKL1234567MNOP";
        System.out.println(lcs1(str1, str2));
        System.out.println(lcs2(str1, str2));
    }

}
