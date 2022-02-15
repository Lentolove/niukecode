package tsp.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author : tsp
 * Time: 2022/2/15 13:37
 * Desc :给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q17_电话号码的字母组合 {

    /**
     * 思路：排列组合问题，分别取出数字对应的字母有多少种情况
     * 1.可以将数字分别映射成对应的字母集合
     */
    public static List<String> letterCombinations(String digits) {
        HashMap<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "jhi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        List<String> ans = new ArrayList<>();
        process(digits.toCharArray(), 0, new StringBuilder(), ans, map);
        return ans;
    }

    public static void process(char[] nums, int index, StringBuilder sb, List<String> ans, HashMap<Character, String> map) {
        if (index == nums.length) {
            ans.add(sb.toString());
            return;
        }
        char[] list = map.get(nums[index]).toCharArray();
        for (Character a : list) {
            sb.append(a);
            process(nums, index + 1, sb, ans, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        List<String> strings = letterCombinations(digits);
        for (String s : strings) {
            System.out.println(s);
        }
    }

}
