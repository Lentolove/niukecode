package tsp.offer;

public class Q25_删除链表的节点 {

    public boolean delectNode(ListNode head,ListNode delect){
        if (head==null||delect==null) return false;
        //删除的节点不是尾节点
        if (delect.next!=null){
            ListNode next = delect.next;
            delect.val = next.val;
            delect.next = next.next;
            next = null;
            return true;
        }else if (head==delect){        //删除的链表只有一个节点
            head=null;
            delect=null;
            return true;
        }else {
            ListNode curr = head;
            //删除尾节点
            while (curr.next!=delect){
                curr = curr.next;
            }
            curr.next = null;
            return true;
        }
    }

}
