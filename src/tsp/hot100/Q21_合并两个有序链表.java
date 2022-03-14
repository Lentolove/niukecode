package tsp.hot100;

/**
 * Author : tsp
 * Time: 2022/2/15 16:42
 * Desc :将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * √√√√√√√√√√√√√√√√√√√√√√√√√√√√√√
 */
public class Q21_合并两个有序链表 {

    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    /**
     * 递归方法
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.value < list2.value) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    /**
     * 思路二：迭代归并
     */
    public static ListNode mergeTwoList1(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (list1 != null && list2 != null) {
            if (list1.value < list2.value) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        if (list1 != null) {
            p.next = list1;
        }
        if (list2 != null) {
            p.next = list2;
        }
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
        ListNode node = create(new int[]{1, 3, 5, 7, 9});
        ListNode nod2 = create(new int[]{2, 4, 6, 8, 10});
//        ListNode newHead = mergeTwoLists(node, nod2);
        ListNode newHead1 = mergeTwoList1(node, nod2);
//        printListNode(newHead);
        printListNode(newHead1);
    }


}
