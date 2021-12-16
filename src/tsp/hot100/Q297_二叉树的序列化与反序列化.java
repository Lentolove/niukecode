package tsp.hot100;

import java.util.TreeSet;

public class Q297_二叉树的序列化与反序列化 {


    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int kthLargest = findKthLargest(nums, 4);
        System.out.println(kthLargest);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //借助TreeSet
    public static int findKthLargest(int[] nums, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int a : nums) {
            set.add(a);
        }
        for (int a : set) {
            k--;
            if (k == 0) {
                return a;
            }
        }
        return -1;

    }
}
