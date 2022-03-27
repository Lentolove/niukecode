package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/22
 * DESC:路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
 * 一定经过根节点。路径和 是路径中各节点值的总和。给你一个二叉树的根节点 root ，返回其 最大路径和 。 
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6 
 * 
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 * 提示：
 * 树中节点数目范围是 [1, 3 * 10⁴] 
 * -1000 <= Node.val <= 1000 
 *  
 *  Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1486 👎 0
 *
 * leetcode submit region begin(Prohibit modification and deletion)
 */
public class Problem_0124_BinaryTreeMaximumPathSum {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info{
        int maxPathSum;
        int maxPathSumWithHead;

        public Info(int maxPathSum, int maxPathSumWithHead) {
            this.maxPathSum = maxPathSum;
            this.maxPathSumWithHead = maxPathSumWithHead;
        }
    }

    /**
     * 至少包含一个节点
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        return process(root).maxPathSum;
    }


    private Info process(TreeNode x){
        if (x == null){
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null){
            p1 = leftInfo.maxPathSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null){
            p2 = rightInfo.maxPathSum;
        }
        int p3 = x.val;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null){
            p4 = x.val + leftInfo.maxPathSumWithHead;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null){
            p5 = x.val + rightInfo.maxPathSumWithHead;
        }
        int p6 = Integer.MIN_VALUE;
        if (leftInfo != null && rightInfo != null){
            p6 = x.val + leftInfo.maxPathSumWithHead + rightInfo.maxPathSumWithHead;
        }
        int maxSum = Math.max(Math.max(p1,p2),Math.max(Math.max(p3,p4),Math.max(p5,p6)));
        int maxSumWithHead = Math.max(Math.max(p3,p4),p5);
        return new Info(maxSum,maxSumWithHead);
    }
}
