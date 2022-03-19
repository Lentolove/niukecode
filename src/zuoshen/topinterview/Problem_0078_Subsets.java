package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.List;

/**
 * author : tsp
 * Date : 2022/3/18
 * DESC:给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * <p>
 * Related Topics 位运算 数组 回溯 👍 1527 👎 0
 */
public class Problem_0078_Subsets {


    /**
     * 恢复
     * 深度优先遍历，当前位置选或者不选
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        process(ans,nums,0,new ArrayList<>());
        return ans;
    }

    public static void process(List<List<Integer>> ans, int[] num, int index, List<Integer> path) {
        if (index == num.length){
            //nums数组选完了
            ans.add(new ArrayList<>(path));
            return;
        }
        //1.当前位置不选
        process(ans,num,index + 1,path);
        //2.当前位置选
        path.add(num[index]);
        process(ans,num,index + 1,path);
        //深度优先遍历返回之前需要移除当前选择
        path.remove(path.size() - 1);
    }


    public static void main(String[] args) {
        int[] num = {1,2,3};
        List<List<Integer>> lists = subsets(num);
        for (List<Integer> list : lists) {
            for (Integer a : list) {
                System.out.print(a + " ");
            }
            System.out.println("--");
        }
    }

}
