package tsp.leetcode.links;

import tsp.offer.ListNode;

/**
 * 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_92 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;
        for (int i = 1; i < m; i++) {
            pre =pre.next; //得到翻转节点的前一个节点
        }
        ListNode cur = pre.next;
        /**
         *  翻转节点 两个两个翻转 感觉类似冒泡法,但是不是相邻之间节点的互换位置
         *  2 -> 3 -> 4 需要翻转两次
         *  1: 3 -> 2 -> 4
         *  2: 4 -> 3 -> 2
         */
        for (int i = m; i < n; i++) {
          ListNode temp = cur.next; //待翻转的节点
          cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummy.next;
    }
}
