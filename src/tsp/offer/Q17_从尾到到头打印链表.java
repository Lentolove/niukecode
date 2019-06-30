package tsp.offer;

import java.util.Stack;

public class Q17_从尾到到头打印链表 {

    public int[] printListReversingly(ListNode head) {
        int length = 0;
        if (head==null) return null;
        Stack<Integer> stack = new Stack<>();
        while (head!=null){
            length++;
            stack.push(head.val);
            head = head.next;
        }
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = stack.pop();
        }
        return a;
    }

}
