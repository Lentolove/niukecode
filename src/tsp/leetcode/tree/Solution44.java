package tsp.leetcode.tree;

import tsp.offer.TreeNode;

import java.util.Arrays;

/**
 * 从前序序与中序遍历序列中构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:你可以假设树中没有重复的元素。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal

 */
public class Solution44 {


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length<1||inorder.length<1) return null;
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 0; i < preorder.length; i++) {
            if (inorder[i]==root.val){
                root.left = buildTree(Arrays.copyOfRange(preorder,1,i+1),
                        Arrays.copyOfRange(inorder,0,i));
                root.right = buildTree(Arrays.copyOfRange(preorder,i+1,preorder.length
                ),Arrays.copyOfRange(inorder,i+1,inorder.length));
            }
        }
        return root;
    }

}
