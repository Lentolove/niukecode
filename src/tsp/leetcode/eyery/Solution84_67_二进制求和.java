package tsp.leetcode.eyery;

/**
 * 67:二进制求和
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 输入为非空字符串且只包含数字 1 和 0。
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 */
public class Solution84_67_二进制求和 {


    /**
     * 自己写的  哎  贼繁琐，运行内存溢出
     */
    public static String addBinary(String a, String b) {
        if (a == null || b == null) return a == null ? b : a;
        StringBuilder sb = new StringBuilder();
        int p_a = a.length() - 1;
        int p_b = b.length() - 1;
        int flag = 0;//进位
        while (p_a >= 0 || p_b >= 0) {
            if (p_a >= 0 && p_b >= 0) {
                int x = (a.charAt(p_a--) - '0') + (b.charAt(p_b--) - '0') + flag;
                flag = x / 2;
                sb.append(x % 2);
            } else if (p_a >= 0) {
                if (flag == 1) {
                    sb.append((a.charAt(p_a) - '0') ^ flag);
                    flag = a.charAt(p_a--) & flag; // 1&1才有进位位
                } else {
                    sb.append(a.substring(0, p_a + 1));
                }
            } else {
                if (flag == 1) {
                    sb.append((b.charAt(p_b) - '0') ^ flag);
                    flag = (b.charAt(p_b--) - '0') & flag; // 1&1才有进位位
                } else {
                    sb.append(b.substring(0, p_b + 1));
                }
            }
        }
        if (flag == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    /**
     * 同样的思路
     * 短字符串前面补零
     * 别人写的代码就是简单明了。
     */
    public String addBinary1(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            sb.append(sum % 2);
            ca = sum / 2;
        }
        sb.append(ca == 1 ? ca : "");
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        System.out.println(addBinary(a, b));
    }
}
