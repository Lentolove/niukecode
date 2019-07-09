package tsp.leetcode.links;

import tsp.offer.ListNode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 */
public class Solution_24 {

    /**
     * 迭代法
     */
    public ListNode swapPairs1(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        pre.next = head;
        while (pre.next!=null&&pre.next.next!=null){
            ListNode l1 = pre.next;
            ListNode l2 = pre.next.next;
            l1.next = l2.next;
            l2.next = l1;
            pre.next = l2;

            pre = l1;
        }
        return dummy.next;
    }


    /**
     * 递归
     * 递归写法要观察本级递归的解决过程，形成抽象模型，因为递归本质就是不断重复相同的事情。
     * 而不是去思考完整的调用栈，一级又一级，无从下手。如图所示，我们应该关注一级调用小单元的情况，
     * 也就是单个 f(x)
     */
    public ListNode swapPairs(ListNode head) {
        if (head==null||head.next==null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;

    }




    }
