package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 题目描述
 * 对字符串中的所有单词进行倒排。
 * 说明：
 * 1、每个单词是以26个大写或小写英文字母构成；
 * 2、非构成单词的字符均视为单词间隔符；
 * 3、要求倒排后的单词间隔符以一个空格表示；如果原字符串中相邻单词间有多个间隔符时，倒排转换后也只允许出现一个空格间隔符；
 * 4、每个单词最长20个字母；
 * 输入描述:
 * 输入一行以空格来分隔的句子
 * 输出描述:
 * 输出句子的逆序
 * 示例1
 * 输入
 * <p>
 * 复制
 * I am a student
 * 输出
 * <p>
 * 复制
 * student a am I
 */
public class Q31_单词倒排 {

    //判断有些字符串不是单词
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            System.out.println(reverWord(str));
        }

    }

    private static String reverWord(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)){
                sb.append(c);
            }else {
                sb.append("#");
            }
        }
        String[] split = sb.toString().trim().split("#");
        StringBuilder sb1 = new StringBuilder();
        for (int i = split.length-1; i >0 ; i--) {
            sb1.append(split[i]).append(" ");
        }
        sb1.append(split[0]);
        return sb1.toString();
    }

}
