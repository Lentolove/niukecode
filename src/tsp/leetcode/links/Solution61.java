package tsp.leetcode.links;

import tsp.offer.ListNode;

/**
 * leetcode:61 旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 */
public class Solution61 {

    /**
     * 方法一：
     * 求出链表的长度 在进行旋转
     */
    public static ListNode rotateRight(ListNode head, int n) {
        if (head==null||head.next==null) return head;
        int len = getSize(head);
        int mod = n%len;
        if (mod==0) return head;

        int pre_index = len-mod;
        if (pre_index==0) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre_index>0){
            pre_index--;
            pre =pre.next;
        }
        ListNode node = pre.next; //待旋转的链表的头节点
        dummy.next = node;
        pre.next=null;
        ListNode last = null;
        while (node!=null){
            last = node;
            node = node.next;
        }
        last.next = head;
        return dummy.next;
    }

    private static int getSize(ListNode head){
        int size = 0;
        while (head!=null){
            size++;
            head=head.next;
        }
        return size;
    }

    /**
     * 方法二
     * 旋转链表，直接将链表形成环，找到断开点切开即可
     */
    public static ListNode rotateRight1(ListNode head, int n) {
        if (head==null||head.next==null) return head;
        //将链表形成环
        ListNode cur = head;
        int len=1;

        /* 这样写得到的是 null 节点 我们需要得到尾部节点
        while (cur!=null){
            len++;
            cur = cur.next;
        }
        cur.next = head;
        */
        while (cur.next!=null){
            len++;
            cur = cur.next;
        }
        cur.next = head;

        ListNode new_tail = head;// 尾节点
        for (int i = 1; i < len-n%len; i++) {
            new_tail = new_tail.next;
        }
        ListNode newHead = new_tail.next;
        new_tail.next=null;
        return newHead;
    }


    public static void main(String[] args) {
        ListNode a = ListNode.creat();
        ListNode newHead = rotateRight1(a, 2);
        while (newHead!=null){
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
}
