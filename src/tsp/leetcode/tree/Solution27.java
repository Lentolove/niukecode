package tsp.leetcode.tree;

import tsp.offer.TreeNode;

/**
 * 题目：二叉树中最大路径和
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 * 给定一个非空二叉树，返回其最大路径和。本题中，路径被定义为一条从树中任意节点出发，
 * 达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出: 42
 */
public class Solution27 {

    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        max_gain(root);
        return maxSum;
    }

    public int max_gain(TreeNode root){
        if (root==null) return 0;
        //获取 root.left 节点的最大贡献值（包括当前节点的值）
        int left_gain = Math.max(max_gain(root.left),0);
        int right_gain = Math.max(max_gain(root.right),0);
        //检查是维护旧路径还是创建新路径。创建新路径的权值
        int price_newpath = left_gain+right_gain+root.val;
        maxSum = Math.max(price_newpath,maxSum);
        return Math.max(left_gain,right_gain)+root.val;
    }
}
