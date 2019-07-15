package tsp.leetcode.eyery;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 77 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 */
public class Solution72_77 {

    /**
     * 回溯法
     */
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        backtrack(1, k, n, new ArrayList<>());
        return result;
    }

    private void backtrack(int first, int k, int n, ArrayList<Integer> item) {
        if (item.size() == k) {
            result.add(new ArrayList<>(item));
        }
        for (int i = first; i < n + 1; i++) {
            item.add(i);
            //use next integers to complete the combination
            backtrack(i + 1, k, n, item);
            //回溯
            item.remove(item.size() - 1);
        }
    }


    /**
     * 字典序组合
     */
    public ArrayList<ArrayList<Integer>> combine1(int n, int k) {
        ArrayList<Integer> item = new ArrayList<>();
        for (int i = 1; i < k + 1; i++) {
            item.add(i);
        }
        item.add(n + 1);
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int j = 0;
        while (j < k) {
            ret.add(new ArrayList<>(item.subList(0, k)));
            // increase first nums[j] by one
            // if nums[j] + 1 != nums[j + 1]
            j = 0;
            while ((j < k) && (item.get(j + 1) == item.get(j) + 1)) {
                item.set(j, j++ + 1);
            }
            item.set(j, item.get(j) + 1);
        }

        return ret;
    }


    public static void main(String[] args) {
        Solution72_77 s = new Solution72_77();
        System.out.println(Arrays.asList(s.combine1(4, 2)));
    }


}
