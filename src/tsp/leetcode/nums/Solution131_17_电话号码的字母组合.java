package tsp.leetcode.nums;

import java.util.*;

/**
 * 17:电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 */
public class Solution131_17_电话号码的字母组合 {

    /**
     * 回溯法：求全排列组合等问题，只是这个题没有限制条件，所有的组合都满足，
     * 也就没有回溯
     * 想法思路我都懂，hashmap我也知道要用，就是没有这个来的直接简单.....
     * 哎。。。。。。。。。。
     */
    public List<String> letterCombinations(String digits) {
        List<String> output = new ArrayList<>();
        if (digits.length() == 0) return output;
        Map<String, String> map = new HashMap<>();
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
        backtrack(output, map, "", digits);
        return output;
    }

    private void backtrack(List<String> output, Map<String, String> map, String item, String next_digits) {
        if (next_digits.length() == 0) {
            output.add(item);
            return;
        }
        String digit = next_digits.substring(0, 1);
        String letters = map.get(digit);
        for (int i = 0; i < letters.length(); i++) {
            String letter = letters.substring(i, i + 1);
            backtrack(output, map, item + letter, next_digits.substring(1));
        }
    }

    public static void main(String[] args) {
//        Map<Character, List<Character>> map = new HashMap<>();
//        List<Character> value = new ArrayList<>();
//        value.add('a');
//        value.add('b');
//        value.add('c');
//        map.put('2', value);
//        System.out.println(map.get('2').get(0));
//        System.out.println(Integer.valueOf('2'));
        S130 s = new S130();
        ArrayList<String> list = s.letterCombinations("23");
        System.out.println(Arrays.asList(list));
    }
}

class S130 {
    //方法二：写的人是个狠人
    public ArrayList<String> letterCombinations(String digits) {
        //用队列方便移除head
        LinkedList<String> output = new LinkedList<>();
        output.add("");
        if (digits.isEmpty()) return new ArrayList<>(output);
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (output.peek().length() == i) {
                String t = output.remove(); //移除队列的头
                for (char s : mapping[x].toCharArray()) {
                    output.add(t + s);
                }
            }
        }
        return new ArrayList<>(output);
    }
}
