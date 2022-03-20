package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * author : tsp
 * Date : 2022/3/19
 * DESC:给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * <p>
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 * <p>
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * Related Topics 栈 树 深度优先搜索 二叉树 👍 1324 👎 0
 * <p>
 * leetcode submit region begin(Prohibit modification and deletion)
 */
public class Problem_0094_BinaryTreeInorderTraversal {


    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 二叉树的中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    /**
     * 递归版本：中序遍历：左根右
     */
    public void process(TreeNode node, List<Integer> ans) {
        if (node == null) return;
        process(node.left, ans);
        ans.add(node.val);
        process(node.right, ans);
    }

    /**
     * 迭代：中序遍历：左根右
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            //一直往左遍历，添加到栈中
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            TreeNode item = stack.pop();
            ans.add(item.val);
            //如果当前节点有右孩子，则加入到stack中
            cur = item.right;
        }
        return ans;
    }


    /**
     * Morris遍历
     * 1.如果当前cur为null，则停止
     * 2.如果cur没有左子树，则cur向右移动，cur = cur.right
     * 3.如果cur有左子树，则找到左子树上的最有节点，记为mostRight
     * 1）如果 mostRight的right指针指向null，则令 mostRight.right = cur,然后cur向左移动
     * 2) 如果 mostRight的right指针指向cur，则令 mostRight.right = null, cur 向右移动，cur = cur.right
     * <p>
     * 树：
     * ------------4
     * --------2      6
     * ------1   3  5   7
     * <p>
     * Morris遍历结果为：4，2，1，2，3，4，6，5，6，7
     */
    public static void morris(TreeNode root) {
        if (root == null) return;
        TreeNode cur = root, mostRight;
        while (cur != null) {
            System.out.println(cur.val);
            mostRight = cur.left;
            if (mostRight != null) {
                //找cur左子树上最右的节点,就是mostRight一直向右移动
                //要确保走到最右边的节点停止，如果是第二次到的过程，存在mostRight.right = cur
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                //2.来到最右位置，如果是第一次来,mostRight.right == null)
                if (mostRight.right == null) {
                    //让它指向cur
                    mostRight.right = cur;
                    //继续这个过程，cur来到cur.left位置,继续一cur.left为头，找它左子树上最右的节点，如果存在的话
                    cur = cur.left;
                } else {
                    //第二次来，断开之前的指针，cur右移动
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {
                //来到右子树
                cur = cur.right;
            }
        }
    }

    /**
     * ------------4
     * --------2      6
     * ------1   3  5   7
     * Morris遍历结果为：4，2，1，2，3，4，6，5，6，7
     * 先序遍历 根左右 ：4，2，1，3，6，5，7
     * Morris遍历加工先序遍历：第一次来到就打印
     * 1.对于cur只能到达一次的节点(无左子树), cur 到达时打印
     * 2.对于cur可以达到两次的节点(有左子树)，cur 第一次到达时打印，第二次达到不打印
     */
    public static void morrisPre(TreeNode root) {
        if (root == null) return;
        TreeNode cur = root, mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                //找cur左子树上最右的节点,就是mostRight一直向右移动
                //要确保走到最右边的节点停止，如果是第二次到的过程，存在mostRight.right = cur
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                //2.来到最右位置，如果是第一次来,mostRight.right == null)
                if (mostRight.right == null) {
                    //让它指向cur
                    mostRight.right = cur;
                    //情况2：对于cur可以达到两次的节点(有左子树)，cur 第一次到达时打印，第二次达到不打印
                    System.out.println(cur.val);
                    //继续这个过程，cur来到cur.left位置,继续一cur.left为头，找它左子树上最右的节点，如果存在的话
                    cur = cur.left;
                } else {
                    //第二次来，断开之前的指针，cur右移动
                    mostRight.right = null;
                    cur = cur.right;
                    //这里第二次来，它的mostRight.right是指向cur的，所以这个时候的cur是第二次来到，打印
                }
            } else {
                //情况1.对于cur只能到达一次的节点(无左子树), cur 到达时打印
                System.out.println(cur.val);
                //来到右子树
                cur = cur.right;
            }
        }
    }


    /**
     * ------------4
     * --------2      6
     * ------1   3  5   7
     * Morris遍历结果为：4，2，1，2，3，4，6，5，6，7
     * 中序遍历 左根右 ：1，2，3，4，5，6，7 : 可以发现，Morris 遍历中，出现两次的根几点打印第二次出现的
     * Morris遍历加工中序遍历：第二次来到就打印
     * 1.对于cur只能到达一次的节点(无左子树), cur 到达时打印
     * 2.对于cur可以达到两次的节点(有左子树)，cur 第二次到达时打印，第一次不打印
     */
    public static void morrisInorder(TreeNode root) {
        if (root == null) return;
        TreeNode cur = root, mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                //找cur左子树上最右的节点,就是mostRight一直向右移动
                //要确保走到最右边的节点停止，如果是第二次到的过程，存在mostRight.right = cur
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                //2.来到最右位置，如果是第一次来,mostRight.right == null)
                if (mostRight.right == null) {
                    //让它指向cur
                    mostRight.right = cur;
                    //继续这个过程，cur来到cur.left位置,继续一cur.left为头，找它左子树上最右的节点，如果存在的话
                    cur = cur.left;
                } else {
                    //第二次来，断开之前的指针，cur右移动
                    mostRight.right = null;
                    //todo 对于cur可以达到两次的节点(有左子树)，cur 第二次到达时打印，第一次不打印
                    System.out.println(cur.val);
                    cur = cur.right;
                    //这里第二次来，它的mostRight.right是指向cur的，所以这个时候的cur是第二次来到，打印
                }
            } else {
                //todo /情况1.对于cur只能到达一次的节点(无左子树), cur 到达时打印
                System.out.println(cur.val);
                //来到右子树
                cur = cur.right;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(4);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(6);
        TreeNode d = new TreeNode(1);
        TreeNode e = new TreeNode(3);
        TreeNode f = new TreeNode(5);
        TreeNode g = new TreeNode(7);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;

//        morrisPre(a);
        morrisInorder(a);

    }


}
