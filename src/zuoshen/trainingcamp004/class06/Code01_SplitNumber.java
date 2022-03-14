package zuoshen.trainingcamp004.class06;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : tsp
 * Time: 2022/3/8 10:15
 * Desc :
 * 给定一个整数 n,将其分割成子序列，eg:{1,1,1,1},{1,1,2},{1,3},{2,2},{4},
 * 返回分割方案数
 * <p>
 * 思路：递归转动态规划，斜率优化
 */
public class Code01_SplitNumber {

    /**
     * 递归思路,定义函数f(pre,rest),pre表示之前分割的结尾的大小，后续的分割数rest需要大于等于pre才算有效方案
     */
    public static int way1(int n) {
        return process(1, n);
    }

    public static int process(int pre, int rest) {
        //base 分割完成
        if (rest == 0) return 1;
        //后续可分割的数值已经小于pre了，该方案不可行
        if (pre > rest) return 0;
        int ans = 0;
        for (int i = pre; i <= rest; i++) {
            ans += process(i, rest - i);
        }
        return ans;
    }

    public static List<List<Integer>> getWay(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        process1(ans, new ArrayList<>(), 1, n);
        System.out.println(ans.size());
        return ans;
    }

    //xxxxxx
    public static void process1(List<List<Integer>> ans, List<Integer> item, int pre, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<>(item));
            return;
        }
        if (pre > rest) {
            item.remove(item.size() - 1);
            return;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = pre; i <= rest; i++) {
            list.add(i);
            process1(ans, list, i, rest - i);
        }
    }

    /**
     * ☆☆☆
     * dp由下向上，由左向右
     * dp[3][8] = dp[3][5] + dp[4][4] + dp[5][3] + dp[6][2] + dp[7][1] + dp[8][0]
     * dp[4][8] = dp[4][4] + dp[5][3] + dp[6][2] + dp[7][1] + dp[8][0]
     * dp[3][8] = dp[4][8] + dp[3][5]
     * dp[i][j] = dp[i + 1][j] + dp[i][j - i]
     * 通过观察dp数组依赖问题，可知 dp[i][j] 依赖它的下方位置和左边的位置，dp推到维度由下往上，由左往右
     */
    public static int wayDp(int n) {
        if (n < 1) return 0;
        //dp[i][j] 表示当前分割结尾为 i,剩余待分割的值为 j
        //第一行不用
        int[][] dp = new int[n + 1][n + 1];
        //初始化第一列，当前 j 为0，都是满足条件的
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
        }
        for (int pre = n; pre >= 1; pre--) {
            for (int rest = pre; rest <= n; rest++) {
                //前驱值为 pre，待分割结果为 rest,有多少种情况
                int ways = 0;
                for (int i = pre; i <= rest; i++) { // i : rest第一个裂开的部分，值是多少
                    ways += dp[i][rest - i];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    /**
     * dp 观察，根据依赖关系，进一步推到
     */
    public static int wapDp2(int n) {
        if (n < 1) return 0;
        int[][] dp = new int[n + 1][n + 1];
        //初始化pre为1,
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
        }
        //初始化斜对角线
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][pre] = 1;
        }
        //其他位置
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }


    public static void main(String[] args) {
//        System.out.println(way1(4));
//        System.out.println(wayDp(4));
        System.out.println(wapDp2(4));
    }


}
