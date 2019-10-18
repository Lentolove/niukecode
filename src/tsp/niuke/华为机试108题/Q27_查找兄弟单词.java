package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 输入描述:
 * 先输入字典中单词的个数n，再输入n个单词作为字典单词。
 * 再输入一个单词，查找其在字典中兄弟单词的个数m
 * 再输入数字k
 * <p>
 * 输出描述:
 * 根据输入，输出查找到的兄弟单词的个数m
 * 然后输出查找到的兄弟单词的第k个单词。
 */
public class Q27_查找兄弟单词 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            String[] strs = str.split(" ");
            int n = Integer.parseInt(strs[0]);
            String[] dic = new String[strs.length - 3];
            for (int i = 0; i < dic.length; i++) {
                dic[i] = strs[i + 1];
            }
            String word = strs[strs.length - 2];//要查找的单词
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            int k = Integer.parseInt(strs[strs.length - 1]);//兄弟单词的第k个单词
            Arrays.sort(dic);
            int count = 0;//兄弟单词的个数
            String target = null;
            for (int i = 0; i < n; i++) {
                if (dic[i].equals(word)) {
                    continue;
                }
                if (idBrother(dic[i], chars)) {
                    count++;
                    if (count == k) {
                        target = dic[i];
                    }

                }
            }
            System.out.println(count);
            if (target != null) {
                System.out.println(target);
            }

        }
    }

    private static boolean idBrother(String s1, char[] word) {
        char[] chars = s1.toCharArray();
        if (s1.length() != word.length) return false;
        Arrays.sort(chars);
        for (int i = 0; i < word.length; i++) {
            if (chars[i] != word[i]) return false;
        }
        return true;
    }
}
