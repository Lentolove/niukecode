package zuoshen.interview.classic4;

/**
 * 左神：背包问题
 * 题目：背包容量为 w,一共有 n 袋零食，第 i 袋的零食体积为 v[i] > 0,
 * 总体积不超过背包容量的情况下，一共有多少种零食的放法？（总体积为0也算一种）
 */
public class Code02_SnacksWays {


    /**
     * 方法一：递归
     */
    public static int way1(int[] arr, int w) {
        return process(arr, 0, w);
    }

    /**
     * index 表示遍历到第 index 个零食
     * rest 表示剩余的背包容量
     */
    public static int process(int[] arr, int index, int rest) {
        //1.base case: 剩余的容量小于0，说明之前的方案有问题，导致剩余容量都为负数了，我们返回 -1 表示无方案的意思
        if (rest < 0) return -1;
        //rest>=0
        //2.当前index已经把零食数组遍历完了，说明无零食可选，此时方案结束，返回 1
        if (index == arr.length) return 1;
        //3.分两种情况，当前零食选与不选,index 号零食 要 or 不要
        int next1 = process(arr, index + 1, rest);//不要
        int next2 = process(arr, index + 1, rest - arr[index]);//要
        //4.返回两种方案的总和，注意无方案的情况
        return next1 + (next2 != -1 ? next2 : 0);
    }

    /**
     * 递归直接改动态规划
     * TODO
     */
    public static int way2(int[] arr,int w){
        return 0;
    }


    /**
     * 动态规划
     * dp[i][j] 表示前 i 个零食中，背包容量严格为 j 有多少种放法
     * dp[i][j] = dp[i - 1][j] + dp[i - 1][j - arr[i]](如果存在)
     */
    public static int way3(int[] arr, int w) {
        int n = arr.length;
        int[][] dp = new int[n][w + 1];//容量w,所以是w + 1
        //容量为0，初始化
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        //初始化第一行中，即index= 0，零食体积刚好为背包容量的位置，当只有一种
        if (arr[0] <= w) {
            dp[0][arr[0]] = 1;
        }
        //开始填充
        for (int i = 1; i < n; i++) {
            //当只有一种时候，只有 dp[i][0] = 1; 位置是1，因为需要满足背包容量严格相等的问题
            for (int j = 0; j <= w; j++) {
                dp[i][j] = dp[i - 1][j] + ((j >= arr[i]) ? dp[i - 1][j - arr[i]] : 0);
            }
        }
        //在把所有的情况加起来
        int ans = 0;
        for (int i = 0; i <= w; i++) {
            ans += dp[n - 1][i];
        }
        return ans;
    }


}
