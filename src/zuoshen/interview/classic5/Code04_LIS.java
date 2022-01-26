package zuoshen.interview.classic5;

import java.util.Arrays;

/**
 * 左神：数组中最长递增子数组
 */
public class Code04_LIS {

    /**
     * 返回数组的最长递增子序列
     */
    public static int[] lis1(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];
        //1.动态规划求最长递增数组
        int n = arr.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return generateLIS(arr, dp);
    }

    /**
     * 生成根据dp生成最长次递增数组
     */
    public static int[] generateLIS(int[] arr, int[] dp) {
        int n = arr.length;
        int len = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        int[] res = new int[len];
        res[--len] = arr[index];
        for (int i = index - 1; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                res[--len] = arr[i];
                index = i;
            }
        }
        return res;
    }

    /**
     * ☆☆☆☆☆
     * 进阶方法：借助 ends 数组加速dp生成
     * 1.ends[i] 表示找到的所有长度为 i+1 的递增子序列中最小的结尾是什么
     * 2.通过二分ends数组找到当前arr[i]在ends中找到第一个大于它的数，
     * 2.1找到:ends[i] 更新成 arr[i]，同样的长度，肯定选择更小的数字结尾
     * 2.2未找到，说明当前 arr[i]比ends数组都大，此时能更新dp数组的长度，并且更新ends数组
     * 2.3保证ends数组的严格递增
     */
    public static int[] getDp(int[] arr) {
        int n = arr.length;
        int[] ends = new int[n];
        int[] dp = new int[n];
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0; //表示ends数组的有效范围0 - right是有效的
        int l, r, m;//用于二分的变量
        for (int i = 1; i < n; i++) {
            //在ends数组中二分查找当前 arr[i] 的左边界
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            //在ends数组中找到第一个比 arr[i] 大的位置，如果存在就更新当前位置l,没找到l会更新成right + 1这个地方
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l + 1;//l,r总一直维护的最长有效数长度
        }
        return dp;
    }


    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 3, 5, 4, 4, 7};
        System.out.println(Arrays.toString(lis1(arr)));
    }


}
