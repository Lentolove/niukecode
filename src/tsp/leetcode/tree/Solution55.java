package tsp.leetcode.tree;

import tsp.offer.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 *  给定一个二叉树，返回其节点值的有序遍历。
 *  For example:
 * Given binary tree{1,#,2,3},
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * return[1,3,2].
 */
public class Solution55 {
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root==null) return result;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur!=null|| !stack.isEmpty()){
            while (cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            result.add(node.val);
            cur = node.right;
        }
        return result;
    }
}
