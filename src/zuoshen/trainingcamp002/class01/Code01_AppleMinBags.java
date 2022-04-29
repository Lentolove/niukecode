package zuoshen.trainingcamp002.class01;

/**
 * Author : tsp
 * Time: 2022/4/26 16:01
 * Desc : 题目描述：给定两种规格的袋子：6 和 8，小明准备买 N 个苹果，问至少需要几个袋子，袋子必须严格装满，否则返回 -1。
 * eg: 1,2,3 都是不能装满，返回 -1。
 */
public class Code01_AppleMinBags {

    /**
     * 思想历程：首先尝试一下暴力解
     * 1.优先装 8 这个袋子，剩下的装入 6 袋子中，严格装满。
     * 2.从最多的 n / 8 个袋子一直尝试，直到找到答案返回，否则返回 -1
     */
    public static int minBags(int n) {
        if (n < 6) return -1;
        int count = 0;
        int bag8 = n / 8;
        for (int i = bag8; i >= 0; i--) {
            int mod = n - i * 8;
            if (mod % 6 == 0) {
                //有效方案
                count = i + mod / 6;
                return count;
            }
        }
        return -1;
    }

    /**
     * 可优化的点：
     * 1. n 为奇数直接返回 -1
     * 2. 当 mod > 24 以后直接返回 -1。
     * 原因：6 与 8 的最大公约数为 24，超过 24 后，25 % 6 = 1 % 6, 32 % 6 = 8 % 6 = 2
     * 如果能整除，则前面就已经处理了。
     * 于是写下以下代码
     */
    public static int minBags1(int n) {
        //base case: 小于 6 或者为奇数直接返回 - 1
        if (n < 6 || ((n & 1) != 0)) return -1;
        int count = 0;
        int bag8 = n / 8;
        for (int i = bag8; i >= 0; i--) {
            int mod = n - i * 8;
            //超过24直接返回-1.
            if (mod > 24) return -1;
            if (mod % 6 == 0) {
                //有效方案
                count = i + mod / 6;
                return count;
            }
        }
        return -1;
    }

    /**
     * 打表法：通过观察暴力递归的结果：
     * 从18开始：每8个数为一组，奇数为-1，偶数为满足 ： (n - 18)/8 + 3
     * 于是可以写出如下代码
     */
    public static int minBags2(int n) {
        if ((n & 1) != 0 || n < 6) return -1;
        //直接列举
        if (n < 18) {
            return (n == 6 || n == 8) ? 1 : (n == 12 || n == 14 || n == 16) ? 2 : -1;
        }
        //超过18，直接写答案
        return (n - 18) / 8 + 3;
    }


    public static void main(String[] args) {

        System.out.println();
        System.out.println();
//
        for (int i = 1; i <= 100; i++) {
            System.out.println("                      " + i + " : " + minBags(i));
        }

//        for (int i = 1; i < 200; i++) {
//            int i1 = minBags(i);
//            int i2 = minBags2(i);
//            if (i1 != i2){
//                System.out.println("失败："+ i);
//            }
//        }
//        System.out.println("成功");

    }

}
