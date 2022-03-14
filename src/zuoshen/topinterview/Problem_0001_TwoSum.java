package zuoshen.topinterview;

import java.util.HashMap;

/**
 * Author : tsp
 * Time: 2022/3/8 14:33
 * Desc :
 */
public class Problem_0001_TwoSum {

    /**
     * 查找数组中两个元素的和为target，返回下标
     */
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

}
