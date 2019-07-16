package tsp.leetcode.eyery;


import java.util.*;

/**
 * 46: 全排列II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 */
public class Solution103_47_全排列II {

    /**
     * 我自己写的 用hashset去重.....
     * 耗时久了点 但是还是通过了
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> output = new HashSet<>();
        List<Integer> itme = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            itme.add(nums[i]);
        }
        backtrack(output, itme, 0, nums.length);
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : output) {
            result.add(list);
        }
        return result;
    }

    private void backtrack(Set<List<Integer>> out, List<Integer> itme, int first, int n) {
        if (first == n) {
            out.add(new ArrayList<>(itme));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(itme, first, i);
            backtrack(out, itme, first + 1, n);
            //回溯 状态重置
            Collections.swap(itme, first, i);
        }
    }

    /**
     * 回溯 + 剪枝
     */
    private List<List<Integer>> res;
    private boolean[] flag;

    public List<List<Integer>> permuteUnique1(int[] nums) {
        int n = nums.length;
        res = new ArrayList<>();
        if (n == 0) return res;
        flag = new boolean[n];
        // 有重复的元素，直接排序，确保 nums[i] = nums[i-1] 的情况
        Arrays.sort(nums);
        helper(nums, n, 0, new Stack<>());
        return res;
    }

    private void helper(int[] nums, int n, int depth, Stack<Integer> stack) {
        if (depth == n) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!flag[i]) {
                //因为排序后重复的元素肯定不会是第一个的 如果出现 nums[i] 与nums[i-1]相等，并且之前的数还未使用
                if (i > 0 && nums[i] == nums[i - 1] && !flag[i - 1]) {
                    continue;
                }
                flag[i] = true;
                stack.add(nums[i]);
                helper(nums, n, depth + 1, stack);
                stack.pop();
                flag[i] = false;
            }
        }
    }


    public static void main(String[] args) {
        Solution103_47_全排列II s = new Solution103_47_全排列II();
        int[] a = {1, 1, 2};
        System.out.println(Arrays.asList(s.permuteUnique1(a)));
    }
}

class Solution47 {

    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

    public ArrayList<ArrayList<Integer>> permuteUnique(int[] nums) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums);//进行排序之后，重复的元素只能从一个方向进行拿，所以可以保证不重复
        helper(list, nums, new boolean[nums.length]);
        return res;
    }

    public void helper(ArrayList<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] == true) continue;
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue;//只能按一个顺序进行访问连续相同的元素，才能保证唯一
            used[i] = true;
            list.add(nums[i]);
            helper(list, nums, used);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }
}

