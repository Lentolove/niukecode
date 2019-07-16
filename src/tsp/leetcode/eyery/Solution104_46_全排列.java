package tsp.leetcode.eyery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 46: 全排列
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 */
public class Solution104_46_全排列 {

    /**
     * 全排列 回溯法
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output  = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            item.add(nums[i]);
        }
        backtrack(output,item,0,nums.length);
        return output;
    }

    private void backtrack(List<List<Integer>> output,List<Integer> item,int first,int n){
        if (first == n){
            output.add(new ArrayList<>(item));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(item,first,i);
            backtrack(output,item,first+1,n);
            //回溯
            Collections.swap(item,first,i);

        }
    }

}



