package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/11 11:47
 * Desc :给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * <p>
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 * <p>
 * 提示：
 * <p>
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2³¹, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 * <p>
 * Related Topics 位运算 数学 👍 855 👎 0
 */
public class Problem_0029_DivideTwoIntegers {


    /**
     * 不能用操作符
     */
    public int divide(int dividend, int divisor) {
        //1.如果除数是系统最小
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        //2.除数不是系统最小
        if (dividend == Integer.MIN_VALUE) {
            //被除数是系统最小 -2147483648 / - 1 = 系统最大
            if (divisor == negNum(1)) {
                return Integer.MAX_VALUE;
            }
            //为了防止溢出比如 ：用 -10表示系统最小，将其 + 1后做除法，因为我们实现的函数是要将负数转为正数，
            // 所以直接将-2147483648放进去就会转成  2147483647
            //先将 -2147483648 + 1然后才能转成正数正常指定div函数，
            // 在加上div(minus(dividend, multi(res, divisor)), divisor)
            int res = div(add(dividend, 1), divisor);
            // multi(res, divisor) = 结果 * 除数，已经算的部分，剩下没算的就是  dividend - 结果 * 除数
            //接着用剩下的 dividend - 已经算的，再去做除法就能解决溢出问题
            int need = div(minus(dividend, multi(res, divisor)), divisor);
            return add(res,need );
        }
        return div(dividend, divisor);
    }


    /**
     * 加法
     * 输入可为正负
     * 负数为取反 + 1
     * Integer.MIN_VALUE = 10000000000000000000000000000000 =  - 2 ^ 31 = -2147483648
     * -1 的二进制：11111111111111111111111111111111，不看符号位，补码 + 1= 10000000000000000000000000000001
     */
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {//直到进位为0停止
            //  a = 5 = 101, b = 6 = 110
            //  sum = a^b = 011
            //当前进位：(a & b) << 1 = 1000，继续
            sum = a ^ b;//sum 保存的是
            b = (a & b) << 1;//b保存的是进位信息
            a = sum;
        }
        return sum;
    }

    /**
     * 减操作： a - b = a + (-b)
     */
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    /**
     * 乘操作：eg: 5 * 6 = 30
     * 101
     * 110
     * ---------
     * 000
     * 101
     * 101
     * ---------
     * 11110 = 30
     * 步骤：每次取b的最后位置，与 a ^ 运算，然后 b 无符号右移，a左移，继续做^运算，直到b为0
     */
    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {//b的最后一位不为0，才能与a^运算有效,类似例子中的 101
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    /**
     * 除法操作，如果有负数，将其转成正数操作，一定要类比成我们的除法过程，在本子上画画
     * 思路：类比除法的过程: 30/5 = 6
     * 11110/101 a/b
     * 求最高位：将 101 向左移动i个位置，直到 a>=  i<< b,这样当前位置才能算出一个1来
     * 2<<101 = 10100 ->  11110/10100 可以得到高位的1,当前二进制位置是  1 << 2  = 100，将其|到结果上
     * 就能得到当前第一个除数 res |= 100 = 100;
     * 把当前的树减去。当前树是 i << 100  = 2 << 100 = 10000, 11110 - 10100 = 01110 继续这个过程
     */
    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 31; i > negNum(1); i = minus(i, 1)) {//就是 i = 31: i >= 0; i--
            if ((x >> i) >= y) {//当前高位可以获得除数
                res |= (1 << i);//当前除数
                //减去当前除的数，11110 - 10100 = 01010 - > 01010/101 = 00010 + 00100 = 00110 = 6
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }


    /**
     * 将一个数转成相反数
     * 取反+1
     */
    public static int negNum(int a) {
        return add(~a, 1);
    }

    /**
     * 一个数是否是负数
     */
    public static boolean isNeg(int a) {
        return a < 0;
    }


    public static String printNumBinary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            //取出每个位置的1
            sb.append(((num >> i) & 1) == 0 ? "0" : "1");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(printNumBinary(Integer.MIN_VALUE));
        System.out.println(printNumBinary(-1));
        System.out.println(Integer.MIN_VALUE);

        System.out.println(negNum(-100));
        System.out.println(multi(5, -6));
        System.out.println(negNum(1));
    }


}
