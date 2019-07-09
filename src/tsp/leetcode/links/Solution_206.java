package tsp.leetcode.links;

import tsp.offer.ListNode;

/**
 * 反转链表 II
 * <p>
 * <p>
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 */
public class Solution_206 {

    /**
     * 方法一：递归
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dumy = new ListNode(-1);
        ListNode pre = dumy;
        dumy.next = head;
        for (int i = 0; i < m - 1; i++) { //题目是从1开始计数的 找到要反转节点的前一个节点
            pre = pre.next;
        }
        ListNode cur = pre.next;
        for (int i = m; i < n; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dumy.next;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        reverseBetween(a, 1, 1);
    }
}
