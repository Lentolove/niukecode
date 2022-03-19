package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/17
 * DESC:给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * <p>
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * <p>
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * <p>
 * Related Topics 数组 数学 👍 947 👎 0
 */
public class Problem_0066_PlusOne {


    /**
     * 思路：因为是数组，由高位到低位，加法从最后一位开始算，如果当前位置小于9，则直接给该位置+1返回结束
     * 否则继续向左遍历，只有全为[9,9,9,9,9]需要进一位，最后单独创建[1,0,0,0,0,0]
     * [1,2,3,9,9,9] => [1,2,4,0,0,0]
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9){
                digits[i] += 1;
                return digits;
            }else {
                digits[i] = 0;
            }
        }
        //只剩全是9这种情况
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }
}
