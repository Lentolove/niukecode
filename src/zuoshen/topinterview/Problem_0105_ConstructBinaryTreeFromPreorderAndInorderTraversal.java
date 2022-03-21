package zuoshen.topinterview;

import java.util.HashMap;

/**
 * author : tsp
 * Date : 2022/3/21
 * DESC:给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
 * 返回其根节点。
 * <p>
 * 示例 1:
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * <p>
 * 示例 2:
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 * <p>
 * 提示:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 * <p>
 * Related Topics 树 数组 哈希表 分治 二叉树 👍 1492 👎 0
 * <p>
 * <p>
 * leetcode submit region begin(Prohibit modification and deletion)
 */
public class Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 由先序遍历和中序遍历构建后续遍历
     * 1.递归的思路，根据 preorder 找到根节点， 在 inorder 中找到左子树和右子树
     * 2.创建根节点，左子树继续递归，右子树继续递归
     * 3.难点在于区间的划分
     * 4.借助HashMap存取中序遍历的值及索引
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) return null;
        int n = preorder.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return process(preorder, 0, n - 1, inorder, 0, n - 1, map);
    }

    /**
     * l1 he r1 ,l2 ,r2 表示的是区间
     * 自己动手用简单的例子来确定递归过程中区间的边界位置
     */
    public static TreeNode process(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2, HashMap<Integer, Integer> map) {
        if (l1 > r1) return null;
        int rootValue = preorder[l1];
        TreeNode head = new TreeNode(rootValue);
        if (l1 == r1) return head;
        //在中序变种查找 rootValue 的位置，把它分为 左子树 和 右子树
        int index = map.get(rootValue);
        int count = index - l2;
        head.left = process(preorder, l1 + 1, l1 + count, inorder, l2, index - 1, map);
        head.right = process(preorder, l1 + count + 1, r1, inorder, index + 1, r2, map);
        return head;
    }

    public static void main(String[] args) {
        int[] pre = {3,9,20,15,7};
        int[] in = {9,3,15,20,7};
        TreeNode head = buildTree(pre, in);
        print(head);
    }

    public static void print(TreeNode head){
        if (head == null) return;

        print(head.left);
        print(head.right);
        System.out.println(head.val +" - ");
    }

}
