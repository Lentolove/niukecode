package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/11 11:10
 * Desc : 实现 strStr() 函数。
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如
 * 果不存在，则返回 -1 。
 * <p>
 * 说明：当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * <p>
 * 示例 1：
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * <p>
 * 示例 3：
 * <p>
 * 输入：haystack = "", needle = ""
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * 0 <= haystack.length, needle.length <= 5 * 10⁴
 * haystack 和 needle 仅由小写英文字符组成
 * <p>
 * Related Topics 双指针 字符串 字符串匹配 👍 1302 👎 0
 */
public class Problem_0028_ImplementStrStr {

    /**
     * 标准的kmp算法
     */
    public static int getIndexOf(String s, String m) {
        //1.大的过滤条件
        if (s == null || m == null ||  s.length() < m.length()) return -1;
        if (m.isEmpty()) return 0;
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        //设置 str 和 match 的移动指针,即当前比对到的位置
        int x = 0;
        int y = 0;
        //获取 match 字符数组的前缀和
        int[] next = getNextArray(match);
        while (x < str.length && y < match.length) {
            //如果当前字符相等
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {//不相等，就看一下next的前缀和
                //2. - 1 表示 match 中指针已经走到其实位置了，等价于 y == 0,那直接选择str的下一个字符重新来
                x++;
            } else {
                //3. kmp 算法加速的地方，根据 next 的前缀和来优化y指针的位置，暴力方法直接从 match[0] 位置开始匹配
                //而从前缀和 a = next[y],可知 match[0...a-1] 部分的字符是和 match[y - a ,y -1]字符是相等的，这也是前缀和的定义所在
                //又因为 str[x - a,x - 1] 部分字符与 match[y - a ,y -1]字符相等，这是我们刚移动指针一步步匹配到 x,y位置才发现不相等。
                //所以 str[x - a,x - 1] 与 match [0...a-1] 字符相等，直接从  str[x] 位置与 match[a] 位置开始比较，而不是从 match[0]
                //位置开始比较
                y = next[y];
            }
        }
        return y == match.length ? x - y : -1;
    }


    /**
     * 获取 match 字符串的前缀和：
     * 前缀和的意思：给定字符串"abcabctx"，0位置规定为-1,1位置为0
     * 1.next[i]表示[0,i-1]的字符的最长前缀的长度：[0,i-1]中，next[4]的前缀为"abca"
     * 2.前缀为"abca",1.长度不能超过自身，前缀与后坠相等, 0-0:a 和3-3:a 所以next[4]位置最长前缀和为1
     * 3.next[5],"abcabc"最长前缀和：0-1:ab，3-4：ab，所以最长前缀和为2
     * match:  "a  b  c  a  b  c  t  x"
     * next:  [-1, 0, 0, 0, 1, 2, 3, 0]
     */
    public static int[] getNextArray(char[] m) {
        if (m.length == 1) return new int[]{-1};
        int n = m.length;
        int[] next = new int[n];
        next[0] = -1;
        next[1] = 0;
        //因为0,1位置规定前缀和就是-1,0,所以从 i = 2 位置开始
        int i = 2;
        //cn 表示 cn 位置的字符，是当前 和 i - 1位置比较的字符
        int cn = 0;
        while (i < n) {
            if (m[cn] == m[i - 1]) {
                //当前字符相等，移动指针，说明前缀和匹配成功
                cn++;
                next[i] = cn;
                i++;
                // i= 4时候，ms[0] = ms[3] = a,此时找到i位置的前缀和为1，
            } else if (cn > 0) {
                //     * match:  "a  b  c  a  b  c  t  x"
                //     * next:  [-1, 0, 0, 0, 1, 2, 3, 0]
                cn = next[cn];
            } else {
                //cn为0了
                next[i++] = 0;
            }
        }
        return next;
    }

}
