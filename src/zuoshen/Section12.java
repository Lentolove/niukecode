package zuoshen;

public class Section12 {


    public static void main(String[] args) {
        System.out.println(walk(7, 4, 9, 5));
        System.out.println(walkDp(7, 4, 9, 5));
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

}
