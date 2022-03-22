package zuoshen.topinterview;

/**
 * author : tsp
 * Date : 2022/3/22
 * DESC:给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由
 * next 指针连接，'#' 标志着每一层的结束。
 * <p>
 * 示例 2:
 * 输入：root = []
 * 输出：[]
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * Related Topics 树 深度优先搜索 广度优先搜索 链表 二叉树 👍 705 👎 0
 * <p>
 * <p>
 * leetcode submit region begin(Prohibit modification and deletion)
 * /*
 * Definition for a Node.
 * class Node {
 * public int val;
 * public Node left;
 * public Node right;
 * public Node next;
 * <p>
 * public Node() {}
 * <p>
 * public Node(int _val) {
 * val = _val;
 * }
 * <p>
 * public Node(int _val, Node _left, Node _right, Node _next) {
 * val = _val;
 * left = _left;
 * right = _right;
 * next = _next;
 * }
 * };
 */

public class Problem_0116_PopulatingNextRightPointersInEachNode {


    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    }

    /**
     * 借助Node的next指针封装单链表，只是用有限几个变量
     * 1.提供增加和弹出的操作
     */
    public static class MyQueue {
        public Node head, tail;
        int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void offer(Node cur) {
            size++;
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
        }

        //弹出之前自己判空：isEmpty
        public Node poll() {
            size--;
            Node ans = head;
            head = head.next;
            ans.next = null;
            return ans;
        }
    }

    /**
     * 题意：Node节点有next指针，按照每层，将左边的 left.next = right
     * label: 数据结构设计题目
     * ------1        1 ->null
     * ---2    3    2 -> 3 -> null
     * <p>
     * 思路：层序遍历，按照每层从左往右将该层的next指针连起来
     * 进阶思路：只能使用常量级额外空间，不适用队列来实现层序遍历
     * 借助Node的next指针，自己封装 单链表来实现队列
     */
    public Node connect(Node root) {
        if (root == null) return root;
        MyQueue queue = new MyQueue();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size;
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                //连接next指针
                if (pre == null){
                    pre = cur;
                }else {
                    pre.next = cur;
                    pre = cur;
                }
            }
        }
        return root;
    }

}
