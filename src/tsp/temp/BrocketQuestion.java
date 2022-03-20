package tsp.temp;

import java.util.Stack;

/**
 * author : tsp
 * Date : 2022/3/14
 * DESC:
 */
public class BrocketQuestion {
    public BrocketQuestion() {
    }

    public static void main(String[] args) {
        String s = "((()";
        String s2 = "()()()(";
        String s3 = "((((())()";
        String s4 = "((((())))";
        System.out.println(valid(s));
        System.out.println(valid(s2));
        valid1(s3);
    }

    public static void valid1(String s) {
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack();

        int index;
        for(index = 0; index < chars.length; ++index) {
            if (chars[index] == '(') {
                stack.add(index);
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
        }

        index = -1;
        if (!stack.isEmpty()) {
            index = (Integer)stack.peek();
        }

        System.out.println(s.substring(index + 1));
    }

    public static String valid(String str) {
        char[] strs = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack();
        char[] var4 = strs;
        int var5 = strs.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            char c = var4[var6];
            if (c == '(') {
                stack.add(c);
            } else if (!stack.isEmpty() && (Character)stack.peek() == '(') {
                sb.append("()");
                stack.pop();
            }
        }

        return sb.toString();
    }
}
