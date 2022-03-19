package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/17
 * DESC:给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * <p>
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * <p>
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * 提示：
 * 1 <= s.length, t.length <= 10⁵
 * s 和 t 由英文字母组成
 * <p>
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 👍 1719 👎 0
 */
public class Problem_0076_MinimumWindowSubstring {

    /**
     * 思路：欠账本 + 双指针 + Hash表
     */
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        char[] str = s.toCharArray();
        char[] sub = t.toCharArray();
        int n = str.length, m = sub.length;
        //定义欠账本map
        int[] map = new int[256];
        int total = m;
        //记录sub的词频
        for (char c : sub) {
            map[c]++;
        }
        //定义滑动窗口指针
        int l = 0, r = 0;
        int len = -1, left = -1, right = -1;//记录结果
        while (r < n) {
            //当前来到r位置，map词频--
            map[str[r]]--;
            //1.如果当前词频是 >= 0的，说明是 sub 中的
            if (map[str[r]] >= 0) {
                //当前遇到了一个目标字符
                total--;
            }
            //如果total = 0，表示sub目标字符都找到了
            if (total == 0) {
                //开始移动左指针，左指针移动还账，如果当前map[l]词频小于0，表示是之前过程中欠的不相干字符，左指针移动时候还回去
                while (map[str[l]] < 0) {
                    map[str[l++]]++;
                }
                //从上面的while循环出来，则当前来到的位置就是囊括sub的一个边界窗口了
                if (len == -1 || len > r - l + 1) {//更新
                    len = r - l + 1;
                    left = l;
                    right = r;
                }
                //此时当前l指针当前的字符需要从窗口中移除，左指针移动还账，词频++
                map[str[l++]]++;
                total++;
            }
            //继续移动r指针
            r++;
        }
        return len == -1 ? "" : String.copyValueOf(str, left, len);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

}
