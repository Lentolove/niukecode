package tsp.leetcode.nums;

/**
 * 28:实现strStr
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 */
public class Solution122_28_实现strStr {

    public static int strStr(String haystack, String needle) {
        int n1 = haystack.length();
        int n2 = needle.length();
        if (n1 < n2) {
            return -1;
        } else if (n2 == 0) {
            return 0;
        }
        for (int i = 0; i < n1 - n2 + 1; i++) {
            if (haystack.substring(i, i + n2).equals(needle)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "mississippi";
        String b = "issip";
        System.out.println(strStr(s, b));
    }

}

class s {
    public String strStr(String s, String t) {
        if (s == null || t == null) return null;
        if (t.length() == 0) return s;
        int n1 = s.length();
        int n2 = t.length();
        if (n1 < n2) return null;
        for (int i = 0; i < n1 - n2 + 1; i++) {
            if (s.substring(i, i + n2).equals(t)) return s.substring(i);
        }
        return null;
    }
}
