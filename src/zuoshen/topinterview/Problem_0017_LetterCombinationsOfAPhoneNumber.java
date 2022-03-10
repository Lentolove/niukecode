package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : tsp
 * Time: 2022/3/10 17:35
 * Desc :
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * <p>
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * 提示：
 * <p>
 * *  0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 * <p>
 * Related Topics 哈希表 字符串 回溯 👍 1763 👎 0
 */
public class Problem_0017_LetterCombinationsOfAPhoneNumber {

    /**
     * 暴力递归，都不需要剪枝
     * 定义数字和字符数组的映射
     */
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        char[][] map = {
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };
        process(map, digits.toCharArray(), result, new char[digits.length()], 0);
        return result;
    }

    /**
     * path 当前保存的路径，深度优先遍历,不需要剪枝，不需要回退
     */
    public static void process(char[][] map, char[] str, List<String> ans, char[] path, int index) {
        if (index == str.length) {
            //index到最后位置了
            ans.add(String.valueOf(path));
        } else {
            //已经填满了[....index - 1],当前可选择的位置 index
            char[] item = map[str[index] - '2'];//当前 index [a,b,c]可选择三个位置
            for (char c : item) {
                path[index] = c;
                process(map, str, ans, path, index + 1);
            }
        }
    }

    public static void main(String[] args) {
        List<String> strings = letterCombinations("23");
        for (String string : strings) {
            System.out.println(string);
        }
    }

}
