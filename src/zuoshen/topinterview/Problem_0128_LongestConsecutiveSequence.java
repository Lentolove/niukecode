package zuoshen.topinterview;

import java.util.HashMap;

/**
 * Author : tsp
 * Time: 2022/3/22 18:22
 * Desc :给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <p>
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * <p>
 * 提示：
 * 0 <= nums.length <= 10⁵
 * -10⁹ <= nums[i] <= 10⁹
 * <p>
 * Related Topics 并查集 数组 哈希表 👍 1170 👎 0
 */
public class Problem_0128_LongestConsecutiveSequence {

    /**
     * 找出数字连续的最长序列：要求 O(n) 解法
     * label: HashMap 并查集，不断的合并区间
     * map 的key表示当前数值，len 表示当前连接的长度
     * 1.假如当前num = a,在 map 中查到 a - 1,表示它的前缀长度
     * 2.如果在map中查到a+1，表示能和后面记起来，后续能接的长度为 map[a+1]。
     * 注意：a+1是不可能往前接的，因为往前接，就说明 a 已经接入过了，那既然a接过了，那a-1在map中也接过了
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a : nums) {
            if (!map.containsKey(a)){//过滤掉重复的字符
                map.put(a, 1);
                int preLen = map.getOrDefault(a - 1, 0);
                int posLen = map.getOrDefault(a + 1, 0);
                int totalLen = preLen + posLen + 1;
                map.put(a - preLen, totalLen);//其中 a - preLen 表示当前区间的第一个值
                map.put(a + posLen, totalLen);//a + posLen表示当前区间的最后一个值
                ans = Math.max(ans, totalLen);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] num = {1,2,0,1};
        System.out.println(longestConsecutive(num));
    }

}
