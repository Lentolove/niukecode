package zuoshen.interview.classic10;

import java.util.Arrays;

/**
 * Author : tsp
 * Time: 2022/2/11 20:27
 * Desc : 打怪兽通关问题：花费的最小钱数
 */
public class Code04_MoneyProblem {

    /**
     * 方法一：暴力递归: 数组 d[i] 表示怪兽的能力， p[i] 表示贿赂该收需要的钱数
     * 到达当前位置
     */
    public static int fightMonsters1(int[] d, int[] p) {
        return process(d, p, 0, 0);
    }

    /**
     * 其中 ability 表示当前所拥有的能力，index 表示通关到的怪兽位置,返回要花的钱
     */
    public static int process(int[] d, int[] p, int ability, int index) {
        if (index == d.length) return 0;
        //1.如果当前能力小于怪兽，则必须贿赂
        if (ability < d[index]) {
            return p[index] + process(d, p, ability + d[index], index + 1);
        } else {
            //2.当前能力大于怪兽，可以选择贿赂，也可以不贿赂，取其中的较小值
            return Math.min(
                    p[index] + process(d, p, ability + d[index], index + 1),//贿赂
                    process(d, p, ability, index + 1)//直接通过
            );
        }
    }

    /**
     * 思路二：暴力递归改动态规划，可变参数有 ability 和 index，二维表
     * // dp[i][j]含义：
     * 能经过0～i的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
     * 如果dp[i][j] == -1，表示经过0～i的怪兽，花钱为j是无法通过的，或者之前的钱怎么组合也得不到正好为j的钱数
     */
    public static int fightMonsters2(int[] d, int[] p) {
        //1.把全部怪兽售卖的总钱
        int sum = 0;
        for (int a : p) {
            sum += a;
        }
        int n = d.length;
        //2.定义dp数组
        int[][] dp = new int[n][sum + 1];//dp[i][j]表示当前获得的最大能力
        //3.如果dp[i][j]==-1，表示经过0～i的怪兽，花钱为j是无法通过的，或者之前的钱怎么组合也得不到正好为j的钱数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        //4.初始化第一行，经过第一个怪兽时候，只有 d[0] 处才能，其它处是无效的，因为是钱数是严格的
        dp[0][p[0]] = d[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= sum; j++) {//花的钱
                //1.可能性一：贿赂当前怪兽，则之前[0,i-1]怪兽花的钱严格为 j - p[i];
                if (j - p[i] >= 0 && dp[i - 1][j - p[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - p[i]] + d[i];
                }
                //2.可能性二：当前能力大于怪兽能力，不贿赂,即之前的 dp[i - 1][j] 是成功的
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        //从dp数组的最后一行中从左往右找到第一个不为 -1的钱数及可
        for (int i = 0; i <= sum; i++) {
            if (dp[n - 1][i] != -1) {
                return i;
            }
        }
        return 0;
    }


    public static int[][] generateTwoRandomArray(int len, int value) {
        int size = (int) (Math.random() * len) + 1;
        int[][] arrs = new int[2][size];
        for (int i = 0; i < size; i++) {
            arrs[0][i] = (int) (Math.random() * value) + 1;
            arrs[1][i] = (int) (Math.random() * value) + 1;
        }
        return arrs;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 20;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[][] arrs = generateTwoRandomArray(len, value);
            int[] d = arrs[0];
            int[] p = arrs[1];
            long ans1 = fightMonsters1(d, p);
            long ans2 = fightMonsters2(d, p);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }

    }

}
