package tsp.offer;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Q60_n个骰子点数 {


    public static void main(String[] args) {
        Solution solution = new Solution();
        double[] doubles = solution.dicesProbability(2);
        for (double v : doubles) {
            System.out.println(v);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.poll();

    }


    static class Solution {

        //动态规划，一个筛子每个1-6出现的概率为1/6
        public double[] dicesProbability(int n) {
            double[] dp = new double[6];
            Arrays.fill(dp, 1.0 / 6.0);
            //从第二个筛子开始，前面出现的概率会对后面的值有影响，比如第一个筛子是 2，
            //第二个筛子的和为 3,4,5,6,7,8概率分别 1/6 *(1/6,1/6,1/6,1/6,1/6,1/6),n 个筛子的所有和为 6 * n - n + 1 = 5n+1
            for(int i = 2; i <= n;i++){
                //i个筛子出现的所有和的概率dp数组
                double[] temp = new double[5 * i + 1];
                //当前 i 个筛子的取值概率跟 i- 1 有关
                for(int j = 0;j < dp.length; j++){
                    //计算 temp 概率值,次筛子只能出现 1-6
                    for(int k = 0; k < 6; k++){
                        //之前dp[1] = 1/6,表示1个筛子 和为 2 的概率，现在最新的筛子为 k,则当前和为 j + k
                        temp[j + k] += dp[j] * (1.0 / 6.0);
                    }
                }
                dp = temp;
            }
            return dp;
        }
    }

}
