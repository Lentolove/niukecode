package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * author : tsp
 * Date : 2022/3/20
 * DESC:给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。 
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 *
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 *
 * 提示：
 *  树中节点数目在范围 [0, 2000] 内 
 *  -1000 <= Node.val <= 1000 
 *  
 *  Related Topics 树 广度优先搜索 二叉树 👍 1233 👎 0
 *
 * leetcode submit region begin(Prohibit modification and deletion)
 */
public class Problem_0102_BinaryTreeLevelOrderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 二叉树的层序遍历：借助队列来实现层序遍历
     * 每一层可根据当前队列的大小来确定，一层一层的添加
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
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
            ans.add(item);
        }
        return ans;
    }
    
}
