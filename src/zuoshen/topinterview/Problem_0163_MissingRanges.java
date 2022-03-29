package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : tsp
 * Time: 2022/3/29 18:58
 * Desc :
 */
public class Problem_0163_MissingRanges {

    /**
     * 题目:缺失的区间
     * eg: arr = [3,5,7,16,28] ,lower = 1,upper = 100
     * out-》 1->2,4,6,8->15,17->27,28->100
     */
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        for (int num : nums) {
            if (num > lower) {
                ans.add(miss(lower, num - 1));
            }
            if (num == upper) {
                return ans;
            }
            lower = num + 1;
        }
        if (lower <= upper) {
            ans.add(miss(lower, upper));
        }
        return ans;
    }

    public static String miss(int low, int upper) {
        String left = String.valueOf(low);
        String right = "";
        if (upper > low) {
            right = "->" + upper;
        }
        return left + right;
    }
}
