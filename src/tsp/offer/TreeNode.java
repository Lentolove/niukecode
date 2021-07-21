package tsp.offer;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x){
        this.val = x;
    }


    public static TreeNode creatTreeNode() {
        TreeNode a = new TreeNode(1);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        a.left = c;
        a.right = d;
        return a;
    }
}
