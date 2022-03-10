package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/8 14:45
 * Desc : 给定两个链表 head1 3->4->6 和 head2 4->6->2
 * 表示 643 + 264 = 907 输出：7->0->9
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 */
public class Problem_0002_AddTwoNumbers {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int value) {
            this.val = value;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = new ListNode(-1);
        int ca = 0;
        ListNode cur = head;
        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = ca + a + b;
            cur.next = new ListNode(sum % 10);
            ca = sum / 10;
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (ca != 0) {
            cur.next = new ListNode(ca);
        }
        return head.next;
    }
}
