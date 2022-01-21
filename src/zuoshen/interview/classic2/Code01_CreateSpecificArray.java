package zuoshen.interview.classic2;

/**
 * 左神：给定一个正整数 M,请构造出一个长度为 M 的数组 arr，要求任意的 i,j,k 三个位置，如果 i < j < k
 * 都有 arr[i] + arr[k] != 2 * arr[j],返回构造出的 arr
 */
public class Code01_CreateSpecificArray {

    /**
     * 思路：当 n = 1时， 1 不存在左右，满足条件。当 n = 2 同理。
     * 一种分治的思想，由已知数组去推广到更大范围的数组，根据：[2a - 1,2b - 1,2c - 1,2a,2b,2c]
     * 按照数组长度一分为2，左侧为奇数，右侧为偶数，任何三个奇数按照顺序之间的计算递推公式都可以化简得到 a +  c != 2b
     * 任何三个顺序的偶数也都能得到同样化简公式： a + c != 2b
     *
     */
    public static int[] makeSpecificArray(int size) {
        if (size == 1) return new int[]{1};
        //size，一分为2，左边和右边
        int halfSize = (size + 1) / 2;
        int[] base = makeSpecificArray(halfSize);
        //以当前halfSize的左边为奇数，递推式为 2*a -1;从[0..half] -> [2*arr[0]-1,2*arr[1]-1,....,2*arr[half]-1]
        //[a,b,c] => a + c != 2b =>  [2a-1,2b-1,2c -1] => 2a + 2c - 2 != 4b - 2 =>  2a + 2c != 4b => a + c != b
        int[] ans = new int[size];
        int index = 0;
        for (; index < halfSize; index++) {
            ans[index] = base[index] * 2 - 1;
        }
        //右边部分为偶数，又部分数组都为偶数，递推公式 [2a,2b,2c]
        for (int i = 0; index < size; index++, i++) {
            ans[index] = base[index] * 2;
        }
        //可以得到递推数组：[a,b,c] => [2a - 1,2b - 1,2c - 1,2a,2b,2c];任意是哪个元素：2b - 1 + 2b != 2a*2 =>
        // 左边奇数，右边偶数，成立
        return ans;
    }

}
