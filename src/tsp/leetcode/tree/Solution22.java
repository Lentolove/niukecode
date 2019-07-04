package tsp.leetcode.tree;

import tsp.offer.TreeNode;

/**
 * 题目：求跟到叶子节点数字之和
 * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * 计算从根到叶子节点生成的所有数字之和。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 *  对于递归函数 只考虑三个因素
 *  1.递归终止条件 root == null;
 *  2.每一次迭代需要记录的值(或者需要返回的值)
 *  3.对于一个树只用考虑三个节点
 *         root
 *         /  \
 *      left  right
 */
public class Solution22 {

    int sum = 0;
    public int sumNumbers(TreeNode root) {
        childSum(0,root);
        return sum;
    }
    public void childSum(int val,TreeNode root){
        if (root==null) return;
        int k = (val*10+root.val);
        if (root.left==null&&root.right==null){
            sum += k;
        }
        childSum(k,root.left);
        childSum(k,root.right);

    }

    /**
     * 前序遍历的思想(根左右)+数字求和(每一层都比上层和*10+当前根节点的值)
     */
    public int sumNumbers1(TreeNode root) {
        int sum = 0;
        if (root==null) return sum;
        return preDerSumber(root,sum);
    }

    public int preDerSumber(TreeNode root,int sum){
        //递归终止条件 及递归函数的返回值
        if (root==null) return 0;
        sum = sum*10+root.val;
        if (root.left==null&&root.right==null){
            return sum;
        }
        return preDerSumber(root.left,sum)+preDerSumber(root.right,sum);

    }
}
