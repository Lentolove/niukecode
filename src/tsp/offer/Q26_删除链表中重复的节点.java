package tsp.offer;

/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，
 * 重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class Q26_删除链表中重复的节点 {

    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        ListNode new_head = new ListNode(-1);
        new_head.next = pHead;
        ListNode pre = new_head, curr = pHead, next = null;
        while (curr.next != null && curr != null) {
            next = curr.next;
            if (curr.val == next.val){
                while (next!=null&&curr.val==new_head.val){
                    next = next.next;
                }
                curr = next;
                pre.next = next;
            }else {
                pre = curr;
                curr= curr.next;
            }
        }
        return new_head.next;
    }
}
