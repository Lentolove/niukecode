package zuoshen.interview.classic5;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 左神：将二叉树搜索树转成双向链表
 * 思路：1.中序遍历是有序的，先用队列按照中序遍历存储各个节点，然后将节点连成双链表
 * 2.树的问题，直接用树形DP去解决
 */
public class Code02_BSTtoDoubleLinkedList {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 思路一：用队列存储中序遍历
     */
    public static Node convert1(Node head) {
        if (head == null) return null;
        //1.将节点放在队列中
        Queue<Node> queue = new LinkedList<>();
        inOrderToQueue(head, queue);
        //2.开始转成双链表
        head = queue.poll();
        Node pre = head;//前节点
        //头节点
        pre.left = null;
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        //遍历到最后一个节点
        pre.right = null;
        return head;
    }

    //中序遍历
    public static void inOrderToQueue(Node head, Queue<Node> queue) {
        if (head == null) return;
        inOrderToQueue(head.left, queue);
        queue.add(head);
        inOrderToQueue(head.right, queue);
    }


    /**
     * 树形DP：整棵树传承双向链表，返回头节点和尾节点
     */
    public static class Info {
        public Node start;
        public Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Info process(Node x) {
        if (x == null) return new Info(null, null);
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        //有了x的左右两侧的信息
        if (leftInfo.end != null) {
            leftInfo.end.right = x;
        }
        x.left = leftInfo.end;
        x.right = rightInfo.start;
        if (rightInfo.start != null) {
            rightInfo.start.left = x;
        }
        //返回当前结果
        return new Info(
                // 整棵树的头，
                leftInfo.start != null ? leftInfo.start : x,
                // 整棵树的尾
                rightInfo.end != null ? rightInfo.end : x
        );
    }

    public static Node convert2(Node head) {
        if (head == null) return null;
        return process(head).start;
    }


    public static void inOrderPrint(Node head) {
        if (head == null) {
            return;
        }
        inOrderPrint(head.left);
        System.out.print(head.value + " ");
        inOrderPrint(head.right);
    }

    public static void printBSTInOrder(Node head) {
        System.out.print("BST in-order: ");
        if (head != null) {
            inOrderPrint(head);
        }
        System.out.println();
    }

    public static void printDoubleLinkedList(Node head) {
        System.out.print("Double Linked List: ");
        Node end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.right;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.left;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);

        printBSTInOrder(head);
        head = convert1(head);
        printDoubleLinkedList(head);

        head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);

        printBSTInOrder(head);
        head = convert2(head);
        printDoubleLinkedList(head);

    }
}
