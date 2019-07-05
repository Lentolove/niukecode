package tsp.leetcode.tree;

import tsp.offer.TreeLinkNode;

import java.util.LinkedList;


/**
 * leetcode 116 题
 * 填充每个节点的下一个右侧节点指针
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 * <p>
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
 */
public class Solution35 {

    /**
     * 方法一：非递归版本
     */
    public static void connect1(TreeLinkNode root) {
        if (root == null) return;
        LinkedList<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);

        //写法一
        /*while (!queue.isEmpty()) {
            int size = queue.size();
            TreeLinkNode pre = null;
            while ((size--) > 0) {
                TreeLinkNode cur = queue.poll();
                if (pre == null) {
                    pre = cur;
                } else {
                    pre.next = cur;
                    pre = pre.next;
                }
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }*/

        //写法二
        while (!queue.isEmpty()){
            int len = queue.size();
            for (int i = 0; i < len-1; i++) {
                TreeLinkNode node = queue.poll();
                node.next = queue.peek();
                if (node.left!=null){ //题目说是完全二叉树 所以就没必要判断右节点书否为空啦
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }

        }
    }

    /**
     * 递归版本
     */
    public  void connect(TreeLinkNode root) {
        if (root==null||root.left==null) return;
        root.left.next = root.right;
        if (root.next!=null){
            /**
             *           1
             *         /   \    把一棵树 当做三点 root left right 三个节点来分析
             *        2    3    2.next = 3
             *      /  \  / \   3.next.right = 2.next.right
             *     4   5  6  7
             */
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
    }

}
