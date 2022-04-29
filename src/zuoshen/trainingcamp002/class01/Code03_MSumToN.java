package zuoshen.trainingcamp002.class01;

/**
 * Author : tsp
 * Time: 2022/4/26 17:07
 * Desc : 题目：定义一种数：可以表示成如果(数量 > 1) 连续正数和的树：
 * eg: 5 = 2 + 3
 * 12 = 3 + 4 + 5
 * 1 不满足，因为要求数量大于1,2 = 1+1也不满足，因为不是连续递增的。
 * 给定一个参数 N ，返回这个是是否可以表示成若干连续正数和的数字
 */
public class Code03_MSumToN {


    /**
     * 暴力尝试，从 i - n 不停的尝试，找到了就返回 - 1
     */
    public static boolean isSum(int n) {
        if (n <= 2) return false;
        for (int i = 1; i <= n / 2; i++) {//因为会少两个数，第一个数字的取值范围为 1-n/2
            int sum = i;
            for (int j = i + 1; j < n ; j++) {
                sum += j;
                if (sum == n) return true;
                if (sum > n) break;
            }
        }
        return false;
    }

    /**
     * 打表法：通过观察当 n >=3 后
     * 有：4,8,16,32,64...为 false，其它都为 true
     */
    public static boolean isSum1(int n){
        if (n <= 2) return false;
        //判断一个数是否是2的次方：这个是的二进制中只有一个 1
        //eg: 16 = 001000 则  n & (n - 1) = 00100 & 000111 = 0,则为2的次方
        //eg: 6 = 00110  则  n & (n - 1) = 00110 & 00011 != 0
        return (n & (n - 1)) != 0;
    }


    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (isSum(i) != isSum1(i)){
                System.out.println("出错");
            }
//            System.out.println(i + ":" +isSum(i));
        }
        System.out.println("完成");
    }

}
