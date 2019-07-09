package tsp.leetcode.links;

import tsp.offer.ListNode;
import tsp.offer.TreeLinkNode;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 */
public class Solution_83 {


    /**
     * 递归解法
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    /**
     * 迭代写法
     */
    public ListNode deleteDuplicates1(ListNode head) {
        ListNode cur =head;
        while (cur!=null&&cur.next!=null){
            if (cur.next.val==cur.val){
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(1);
        a.next=b;
        ListNode listNode = deleteDuplicates(a);
        while (listNode!=null){
            System.out.printf(String.valueOf(listNode.val));
            listNode = listNode.next;
        }
    }
}
