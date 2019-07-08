package tsp.leetcode.tree;

import tsp.offer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的层次遍历
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * 例如:给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 */
public class Solution47 {

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root ==null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> item = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                item.add(node.val);
                if (node.left!=null) queue.add(node.left);
                if (node.right!=null) queue.add(node.right);
            }
            result.add(item);
        }
        return result;
    }

    /**
     * 递归 层序遍历
     */
    ArrayList<ArrayList<Integer>> levels = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> levelOrder1(TreeNode root) {
        if (root==null) return levels;
        helper(root,0);
        return levels;
    }

    public void helper(TreeNode node,int level){
        if (levels.size()==level){
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(node.val);
        if (node.left!=null){
            helper(node.left,level+1);
        }
        if (node.right!=null){
            helper(node.right,level+1);
        }
    }
}
