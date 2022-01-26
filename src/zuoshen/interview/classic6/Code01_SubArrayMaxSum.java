package zuoshen.interview.classic6;

/**
 * 左神：返回数组的最大累加
 * 题目：给定一个数组，返回子数组的最大累加和
 * 思路：定义 cur 和 max, cur 一直累加，小于 0的话就更新为0，每次记录max的最大值
 */
public class Code01_SubArrayMaxSum {


    public static int maxArraySum(int[] arr){
        if (arr == null || arr.length == 0) return 0;
        int max = 0;
        int cur = 0;
        for (int a : arr) {
            cur += a;
            max = Math.max(max,cur);
            //如果cur比0还小，那没有添加的必要了
            cur = Math.max(cur, 0);
        }
        return max;
    }


    public static void main(String[] args) {
        int[] arr1 = { -2, -3, -5, 40, -10, -10, 100, 1 };
        System.out.println(maxArraySum(arr1));
    }
}
