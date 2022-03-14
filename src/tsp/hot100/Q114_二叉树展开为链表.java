package tsp.hot100;

/**
 * Author : tsp
 * Time: 2022/2/22 20:16
 * Desc :
 */
public class Q114_二叉树展开为链表 {

    public static class TreeNode{
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    /**
     * 迭代法：
     * 1.找到左子树最右边的节点
     * 2.将右子树接入到左子树的最右边的节点
     * 3.将左子树赋值给右子编，让root的左子树置位null，root指针往右移动，继续判断
     * 4.考虑新的右子树的节点
     */
    public static void flatten(TreeNode node){
        if (node == null) return;
        while (node != null){
            if (node.left == null){
                //1.如果不存在左子树，则指针移到右子树上继续上述过程
                node = node.right;
            }else {
                //2.查找node节点的左子树的最右节点
                TreeNode left = node.left;
                while (left.right != null){
                    left = left.right;
                }
                //3.将node的右子树接上
                left.right = node.right;
                //4.将左子树移动到右子树上
                node.right = node.left;
                //4.指针移动到右子树上继续该过程
                node.left = null;
                node = node.right;
            }
        }
    }


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(5);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(6);
        head.left = a;
        head.right = b;
        a.left = c;
        a.right = d;
        b.right = e;
        flatten(head);
        printTreeNode(head);
    }

    private static void printTreeNode(TreeNode node){
        TreeNode point = node;
        while (point != null){
            System.out.println(point.value);
            point = point.right;
        }
    }

}
