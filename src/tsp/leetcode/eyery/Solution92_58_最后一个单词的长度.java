package tsp.leetcode.eyery;

/**
 * 58: 最后一个单词的长度
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * 如果不存在最后一个单词，请返回 0 。
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 * 示例:
 * <p>
 * 输入: "Hello World"
 * 输出: 5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/length-of-last-word
 */
public class Solution92_58_最后一个单词的长度 {

    /**
     * 借助了库函数
     */
    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() < 1) return 0;
        String[] split = s.split(" ");
        return split.length == 0 ? 0 : split[split.length - 1].length();
    }

    //从后往前遍历
    public static int lengthOfLastWord1(String s) {
        int len = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                len++;
            } else {
                if (len > 0) break;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(" ".length());
        //System.out.println(lengthOfLastWord1(" "));
    }
}
