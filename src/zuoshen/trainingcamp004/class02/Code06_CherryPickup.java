package zuoshen.trainingcamp004.class02;

/**
 * Author : tsp
 * Time: 2022/3/1 20:58
 * Desc : 给定一个
 */
public class Code06_CherryPickup {

    /**
     * 从matrix左上角，走到右下角，过程中只能向右或者向下
     * 到达后，回来，过程中只能向左或者向上，沿途数字只能获得一遍
     * 返回，最大路径和
     */
    public static int comeGoMaxPathSum(int[][] m) {
        return process(m, m.length, m[0].length, 0, 0, 0);
    }

    /**
     * 思路：用两个小人一起行走，它们走的步骤一定是一样的，且只能向右和向下，
     * 假定 matrix 中没有负数
     * 1.其中 Ar 和 Ac 表示 A当前来到的位置，则走了 Ar + Ac 步
     * 2.B 当前来到的位置为 Br,则列可以算出来 ： Ar + Ac - Br(我两走的步骤一样)
     * 3.两个人会一起来到右下角，返回两个人路径的最大累加和，如果两个人来到同一个位置，则只能算一份
     */
    public static int process(int[][] m, int N, int M, int Ar, int Ac, int Br) {
        //1.A 和 B 来到右下角，A来到了，则B一定来到了
        if (Ar == N - 1 && Ac == M - 1) {
            return m[N - 1][M - 1];
        }
        //2.还没到右下角，分四种情况讨论,A 和 B 的下一步走向
        // A 右 B 右
        // A 右 B 下
        // A 下 B 右
        // A 下 B 下
        int Bc = Ac + Ar - Br;//B当前的位置
        int ARightBRight = -1;
        if (Ac + 1 < M && Bc + 1 < M) {
            ARightBRight = process(m, N, M, Ar, Ac + 1, Br);
        }
        int ARightBDown = -1;
        if (Ac + 1 < M && Br + 1 < N) {
            ARightBDown = process(m, N, M, Ar, Ac + 1, Br + 1);
        }
        int ADownBRight = -1;
        if (Ar + 1 < N && Bc + 1 < M) {
            ADownBRight = process(m, N, M, Ar + 1, Ac, Br);
        }
        int ADownBDown = -1;
        if (Ar + 1 < N && Br + 1 < N) {
            ADownBDown = process(m, N, M, Ar + 1, Br, Br + 1);
        }
        //从中选择后续最好的路径总和
        int nextBest = Math.max(Math.max(ARightBRight, ARightBDown), Math.max(ADownBRight, ADownBDown));
        //当前情况，如果A和B在同一个位置,那么只需要Ar = Br就可以
        if (Ar == Br) {
            //一定在同一个位置,只取一份
            return nextBest + m[Ar][Ac];
        }
        //不在同一个位置
        return m[Ar][Ac] + m[Br][Bc] + nextBest;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 } };
        System.out.println(comeGoMaxPathSum(matrix));

    }


}
