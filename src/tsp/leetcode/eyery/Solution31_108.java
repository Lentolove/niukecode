package tsp.leetcode.eyery;

import tsp.offer.TreeNode;

/**
 * leetcode:108 将有序数组转换成二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 */
public class Solution31_108 {

    public TreeNode sortedArrayToBST(int[] num) {
        return toBFS(num,0,num.length-1);
    }

    private TreeNode toBFS(int[] num,int sIdx,int eIdx){
        if (sIdx>eIdx) return null;
        //int mid = (sIdx+eIdx)/2;
        //牛客网
        int mid = sIdx+(eIdx-sIdx+1)/2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = toBFS(num,sIdx,mid-1);
        root.right = toBFS(num,mid+1,eIdx);
        return root;
    }
}
