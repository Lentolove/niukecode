package tsp.offer;

public class Q31_序列化二叉树 {


    String Serialize(TreeNode root) {
        if (root==null) return "";
        StringBuilder sb = new StringBuilder();
        Serialize2(root,sb);
        return sb.toString();
    }

    void Serialize2(TreeNode root ,StringBuilder sb){
        if (root==null){
            sb.append("#,");
            return;
        }
        sb.append(root.val);
        sb.append(',');
        Serialize2(root.left,sb);
        Serialize2(root.right,sb);
    }

    int index = -1;
    TreeNode Deserialize(String str) {
        if (str.length()==0) return null;
        String[] s = str.split(",");
        return Deserialize2(s);
    }
    TreeNode Deserialize2(String[] str){
        index++;
        if (!str[index].equals("#")){
            TreeNode root = new TreeNode(0);
            root.val = Integer.parseInt(str[index]);
            root.left = Deserialize2(str);
            root.right = Deserialize2(str);
            return root;
        }
        return null;
    }


    public static void main(String[] args) {
        Q31_序列化二叉树 t = new Q31_序列化二叉树();
        TreeNode a = new TreeNode(1);
        TreeNode b1 = new TreeNode(2);
        TreeNode b2 = new TreeNode(3);
        TreeNode c1 = new TreeNode(4);
        TreeNode c2 = new TreeNode(5);
        TreeNode c3 = new TreeNode(6);
        a.left=b1;
        a.right=b2;
        b1.left=c1;
        b2.left=c2;
        b2.right=c3;
        String serialize = t.Serialize(a);
        System.out.println(serialize);
        TreeNode root = t.Deserialize(serialize);
        t.prePrint(root);
    }

    void prePrint(TreeNode root){
        if (root==null){
            return;
        }
        System.out.println(root.val);
        prePrint(root.left);
        prePrint(root.right);
    }


}
