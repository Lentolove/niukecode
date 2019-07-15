package tsp.leetcode.eyery;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 76.最小覆盖子串 (Hard)  label: 滑动窗口
 * <p>
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 */
public class Solution73_76 {

    /**
     * 滑动窗口
     * 重要思想：在滑动窗口类型的问题中都会有两个指针。一个用于延伸现有窗口的right指针
     * 和一个用于收缩窗口的lft指针。在任意时刻只有一个指针运动，另一个保持静止
     */
    public String minWindow(String S, String T) {
        if (S.length() == 0 || T.length() == 0) return "";
        //定义一个map存储T中的每一个字符
        Map<Character, Integer> map = new HashMap<>();
        //定义一个 windCounts 存储 S 中的每一个字符
        Map<Character, Integer> windCounts = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            // getOrDefault(k,value) 如果key存在则返回其值否则返回默认给的值 value
            int count = map.getOrDefault(T.charAt(i), 0);
            map.put(T.charAt(i), count + 1);
        }
        //t中唯一字符的数量，这些字符需要出现在所需的窗口中。
        int require_len = map.size();
        //定义滑动串口的左右指针
        int l = 0, r = 0;
        //记录 window 中已经有多少字符匹配
        int form = 0;

        //窗口的长度 左指针，右指针
        int[] ans = {-1, 0, 0};
        while (r < S.length()) {
            char c = S.charAt(r);
            int count = windCounts.getOrDefault(c, 0);
            windCounts.put(c, count + 1);
            //如果添加的当前字符的频率等于t中的期望计数，则将形成的计数增加1。
            if (map.containsKey(c) && windCounts.get(c).intValue() == map.get(c).intValue()) {
                form++;
            }

            while (l <= r && form == require_len) {
                c = S.charAt(l); //左指针指向的字符
                //存储最小窗口
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
                //符号所指向的位置上的字符“左”指针不再是窗口的一部分。
                windCounts.put(c, windCounts.get(c) - 1);
                if (map.containsKey(c) && windCounts.get(c).intValue() < map.get(c).intValue()) {
                    form--;
                }
                //向后移动左指针
                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : S.substring(ans[1], ans[2] + 1);
    }

    /**
     * 针对本题优化的 滑动窗口
     * 定义一个 Pair<int,Character> 存储 T 中包含的字符在 S 中的索引下标
     * file_list = [(0,'A'),(1,'B')...  ]
     */
    public String minWindow1(String S, String T) {
        if (S.length() == 0 || T.length() == 0) return "";
        Map<Character, Integer> dicT = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            dicT.put(T.charAt(i), dicT.getOrDefault(T.charAt(i), 0) + 1);
        }
        int require_len = dicT.size();
        //将 S 中的所有字符及其索引过滤到一个新列表中。过滤标准是字符应该出现在  T 中。
        List<Pair<Integer, Character>> list = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (dicT.containsKey(c)) {
                list.add(new Pair<>(i, c));
            }
        }
        //定义滑动串口的指针
        int l = 0, r = 0, match = 0;
        //定义一个 windCounts 存储 S 中的每一个字符
        Map<Character, Integer> windCounts = new HashMap<>();
        int[] ans = {-1,0,0};
        while (r<S.length()){
            char c = list.get(r).getValue();
            int count = windCounts.getOrDefault(c,0);
            windCounts.put(c,count+1);
            if (dicT.containsKey(c)&&windCounts.get(c).intValue()==dicT.get(c).intValue()){
                match++;
            }
            while (l<=r&&match==require_len){
                c = list.get(l).getValue();
                //更新最小窗口和指针
                int end = list.get(r).getKey();
                int start = list.get(l).getKey();
                if (ans[0]==-1||end-start+1<ans[0]){
                    ans[0] = end - start +1;
                    ans[1] = start;
                    ans[2] = end;
                }
                windCounts.put(c,windCounts.get(c)-1);
                if (dicT.containsKey(c)&&windCounts.get(c).intValue()<dicT.get(c).intValue()){
                    match--;
                }
                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : S.substring(ans[1], ans[2] + 1);
    }


    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        Solution73_76 s1 = new Solution73_76();
        System.out.println(s1.minWindow(s, t));

    }

}
