package zuoshen.topinterview;

import java.util.*;

/**
 * author : tsp
 * Date : 2022/3/20
 * DESC:给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 *
 * 输入：root = [1]
 * 输出：[[1]]
 *
 * 提示
 *  
 * 树中节点数目在范围 [0, 2000] 内 
 *  -100 <= Node.val <= 100 
 *  
 *  Related Topics 树 广度优先搜索 二叉树 👍 606 👎 0
 *
 * leetcode submit region begin(Prohibit modification and deletion)
 */
public class Problem_0103_BinaryTreeZigzagLevelOrderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 二叉树的层序遍历，锯齿打印，偶数行顺序打印，奇数行反着打印
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> item = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                item.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            if (ans.size() % 2 != 0){
                //奇数行
                Collections.reverse(item);
            }
            ans.add(item);
        }
        return ans;
    }
}
