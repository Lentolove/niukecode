package tsp.leetcode.tree;

import tsp.offer.TreeNode;

/**
 *  平衡二叉树
 *  https://leetcode-cn.com/problems/balanced-binary-tree/
 *  给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 */
public class Solution39 {

    /**
     * 此方法更好
     */
    boolean result = true;
    public boolean isBalanced(TreeNode root) {
        getDepth(root);
        return result;
    }

    public int getDepth(TreeNode root){
        if (root==null) return 0;
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        if (Math.abs(l-r)>1) result = false;
        return Math.max(l,r)+1;
    }


    /**
     * 常规递归
     */
    public boolean isBalanced1(TreeNode root) {
        if (root==null) return true;
        if (Math.abs(tall(root.left)-tall(root.right))>1) return false;
        return isBalanced(root.left)&&isBalanced(root.right);
    }

    private int tall(TreeNode root){
        if (root==null) return 0;
        return Math.max(tall(root.left),tall(root.right))+1;
    }

}
