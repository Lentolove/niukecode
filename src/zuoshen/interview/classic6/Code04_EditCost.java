package zuoshen.interview.classic6;

/**
 * 左神：最小编辑距离
 * 题目：给定两个字符str1和str2，再给定是哪个整数ic,dc和rc,分别代表插入，删除和替换一个字符的代价
 * 返回str1编辑成str2的最小代价
 */
public class Code04_EditCost {

    /**
     * 左神：str1编辑成str2的最小代价
     */
    public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) return 0;
        int n = s1.length() + 1;
        int m = s2.length() + 1;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        //n,m表示的是长度
        int[][] dp = new int[n][m];//dp[i][j]表示str1长度为i和str2长度为j两个字符的最小编辑距离
        //1.初始化第一行
        for (int i = 1; i < m; i++) {
            //str1长度为0,变成str2长度为i的编辑距离,那需要对str1进行插入操作
            dp[0][i] = i * rc;
        }
        //2.初始化第一列
        for (int i = 1; i < n; i++) {
            //str1长度为i,变成str2长度为0，通过删除操作
            dp[i][0] = i * dc;
        }
        //3.填表
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // 1.str1[i-1] == str2[j-1](i,j表示长度),不操作 dp[i][j] = dp[i-1][j-1]
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //2.1.str1[i-1] ！= str2[j-1],执行替换操作
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                //3.str1[0,i-1]与str2[0,j-2]匹配，需要为str1插入一个字符与str2[j-1]匹配
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                //3.str1[0,i-2]与str2[0,j-1]匹配，需要将str1[i-1]位置字符删除
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[n - 1][m - 1];
    }

    /**
     * 空间压缩
     * 左神：对二维dp进行压缩，我们选择较短的字符作为dp长度，在脑海中去更新二维dp
     * 1.如果str1.length < str2.length，就变成将str2编辑成str1的最小编辑代价，对应的ic和rc值需要互换。
     * 2.是将长字符编辑成短字符
     */
    public static int minCost2(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) return 0;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] longChars = str1.length >= str2.length ? str1 : str2;
        char[] shortChars = str1.length < str2.length ? str1 : str2;
        //1.如果str1.length < str2.length，则就选择将str2编辑成str1，对应的插入和删除操作的值需要互换
        //因为题目要求的是str1编辑成str2的代价，而替换操作是等价的。这样可以节省dp空间
        if (str1.length < str2.length) {
            int temp = dc;
            dc = ic;
            ic = temp;
        }
        //定义一维dp数组
        int n = longChars.length + 1;// 二维dp的行
        int m = shortChars.length + 1;//二维dp的列
        int[] dp = new int[m];
        //初始化第一行，dp[0][i]，需要将longChars[0]个字符编辑成 shortChars[i]，那都需要插入操作
        for (int i = 1; i < m; i++) {
            dp[i] = ic * i;
        }
        //开始填表，这个二维表在脑海里，由一维dp从上往下走，填完整个二维表
        for (int i = 1; i < n; i++) {
            //记录左上角的值
            int pre = dp[0];
            //左边的值即 dp[i][0]表示需要将longChars[i]个字符编辑成shortChars[0]个字符的代价,那肯定是删除操作啦
            dp[0] = dc * i;
            //开始填表,填写当前行
            for (int j = 1; j < m; j++) {//注意：i,j都是表示长度的，取字符对应索引
                //记录dp[j] 位置的值，用于更新
                int temp = dp[j];
                //1.当前位置longChars[i-1]与shortChars[j-1]相等
                if (longChars[i - 1] == shortChars[j - 1]) {
                    //等于左上角
                    dp[j] = pre;
                } else {
                    //不相等,dp[i][j] = dp[i-1][j-1] + rc,最后一个字符执行替换操作
                    dp[j] = pre + rc;
                }
                //3.str1[0,i-1]与str2[0,j-2]匹配，需要为str1插入一个字符与str2[j-1]匹配
//                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                dp[j] = Math.min(dp[j], dp[j - 1] + ic);//上方

                //3.str1[0,i-2]与str2[0,j-1]匹配，需要将str1[i-1]位置字符删除
//                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
                dp[j] = Math.min(dp[j], temp + dc);
                //dp往右遍历，更新pre的值
                pre = temp;
            }
        }
        return dp[m - 1];
    }


    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(minCost1(str1, str2, 5, 3, 2));
        System.out.println(minCost2(str1, str2, 5, 3, 2));
    }


}



