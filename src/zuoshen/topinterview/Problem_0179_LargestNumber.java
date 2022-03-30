package zuoshen.topinterview;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author : tsp
 * Time: 2022/3/30 20:13
 * Desc :给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 * <p>
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10⁹
 * <p>
 * Related Topics 贪心 字符串 排序 👍 902 👎 0
 */
public class Problem_0179_LargestNumber {

    /**
     * 题意：就是拼接数组里的所有数字，使之得到最大值，定义一个compartor就行
     */
    public static String largestNumber(int[] nums) {
        StringBuilder sb = new StringBuilder();
        String[] ans = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(ans, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        for (String s : ans) {
            sb.append(s);
        }
        //可能存在都为0的情况，去掉开头的0
        String result = sb.toString();
        char[] strs = result.toCharArray();
        int index = -1;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] != '0'){
                index = i;
                break;
            }
        }
        return index == -1 ? "0" : result.substring(index);
    }


    public static void main(String[] args) {
        int[] arr = {0,0};
        System.out.println(largestNumber(arr));
    }

}
