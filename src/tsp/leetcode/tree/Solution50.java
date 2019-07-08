package tsp.leetcode.tree;

import tsp.offer.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 二叉搜索树中的两个节点被错误地交换。
 * 请在不改变其结构的情况下，恢复这棵树。
 * 示例 1：
 * 输入: [1,3,null,null,2]
 *    1
 *   /
 *  3
 *   \
 *    2
 * 输出: [3,1,null,null,2]
 *    3
 *   /
 *  1
 *   \
 *    2
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution50 {

    TreeNode pre, q, p;

    // 根据中序遍历的有序性  左 根 右
    public void recoverTree(TreeNode root) {
        dfs(root);
        int tem = p.val;
        p.val = q.val;
        q.val = tem;
    }

    public void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left); //处理节点
        //处理根节点
        if (pre != null && pre.val > root.val) {
            if (p == null) {
                p = pre;
                q = root;
            } else {
                q = root;
            }
        }
        pre = root;
        dfs(root.right);
    }

    /**
     * 非递归结题
     *    2
     *  3  1
     *  list = 3 2 1
     *  after sort  list = 1 2 3
     *  在进行一次中序遍历 因为二叉搜索数的中序遍历的结果是 排序好的
     *
     */
    public void recoverTree1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper1(root,list);
        Collections.sort(list);
        helper2(root,list);
    }

    //存储中序遍历的节点值
    public void helper1(TreeNode root, List list) {
        if (root == null) return;
        helper1(root.left, list);
        list.add(root.val);
        helper1(root.right, list);
    }

    public void helper2(TreeNode root,List list){
        if (root==null) return;
        helper2(root.left,list);
        Integer temp = (Integer)list.remove(0);
        if (temp!=root.val){
            root.val = temp;
        }
        helper2(root.right,list);
    }


    public static void main(String[] args) {
        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(1);
        a.left = b;
        a.right = c;
        Solution50 s = new Solution50();
        s.recoverTree(a);
        s.helper(a);

    }

    public void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        System.out.println(root.val);
        helper(root.right);
    }
}
