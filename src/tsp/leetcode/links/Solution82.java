package tsp.leetcode.links;

import tsp.offer.ListNode;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 */
public class Solution82 {

    /**
     * 迭代
     * 开心：哈哈 我自己写的 居然没出错
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head==null)return null;
        ListNode dumy = new ListNode(-1);
        ListNode pre = dumy;
        ListNode cur = head;
        pre.next = head;
        while (cur!=null&&cur.next!=null){
            if (cur.val==cur.next.val){
                while (cur!=null&&cur.next!=null&&cur.val==cur.next.val){
                    cur =cur.next;
                }
            }else {
                pre = cur;
            }
            cur = cur.next;
            pre.next = cur;
        }
        return dumy.next;
    }



    /**
     * 递归
     */
    public ListNode deleteDuplicates1(ListNode head){
        if(head==null) return head;
        if (head.next!=null&&head.val==head.next.val){
            while (head.next!=null&&head.val==head.next.val){
                head = head.next;
            }
            return deleteDuplicates1(head.next);
        }else {
            head.next = deleteDuplicates1(head.next);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(1);
        ListNode c = new ListNode(2);
        ListNode d = new ListNode(2);
        ListNode e = new ListNode(4);
        a.next =b;
//        b.next =c;
//        c.next =d;
//        d.next =e;
        ListNode newhead = deleteDuplicates(a);
        while (newhead!=null){
            System.out.println(newhead.val);
            newhead = newhead.next;
        }

    }
}
