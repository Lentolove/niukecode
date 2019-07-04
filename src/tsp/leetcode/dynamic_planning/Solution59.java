package tsp.leetcode.dynamic_planning;

/**
 *  一种包含字母从 A - Z 编码到数字的消息，使用以下映射：
 *  'A' -> 1
 *  'B' -> 2
 *  ...
 *  'Z' -> 26
 *  给定包含数字的编码消息，确定解码方法的总数。
 *  例如，给定编码的电文“12”，可以解码为“AB”(12)或“L”(12)。解码“12”的方式数为2。
 */
public class Solution59 {

    public static int numDecodings(String s) {
        if (s==null||s.length()==0||s.charAt(0)=='0') return 0;
        int[] dp = new int[s.length()+1];
        /**
         *  dp[i] 表示 s[0 -> i-1] 有多少种解码方法
         *  如果： 1
         */
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <=s.length() ; i++) {
            if (s.charAt(i-1) >='1'&& s.charAt(i-1)<='9')  dp[i] +=dp[i-1];
            if (Integer.valueOf(s.substring(i-2,i)) >=10 &&Integer.valueOf(s.substring(i-2,i))<=26){
                dp[i] += dp[i-2];
            }

        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "110";
        int i = numDecodings(s);
        System.out.println(i);
    }

}
