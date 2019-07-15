package tsp.leetcode.eyery;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * leetcode:128  最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * <p>
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * <p>
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 */
public class Solution23_128 {

    /**
     * leetcode 官方题解
     * 借助hashSet 实现 O(1)时间查询
     */
    public int longestConsecutive(int[] num) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < num.length; i++) {
            set.add(num[i]);
        }
        int longestStreak = 0;

        for (int a : set) {
            if (!set.contains(a - 1)) {
                int currentNum = a;
                int currentStread = 1;
                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStread += 1;
                }
                longestStreak = Math.max(longestStreak, currentStread);
            }
        }
        return longestStreak;
    }


    /**
     * 此法甚好
     */
    public static int longestConsecutive1(int[] num) {
        int result = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int a: num) {
            if (!map.containsKey(a)){
                //获取左右边界对应的序列长度
                int left = map.getOrDefault(a-1,0);
                int right = map.getOrDefault(a+1,0);
                int len = left+right+1;
                result = len>result?len:result;
                map.put(a-left,len);
                map.put(a+right,len);
                //把num加入map中防止重复判断(关键在于将num加入keyset中, value可以是任意值)
                map.put(a,len);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums ={200,3,100,1,4,2,40};
        System.out.println(longestConsecutive1(nums));
    }
}
