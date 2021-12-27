package zuoshen.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tsp.offer.TreeNode;

public class Section7 {

    public static class Node {
        int value;
        Node left, right, parent;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 左神：返回节点的后继节点
     * 方法：考虑一个节点和其后继节点的结构之间的关系：
     * - 如果一个节点x有右树，那么其后继节点就是右树最左的节点。
     * - 如果x没有右树，往上找父亲节点。如果 x是其父亲的右孩子继续往上找，如果某节点是其父亲节点的左孩子，那么该节点的父亲就是x的后继节点
     */
    public static Node getSuccessorNode(Node node) {
        if (node == null)
            return null;
        if (node.right != null) {
            return getLeft(node.right);
        } else {
            // node没有右树，往上找父节点
            Node parent = node.parent;
            // 如果node为父节点的右子树，则继续向上查找，知道找到root节点，该节点的parent为null
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    /**
     * 返回一棵树的最左节点
     */
    public static Node getLeft(Node node) {
        if (node == null)
            return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /************************************************
     * 折纸问题
     *********************************************************/

    /**
     * 左神：折纸问题
     * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕凸起的方向指向纸条的背面。
     * 如果从纸条的下边向上方对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕，下折痕和上折痕。给定一个输入参数N,代表纸条都从下边向上方连续对折N次。请从上到下打印所有的折痕的方向。
     * 对应的树结构按层输出为：
     * 1凹 //折纸第一次凹痕
     * / \
     * 2凹 2凸 //折纸第二次凹痕
     * / \ / \
     * 3凹 3凸 3凹 3凸 //折纸第三次凹痕
     */
    public static void printAllFolds(int n) {
        printProcess(1, n, true);
    }

    /**
     * 因为纸是从上往下打印，对应的是二叉树的中序遍历
     */
    public static void printProcess(int i, int n, boolean down) {
        if (i > n)
            return;
        // 中序遍历
        // 每个当前节点的左子节点是凹
        printProcess(i + 1, n, true);
        System.out.println(down ? "凹" : "凸");
        // 当前节点的右子树都是凸
        printProcess(i + 1, n, false);
    }

    /**********************打家劫舍III********************************/
    class RobInfo {
        int rob;// 偷当前节点的最大值
        int noRob;// 不偷当前节点的最大值

        public RobInfo(int rob, int noRob) {
            this.rob = rob;
            this.noRob = noRob;
        }
    }

    /**
     * 左神：树型DP思路
     * 1.当前节点选择偷时，那么两个孩子节点就不能选择偷了
     * 2.当前节点选择不偷时，两个孩子节点只需要拿最多的钱出来就行(两个孩子节点偷不偷没关系)
     */
    public RobInfo dfs(TreeNode node) {
        if (node == null)
            return new RobInfo(0, 0);
        RobInfo left = dfs(node.left);
        RobInfo right = dfs(node.right);
        // 偷当前节点能得到的最大值
        int rob = node.val + left.noRob + right.noRob;
        // 当前节点不偷，那么子孩子可偷可不偷，取较大值即可
        int noRob = Math.max(left.rob, left.noRob) + Math.max(right.rob, right.noRob);
        return new RobInfo(rob, noRob);
    }

    public int rob(TreeNode node) {
        if (node == null)
            return 0;
        RobInfo ans = dfs(node);
        return Math.max(ans.rob, ans.noRob);
    }

    /************** 二叉树是否是平衡二叉树*****************************/

    class Info1 {
        boolean isBalanced;// 是否是平衡二叉树
        int height;// 它的高度是多少

        Info1(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public boolean isBalanced(Node node) {
        if (node == null)
            return true;
        Info1 info = process(node);
        return info.isBalanced;
    }

    public Info1 process(Node node) {
        if (node == null)
            return new Info1(true, 0);
        Info1 left = process(node.left);
        Info1 right = process(node.right);
        // 1.是否是平衡树
        boolean isBalance = true;
        if (!left.isBalanced || !right.isBalanced || Math.abs(left.height - right.height) > 1) {
            isBalance = false;
        }
        // 2.高度等于左右最大高度，加上当前头结点的高度1
        int height = Math.max(left.height, right.height) + 1;
        return new Info1(isBalance, height);
    }

    /****************** 返回二叉树任意两个节点最大值*********************/
    // 我们的信息，整棵树的最大距离和整棵树的高度
    class Info2 {
        int maxDistance;
        int height;

        Info2(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public Info2 process2(Node node) {
        if (node == null)
            return new Info2(0, 0);
        Info2 left = process2(node.left);
        Info2 right = process2(node.right);
        // 计算高度，那就拿子树的最大高度+1；
        int height = Math.max(left.height, right.height) + 1;
        // 自身最大距离，是左右树最大距离和左右树高度相加再加1，求最大值,前面不经过X,后面经过x
        int maxDistance = Math.max(Math.max(left.maxDistance, right.maxDistance), left.height + right.height + 1);
        return new Info2(maxDistance, height);
    }

    public int maxDistance(Node node) {
        return process2(node).maxDistance;
    }

    /********************返回二叉树中的最大二叉搜索树Size**************************/
    static class Info3 {
        boolean isAllBST;// 是否是二叉搜索树
        int maxSubBSTSize;// 最大满足二叉搜索树条件的size
        int minValue;// 整棵树的最小值
        int maxValue;// 整棵树的最大值

        Info3(boolean isAllBST, int maxSubBSTSize, int minValue, int maxValue) {
            this.isAllBST = isAllBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

    public static Info3 process3(Node node) {
        if (node == null)
            return null;
        Info3 left = process3(node.left);
        Info3 right = process3(node.right);
        int min = node.value;
        int max = node.value;
        if (left != null) {
            min = Math.min(min, left.minValue);
            max = Math.max(max, left.maxValue);
        }
        if (right != null) {
            min = Math.min(min, right.minValue);
            max = Math.max(max, right.maxValue);
        }
        // 1.与当前节点X无关，即最终找到的搜索二叉树，不以X为头
        int maxSubBSTSize = 0;
        if (left != null)
            maxSubBSTSize = left.maxSubBSTSize;
        if (right != null)
            maxSubBSTSize = Math.max(maxSubBSTSize, right.maxSubBSTSize);
        // 2.与x节点有关，即二叉搜索树包涵x节点
        boolean isAllBST = false;
        if ((left == null || left.isAllBST) && (right == null || right.isAllBST) &&
                (left == null || left.maxValue < node.value) && (right == null || right.minValue > node.value)) {
            isAllBST = true;
            maxSubBSTSize = (left == null ? 0 : left.maxSubBSTSize) + (right == null ? 0 : right.maxSubBSTSize) + 1;
        }
        return new Info3(isAllBST, maxSubBSTSize, min, max);
    }

    public static int maxSubBSTSize(Node node) {
        if (node == null)
            return 0;
        return process3(node).maxSubBSTSize;
    }

    /********************************派对最大快乐值***********************************
     */

    class Employee {
        // 这名员工可以带来的快乐值
        public int happy;
        // 这名员工有哪些直接的下级
        List<Employee> nexts;
    }

    class Info4 {
        int yes;// 头结点在来的情况下整棵树的最大值
        int no;// 头结点在不来的情况下整棵树的最大值

        Info4(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public Info4 process4(Employee x) {
        // base case 基层员工,那肯定是直接选择该快乐值
        if (x.nexts == null)
            return new Info4(x.happy, 0);
        // 当前x来初始值
        int yes = x.happy;
        // x不来的初始值
        int no = 0;
        for (Employee e : x.nexts) {
            // 递归查找每个下级的快乐值
            Info4 info = process4(e);
            // 根据子树的递归返回的信息，加工自身的info
            // 如果X来，子不来
            yes += info.no;
            // x不来，下级来不来取其中的最大值即可
            no += Math.max(info.yes, info.no);
        }
        return new Info4(yes, no);
    }

    public int maxHappy(Employee boss) {
        if (boss == null)
            return 0;
        Info4 info = process4(boss);
        return Math.max(info.yes, info.no);
    }

    /************************ 判断二叉树是否是满二叉树******************************** */
    class Info5 {
        int height;// 树的高度
        int nodes;// 树的节点数

        Info5(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public Info5 process5(Node node) {
        if (node == null)
            return new Info5(0, 0);
        Info5 left = process5(node.left);
        Info5 right = process5(node.right);
        // 1.高度
        int height = Math.max(left.height, right.height) + 1;
        int nodes = left.nodes + right.nodes + 1;
        return new Info5(height, nodes);
    }

    public boolean isFull(Node node) {
        if (node == null)
            return true;
        Info5 info = process5(node);
        return info.nodes == (1 << info.height) - 1;
    }

    /*******************************是否是完全二叉树****************************************/
    /**
     * 给定一棵二叉树的头结点head，返回这颗二叉树是不是完全二叉树
     * 定义：完全二叉树，左右树高度相差不超过一
     * 解法一：迭代法
     * - 如果用树的宽度优先遍历的话，如果某个节点有右孩子，但是没有左孩子，一定不是完全二叉树
     * - 在1条件的基础上，一旦遇到第一个左右孩子不双全的节点，后续所有节点必须为叶子节点
     */
    public boolean isCBT1(Node node) {
        if (node == null)
            return true;
        Queue<Node> queue = new LinkedList<>();
        // 是否遇到过左右孩子不双全的情况的节点
        boolean leaf = false;
        Node l = null, r = null;
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            l = node.left;
            r = node.right;
            // 如果之前已经遇到非双全的父节点，在遇到一定不满足,2左孩子为null，右孩子不为null，一定不满足
            if (leaf && (l != null || r != null) || (l == null && r != null)) {
                return false;
            }
            if (l != null)
                queue.add(l);
            if (r != null)
                queue.add(r);
            if (l == null && r == null)
                leaf = true;
        }
        return true;
    }

    // 对每一棵子树，是否是满二叉树、是否是完全二叉树、高度
    class Info6 {
        boolean isFull;
        boolean isCBT;
        int height;

        Info6(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public Info6 process6(Node node) {
        if (node == null)
            return new Info6(true, true, 0);
        Info6 left = process6(node.left);
        Info6 right = process6(node.right);
        int height = Math.max(left.height, right.height) + 1;
        // X是否是满二叉树信息=左右都是满且左右高度一样
        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        // 是否是完全二叉树
        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else {
            // 有缺口，1缺口可能停在我的左树上。左树需要给我是否是完全二叉树，右树需要给X是否是满二叉树，且左树高度比右树高度大1
            if (left.isCBT && right.isFull && left.height == right.height + 1) {
                isCBT = true;
            }
            // 缺口可能在左右树的分界。左树是满的，右树也是满的，左树高度比右树大1
            if (left.isFull && right.isFull && left.height == right.height + 1) {
                isCBT = true;
            }
            // 左树已经满了，缺口可能在我的右树上。左树是满的，右树是完全二叉树，且左右树高度一样
            if (left.isFull && right.isCBT && left.height == right.height) {
                isCBT = true;
            }
        }
        return new Info6(isFull, isCBT, height);
    }

    public boolean isCBT(Node node) {
        if (node == null)
            return true;
        return process6(node).isCBT;
    }

    /***************** 二叉树的最近公共祖先********************/

    // 任何子树需要的信息结构,我们需要求交集
    class Info7 {
        Node ans;// o1和o2的最初交汇点，如果不是在当前这颗X节点的树上，返回空
        boolean findo1;// 在当前子树上，是否发现过o1和o2
        boolean findo2;

        Info7(Node ans, boolean findo1, boolean findo2) {
            this.ans = ans;
            this.findo1 = findo1;
            this.findo2 = findo2;
        }
    }

    /**
     * 1、o1和o2都不在以X为头的树上
     * 2、o1和o2有一个在以X为头的树上
     * 3、o1和o2都在以X为头的树上
     */
    public Info7 process7(Node x, Node o1, Node o2) {
        //当前node为null，则交汇点为null，在当前子树未发现o1和o2
        if (x == null) return new Info7(null, false, false);
        Info7 left = process7(x.left, o1, o2);
        Info7 right = process7(x.right, o1, o2);
        //1.构建x自身的返回Info7
        //2.x为头是否发现了o1,o2
        boolean findo1 = x == o1 || left.findo1 || right.findo1;
        boolean findo2 = x == o2 || left.findo2 || right.findo2;
        //3.o1和o2是否都在以x为头的树上,如果不是在当前这颗X节点的树上，返回空
        Node ans = null;
        if (left.ans != null) ans = left.ans;
        if (right.ans != null) ans = right.ans;
        if (ans == null) {
            //没有在x点交汇，但是在x点子树上找到了o1和o2，则交汇点就是本身，
            if (findo1 && findo2) ans = x;
        }
        return new Info7(ans, findo1, findo2);
    }

    public Node lowestAncestor(Node head, Node o1, Node o2) {
        return process7(head, o1, o2).ans;
    }


    /*******************Morris遍历*********************/

    public static void morris(Node head) {
        if (head == null) return;
        Node cur = head, mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                //1.找到左子树上最右边的节点,mostRight.right 指向 cur，要确保走到最右边的节点停止，否则进入循环
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                //2.如果 mostRight.right 指向 null，则说明第一次来到，我们让他指向 cur
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    //cur指针向左移动，继续这个过程
                    cur = cur.left;
                    continue;
                } else {
                    //如果 mostRight.right 是指向 cur 的，说明是第二次来,这个时候需要断开了，恢复树
                    mostRight.right = null;
                }
            }
            //1.cur没有左子树，则cur向右移动
            //2.或者 cur 的左子树的最右节点指向的是cur，则在  mostRight.right = null; 恢复之后，说明cur左边遍历完了，继续往右边
            cur = cur.right;
        }
    }

    //morris遍历顺序：4-21-2-3-4-6-5-6-7
    public static void morrisPre(Node head) {
        if (head == null) return;
        Node cur = head, mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                //1.找到左子树上最右边的节点,mostRight.right 指向 cur，要确保走到最右边的节点停止，否则进入循环
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                //2.如果 mostRight.right 指向 null，则说明第一次来到，我们让他指向 cur
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    //第一次到来打印
                    System.out.println(cur.value);
                    //cur指针向左移动，继续这个过程
                    cur = cur.left;
                    continue;
                } else {
                    //如果 mostRight.right 是指向 cur 的，说明是第二次来,这个时候需要断开了，恢复树
                    mostRight.right = null;
                }
            }else {
                //第一次
                System.out.println(cur.value);
            }
            //1.cur没有左子树，则cur向右移动
            //2.或者 cur 的左子树的最右节点指向的是cur，则在  mostRight.right = null; 恢复之后，说明cur左边遍历完了，继续往右边
            cur = cur.right;
        }
    }


    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
//        int maxLevel = 4;
//        int maxValue = 100;
//        int testTimes = 1000000;
//        for (int i = 0; i < testTimes; i++) {
//            Node head = generateRandomBST(maxLevel, maxValue);
//            if (maxSubBSTSize1(head) != maxSubBSTSize(head)) {
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("finish!");

        Node node = new Node(4);
        Node a = new Node(2);
        Node b = new Node(6);
        Node c = new Node(1);
        Node d = new Node(3);
        Node e = new Node(5);
        Node f = new Node(7);
        node.left = a;
        node.right = b;
        a.left = c;
        a.right = d;
        b.left = e;
        b.right = f;
        morris(node);
        morrisPre(node);

    }

}
