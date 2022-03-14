package zuoshen;

import java.util.Arrays;

public class Section12 {


    public static void main(String[] args) {
//        System.out.println(walk(7, 4, 9, 5));
//        System.out.println(walkDp(7, 4, 9, 5));


        //硬币数量
        int[] arr = {1,2,5};
        System.out.println(minCoin1(arr,11));
        System.out.println(minCoin2Dp(arr,11));
        System.out.println(minCoin3Dp(arr,11));
    }

    /*****************机器人到达指定位置的方法数*************************/

    /**
     * 左神：机器人到达指定位置的方法数
     * 方法一：递归暴力求解
     *
     * @param N 表示1-N个位置
     * @param M 表示当前起始位置
     * @param K 表示机器人可以走几步
     * @param P 最终到达哪一步
     * @return 一共有多少种走法
     */
    public static int way(int N, int M, int K, int P) {
        //去掉无效输入
        if (N < 2 || K < 1 || M < 1 || M > N || P > N || P < 1) return 0;
        return walk(N, M, K, P);
    }

    /**
     * @param N   表示1-N个位置  ： 固定参数
     * @param cur 机器人当前位置  ： 可变参数
     * @param res 还可以走多少步  ： 可变参数
     * @param P   最终需要停在的位置 ：固定参数
     */
    public static int walk(int N, int cur, int res, int P) {
        //1.如果步数走完，当前位置是否是P位置,在P上，则该次尝试有效，否则无效
        if (res == 0) return cur == P ? 1 : 0;
        //2.当前走到1位置，只能往 2 走
        if (cur == 1) return walk(N, 2, res - 1, P);
        //3.当前位置在N处，则只能往N-1走
        if (cur == N) return walk(N, N - 1, res - 1, P);
        //4.处在中间位置，则两边都可以走
        return walk(N, cur + 1, res - 1, P) + walk(N, cur - 1, res - 1, P);
    }


    public static int walkDp(int N, int M, int K, int P) {
        if (N < 2 || K < 1 || M < 1 || M > N || P > N || P < 1) return 0;
        //根据可变参数可知，只有cur和res参数可变，其它固定值对参数无关，dp[i][j] 纵轴 i 表示走的步数，横轴 j 表示当前位置
        int[][] dp = new int[K + 1][N + 1];
        //1.初始化base,第一行，即步数为0，只有当前位置 j == p 的时候该位置是有效的
        dp[0][P] = 1;
        //2.由暴力递归可知，当cur为1时候，它的位置只能由cur = 2跳过来，当cur = N的时候，它的位置只能有cur = N-1位置跳过来
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                //1.如果当前位置在第一列，及cur = 1,之前的步数是 i - 1;
                if (j == 1) {
                    dp[i][j] = dp[i - 1][2];
                } else if (j == N) {
                    //2.如果cur = N，那么它只能有N-1跳过来，之前的步数是 i - 1
                    dp[i][j] = dp[i - 1][N - 1];
                } else {
                    //3.cur位于中间，可以从两边跳过来,那么之前的步数是 i - 1
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        //移动走K步，起始位置是M有多少种方法。
        return dp[K][M];
    }


    /**********************跳跃游戏************************/

    /**
     * 左神：跳跃到目的点的最少步数
     * 定义三个变量，jump 表示跳跃次数，cur 表示当前到的距离，next 表示如果在此位置多跳一步能达到的最远距离
     */
    public static int jump(int[] arr) {
        int jump = 0, cur = 0, next = 0;
        for (int i = 0; i < arr.length; i++) {
            //1.如果当前位置小于 i ，说明我必须跳一步了
            if (cur < i) {
                jump++;
                //next记录是的
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return jump;
    }


    /*********************换钱的最少货币数量********************************/

    /**
     * 左神：换钱的最少货币数量
     *
     * @param arr    不同面值的货币，数量不限
     * @param target 目标要凑的金额
     * @return 返回需要的最少的货币数量
     */
    public static int minCoin1(int[] arr, int target) {
        if (arr == null || arr.length == 0 || target < 0) return -1;
        return process1(arr, 0, target);
    }

    /**
     * @param i 表示当前换哪种硬币
     */
    public static int process1(int[] arr, int i, int target) {
        //1.已经没有可选的面值了，i 已经来到 arr 的末尾
        if (i == arr.length) {
            //如果此时 target 为 0，说明刚好凑齐，返回 0 张, 否则返回 - 1
            return target == 0 ? 0 : -1;
        }
        //最少张数，初始为 - 1,
        int res = -1;
        //依次尝试换取当前货币i的张数，直到超过了目标值
        for (int k = 0; k * arr[i] <= target; k++) {
            //1.选择了 k 张 arr[i] 面值的货币，则还需要 target - k * arr[i]
            //2.剩下的从 i + 1位置以后去选择货币,后续返回我 nextCount 货币数量，如果为 - 1，说明当前选择无效
            int nextCount = process1(arr, i + 1, target - k * arr[i]);
            if (nextCount != -1) {
                //说明当前选择有效,货币数量为 nextCount + k;
                int curCount = nextCount + k;
                if (res == -1) res = curCount;
                res = Math.min(res, curCount);
            }
        }
        return res;
    }

    public static int minCoin3Dp(int[] arr,int target){
        int n = arr.length;
        int[] dp = new int[target + 1];
        Arrays.fill(dp,target + 1);
        dp[0] = 0;
        for (int i = 1; i <= target; i++){
            for (int k : arr) {
                if (i >= k) {
                    //可以选择当前硬币
                    dp[i] = Math.min(dp[i], dp[i - k] + 1);
                }
            }
        }
        return dp[target];
    }



    public static int minCoin2Dp(int[] arr, int target) {
        if (arr == null || arr.length == 0 || target < 0) return -1;
        int n = arr.length;
        int[][] dp = new int[n + 1][target + 1];
        //设置最后一排的初始值,因为最后一排，是不存在这样的硬币,只有target为0的时候才满足情况dp[n][0] = 0;
        for (int col = 1; col <= target; col++) {
            dp[n][col] = -1;
        }
        //由递归方法可知，由底层向上推
        for (int i = n - 1; i >= 0; i--) {//从低往上计算每一行
            for (int rest = 0; rest <= target; rest++) {
                //初始值先设置 dp[i][rest] = - 1，表示无效
                dp[i][rest] = -1;
                if (dp[i + 1][rest] != -1) {
                    //下一层能凑到 rest 面值方案存在，那么可以直接继承过来该方案，再去判断是否有更优解
                    dp[i][rest] = dp[i + 1][rest];
                }
                //如果左边的位置不越界，且有效
                if (rest - arr[i] >= 0 && dp[i][rest - arr[i]] != -1) {
                    if (dp[i][rest] == -1) {//之前值无效
                        dp[i][rest] = dp[i][rest - arr[i]] + 1;
                    } else {
                        dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - arr[i]] + 1);
                    }
                }
            }
        }
        return dp[0][target];
    }


}
