package tsp.leetcode.eyery;

/**
 * 69：x的平方根
 * 实现 int sqrt(int x) 函数。计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 示例 1:
 * 输入: 4
 * 输出: 2
 *
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 */
class Solution69{


    /**
     * 二分法
     * 注意：针对特殊测试用例，例如 2147395599
     * 要把搜索的范围设置成长整型
     */
    public int sqrt(int x) {
        if (x==0) return 0;
        long left =1;
        //为了照顾到 1 把右边界设置为 x / 2 + 1
        long right = x/2;
        while (left<right){
            // 注意：这里一定取右中位数，如果取左中位数，代码会进入死循环
            // in mid = left + (right - left + 1) / 2;
            long mid = (left+right+1)>>>1;
            long squart = mid*mid;
            if (squart>x){
                right = mid-1;
            }else {
                left = mid;
            }
        }
        return (int)left;
    }

    /**
     * 方法二
     */
    public int sqrt1(int x){
        long r = x;
        while ((r*r>x)){
            r = (r+x/r)/2;
        }
        return (int)r;
    }


}


public class Solution80_69_x的平方根 {
    public static void main(String[] args) {
        Solution69 solution69 = new Solution69();
        System.out.println(solution69.sqrt(2147395599));
    }
}
