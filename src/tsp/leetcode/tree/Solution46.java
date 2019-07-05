package tsp.leetcode.tree;

import tsp.offer.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 */
public class Solution46 {


    /**
     * 方法与Binary Tree Level Order Traversal一致 区别是需要用一个flag判断应该正序or逆序打印
     * 逆序答应就从 前面插入
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>>  result = new ArrayList<>();
        if (root==null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            //标志位 这一层是顺着打印还是逆打印
            ArrayList<Integer> item = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (flag){
                    item.add(node.val);
                }else { //本来这一行是 2 3  逆序就是  3 2 从前面添加
                    item.add(0,node.val);
                }
                if (node.left!=null) queue.offer(node.left);
                if (node.right!=null) queue.offer(node.right);

            }
            flag = !flag;
            result.add(item);
        }
        return result;
    }

    /**
     * 方法二：用两个个堆栈 s1 正常打印 s2 逆序打印
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder1(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root==null) return result;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()||!s2.isEmpty()){
            ArrayList<Integer> item = new ArrayList<>();
            while (!s1.isEmpty()){
                TreeNode node = s1.pop();
                item.add(node.val);
                if (node.left!=null) s2.push(node.left);
                if (node.right!=null) s2.push(node.right);
            }
            result.add(item);
            //item.clear 一直出错
            item = new ArrayList<>();
            while (!s2.isEmpty()){
                TreeNode node = s2.pop();//反着
                item.add(node.val);
                if (node.right!=null) s1.push(node.right);
                if (node.left!=null) s1.push(node.left);
            }
            if (!item.isEmpty()){
                result.add(item);
            }
        }
        return result;
    }



    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        a.left = b;
        a.right =c;
        b.left=d;
        c.right = e;
        Solution46 s = new Solution46();
        System.out.println(Arrays.asList(s.zigzagLevelOrder1(a)));
    }
}
