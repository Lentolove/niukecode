package tsp.leetcode.links;

import tsp.offer.ListNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * leetcode:445 两数相加
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 进阶:
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * 示例:
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 8 -> 0 -> 7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 */
class p {

}


public class Solution_445 {

    /**
     * leetcode 右端对齐
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode node1 = l1;
        while (node1 != null) {
            stack1.add(node1.val);
            node1 = node1.next;
        }
        ListNode node2 = l2;
        while (node2 != null) {
            stack2.add(node2.val);
            node2 = node2.next;
        }
        ListNode head = null;
        int flag = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || flag != 0) {
            int value = 0;
            if (!stack1.isEmpty()) {
                value += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                value += stack2.pop();
            }
            value += flag;
            ListNode node = new ListNode(value % 10);
            flag = value / 10;
            //反向连接链表 弄得我去构建第三个栈输出顺序链表 哎.....
            node.next = head;
            head = node;
        }
        return head;
    }

    /**
     * 牛客网是： 左端对齐
     * 2->4->3 + 5->6->4 = 7->0->8
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        ListNode node1 = l1;
        while (node1 != null) {
            q1.add(node1.val);
            node1 = node1.next;
        }
        ListNode node2 = l2;
        while (node2 != null) {
            q2.add(node2.val);
            node2 = node2.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int flag = 0;
        while (!q1.isEmpty() || !q2.isEmpty() || flag != 0) {
            int value = 0;
            if (!q1.isEmpty()) {
                value += q1.poll();
            }
            if (!q2.isEmpty()) {
                value += q2.poll();
            }
            value += flag;
            ListNode node = new ListNode(value % 10);
            flag = value / 10;
            cur.next = node;
            cur = cur.next;
        }

        return dummy.next;
    }

    /**
     * 解法二：
     * 牛客网是： 左端对齐
     * 2->4->3 + 5->6->4 = 7->0->8
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int count = 0;
        while (l1 != null && l2 != null) {
            int value = l1.val + l2.val + count;
            cur.next = new ListNode(value % 10);
            cur = cur.next;
            count = value / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int value = l1.val + count;
            cur.next = new ListNode(value % 10);
            cur = cur.next;
            count = value / 10;
            l1 = l1.next;
        }
        while (l2 != null) {
            int value = l2.val + count;
            cur.next = new ListNode(value % 10);
            cur = cur.next;
            count = value / 10;
            l2 = l2.next;
        }
        if (count == 1) {
            cur.next = new ListNode(1);
        }
        return dummy.next;
    }


    public ListNode creat(int[] nums) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (int i = 0; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution_445 s = new Solution_445();
//        int[] a = {7, 2, 4, 3};
        int[] a = {2, 4, 3};
//        int[] b = {5, 6, 4};
        int[] b = {5, 6, 4};
        ListNode l1 = s.creat(a);
        ListNode l2 = s.creat(b);
        ListNode head = s.addTwoNumbers2(l1, l2);
        while (head != null) {
            System.out.println(head.val + " ");
            head = head.next;
        }
    }
}
