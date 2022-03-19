package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author : tsp
 * Date : 2022/3/14
 * DESC: 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 * <p>
 * Related Topics 数组 回溯 👍 1842 👎 0
 */
public class Problem_0046_Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        process(ans, nums, 0);
        return ans;
    }

    public static void process(List<List<Integer>> ans, int[] num, int index) {
        if (index == num.length) {
            List<Integer> item = new ArrayList<>();
            for (int a : num) {
                item.add(a);
            }
            ans.add(item);
        } else {
            //当前来到的位置为index，可以根后面的想交换：[1,2,3] index 只能和它后面的交换
            //eg: [1,2,3],index = 1,只能和 1,2交换
            for (int i = index; i < num.length; i++) {
                swap(num, index, i);
                process(ans, num, index + 1);
                //恢复现场
                swap(num, index, i);
            }
        }
    }

    public static void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }


    public static void main(String[] args) {
        int[] num = {1, 2, 3};
        List<List<Integer>> permute = permute(num);
        for (List<Integer> list : permute) {
            for (Integer a : list) {
                System.out.print(a + "");
            }
            System.out.println("---------");
        }
    }


}
