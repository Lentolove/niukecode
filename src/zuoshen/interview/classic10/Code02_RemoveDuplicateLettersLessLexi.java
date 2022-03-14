package zuoshen.interview.classic10;

/**
 * 左神：给定一个全是小写字母的字符串 str,删除多余的字符，使得每种字符只保留一个，并让最终结果字符串的字典序最小
 * eg: str = "acbc"，删掉第一个 'c'，得到 'abc'，是所有字符串中字典序最小的
 * str = "bdcacbca",删除第一个'b'，第一个'c'，第二个'c'，第二个'a'，得到 'dabc'，是所有结果字符串中字典序最小的
 */
public class Code02_RemoveDuplicateLettersLessLexi {

    /**
     * 思路：在str中，每种字符都要保留一个，让最后的结果，字典序最小 ，并返回
     * 1.首先通过Map统计每个字符的词频，然后从左往右开始遍历删除对应的字符，并修改词频，
     * 2.如果 str[i] 该字符词频降为0，则不能继续往后遍历了，后面再无该字符；
     * 3.找到[0,i - 1]位置字典序最小的字符 str[j] = x 保留,删除[j + 1,n - 1] 中所有的 x 字符，
     * 4.继续继续遍历 [j + 1， m - 1]后的字符继续过程
     * 复杂度：每次确定一个字符，O(k*n)，k表示不同字符的种类
     */
    public static String removeDuplicateLetters1(String str) {
        if (str == null || str.length() < 2) return str;
        //1.可以用数组代替HashMap来统计词频
        int[] map = new int[256];
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i)]++;
        }
        //2.开始遍历str，直至遇到第一个词频降为0的字符，停止当前删除降频行为
        int minASCIndex = 0;//字典序最小的字符出现的位置
        for (int i = 0; i < str.length(); i++) {
            minASCIndex = str.charAt(minASCIndex) > str.charAt(i) ? i : minASCIndex;
            if (--map[str.charAt(i)] == 0) {
                //词频降为0了，保留 minASCIndex 处的字符
                break;
            }
        }
        //4.确定一个字符后 x 后，删除后面所有的 x
        return str.charAt(minASCIndex) + removeDuplicateLetters1(str.substring(minASCIndex + 1)
                .replaceAll(String.valueOf(str.charAt(minASCIndex)), ""));
    }

    /**
     * 思路二：迭代法，思路与递归一样
     */
    public static String removeDuplicateLetters2(String s) {
        char[] str = s.toCharArray();
        //1.小写字母ascii码值范围[97~122]，所以用长度为26的数组做次数统计
        //如果map[i] > -1，则代表ascii码值为i的字符的出现次数
        //如果map[i] == -1，则代表ascii码值为i的字符不再考虑
        int[] map = new int[26];
        //2.统计词频
        for (char c : str) map[c - 'a']++;
        //3.记录结果的字符数组
        char[] ans = new char[26];
        int index = 0;//向ans填写保留字符的索引
        int l = 0, r = 0;
        while (r < str.length) {
            //1.如果当前字符对应的map[r] = -1，表示不在考虑，直接跳过
            //如果当前字符对应的map[r]次数减1后 > 0，则表示后面还能再出现，则直接跳过
            if (map[str[r] - 'a'] == -1 || --map[str[r] - 'a'] > 0) {
                r++;
            } else {
                // 当前字符需要考虑并且之后不会再出现了,在str[L..R]上所有需要考虑的字符中，找到ascii码最小字符的位置
                int pick = -1;
                //在区间 [l,r]中找到词频不为 -1，并且字典序最小的字符挑选出来
                for (int i = l; i <= r; i++) {
                    if (map[str[i] - 'a'] != -1 && (pick == -1 || str[i] < str[pick])) {
                        pick = i;
                    }
                }
                ans[index++] = str[pick]; //把ascii码最小的字符放到挑选结果中
                //在跳入到 else 分支之前，将 [l,r]之间的字符词频都减少了，现在[l,pick]之间字符不考虑了，直接删掉了，所以不用加回来
                //而[pick + 1,r]之间需要继续上述过程，找到第二个，所以把之前减去的词频加回来
                for (int i = pick + 1; i <= r; i++) {
                    //注意 只增加以后需要考虑字符的次数
                    if (map[str[i] - 'a'] != -1) {
                        map[str[i] - 'a']++;
                    }
                }
                // 选出的ascii码最小的字符，以后不再考虑了，即将[pick + 1,r] 中已经选中的字符变为 - 1，不在考虑
                //等同于方法一中的 将已经确定的字符删除，替换为空
                map[str[pick] - 'a'] = -1;
                //继续上述过程
                l = pick + 1;
                r = l;
            }
        }
        return String.valueOf(ans, 0, index);
    }


    public static void main(String[] args) {
        String str = "daccbdbaccdbba";
        System.out.println(removeDuplicateLetters2(str));
        System.out.println(removeDuplicateLetters1(str));
    }

}
