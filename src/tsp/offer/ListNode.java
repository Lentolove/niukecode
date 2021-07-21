package tsp.offer;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x){
        this.val = x;
    }


    public static ListNode creat(){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(1);
        a.next =b;
        return a;
    }
}
