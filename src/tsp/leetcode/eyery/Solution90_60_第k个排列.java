package tsp.leetcode.eyery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 60:第k个排列  回溯 全排列 康拓编码
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 */
public class Solution90_60_第k个排列 {

    /**
     * 考察康拓编码 全排列
     *  X=an*(n-1)!+an-1*(n-2)!+...+ai*(i-1)!+...+a2*1!+a1*0!
     *  求12345的全排列的第16个组合
     *
     */
    public String getPermutation(int n, int k) {
        int[] jc = new int[n]; //保存1 - n-1的阶乘
        jc[0]=1;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <n; i++) {
            list.add(i);
            jc[i]=jc[i-1]*i;
        }
        list.add(n);
        StringBuilder sb = new StringBuilder();
        int temp=0;
        k = k-1;
        for (int i = 0; i < n; i++) {
            temp = k/jc[n-1-i]; //除数
            k = k - temp*jc[n-1-i]; //余数
            sb.append(list.get(temp));
            list.remove(temp);
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        int[] a = {1,2,3};
        Solution46 solution46 = new Solution46();
        List<List<Integer>> permute = solution46.permute(a);
        System.out.println(Arrays.asList(permute));
    }


}

class Solution46{
    /**
     * 全排列
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
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
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/permutations
     */

    public List<List<Integer>> permute(int[] nums) {
        //初始化输出
        List<List<Integer>> output = new ArrayList<>();
        //将数组中的数添加到 list 从而添加到 output中
        ArrayList<Integer> item = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            item.add(nums[i]);
        }
        int n = nums.length;
        backtrack(n,output,item,0);
        return output;
    }

    private void backtrack(int n,List<List<Integer>> output,List<Integer> item,int first){
        if (first==n){
            /**
             * Java 中的对象是引用传递，直接 output.add(nums)只是把nums的引用拷贝了一份，
             * 更改nums的时候就会影响到output中的每一份nums的引用。
             * 用拷贝构造函数就能进行值传递，把整个nums拷贝了一份，后面再修改就不会受影响。
             */
            output.add(new ArrayList<>(item));
        }
        for (int i = first; i < n; i++) {
            //将第 i 个元素放到第一位置
            Collections.swap(item,first,i);
            backtrack(n,output,item,first+1);
            // 回溯
            Collections.swap(item,first,i);
        }
    }
}
