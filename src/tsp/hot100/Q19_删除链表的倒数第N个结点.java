package tsp.hot100;

/**
 * Author : tsp √√√√√√√√√√√√√√√√√√√√√√√√√√√√√√
 * Time: 2022/2/15 14:24
 * Desc :给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 */
public class Q19_删除链表的倒数第N个结点 {

    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    /**
     * 思路：删除链表节点问题
     * 1.删除节点问题通常创建亚节点，因为可能删除的是头节点
     * 2.删除倒是第 k 个节点，也就是第 n - k 个 节点。
     * 3.用两个指针，一个先走k步，第二个再开始走，直到 first 为 null 停止
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode first = dummy;
        //题目限定了 n <= size
        while (n != 0) {
            first = first.next;
            n--;
        }
        ListNode pre = dummy;
        ListNode second = dummy;
        while (first != null) {
            pre = second;
            second = second.next;
            first = first.next;
        }
        //3.断链
        pre.next = second.next;
        second.next = null;
        return dummy.next;
    }

    public static ListNode create(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return head;
    }

    public static void printListNode(ListNode node) {

        while (node != null) {
            System.out.print(node.value + " : ");
            node = node.next;
        }
    }


    public static void main(String[] args) {
        ListNode head = create(new int[]{1, 2, 3, 4, 5});
        printListNode(head);
        ListNode node = removeNthFromEnd(head, 5);
        printListNode(node);
    }
}
