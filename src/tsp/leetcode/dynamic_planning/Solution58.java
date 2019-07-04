package tsp.leetcode.dynamic_planning;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给定可能包含重复项的整数集合，请返回所有可能的子集。
 * 注意：子集中的元素必须是非降序的。解的结合不能包含重复子集。
 * For example,
 * If S =[1,2,2], a solution is:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 */
public class Solution58 {

    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        if (num == null || num.length < 1) return result;
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(num);
        findSubset(num, 0, list);
        return result;
    }

    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        if (S == null || S.length < 1) return result;
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(S);
        findSubset(S, 0, list);
        return result;
    }

    //不是很明白
    public void findSubset(int[] set, int start, ArrayList<Integer> list) {
        System.out.println("添加到 result 中的list:" + Arrays.asList(list));
        result.add(new ArrayList<Integer>(list));
        for (int i = start; i < set.length; i++) {
//            if (i > start && set[i] == set[i - 1]) continue;
            list.add(set[i]);
            findSubset(set, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution58 solution58 = new Solution58();
        int[] a = {1, 2};
        ArrayList<ArrayList<Integer>> lists = solution58.subsetsWithDup(a);
        System.out.println(Arrays.asList(lists));
    }
}
