package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : tsp
 * Time: 2022/3/10 19:27
 * Desc :
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * 提示：
 * 1 <= n <= 8
 * <p>
 * Related Topics 字符串 动态规划 回溯 👍 2432 👎 0
 */
public class Problem_0022_GenerateParentheses {

    /**
     * 思路：暴力递归，加剪枝操作
     */
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[n * 2];
        process(path, 0, 0, n, ans);
        return ans;
    }

    /**
     * 递归含义
     * ( ( ) ) ( )....
     *
     * @param path            当前已经组成的括号路径保存，长度是 n * 2
     * @param index           当前来到的位置,可以放什么括号 ( 或者 )
     * @param leftReduceRight 已添加的左侧括号的数量减去已经匹配的右括号数量
     * @param leftRemain      剩余还可以放几个左括号：leftRemain <= n
     *                        依次在path上填写决定
     *                        0 1 2 3 4 5
     *                        path[0...index-1]决定已经做完了
     *                        index位置上，( )
     */
    public static void process(char[] path, int index, int leftReduceRight, int leftRemain, List<String> ans) {
        if (index == path.length) {
            ans.add(String.valueOf(path));
        } else {
            //暴力递归的剪枝操作,
            if (leftRemain > 0) {//左括号还能放，初始值为n，一直到为0
                path[index] = '(';
                //去下一个位置做决定，leftReduceRight+1表示又添加了一个左括号,剩余还可以放的(的数量-1
                process(path, index + 1, leftReduceRight + 1, leftRemain - 1, ans);
            }
            if (leftReduceRight > 0) {//已添加的左侧括号的数量减去已经匹配的右括号数量大于0，表示可以添加右括号
                path[index] = ')';
                process(path, index + 1, leftReduceRight - 1, leftRemain, ans);
            }
        }
    }

    /**
     * 思路：暴力递归，不剪枝的话，就需要对结果进行验证后加入
     */
    public static List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[n * 2];
        process2(path, 0, ans);
        return ans;
    }

    /**
     * 暴力递归，在加入的时候验证
     */
    public static void process2(char[] path, int index, List<String> ans) {
        if (index == path.length) {
            if (isValid(path)) {
                ans.add(String.valueOf(path));
            }
        } else {
            path[index] = '(';
            process2(path, index + 1, ans);
            path[index] = ')';
            process2(path, index + 1, ans);
        }
    }

    public static boolean isValid(char[] str) {
        int count = 0;
        for (char c : str) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) return false;
        }
        return count == 0;
    }

    public static void main(String[] args) {
//        List<String> list = generateParenthesis(3);
        List<String> list = generateParenthesis2(3);
        for (String s : list) {
            System.out.println(s);
        }
    }

}
