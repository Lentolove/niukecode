package zuoshen.topinterview;

import java.util.LinkedList;
import java.util.Queue;

/**
 * author : tsp
 * Date : 2022/3/20
 * DESC:给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 提示：
 * <p>
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 * <p>
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 * Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1812 👎 0
 * <p>
 * leetcode submit region begin(Prohibit modification and deletion)
 */
public class Problem_0101_SymmetricTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 判断一棵树是否是镜像树
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    /**
     * 判断两棵树是否镜像
     */
    public boolean isSymmetric(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return isSymmetric(a.left, b.right) && isSymmetric(a.right, b.left);
    }


    /**
     * 迭代做法
     */
    public static boolean isSymmetric1(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;
        Queue<TreeNode> queueLeft = new LinkedList<>();
        Queue<TreeNode> queueRight = new LinkedList<>();
        queueLeft.add(root.left);
        queueRight.add(root.right);
        while (!queueLeft.isEmpty() && !queueRight.isEmpty()) {
            if (queueLeft.size() != queueRight.size()) return false;
            TreeNode a = queueLeft.poll();
            TreeNode b = queueRight.poll();
            if (a == null && b == null) continue;
            if (a == null || b == null) return false;
            if (a.val != b.val) return false;
            queueLeft.add(a.left);
            queueLeft.add(a.right);
            queueRight.add(b.right);
            queueRight.add(b.left);
        }
        return true;
    }


    public static void main(String[] args) {
        //[1,2,2,3,4,4,3]
        //[1,2,2,null,3,null,3]
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(2);
        TreeNode d = new TreeNode(3);
        TreeNode e = new TreeNode(4);
        TreeNode f = new TreeNode(4);
        TreeNode g = new TreeNode(3);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        System.out.println(isSymmetric1(a));
    }


}
