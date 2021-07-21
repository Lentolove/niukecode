package tsp.offer;

import java.util.Arrays;

public class Q18_重建二叉树 {

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length<=0||inorder.length<=0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 0; i < preorder.length; i++) {
            if (preorder[0]==inorder[i]){
                root.left = buildTree(Arrays.copyOfRange(preorder, 1, i + 1), Arrays.copyOfRange(inorder, 0, i));
                root.right = buildTree(Arrays.copyOfRange(preorder, i+1, preorder.length), Arrays.copyOfRange(inorder, i+1, inorder.length));
            }
        }
        return root;
    }


    public static void main(String[] args) {
        int[] pre = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(pre, inorder);
    }
}
