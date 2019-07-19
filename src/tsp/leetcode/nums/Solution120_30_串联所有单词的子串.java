package tsp.leetcode.nums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 30：串联所有单词的子串
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 示例 1：
 * <p>
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * <p>
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 */
public class Solution120_30_串联所有单词的子串 {

    /**
     * 方法一：此题用HashMap1保存字典 word 毫无疑问，但是有几个问题需要注意
     * <p>
     * 1.HashMap中的每个单词及个数都要在当前搜索的子串中，并且长度与子串的长度相等。
     * 2.在迭代子串的过程中，每次迭代的长度必须为 words.size(). 子串中每次切割的单词长度与字典
     * 中的单词长度相同，它们的长度都是固定的。
     * 3.我们在比较子串和map是否相等的时候，可以将子串的每个单词存储到另一个hashmap2中，查询hashmap1中是否包含
     * 次单词，并且它们的个数在遍历完子串的时候需要相等。
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> output = new ArrayList<>();
        //单词的个数
        int wordNum = words.length;
        if (wordNum == 0) return output;
        //单词长度
        int wordLen = words[0].length();
        //每个子串的长度
        int len = wordLen * wordNum;
        //将字典中的单词存储到hashmap1中
        HashMap<String, Integer> hashMap1 = new HashMap<>();
        for (int i = 0; i < wordNum; i++) {
            hashMap1.put(words[i], hashMap1.getOrDefault(words[i], 0) + 1);
        }
        //遍历所有子串
        for (int i = 0; i < s.length() - len + 1; i++) {
            //HashMap2中存储当前扫描的字符串包含的单词
            HashMap<String, Integer> hashMap2 = new HashMap<>();
            int num = 0;//记录符合单词的个数
            while (num < wordNum) {
                //切割出的每个单词
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                if (hashMap1.containsKey(word)) { //包含单词
                    hashMap2.put(word, hashMap2.getOrDefault(word, 0) + 1);
                    //判断当前单词的vale和hashmap1中的单词的value,如果map2中的value比map1中的还要大，那肯定不满足了
                    if (hashMap1.get(word) < hashMap2.get(word)) break;
                } else {
                    break;
                }
                //这两个break用的精髓啊，
                num++;
            }
            if (num == wordNum) output.add(i);
        }
/*  上面也可以这样写 比较两个hashmap更耗时 但是这样写好像耗时更长
        for(int i = 0; i<s.length()-len +1;i++){
            //HashMap2中存储当前扫描的字符串包含的单词
            HashMap<String, Integer> hashMap2 = new HashMap<>();
            String temp = s.substring(i, i + len);
            for (int j = 0; j < len; j += wordLen) {
                String w = temp.substring(j, j + wordLen);
                hashMap2.put(w, hashMap2.getOrDefault(w, 0) + 1);
            }
            if (hashMap1.equals(hashMap2)) output.add(i); //比较两个hashmap是否相等 比较key和value
        }
*/
        return output;
    }

    /**
     * 解法一中每次只移动一个单词 现在我们每次移动一个单词的长度，但是这个要分情况讨论
     * 1.当子串完全匹配的时候，移动到下一个子串的时候
     * 2.在判断过程中，出现不符合的单词
     * 3.判断过程中，出现的是符合的单词，但是次数超了
     * 这是一个滑动窗口问题
     */
    public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //将所有移动分成 wordLen 类情况
        for (int j = 0; j < wordLen; j++) {
            HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
            int num = 0; //记录当前 HashMap2（这里的 hasWords 变量）中有多少个单词
            //每次移动一个单词长度
            for (int i = j; i < s.length() - wordNum * wordLen + 1; i = i + wordLen) {
                boolean hasRemoved = false; //防止情况三移除后，情况一继续移除
                while (num < wordNum) {
                    String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                    if (allWords.containsKey(word)) {
                        int value = hasWords.getOrDefault(word, 0);
                        hasWords.put(word, value + 1);
                        //出现情况三，遇到了符合的单词，但是次数超了
                        if (hasWords.get(word) > allWords.get(word)) {
                            // hasWords.put(word, value);
                            hasRemoved = true;
                            int removeNum = 0;
                            //一直移除单词，直到次数符合了
                            while (hasWords.get(word) > allWords.get(word)) {
                                String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
                                int v = hasWords.get(firstWord);
                                hasWords.put(firstWord, v - 1);
                                removeNum++;
                            }
                            num = num - removeNum + 1; //加 1 是因为我们把当前单词加入到了 HashMap 2 中
                            i = i + (removeNum - 1) * wordLen; //这里依旧是考虑到了最外层的 for 循环，看情况二的解释
                            break;
                        }
                        //出现情况二，遇到了不匹配的单词，直接将 i 移动到该单词的后边（但其实这里
                        //只是移动到了出现问题单词的地方，因为最外层有 for 循环， i 还会移动一个单词
                        //然后刚好就移动到了单词后边）
                    } else {
                        hasWords.clear();
                        i = i + num * wordLen;
                        num = 0;
                        break;
                    }
                    num++;
                }
                if (num == wordNum) {
                    res.add(i);

                }
                //出现情况一，子串完全匹配，我们将上一个子串的第一个单词从 HashMap2 中移除
                if (num > 0 && !hasRemoved) {
                    String firstWord = s.substring(i, i + wordLen);
                    int v = hasWords.get(firstWord);
                    hasWords.put(firstWord, v - 1);
                    num = num - 1;
                }

            }

        }
        return res;
    }
}



