package tsp.leetcode.dynamic_planning;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Solution71 {
    public static ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>>  result = new ArrayList<>();
        int size = 1<<S.length;
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> item = new ArrayList<>();
            for (int j = 0; j < S.length; j++) {
                if ((i&(1<<j))!=0){
                    item.add(S[j]);
                }
            }
            result.add(item);
        }
        return result;
    }





    public static void main(String[] args) {
        int[] num = {1,2,3};
        System.out.println(Arrays.asList(subsets(num)));
    }
}
