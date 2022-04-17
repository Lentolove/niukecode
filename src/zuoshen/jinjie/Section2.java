package zuoshen.jinjie;

/**
 * 类似斐波那契数列的递归
 */
public class Section2 {

    public static void main(String[] args) {
//        System.out.println(horseJump(4, 5, 3));

//        System.out.println(fb1(8));

//        System.out.println(fb2(6));

        System.out.println(quickMi(3,8));
        System.out.println(quickPow(3,8));

//        System.out.println(fb2(8));
//        System.out.println(fb3(8));
//        System.out.println(horseJump2(4, 5, 3));
    }


    /**********1.马的跳跃问题***********/

    /**
     * 左神：马跳跃问题方法数量
     * 递归方法：
     * 1.象棋棋盘是 10 * 9, 10行9列,注意边界问题，一共走 k 步
     * 2.马儿从(0,0)位置出发
     */
    public static int horseJump(int x, int y, int k) {
        return f(x, y, k);
    }

    public static int f(int x, int y, int k) {
        //1.base case1: 当前只能走0步，只有 (0,0)位置才满足条件
        if (k == 0) return x == 0 && y == 0 ? 1 : 0;
        //2.判断是否越界
        if (x < 0 || x > 9 || y < 0 || y > 8) return 0;
        //3.马儿要跳到(x,y)位置，跳了k步，那只能从以(x,y)为中心的附近8个位置，k-1步跳过来
        return f(x + 2, y + 1, k - 1) +
                f(x + 1, y + 2, k - 1) +
                f(x - 1, y + 2, k - 1) +
                f(x - 2, y + 1, k - 1) +
                f(x - 2, y - 1, k - 1) +
                f(x - 1, y - 2, k - 1) +
                f(x + 1, y - 2, k - 1) +
                f(x + 2, y - 1, k - 1);
    }

    /**
     * 动态规划：
     * 1.三个可变元素，则定义三维dp数组
     * 2.分别在x,y,z轴三分方向上。
     */
    public static int horseJump2(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];
        //1.初始状态只有[0][0][0]位置为1，起始位置马儿在(0,0)位置，跳0步
        dp[0][0][0] = 1;
        //2.从递归函数可知，每一层至于下一层有关，与(x,y)的位置依赖关系
        for (int level = 1; level <= k; level++) {
            //(x,y)位置从哪个方向都可以
            for (int i = 0; i < 10; i++) {//表示 行 位置
                for (int j = 0; j < 9; j++) { //表示 列 轴的位置
                    dp[i][j][level] =
                            getValue(dp, i + 2, j + 1, level - 1) +
                                    getValue(dp, i + 1, j + 2, level - 1) +
                                    getValue(dp, i - 1, j + 2, level - 1) +
                                    getValue(dp, i - 2, j + 1, level - 1) +
                                    getValue(dp, i - 2, j - 1, level - 1) +
                                    getValue(dp, i - 1, j - 2, level - 1) +
                                    getValue(dp, i + 1, j - 2, level - 1) +
                                    getValue(dp, i + 2, j - 1, level - 1);
                }
            }
        }
        return dp[x][y][k];
    }

    //越界取值越界判断
    public static int getValue(int[][][] dp, int x, int y, int k) {
        if (x < 0 || x > 9 || y < 0 || y > 8) return 0;
        return dp[x][y][k];
    }


    /**********2.斐波那契问题***********/

    /**
     * 斐波那契问题：递归解法
     * 时间复杂度：2^n
     */
    public static int fb1(int n) {
        if (n < 1) return 0;
        if (n == 1 || n == 2) return 1;
        return fb1(n - 1) + fb1(n - 2);
    }

    /**
     * 暴力递归改动态规划，一个可变参数，对应一维dp表
     * 动态规划的过程，本质上就是填写 dp 表的过程
     * 根据递推公式，找到 dp 表的以来关系，将 dp 表填写完成
     */
    public static int dp(int n) {
        if (n <= 0) return 0;
        if (n <= 2) return 1;
        //1.有暴力递归可知，可变参数取值为[0,n]
        int[] dp = new int[n + 1];
        //2.下面来填写dp表，先填写初始位置，对应暴力递归的：if (n == 1 || n == 2) return 1;
        dp[1] = 1;
        dp[2] = 1;
        //3.再填写普遍位置
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    /**
     * 动态规划2.0版本：空间压缩
     */
    public static int dp2(int n) {
        if (n <= 0) return 0;
        if (n <= 2) return 1;
        int dpi_1 = 1;
        int dpi_2 = 1;
        for (int i = 3; i <= n; i++) {
            int temp = dpi_1 + dpi_2;
            dpi_1 = dpi_2;
            dpi_2 = temp;
        }
        return dpi_1;
    }


    /**
     * 动态规划解法
     * 时间复杂度：O(n)
     */
    public static int fb2(int n) {
        if (n < 1) return 0;
        if (n == 1 || n == 2) return 1;
        int one = 1;
        int two = 1;
        for (int i = 3; i <= n; i++) {
            int temp = one + two;
            one = two;
            two = temp;
        }
        return two;
    }


    public static int quickMi(int x,int n){
        //未做输入参数检验
        int sum = 1;
        while (n != 0){
            sum *= x;
            n--;
        }
        return sum;
    }


    public static int quickPow(int x,int n){
        int sum = 1;
        while (n != 0){
            //二进制中最后一个位置是否为1，是1就乘上去，是 0 就没必要
            if ((n & 1) == 1){
                sum *= x;
            }
            //n无符号右移
            n >>>= 1;
            x *= x;
        }
        return sum;
    }


    /**
     * 斐波那契数列 logn 解法
     * 二阶递推：|a,b|
     *         |c,d|
     */
    public static int fb3(int n) {
        if (n < 1) return 0;
        if (n == 1 || n == 2) return 1;
        //1.定义二阶矩阵,通过手动计算出来
        int[][] base = {{1, 1}, {1, 0}};
        //2.求出 base^(n-2)次方
        int[][] res = matrixPower(base, n - 2);
        //3. |f(n),f(n-1)|=|f(2),f(1)|* res = a * f(2) + c * f(1);
        return res[0][0] + res[1][0];
    }

    /**
     * 矩阵的n次方，快速幂的方法
     */
    public static int[][] matrixPower(int[][] m, int n) {
        int[][] res = new int[m.length][m[0].length];
        //1.构造单位矩阵
        for (int i = 0; i < m.length; i++) {
            res[i][i] = 1;
        }
        //2.快速幂求矩阵的n次方
        int[][] temp = m;
        for (; n != 0; n >>= 1) {
            //3.如果当前二进制位是1，则相乘
            if ((n & 1) != 0) {
                res = multiMatrix(res, temp);
            }
            temp = multiMatrix(temp, temp);
        }
        return res;
    }

    /**
     * 定义两个矩阵的相乘
     */
    public static int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {//列数
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    /**
     * 奶牛繁殖问题：
     * 1,2,3,4,6,9
     * f(n) = f(n-1) + f(n-3)
     */
    public static int cowPb(int n) {
        if (n < 1) return 0;
        if (n == 1 || n == 2 || n == 3) return n;
        int[][] base = {
                {1, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }

}
