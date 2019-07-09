package tsp.leetcode.links;

import tsp.offer.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表
 *
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 */
public class Solution_141 {

    /**
     * 方法一：迭代
     * 快慢指针，如果有循环，则两个指针一定会相遇
     */
    public static boolean hasCycle(ListNode head) {
        if (head==null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next!=null&&fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast==slow){
                return true;
            }
        }
        return false;
    }

    //  leetcode 官方题解
    public static boolean hasCycle1(ListNode head) {
        if (head==null||head.next==null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow!=fast){
            if (slow==null||fast==null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 方法二：哈希表
     * 我们可以通过检查一个结点此前是否被访问过来判断链表是否为环形链表。常用的方法是使用哈希表。
     * 我们遍历所有结点并在哈希表中存储每个结点的引用（或内存地址）。如果当前结点为空结点 null（即已检测到链表尾部的下一个结点），那么我们已经遍历完整个链表，并且该链表不是环形链表。如果当前结点的引用已经存在于哈希表中，那么返回 true（即该链表为环形链表）。
     */
    public  boolean hasCycle2(ListNode head){
        Set<ListNode> nodeSet = new HashSet<>();
        while (head!=null){
            if (nodeSet.contains(head)){
                return true;
            }else {
                nodeSet.add(head);
            }
            head = head.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode creat = ListNode.creat();
        System.out.println(hasCycle(creat));
    }
}
