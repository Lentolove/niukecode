package tsp.leetcode.nums;

/**
 * 14:最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 */
public class Solution135_14_最长公共前缀 {

    /**
     * 方法一：水平扫描
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        //最长的前缀的长度 <= 最短单词的长度
        for (int i = 0; i < strs[0].length(); i++) {
            //直接以第一个字符为准就行 反正是公共前缀
            // 在扫描strs的数组中如果出现某个字符串的第i个字符比匹配，则直接范围0——(i-1)字符
            char c = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    //同上
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(ans) != 0) {
                ans = ans.substring(0, ans.length() - 1);
                if (ans.isEmpty()) return "";
            }
        }
        return ans;
    }
}

/**
 * 很标准的分治法
 * 可能在本题效率不是最高的，但是思想很好
 */
class Method1 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        return helper(strs, 0, strs.length - 1);
    }

    private String helper(String[] strs, int left, int right) {
        if (left == right) return strs[left];
        else {
            int mid = (left + right) / 2;
            String lcpLeft = helper(strs, left, mid);
            String lcpRight = helper(strs, mid + 1, right);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    //求公共前缀
    private String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
    }
}

/**
 * 方法三：二分查找的思想
 * 首先找到最短的字符串的长度
 * 然后取一般 判断公共前缀是长了还是短了
 */
class Method2{

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        int minLen = strs[0].length();
        for (String s : strs) {
            minLen = Math.min(minLen,s.length());
        }
        int low = 1;
        int hight = minLen;
        while (low <= hight){
            int middle = (low+hight)/2;
            if (isCommonPrefix(strs,minLen)){
                low = middle+1;
            }else {
                hight = middle -1;
            }
        }
        return strs[0].substring(0,(low+hight)/2);
    }

    private boolean isCommonPrefix(String[] strs, int len){
        String str = strs[0].substring(0,len);
        for (int i = 1; i< str.length(); i++) {
            if (!strs[i].startsWith(str)) return false;
        }
        return true;
    }
}