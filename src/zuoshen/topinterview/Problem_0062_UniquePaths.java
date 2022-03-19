package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/17
 * DESC:一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * <p>
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 * <p>
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10⁹
 * <p>
 * Related Topics 数学 动态规划 组合数学 👍 1324 👎 0
 */
public class Problem_0062_UniquePaths {


    /**
     * 思路：机器人从(0,0)位置出发，只能向右或者向下走，
     * n = 3, m = 7,向右走6不，向下走2步
     * count = C8^2 = 8 * 7/ 2 * 1 = 28
     */
    public static int uniquePaths(int m, int n) {

        int s = m + n - 2;//一共的步数
        int r = Math.min(m, n) - 1;//向左或向右需要走的步数
        long o1 = 1,o2 = 1;//分别表示分母和分子
        for (int i = s, j = r; i >= (s - r + 1) && j >= 1; i--, j--) {
            o1 *= i;
            o2 *= j;
            long gcd = gcd(o1,o2);//最大公约数'
            o1 /= gcd;
            o2 /= gcd;
        }
        return (int) o1;
    }

    public static long gcd(long a,long b){
        return b == 0 ? a : gcd(b,a % b);
    }


    public static void main(String[] args) {
        System.out.println(uniquePaths(3,7));
    }

}
