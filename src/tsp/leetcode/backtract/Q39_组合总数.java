package tsp.leetcode.backtract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q39_组合总数 {

    /**
     * 标准的回溯算法
     * 跟之前的全排列列⼀一样
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> output = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(output, new ArrayList<>(), target, 0, candidates);
        return output;
    }

    private void backtrack(List<List<Integer>> output, List<Integer> item, int target, int start, int[] nums) {
        //回溯的边间条件 当新添加的元素过⼤大是 会出现超出target的情况
        if (target == 0) {
            output.add(new ArrayList<>(item));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (target < nums[i]) break; //nums已经排序过了了 如果当前需要的target⽐比最⼩小的元素还⼩小 target⼀一定不不能为0
            item.add(nums[i]);
            // 【关键】因为元素可以重复使⽤用，这⾥里里递归传递下去的是 i ⽽而不不是 i + 1
            backtrack(output, item, target - nums[i], i, nums);
            //回溯 移除刚添加的元素
            item.remove(item.size() - 1);
        }
    }
}
