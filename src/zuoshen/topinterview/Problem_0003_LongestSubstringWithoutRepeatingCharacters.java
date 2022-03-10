package zuoshen.topinterview;

import java.util.Arrays;

/**
 * Author : tsp
 * Time: 2022/3/8 14:34
 * Desc : 给定一个字符串，查找最长无重复的子串
 */
public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {

    /**
     * idea:当遇到子串子序列问题，考虑以i字符开始怎么推，以i字符结尾怎么推
     * 1.当前子串以i结尾的最长无重复字符串的长度。
     * 2.用一个 map 记录每个字符最近出现的次数，用一个变量 pre 记录 i - 1 位置往前能推到的最远下标为 pre
     * 3.当遇到字符 str[i] 时候查询该字符最近出现的位置，更新 pre，因为之前一个字符往左推的最远下标为 pre
     * i位置不可能跨过这个位置，所以 pre = Math.max(pre,map[str[i]]),更新答案。
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int len = 0;
        int pre = -1;
        int curLen = 0;
        //用map记录当前字符出现的位置，初始值为 - 1
        int[] map = new int[256];
        Arrays.fill(map, -1);
        for (int i = 0; i < n; i++) {
            pre = Math.max(pre, map[str[i]]);
            curLen = i - pre;
            len = Math.max(len, curLen);
            //更新map
            map[str[i]] = i;
        }
        return len;
    }

}
