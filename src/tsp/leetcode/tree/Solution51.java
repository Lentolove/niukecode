package tsp.leetcode.tree;

import tsp.offer.TreeNode;

import java.util.Stack;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
public class Solution51 {

    TreeNode pre = null;

    //查找二叉树要时刻记住中序遍历
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) {
            return false;
        }
        if (pre != null) {
            if (pre.val >= root.val) {
                return false;
            } else {
                pre = root;
            }
        } else {
            pre = root;
        }
        return isValidBST(root.right);
    }

    //写法二：
    public boolean isValidBST1(TreeNode root) {
        return helper(root);
    }

    private boolean helper(TreeNode root) {
        if (root == null) return true;
        if (isValidBST(root.left)) {
            if (pre != null && root.val <= pre.val) {
                return false;
            }
            pre = root;
            return isValidBST(root.right);
        }
        return false;
    }

    /**
     * 方法二：利用中序遍历来判断 非迭代
     */

    public boolean isValidBST2(TreeNode root){
        if (root==null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (!stack.isEmpty()||cur!=null){
            if (cur==null){
                cur = stack.pop();
                if (pre!=null&&pre.val>=cur.val){
                    return false;
                }
                pre = cur;
                cur = cur.right; //中序遍历
            }else {
                stack.push(cur);
                cur = cur.left;
            }
        }
        return true;
    }

}
