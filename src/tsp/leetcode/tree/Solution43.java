package tsp.leetcode.tree;

import tsp.offer.TreeNode;

import java.util.Arrays;

/**
 * 从中序与后序遍历序列中构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:你可以假设树中没有重复的元素。
 * 例如，给出
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 */
public class Solution43 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length < 1 || postorder.length < 1) return null;
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                // 每次切割出 中序遍历 后序遍历
                root.left = buildTree(Arrays.copyOfRange(inorder, 0, i), Arrays.copyOfRange(postorder,0,i));
                root.right = buildTree(Arrays.copyOfRange(inorder,i+1,inorder.length),Arrays.copyOfRange(postorder,i,postorder.length-1));
            }
        }
        return root;
    }
}
