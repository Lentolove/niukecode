package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/20
 * DESC:给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。节点的右子树只包含 大于 当前节点的数。所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 * <p>
 * 提示：
 * 树中节点数目范围在[1, 10⁴] 内
 * -2³¹ <= Node.val <= 2³¹ - 1
 * <p>
 * Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1475 👎 0
 * <p>
 * leetcode submit region begin(Prohibit modification and deletion)
 */
public class Problem_0098_ValidateBinarySearchTree {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 思路：验证一个树否是二叉搜索树，利用二叉搜索树的中序遍历是有序的来判断
     * TODO
     */
    
    private static boolean isBinarySearchTree = true;
    
    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return isBinarySearchTree;
    }
    
    public static void process(TreeNode root,int pre){
        if (root == null) return;
        process(root.left,root.val);
        if (pre >= root.val){
            isBinarySearchTree = false;
            return;
        }
        process(root.right,root.val);
    }

    public static void main(String[] args) {
//        TreeNode a = new TreeNode(4);
//        TreeNode b = new TreeNode(2);
//        TreeNode c = new TreeNode(6);
//        TreeNode d = new TreeNode(1);
//        TreeNode e = new TreeNode(3);
//        TreeNode f = new TreeNode(5);
//        TreeNode g = new TreeNode(7);
//        a.left = b;
//        a.right = c;
//        b.left = d;
//        b.right = e;
//        c.left = f;
//        c.right = g;
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(2);
        TreeNode  c = new TreeNode(2);
        a.left = b;
        a.right = c;
        System.out.println(isValidBST(a));
    }
    
}
