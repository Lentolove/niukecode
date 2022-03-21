package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/21
 * DESC:给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * <p>
 * 示例 1：
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 * <p>
 * 示例 2：
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10⁴
 * -10⁴ <= nums[i] <= 10⁴
 * nums 按 严格递增 顺序排列
 * <p>
 * Related Topics 树 二叉搜索树 数组 分治 二叉树 👍 971 👎 0
 * <p>
 * leetcode submit region begin(Prohibit modification and deletion)
 */
public class Problem_0108_ConvertSortedArrayToBinarySearchTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 将排序数组转成二叉搜索树，高度平衡
     * 1.采用分值递归的思想，每次选择中间位置作为head，构建左子树，构建右子树
     * 2.同105题目一样
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return process(nums,0,nums.length - 1);
    }

    public static TreeNode process(int[] nums, int l, int r) {
        if (l > r) return null;
        int m = l + (r - l) / 2;
        TreeNode head = new TreeNode(nums[m]);
        if (l == r) return head;
        head.left = process(nums,l,m - 1);
        head.right = process(nums,m + 1,r);
        return head;
    }


}
