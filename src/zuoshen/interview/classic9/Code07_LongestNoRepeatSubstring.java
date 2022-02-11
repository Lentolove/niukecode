package zuoshen.interview.classic9;

import java.util.HashMap;

/**
 * 左神：在一个字符串中找到没有重复字符子串中最长的长度：
 * eg：abcabcbb 没有重复字符的最长子串是 abc,长度为 3
 * bbbbb,答案是b,长度为是1
 * pwwkew，答案是wke，长度是3
 * 要求：答案必须是子串，"pwke"不是子串
 */
public class Code07_LongestNoRepeatSubstring {

    public static int maxUnique(String str) {
        if (str == null || str.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int pre;//表示当前字符最近出现的位置
        int len = 0;
        int cur;
        for (int i = 0; i < str.length(); i++) {
            //1.获取当前字符之前的位置,如果不存在则为 -1
            pre = map.getOrDefault(str.charAt(i), -1);
            //2.当前的总长度为,如果之前没出现过
            cur = i - pre;
            len = Math.max(cur, len);
            //3.将当前元素放入map中
            map.put(str.charAt(i), i);
        }
        return len;
    }

    // for test
    public static String getRandomString(int len) {
        char[] str = new char[len];
        int base = 'a';
        int range = 'z' - 'a' + 1;
        for (int i = 0; i != len; i++) {
            str[i] = (char) ((int) (Math.random() * range) + base);
        }
        return String.valueOf(str);
    }

    // for test
    public static String maxUniqueString(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = -1;
        int pre = -1;
        int cur = 0;
        int end = -1;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            if (cur > len) {
                len = cur;
                end = i;
            }
            map[chas[i]] = i;
        }
        return str.substring(end - len + 1, end + 1);
    }

    public static void main(String[] args) {
        String str = getRandomString(20);
        System.out.println(str);
        System.out.println(maxUnique(str));
        System.out.println(maxUniqueString(str));
    }

}
