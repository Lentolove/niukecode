package tsp.leetcode.links;

import tsp.offer.ListNode;

/**
 * 合并两个链表
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 */
public class Solution_21 {

    /**
     * 方法一：递归
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 方法二：迭代
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        //涉及链表操作的多数创建哑结点
        ListNode prehead = new ListNode(-1);
        ListNode pre = prehead;
        while (l1!=null&&l2!=null){
            if (l1.val<=l2.val){
                pre.next = l1;
                l1=l1.next;
            }else {
                pre.next = l2;
                l2=l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1==null?l2:l1;
        return prehead.next;
    }
}