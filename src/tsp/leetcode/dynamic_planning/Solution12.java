package tsp.leetcode.dynamic_planning;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution12 {
    ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
//        return DFS(s, dict, new HashMap<String, ArrayList<String>>());
        dfs(s,s.length(),"",dict);
        return list;
    }


    public ArrayList<String> DFS(String s, Set<String> dir, HashMap<String,ArrayList<String>> map){
        if (map.containsKey(s)){
            return map.get(s);
        }
        ArrayList<String> res = new ArrayList<>();
        if (s.length()==0){
            res.add(" ");
            return res;
        }
        for (String substr:dir) {
            if (s.startsWith(substr)){
                for (String str: DFS(s.substring(substr.length()),dir,map) ) {
                    res.add(substr+(str==""?"":" ")+str);
                }
            }
        }
        map.put(s,res);
        return res;
    }

    private void dfs(String s,int index,String str,Set<String> set){
        if (index<=0){
            if (str.length()>0){
                list.add(str.substring(0,str.length()-1));
            }
        }
        for (int i = index; i >=0 ; i--) {
            if (set.contains(s.substring(i,index))){
                dfs(s,i,s.substring(i,index)+" "+str,set);
            }
        }
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        String[] li ={"cat", "cats", "and", "sand", "dog"};
        Set<String> set = new HashSet<>();
        for (int i = 0; i < li.length; i++) {
            set.add(li[i]);
        }
        Solution12 solution12 = new Solution12();
        ArrayList<String> strings = solution12.wordBreak(s, set);
        for (String s1: strings
             ) {
            System.out.println(s1);
        }
    }
}
