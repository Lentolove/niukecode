package tsp.leetcode.eyery;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 71: 简化路径
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 * 示例 1：
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/simplify-path
 */



class Solution {
    public String simplifyPath(String path) {
        String[] s = path.split("/");
        /**
         * 切割完之后: [ ,a,.,b,..,..,c]
         * 遇到.和''表示在当前路径 不需要操作
         */
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals("") || s[i].equals(".")) {
                continue;
            } else if (s[i].equals("..")) { //返回上一层
                if (!res.isEmpty()) {
                    res.remove(res.size() - 1);//返回上一级 则去掉一层目录,类似于栈的弹出
                }
            } else {
                res.add(s[i]);
            }
        }
        if (res.isEmpty()) return "/";//什么都没有
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sb.append("/");
            sb.append(res.get(i));
        }
        return sb.toString();
    }
}

class Solution22 {
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        for (String item : path.split("/")) {
            if (item.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!item.isEmpty() && !item.equals(".")) stack.push(item);
        }
        String res = "";
        for (String d : stack) res = "/" + d + res;
        return res.isEmpty() ? "/" : res;
    }
}

public class Solution78_71_简化路径 {

    public static void main(String[] args) {
        String s = "/a/./b/../../c/";
        Solution solution = new Solution();
        System.out.println(solution.simplifyPath(s));
    }
}
