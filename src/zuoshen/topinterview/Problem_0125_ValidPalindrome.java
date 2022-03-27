package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/25
 * DESC:给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * <p>
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 * <p>
 * 提示：
 * 1 <= s.length <= 2 * 10⁵
 * 字符串 s 由 ASCII 字符组成
 * <p>
 * Related Topics 双指针 字符串 👍 495 👎 0
 */
public class Problem_0125_ValidPalindrome {

    /**
     * 题意：验证一个字符串是否是回文
     * 思路：收尾指针一起移动，不相等就返回false，否则l++,r--
     * 注意特殊字符处理问题
     * 1.不同于寻找字符串的最长回文子串问题
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) return true;
        int n = s.length();
        if (n == 1) return true;
        int l = 0, r = n - 1;
        while (l < r) {
            char a = s.charAt(l);
            char b = s.charAt(r);
            if (isValid(a) && isValid(b)) {
                if (!isEqual(a, b)) return false;
                l++;
                r--;
            } else {
                l += isValid(a) ? 0 : 1;
                r -= isValid(b) ? 0 : 1;
            }
        }
        return true;
    }

    public static boolean isValid(char a) {
        return isLetter(a) || isNumber(a);
    }


    public static boolean isEqual(char a, char b) {
        if (isNumber(a) && isNumber(b)) {
            return a == b;
        }
        if (isLetter(a) && isLetter(b)) {
            return a == b || Math.abs(a - b) == 32;
        }
        return false;
    }

    public static boolean isNumber(char a) {
        return a >= '0' && a <= '9';
    }

    public static boolean isLetter(char a) {
        return a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z';
    }

    public static void main(String[] args) {
        String s = "0P";
        System.out.println(isPalindrome(s));
    }

}
