package tsp.leetcode.links;

import tsp.offer.ListNode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 */
public class Solution_19 {


    /**
     * 方法一：
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dumy = new ListNode(-1);
        dumy.next = head;
        ListNode fast = dumy;
        ListNode slow = dumy;
        while (n-->0){
            fast = fast.next;
        }
        while (fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dumy.next;
    }

    /**
     * 方法二：两次遍历
     *  删除链表的第 L - n + 1 个节点，其中L是链表的长度
     *
     */

    public ListNode removeNthFromEnd1(ListNode head,int n){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int lenght = 0;
        ListNode first = head;
        while (first!=null){
            lenght++;
            first = first.next;
        }
        lenght -=n;  //从 哑结点 开始的 所以不需要加一
        first = dummy;
        while (lenght>0){
            lenght--;
            first =first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }

}
