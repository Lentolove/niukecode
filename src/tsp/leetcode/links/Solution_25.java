package tsp.leetcode.links;

import tsp.offer.ListNode;


import java.util.Stack;

/**
 * leetcode: 25 K个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 示例 :
 * 给定这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 说明 :
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 */
public class Solution_25 {

    /**
     * 尾插法 k=3
     * pre
     * tail    head
     * dummy    1     2     3     4     5
     * # 我们用tail 移到要翻转的部分最后一个元素
     * pre     head       tail
     * dummy    1     2     3     4     5
     * cur
     * # 我们尾插法的意思就是,依次把cur移到tail后面
     * pre          tail  head
     * dummy    2     3    1     4     5
     * cur
     * # 依次类推
     * pre     tail      head
     * dummy    3     2    1     4     5
     * cur
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        //创建哑结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;//前序节点
        ListNode tail = dummy;//尾节点节点
        int index = k;
        while (true) {
            int count = 0;
            while (tail != null && count != k) {
                count++;
                tail = tail.next;
            }
            if (tail == null) break;
            ListNode hea1 = pre.next;
            while (pre.next != tail) {
                ListNode cur = pre.next;
                pre.next = cur.next;
                cur.next = tail.next;
                tail.next = cur;
            }
            pre = hea1;
            tail = hea1;
        }
        return dummy.next;
    }

    /**
     * 方法二：用栈 每k个节点压入栈中 弹出的就是翻转的链表
     * 注意问题：
     * 问题一：剩下的链表个数不够k个
     * 问题二：已经反转的链表与剩下的链表连接起来
     */
    public static ListNode reverseKGroup1(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (true) {
            int count = 0;
            ListNode temp = head;
            while (temp != null && count < k) {
                stack.push(temp);
                temp = temp.next;
                count++;
            }
            if (count != k) {
                p.next = head;
                break;
            }
            while (!stack.isEmpty()) {
                p.next = stack.pop();
                p = p.next;
            }
            p.next = temp;
            head = temp;
        }
        return dummy.next;
    }

    /**
     * 递归解法：
     */
    public static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;
        while (cur!=null&&count!=k){
            cur = cur.next;
            count++;
        }
        if (count==k){
            cur = reverseKGroup2(cur,k);
            while (count!=0){
                count--;
                ListNode temp = head.next;
                head.next =cur;
                cur =head;
                head = temp;
            }
            head = cur;
        }
        return head;
    }
}
