package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * author : tsp
 * Date : 2022/3/27
 * DESC:给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可
 * 能的句子。注意：词典中的同一个单词可能在分段中被重复使用多次。
 * <p>
 * 示例 1：
 * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * 输出:["cats and dog","cat sand dog"]
 * <p>
 * 示例 2：
 * 输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine",
 * "pineapple"]
 * 输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * 解释: 注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * 输出:[]
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中所有字符串都 不同
 * <p>
 * Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 回溯 👍 574 👎 0
 */
public class Problem_0140_WordBreakII {


    /**
     * 给定字符串和字典，按照字典中有的情况进行划分，返回分的所有情况
     */
    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        process(s, ans, new ArrayList<>(), 0, new HashSet<>(wordDict));
        return ans;
    }

    /**
     * 思路一：暴力递归
     */
    public static void process(String s, List<String> ans, List<String> path, int index, HashSet<String> set) {
        if (index == s.length()) {
            //一个有效方案
            StringBuilder sb = new StringBuilder();
            for (String item : path) {
                sb.append(item).append(" ");
            }
            String result = sb.substring(0, sb.length() - 1);
            ans.add(result);
            return;
        }
        for (int end = index; end < s.length(); end++) {
            String item = s.substring(index, end + 1);
            if (set.contains(item)) {
                path.add(item);
                process(s, ans, path, end + 1, set);
                //恢复现场
                path.remove(path.size() - 1);
            }
        }
    }


    /**
     * 构建字典的前缀树，加速查找过程，并记录当前path
     */
    public static class Node {
        public String path;//当前路径
        public boolean end;//当前节点是否是叶子节点，既是否是单词的结尾
        public Node[] next;//当前节点的下一节路径

        public Node() {
            path = null;
            end = false;
            next = new Node[26];
        }
    }

    /**
     * 对于收集List这种问题，通常都采取递归，但是在 set.contains(item) 这种过程中
     * 可以用前缀树 + dp 来加速
     * 1.前缀树用来加速查询当前 item 字符是否在字典中，用 dp[j] 来判断后续是否能有效分隔
     * 来做一些剪枝操作
     */
    public static List<String> wordBreak1(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        Node root = getTrie(wordDict);
        boolean[] dp = getDp(s, root);
        process1(s.toCharArray(),ans,root,dp,new ArrayList<>(),0);
        return ans;
    }

    public static void process1(char[] str,List<String> ans,Node root,boolean[] dp,List<String> path,int index){
        if (index == str.length) {
            //一个有效方案
            StringBuilder sb = new StringBuilder();
            for (String item : path) {
                sb.append(item).append(" ");
            }
            String result = sb.substring(0, sb.length() - 1);
            ans.add(result);
            return;
        }
        //利用前缀树
        Node cur = root;
        for (int end = index; end < str.length; end++) {
            int road = str[end] - 'a';//当前前缀树中选择的路
            if (cur.next[road] == null){
                //当前没有有效路径
                break;
            }
            cur = cur.next[road];
            //如果是叶子节点
            if (cur.end && dp[end + 1]){
                //有效的方案
                path.add(cur.path);
                process1(str,ans,root,dp,path,end + 1);
                //恢复现场
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 根据字典list结合构建前缀树
     */
    public static Node getTrie(List<String> wordDic) {
        Node root = new Node();
        for (String s : wordDic) {
            char[] str = s.toCharArray();
            Node cur = root;
            for (char a : str) {
                //是否存在当前路径，在next分支中查找
                int index = a - 'a';
                if (cur.next[index] == null) {//判断当前节点是否有这条路径，没有就创建
                    cur.next[index] = new Node();
                }
                cur = cur.next[index];
            }
            //遍历完成来到叶子节点，记录当前路径
            cur.path = s;
            cur.end = true;
        }
        return root;
    }

    /**
     * 根据前缀树来计算dp
     * dp[i] 表示从[i..n-1]能否被字典中单词分解
     */
    public static boolean[] getDp(String s, Node root) {
        int n = s.length();
        char[] str = s.toCharArray();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            Node cur = root;
            int start = str[i] - 'a';
            //以当前字母为开头的就不存在，直接下一个
            if (cur.next[start] == null) continue;
            for (int end = i; end < n; end++) {
                //当前[i - end] 是否存在
                int index = str[end] - 'a';
                if (cur.next[index] == null) {
                    //当前路径不存在，直接break
                    break;
                }
                //存在
                cur = cur.next[index];
                //如果是叶子节点，并且[end+1...n]可以有效分解
                if (cur.end && dp[end + 1]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> list = new ArrayList<>();
        list.add("cat");
        list.add("cats");
        list.add("and");
        list.add("sand");
        list.add("dog");
        List<String> result = wordBreak1(s, list);
        for (String s1 : result) {
            System.out.println(s1);
        }


    }


}
