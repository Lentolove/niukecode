package tsp.leetcode.tree;

import tsp.offer.TreeNode;

/**
 * 路径总和
 * https://leetcode-cn.com/problems/path-sum/
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class Solution38 {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root==null) return false;
        if (root.left==null&&root.right==null&&sum==root.val) return true;
        return hasPathSum(root.left,sum-root.val)|| hasPathSum(root.right,sum-root.val);
    }

}
