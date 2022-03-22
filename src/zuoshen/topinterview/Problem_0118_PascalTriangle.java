package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.List;

/**
 * author : tsp
 * Date : 2022/3/22
 * DESC:给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例 1:
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * <p>
 * 示例 2:
 * 输入: numRows = 1
 * 输出: [[1]]
 * <p>
 * 提示:
 * <p>
 * 1 <= numRows <= 30
 * <p>
 * Related Topics 数组 动态规划 👍 717 👎 0
 */
public class Problem_0118_PascalTriangle {

    /**
     * 题目：构建杨辉三角
     * 1.有几行就构建几个List 集合
     * 2.当前行的构建与上一行有关，  第i行 line[x] = 第i-1行 line[x] + lin[x+1]
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        //初始化数据
        for (int i = 0; i < numRows; i++) {
            ans.add(new ArrayList<>());
            //每个集合的第一个元素和最后一个元素都是1
            ans.get(i).add(1);
        }
        //开始从第二行填写
        for (int i = 1; i < numRows; i++){
            List<Integer> preList = ans.get(i - 1);
            List<Integer> curList = ans.get(i);
            for (int j = 0; j < preList.size() - 1; j++) {
                curList.add(preList.get(j) + preList.get(j + 1));
            }
            curList.add(1);
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = generate(4);
        for (List<Integer> item : lists) {
            for (Integer a : item) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }
}
