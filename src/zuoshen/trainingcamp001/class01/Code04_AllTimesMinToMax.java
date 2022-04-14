package zuoshen.trainingcamp001.class01;

import java.util.Stack;

/**
 * Author : tsp
 * Time: 2022/4/14 20:14
 * Desc : 给定一个只包含正数的 arr，arr 中任何一个子数组 sub，一定都可以算出(sub累加和) * (sub中的最小值)是什么
 * 那么所有子数组中，这个最大值是多少？
 */
public class Code04_AllTimesMinToMax {


    /**
     * 暴力思路：首先以每个位置为开头，[l,r] 的子子数组的所有情况，更新最大值
     * 1.查找区间复杂度 O(n^2)，计算累加和和确定最小值复杂度为 O(n),总的复杂度为 O(n^3)
     */
    public static int maxSubSunMinMax(int[] arr) {
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                int min = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = l; k <= r; k++) {
                    sum += arr[k];
                    min = Math.min(min, arr[k]);
                }
                max = Math.max(max, sum * min);
            }
        }
        return max;
    }

    /**
     * 优化思路：
     * 1.求sub累加和，用预处理技巧。
     * 2.区间定义：必须以 l 位置为子数组的最小值求最大答案，每个位置都遍历求一个答案，O(n) 拿下。
     * 3.等价， 找 arr[l] 左边第一个比自己小的位置，右边第一个比自己小的位置，这不就是单调栈的定义么。
     * 3.eg：arr[3,4,5,6,3,2,7]，此时如果以 4 位置为最小值，左边比自己小的位置在 0 位置，右边比自己小的位置在4位置
     * 4.这样就能确定一个区间：[4,5,6],每个位置都尝试以自己为最小值。
     */
    public static int maxSubSunMinMax1(int[] arr) {
        int n = arr.length;
        //1.数组预处理,求前缀和
        int[] sum = new int[n];
        sum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        //2.单调栈解决左右两边比自己小的最远距离,单调递增栈
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            //等价于求左边 leftIndex，右边 rightIndex 这个区间内
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                //当前有一个元素使得不能单调递增了，右边的最小值为 rightIndex= i
                int index = stack.pop();//以当前元素为最小值
                int subSum = sum[i - 1] - (stack.isEmpty() ? 0 : sum[stack.peek()]);
                max = Math.max(max, subSum * arr[index]);
            }
            stack.add(i);
        }
        //3.最后结算stack中剩余的元素
        while (!stack.isEmpty()){
            int index = stack.pop();
            int subSum = sum[n - 1] - (stack.isEmpty() ? 0 : sum[stack.peek()]);
            max = Math.max(max,subSum * arr[index]);
        }
        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (maxSubSunMinMax(arr) != maxSubSunMinMax1(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }


}
