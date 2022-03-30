package zuoshen.topinterview;

import java.util.HashMap;

/**
 * Author : tsp
 * Time: 2022/3/29 19:10
 * Desc :给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。如果存在多个答案，只需返回 任意一个 。
 * 对于所有给定的输入，保证 答案字符串的长度小于 10⁴ 。
 * <p>
 * 示例 1：
 * 输入：numerator = 1, denominator = 2
 * 输出："0.5"
 * <p>
 * 示例 3：
 * 输入：numerator = 4, denominator = 333
 * 输出："0.(012)"
 * <p>
 * 提示：
 * <p>
 * -2³¹ <= numerator, denominator <= 2³¹ - 1
 * denominator != 0
 * <p>
 * Related Topics 哈希表 数学 字符串 👍 370 👎 0
 */
public class Problem_0166_FractionToRecurringDecimal {

    /**
     * 题意：求两个数相除的结果，eg: 1/3 = 0.3333的循环
     * 思路：按照除法的步骤，除数 * 10 计算，用一个HashMap来记录：key表示当前值，
     * value 表示它在小数点后的位置
     * eg: 71/6 = 11.8(3)
     * 71/6 = 11
     * 71 % 6 = 5 * 10 = 50 / 6 = 8,在 11. 位置上：(8,3),余数为 2-》20/6 = 3，map中(3,4),余数为 2，20/6 = 3
     * 此时3在map中，出现循环了，map.get(3) = 4， 11.83 在index = 4位置插入(，末尾加上)
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        HashMap<Long, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        //1.判断是否是负数
        sb.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        //2.计算整数部分
        sb.append(num / den);
        //3.如果整除，则直接返回`
        num %= den;
        if (num == 0) return sb.toString();
        sb.append(".");
        map.put(num,sb.length());
        //4.无法整除
        while (num != 0) {
            num *= 10;
            sb.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                sb.insert(index, "(");
                sb.append(")");
                break;
            } else {
                map.put(num, sb.length());
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(fractionToDecimal(0, 3));
    }
}
