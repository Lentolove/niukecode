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
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(5);
        TreeNode d = new TreeNode(2);
        TreeNode e = new TreeNode(6);
        TreeNode f = new TreeNode(4);
        b.left = a;
        b.right = c;
        a.left = d;
        a.right = e;
        d.left = f;
        return b;
    }
}
