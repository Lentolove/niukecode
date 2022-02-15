package tsp.hot100;

/**
 * Author : tsp
 * Time: 2022/2/14 20:52
 * Desc :给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q2_两数相加 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 双指针：注意进位
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        int ca = 0;
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = a + b + ca;
            ListNode item = new ListNode(sum % 10);
            ca = sum / 10;
            cur.next = item;
            cur = item;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) l2 = l2.next;
        }
        if (ca != 0) {
            cur.next = new ListNode(ca);
        }
        return dummy.next;
    }
}
