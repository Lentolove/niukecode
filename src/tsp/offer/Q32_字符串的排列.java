package tsp.offer;
/**
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,
 * 则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 */

import java.util.ArrayList;
import java.util.List;

public class Q32_字符串的排列 {

    public ArrayList<String> Permutation(String str) {
        List<String> result = new ArrayList<>();
        if (str!=null&&str.length()>0){
            PermutationHelper(str.toCharArray(),0,result);
        }
        return (ArrayList<String>) result;
    }



    public void PermutationHelper(char[] cs,int i ,List<String> list){
        if (i==cs.length-1){
            String val = String.valueOf(cs);
            if (!list.contains(val)){
                list.add(val);
            }
        }else {
            //最后一个循环是递归调用swap交换前后两个字符，在最后交换完成入List之后再交换回来，
            // 回到初始状态再进下一个循环
            for (int j = i; j < cs.length; j++) {
                swap(cs,i,j);
                PermutationHelper(cs,i+1,list);
                swap(cs,i,j);//恢复到初始状态 a b c
            }
        }
    }
    public void swap(char[] cs ,int i,int j){
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] =temp;
    }

    public static void main(String[] args) {
        Q32_字符串的排列 t = new Q32_字符串的排列();
        String str = "abc";
        ArrayList<String> permutation = t.Permutation(str);
        for (String s:
             permutation) {
            System.out.print(s+" ");
        }
    }
}
