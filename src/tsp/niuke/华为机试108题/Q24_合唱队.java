package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 题目描述
 * 计算最少出列多少位同学，使得剩下的同学排成合唱队形
 * 说明：
 * N位同学站成一排，音乐老师要请其中的(N-K)位同学出列，使得剩下的K位同学排成合唱队形。
 * 合唱队形是指这样的一种队形：设K位同学从左到右依次编号为1，2…，K，他们的身高分别为T1，T2，…，TK，   则他们的身高满足存在i（1<=i<=K）使得T1<T2<......<Ti-1<Ti>Ti+1>......>TK。
 * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 * <p>
 * <p>
 * 输入描述:
 * 整数N
 * 输出描述:
 * 最少需要几位同学出列
 * 示例1
 * 输入
 * <p>
 * 复制
 * 8
 * 186 186 150 200 160 130 197 200
 * 输出
 * <p>
 * 复制
 * 4
 */
public class Q24_合唱队 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            int n = Integer.parseInt(str);
            str = bf.readLine();
            System.out.println(minPeople(str, n));
        }
    }

    /**
     * 考点：寻找每个分界点左边最长递增序列和右边最长递减序列，包含该点，动态规划求解
     *
     * 从左往右计算每最长递增子序列中有几个数比它小
     * 186  186  150  200  160  130  197  200   height
     * 0    0    0    1    1    0    2    3    dp1
     * 从右往左计算每最长递增子序列中有几个数比它小
     * 2    2    1    2    1    0    0    0    dp2
     * 现在可以知道每个数左边和右边的分布情况
     * 2    2    1    3    2    0    2    3
     * eg：200 可以知道包含 200 的符合条件的长度为3+1，要包含自身，所以要移走的人个数为 8 - 4 =4
     */

    private static int minPeople(String s, int n) {
        String[] split = s.split(" ");
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(split[i]);
        }
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        //从左到右计算每个数在最大递增子数组中的位置
        for (int i = 1; i < height.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] < height[i] && dp1[i] < dp1[j] + 1) {
                    dp1[i] = dp1[j] + 1;
                }
            }
        }
        //从右边往左计算在递增子数组中的位置
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (height[i] > height[j] && dp2[i] < dp2[j] + 1) {
                    dp2[i] = dp2[j] + 1;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            int sum = dp1[i] + dp2[i];
            max = Math.max(sum, max);
        }
        return n - max - 1;
    }

}
