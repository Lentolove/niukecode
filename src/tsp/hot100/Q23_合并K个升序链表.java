package tsp.hot100;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author : tsp TODO 归并方法
 * Time: 2022/2/15 17:10
 * Desc :给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q23_合并K个升序链表 {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 思路一：用小根堆来存储链表的头节点，每次弹出小根堆中头节点最小的链表连起来接着移动指针
     * 1.没有用到链表已排序的情况
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        for (ListNode head : lists) {
            if (head != null) {
                queue.add(head);
            }
        }
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            p.next = cur;
            cur = cur.next;
            p = p.next;
            if (cur != null) {
                queue.add(cur);
            }
        }
        return dummy.next;
    }


    /**
     * 思路二：归并N个排序数组一样的思路，分治法
     */
    public static ListNode mergeKLists1(ListNode[] lists) {
        return process(lists,0,lists.length - 1);
    }

    /**
     * 归并两链表数组
     */
    public static ListNode process(ListNode[] lists, int l, int r) {
        //1.当只剩一个数组时候，直接返回
        if (r == l) return lists[l];
        int mid = (l + r) / 2;
        ListNode l1 = process(lists, l, mid);
        ListNode l2 = process(lists, mid + 1, r);
        //合并两个排序的链表
        return mergeTwoList(l1, l2);
    }


    public static ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoList(l1, l2.next);
            return l2;
        }
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
            System.out.print(node.val + " : ");
            node = node.next;
        }
    }


    public static void main(String[] args) {
        ListNode list1 = create(new int[]{1, 4, 7, 10, 13});
        ListNode list2 = create(new int[]{2, 5, 8, 11, 14});
        ListNode list3 = create(new int[]{3, 6, 9, 12, 15});
        ListNode[] item = new ListNode[]{list1, list2, list3};
        ListNode node = mergeKLists1(item);
        printListNode(node);
    }

}
