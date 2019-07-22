package tsp.leetcode.nums;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * 3:无重读字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */
public class Solution146_3_无重复字符的最长子串 {


    /**
     * 方法一：滑动窗口 左右指针
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 1;
        while (i < n && j < n) {
            //扩展窗口
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);//j又指针
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }


    /**
     * 方法er：HashMap
     * 优化的滑动窗口
     */
    public int lengthOfLongestSubstring1(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1); //如果出现重复了，则取其索引的下一个位置重新开始
            }
            map.put(s.charAt(i), i);
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }

    /**
     * 假设是ASCII 128 编码
     * 用整数数组替换hashmap
     */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128];
        //扩展[i,j]窗口
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);//记录字符出现的最大位置 j-i+1就是没有重复的子串长度
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}