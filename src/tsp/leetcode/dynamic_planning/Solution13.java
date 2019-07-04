package tsp.leetcode.dynamic_planning;

import java.util.HashSet;
import java.util.Set;

public class Solution13 {


    public boolean wordBreak(String s, Set<String> dict) {
        int len = s.length();
        boolean[]  flag = new boolean[len+1];
        flag[0] = true;
        for (int i = 1; i <=len; i++) {
            for (int j = 0; j < i; j++) {
                if (flag[j]&&dict.contains(s.substring(j,i))){
                    flag[i] = true;
                }
            }
        }
        return flag[len];
    }




    public static void main(String[] args) {
        String s = "leetcode";
        String[] strs ={"leet", "code"};
        Set<String> set = new HashSet<>();
        for (int i = 0; i < strs.length; i++) {
            set.add(strs[i]);
        }
        Solution13 solution13 = new Solution13();
        boolean b = solution13.wordBreak(s, set);
        System.out.println(b);

    }
}
