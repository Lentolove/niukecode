package zuoshen.interview.classic9;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 左神：给定一个正整数数组，返回其中数组的最小不可组成和
 * eg: arr = [3,2,5],则所有子集和为 [2,3,5,7,8,10],即 min = 2,max = 10；
 * 则对应区间为[2,10]，缺少4,6,9不能被任何子集得到。其中 4 是 arr 的最小不可组成和。
 * eg: arr = [1,2,4],min = 1,max = 7,在区间[1,7]上，任何数都可以被子集相加得到。所以8是最小不可组成和。
 * 进阶：如果已知 arr 中肯定含有 1 这个数，能否更快的得到最小的不可组成和
 */
public class Code05_SmallestUnFormedSum {

    /**
     * 思路一：通过递归得到 arr 的所有子集和，然后找出第一个在子集和中未出现的数字
     */
    public static int unformedSum1(int[] arr) {
        if (arr == null || arr.length == 0) return 1;
        int min = Integer.MAX_VALUE;
        int totalSum = 0;
        int n = arr.length;
        for (int j : arr) {
            min = Math.min(min, j);
            totalSum += j;
        }
        //1.拿到所有子集
        HashSet<Integer> set = new HashSet<>();
        process(arr, 0, 0, set);
        //2.从[min,max]找到第一个没出现的数组，如果遍历完都没找到，返回 totalSum + 1;
        for (int i = min; i <= totalSum; i++) {
            if (!set.contains(i)) return i;
        }
        return totalSum + 1;
    }

    public static void process(int[] arr, int i, int sum, HashSet<Integer> set) {
        if (i == arr.length) {
            set.add(sum);
            return;
        }
        process(arr, i + 1, sum, set);//不取当前元素
        process(arr, i + 1, sum + arr[i], set);//取当前元素
    }

    /**
     * 思路二：动态规划
     * 1.定义二维dp数组，dp[i][j] 表示前 i 个数，能否组成和为 j
     */
    public static int unformedSum2(int[] arr) {
        if (arr == null || arr.length == 0) return 1;
        int n = arr.length;
        int min = Integer.MAX_VALUE;
        int totalSum = 0;
        for (int k : arr) {
            min = Math.min(min, k);
            totalSum += k;
        }
        boolean[][] dp = new boolean[n][totalSum + 1];
        //1.初始化第一列，[0,n-1]个数能够组成和为0
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        //2.第一行中,只有一个数 arr[0]能组成
        dp[0][arr[0]] = true;
        //3.填表
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= totalSum; j++) {
                dp[i][j] = dp[i - 1][j] || (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]);
            }
        }
        //4.找到第一个为false的
        for (int i = min; i <= totalSum; i++) {
            if (!dp[n - 1][i]) return i;
        }
        return totalSum + 1;
    }

    /**
     * 进阶：当数组中含有 1 的时候，此题可以进一步优化
     * 定义一个变量 range 表示当前能到的范围，遍历当前 arr数组：
     * 1.如果当前 arr[i] > range + 1 ,则说明找到了无法组成的数。
     * 2.否则，range += arr[i],
     */
    public static int unformedSum3(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr); // O (N * logN)
        int range = 1;
        // arr[0] == 1
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > range + 1){
                return range + 1;
            }else {
                range += arr[i];
            }
        }
        return range + 1;
    }


    public static int[] generateArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) + 1;
        }
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 27;
        int max = 30;
        int[] arr = generateArray(len, max);
        printArray(arr);
        long start = System.currentTimeMillis();
        System.out.println(unformedSum1(arr));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");
        System.out.println("======================================");

        start = System.currentTimeMillis();
        System.out.println(unformedSum2(arr));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");
        System.out.println("======================================");

        System.out.println("set arr[0] to 1");
        arr[0] = 1;
        start = System.currentTimeMillis();
        System.out.println(unformedSum3(arr));
        System.out.println(unformedSum2(arr));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");

    }

}
