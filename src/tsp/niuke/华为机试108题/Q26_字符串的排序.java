package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class Q26_字符串的排序 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {//遍历找出按照字母表排序的字母
                char c = (char) ('a' + i);
                for (int j = 0; j < str.length(); j++) {
                    char c1 = str.charAt(j);
                    if (c1 == c || c1 == c - 32) {
                        sb.append(c1);
                    }
                }
            }
            for (int i = 0; i < str.length(); i++) {//最后将非字母插入当原来的位置
                char c = str.charAt(i);
                if (!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')) {
                    sb.insert(i, c);
                }
            }
            System.out.println(sb.toString());
        }
    }
}

/*
public class Q26_字符串的排序{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            LinkedList<Character> list = new LinkedList<>();
            char[] chars = str.toCharArray();
            for (char c : chars) {
                if (Character.isLetter(c)){//判断char是否为字母
                    list.add(c);
                }
            }
            Collections.sort(list,(a,b)->Character.toLowerCase(a)-Character.toLowerCase(b));//将char进行排序，不考虑大小写
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for (char c : chars) {
                if (Character.isLetter(c) && index < list.size()){
                    sb.append(list.get(index++));
                }else {
                    sb.append(c);
                }
            }
            System.out.println(sb.toString());
        }
    }
}
*/