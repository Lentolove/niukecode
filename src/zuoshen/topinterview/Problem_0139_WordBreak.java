package zuoshen.topinterview;

import java.util.HashSet;
import java.util.List;

/**
 * Author : tsp
 * Time: 2022/3/24 20:30
 * Desc :给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * <p>
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。注意，你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * <p>
 * 提示：
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 * <p>
 * Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 👍 1497 👎 0
 */
public class Problem_0139_WordBreak {



    public boolean wordBreak3(String s, List<String> wordDict) {
        return f(s,wordDict,0) != 0;
    }

    //index表示从[index.,.n-1]有多少种分解方法
    public int f(String s,List<String> wordDic,int index){
        if (index == s.length()){
            return 1;
        }
        int ways = 0;
        for (int end = index; end < s.length(); end++) {
            //[index,end]是否在字典里
            if (wordDic.contains(s.substring(index,end + 1))){
                ways += f(s,wordDic,end + 1);
            }
        }
        return ways;
    }

    public boolean dp(String s, List<String> wordDict) {
        int n =s.length();
        //dp[i] 就表示[i....]有多少种分解方法
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0 ; i--) {
            int ways = 0;
            for (int end = i; end < s.length(); end++) {
                //[index,end]是否在字典里
                if (wordDict.contains(s.substring(i,end + 1))){
                    ways += dp[end + 1];
                }
            }
            dp[i] = ways;
        }
        return dp[0] != 0;
    }



































    /**
     * 给定一个字符串，判断是否可以由字典中的单次组成，字典单次可以重复使用
     * <p>
     * 思路一：暴力递归，f(s,index)，从index开始往后的字符是否满足条件
     * 测试超时：该动态规划,只有一个可变参数
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return process(s, new HashSet<>(wordDict), 0) != 0;
    }

    /**
     * 进阶一点，判断有多少组合方案，大于0就表示可以分割
     */
    public int process(String s, HashSet<String> set, int index) {
        if (index == s.length()) {
            //是一个有效的分割
            return 1;
        }
        //没来到最后
        int ways = 0;
        for (int end = index; end < s.length(); end++) {
            //以[index..end]为开头继续尝试
            if (set.contains(s.substring(index, end + 1))) {
                ways += process(s, set, end + 1);
            }
        }
        return ways;
    }

    /**
     * 动态规划1.0，只有一个可变参数，一维dp问题
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        int n = s.length();
        //求分解的情况数,dp[i]表示以i开始的分解方法数，由暴力递归可知，i可以到n
        int[] dp = new int[n + 1];
        dp[n] = 1;
        //由暴力递归可知，当前位置dp[i] 与 dp[i..]后有关，所以动态规划从后往前填写
        for (int i = n - 1; i >= 0; i--) {
            int ways = 0;
            for (int end = i; end < n; end++) {
                //以[index..end]为开头继续尝试
                if (set.contains(s.substring(i, end + 1))) {
                    ways += dp[end + 1];
                }
            }
            dp[i] = ways;
        }
        return dp[0] != 0;
    }

    /**
     * 构建前缀树节点
     */
    public static class Node {
        //该path是否结束了，一个单词到末尾表示结束
        public boolean end;
        //因为题目规定是小写字母，所有每个节点下最多有26条路径
        public Node[] next;

        public Node() {
            end = false;
            next = new Node[26];
        }
    }

    /**
     * 利用前缀树来替代HashSet的匹配过程
     * label:前缀树
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        int n = s.length();
        //1.根据 wordDict 构建前缀树
        Node root = new Node();
        for (String word : wordDict) {
            char[] str = word.toCharArray();
            Node cur = root;
            for (char a : str) {
                int index = a - 'a';
                if (cur.next[index] != null) {
                    //当前节点有路径可选择
                    cur.next[index] = new Node();
                }
                //cur指针向下移动
                cur = cur.next[index];
            }
            //当前单词挂在完
            cur.end = true;
        }
        //开始dp过程啦
        int[] dp = new int[n - 1];
        char[] str = s.toCharArray();
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            Node cur = root;
            //在前缀树上查找是否有该单词
            for (int end = i; end < n; end++) {
                cur = cur.next[str[end] - 'a'];
                //如果当前路径不存在，则直接bread当前循环，表示不可能存在该路径
                if (cur == null) break;
                //如果是来到了单词的结尾，则将当前情况加到dp[i]
                if (cur.end) dp[i] += dp[end + 1];
            }
        }
        return dp[0] != 0;
    }


    public static void main(String[] args) {

    }
}
