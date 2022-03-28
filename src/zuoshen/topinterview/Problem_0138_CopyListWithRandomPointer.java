package zuoshen.topinterview;

import java.util.HashMap;

/**
 * Author : tsp
 * Time: 2022/3/24 20:03
 * Desc : 带有 random 指针的单链表的复制
 * <p>
 * 提示：
 * 0 <= n <= 1000
 * -10⁴ <= Node.val <= 10⁴
 * Node.random 为 null 或指向链表中的节点。
 * <p>
 * Related Topics 哈希表 链表 👍 843 👎 0
 */

public class Problem_0138_CopyListWithRandomPointer {

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 思路一：借助HashMap来存储原节点和新节点
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        //key为老节点，value 为新节点
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        Node newHead = map.get(head);
        cur = head;
        Node p;
        while (cur != null) {
            p = map.get(cur);
            p.next = map.get(cur.next);
            p.random = map.get(cur.random);
            cur = cur.next;
        }
        return newHead;
    }

    /**
     * 思路二：将两个链表向连： 1->2->3->null
     * 1.先把链表连一起
     * 1->1'->2->2'->3->3'->null
     * 2.根据cur的random指针可以知道cur.next.rand  = cur.random.next
     * 3.连接完random指针后，在进行断链
     */
    public Node copyRandomList1(Node head) {
        if (head == null) return null;
        //1.链表拷贝
        Node cur = head;
        Node next;
        //1->2->3->null  -> 1->1'->2->2'->3->3'->null
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        //2.连接random指针
        cur = head;
        Node copy;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            if (cur.random != null) {
                copy.random = cur.random.next;
            }
            cur = next;
        }
        //3.断链
        cur = head;
        Node newHead = cur.next;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return newHead;
    }

}
