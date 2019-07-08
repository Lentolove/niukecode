package tsp.leetcode.tree;

import tsp.offer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 说明: 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 */
public class Solution48 {


    /**
     * 递归解法
     */
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }

    public boolean isMirror(TreeNode t1,TreeNode t2){
        if (t1==null&&t2==null) return true;
        if (t1==null||t2==null) return false;
        return (t1.val==t2.val)&&isMirror(t1.left,t2.right)&&isMirror(t1.right,t2.left);
    }

    /**
     * 迭代解法 利用队列中每两个连续的节点应该相等的。而且子树互为镜像
     */
    public boolean isSymmetric1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1 ==null&&t2==null) continue;
            if (t1 == null || t2==null) return false;
            if (t1.val!=t2.val) return false;
            queue.add(t1.left);
            queue.add(t2.right);
            //注意queue的添加顺序 t1.left== t2.right t1.right==t2.left
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }

}
