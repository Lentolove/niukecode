package tsp.leetcode.nums;

import java.util.HashMap;
import java.util.Map;

/**
 * 13：罗马数字转整形
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 */
public class Solution136_13_罗马数字转整形 {

    /**
     * 注意只有那么多组合  全考虑到就可以了
     * 而且是从前往后计算
     * 小的数字、（限于 Ⅰ、X 和 C）在大的数字的左边、所表示的数等于大数减小数得到的数、如：Ⅳ=4、Ⅸ=9；
     *
     */
    public int romanToInt(String s) {
        if (s.length() == 0) return 0;
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);
        int ans = 0;
        for (int i = 0; i < s.length();) {
            if ((i + 1) < s.length() && map.containsKey(s.substring(i, i + 2))) {
                ans += map.get(s.substring(i, i + 2));
                i += 2;
            } else {
                ans += map.get(s.substring(i, i + 1));
                i++;
            }
        }
        return ans;
    }

    //方法二：
    public int romanToInt1(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int ans = 0;
        int preValue = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int curValue = map.get(s.charAt(i));
            if (curValue < preValue)
                ans -= curValue;
            else
                ans += curValue;
            preValue = curValue;

        }
        return ans;
    }
}
