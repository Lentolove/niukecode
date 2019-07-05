package tsp.leetcode.tree;

import tsp.offer.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  二叉树的层次遍历 II
 *  https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 *  给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class Solution42 {

    /**
     * 自己完全不用思考写正确也是不容易
     */
    public static ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root==null) return result;
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
            result.add(0,item); //可以从head保存，就不用反转数组了
        }
//        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
//        for (int i = result.size()-1; i >=0; i--) {
//            lists.add(result.get(i));
//        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node = TreeNode.creatTreeNode();
        System.out.println(Arrays.asList(levelOrderBottom(node)));
    }
}
