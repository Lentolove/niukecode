package tsp.leetcode.backtract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q17_电话号码的字母组合 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.letterCombinations("23"));
    }


    static class Solution {

        private Map<Integer, String> map = new HashMap<>();

        List<String> list = new ArrayList<>();

        //1.回溯算法
        public List<String> letterCombinations(String digits) {
            if (digits.isEmpty())
            map.put(2, "abc");
            map.put(3, "def");
            map.put(4, "ghi");
            map.put(5, "jkl");
            map.put(6, "mno");
            map.put(7, "pqrs");
            map.put(8, "tuv");
            map.put(9, "wxyz");
            backtract(new StringBuilder(), digits.toCharArray(), 0);
            return list;

        }

        public void backtract(StringBuilder sb, char[] digits, int index) {
            if (index == digits.length) {
                list.add(sb.toString());
                return;
            }
            String item = map.get(Integer.parseInt(String.valueOf(digits[index])));
            for (char b : item.toCharArray()) {
                sb.append(b);
                //回溯
                backtract(sb, digits, index+1);
                //撤销选择
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

}


