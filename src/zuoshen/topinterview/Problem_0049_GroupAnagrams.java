package zuoshen.topinterview;

import java.util.*;

/**
 * author : tsp
 * Date : 2022/3/15
 * DESC:给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。 
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]] 
 * 
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]] 
 *
 *  提示： 
 *
 *  1 <= strs.length <= 10⁴ 
 *  0 <= strs[i].length <= 100 
 *  strs[i] 仅包含小写字母 
 *  
 *  Related Topics 哈希表 字符串 排序 👍 1052 👎 0
 */
public class Problem_0049_GroupAnagrams {

    /**
     * 思路：将当前遇到的字符串按照字典序排序后，如果相同则视为字母异位词，放在一个集合中
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for (String str : strs) {
            char[] strChars = str.toCharArray();
            Arrays.sort(strChars);
            String key = String.valueOf(strChars);
            if (!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> list = entry.getValue();
            ans.add(list);
        }
        return ans;
    }


    private static final int[] map = {2,3,5,7,11,13,17,19, 23 ,29, 31, 37, 41, 43, 47,
            53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    /**
     * 思路:思路一样，但是在判断字母异位词的时候，采用质数相乘的办法。
     * 题目规定：单个字符串长度在 100以内，101^100
     * 击败99%
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<Double,List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for (String str : strs) {
            char[] strChars = str.toCharArray();
            double key = getCount(strChars);
            if (!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }
        for (Map.Entry<Double, List<String>> entry : map.entrySet()) {
            List<String> list = entry.getValue();
            ans.add(list);
        }
        return ans;
    }

    private static double getCount(char[] str){
        double ans = 1;
        for (char c : str) {
            ans *= map[c - 'a'];
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Math.pow(101,100));
    }

}
