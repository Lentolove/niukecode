package tsp.leetcode.dynamic_planning;

/**
 * 题目：palindrome-partitioning-ii
 * 题目描述：给定字符串s,分区s使得分区的每个子字符串都是回文。返回s的回文分区所需的最小割。
 * 例子：s = "aab"
 * 由于回文分区["aa"，"b"] 可以使用1 cut生成。
 *
 *  dp[i]表示s.substring(0, i + 1)子串的回文最小切割数，
 *  (Java子字符串函数满足左闭右开特点，i+1表示包含第i个字符)
 *  显然最终结果为dp[s.length - 1]。
 *
 * 确认状态转移方程：
 *    当s.substring(j, i + 1) (1 <= j <= i)是回文时，
 *    dp[i] = min(dp[i], dp[j - 1] + 1)。
 *    即用变量j遍历i之前的每个子串，注意判断是否回文，是则重新给dp[i]赋值
 */
public class Solution19 {



    public int minCut(String s) {
        if (s.length() <2) return 0;
        int len = s.length();
        int[] dp = new int[len];    //转态转移方程
        for (int i = 1; i < len; i++) {
            //如果字符串是回文，则不需要切割 dp[i]=0，否则定义最大切割数dp[i]=1
            dp[i] = ispld(s.substring(0,i+1))?0:i;
            if (dp[i]!=0){
                /** s.substring(0,i+1)不是回文
                 * 此时dp[i] = i ，是最坏的情况
                 * 字符串为左开又闭  所以切割为 j->i+1
                 * 判断  0<=j<=i 内部是否有回文
                 */
                for (int j = i; j >0 ; j--) {
                    if (ispld(s.substring(j,i+1))){
                        // 字符串：0-i+1被分成两部分 dp[j-1]  j->i+1 是回文 1：表示j->i+1是回文 不用切了 dp[i] = dp[j-1]+1
                        dp[i] = Math.min(dp[i],dp[j-1]+1);
                    }
                }
            }
        }

        return dp[s.length()-1];
    }

    //判断字符串是否是回文
    private static boolean ispld(String s){
        int len = s.length();
        for (int i = 0; i < len/2; i++) {
            if (s.charAt(i)!=s.charAt(len-i-1)){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String s = "abba";
        System.out.println(ispld(s));
    }
}
