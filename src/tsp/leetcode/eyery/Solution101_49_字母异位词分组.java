package tsp.leetcode.eyery;



import java.util.*;

/**
 * 49: 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 */
public class Solution101_49_字母异位词分组 {

    /* 总体思想就是异位的字符串 hash 到相同的key上*/

    /**
     * 解法三：我们的目的是判断两个字符串中的字符及数量相等
     * 采用算数基本定理：又称正整数的唯一分解定理，没个大于1的自然数，要么本身就是质数
     * 要么可以写为两个以上的质数的积，而且这些质因子按大小排列之后，写法仅有一种方式.
     * 我们将 26 个字母映射到一个正数上。
     * prime = {2,3,5,7,11,13,17,19,23,29,31,3741,43.......}
     * 然后把每个字符串的字符减去 'a'，在 prime中取出对应的质数，把它们累成。
     */
    public static List<List<String>> groupAnagrams3(String[] strs) {
        Map<Integer, List<String>> hash = new HashMap<>();
        //每个字母对应一个质数
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
        for (int i = 0; i < strs.length; i++) {
            int key = 1;
            //累乘得到 key
            for (int j = 0; j < strs[i].length(); j++) {
                key *= prime[strs[i].charAt(j) - 'a'];
            }
            if (!hash.containsKey(key)) hash.put(key, new ArrayList<>());
            hash.get(key).add(strs[i]);
        }
        return new ArrayList<List<String>>(hash.values());
    }


    /**
     * 官方题解二： 按计数分类 记录每个字母出现的次数
     * aabbcde 表示为 #2#2#1#1#1.....；防止直接用12311...可能产生hash冲突
     * 当且仅当它们的字符计数（每个字符的出现次数）相同时，两个字符串是字母异位词
     */
    public static List<List<String>> groupAnagrams1(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> ret = new HashMap<>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            char[] chars = s.toCharArray();
            for (char c : chars) {
                count[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ret.containsKey(key)) ret.put(key, new ArrayList<>());
            ret.get(key).add(s);
        }
        return new ArrayList<List<String>>(ret.values());
    }


    /**
     * leetcode 官方题解一  真好！！！
     * 思路：维护一个 hashmap 映射 Map<String,List>
     * key是每个字符串排序后的值，list是初始化的字符串
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> ret = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca); //对字符串进行排序 作为hashmap的key值
            String key = String.valueOf(ca);
            if (!ret.containsKey(key)) {
                ret.put(key, new ArrayList());
            }
            ret.get(key).add(s);
        }
        return new ArrayList<List<String>>(ret.values());
    }

}

//牛客网
class Solution101 {
    public ArrayList<String> anagrams(String[] strs) {
        ArrayList<String> result = new ArrayList<>();
        if (strs.length == 0) return result;
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.copyValueOf(chars);
            if (!map.containsKey(key)) map.put(key, new ArrayList<String>());
            map.get(key).add(s);
        }
        for (String s : map.keySet()) {
            ArrayList<String> list = map.get(s);
            if (list.size()>1) result.addAll(list);
        }
        return result;
    }
}
