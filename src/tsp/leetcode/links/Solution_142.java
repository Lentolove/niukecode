package tsp.leetcode.links;

import tsp.offer.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表二
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 *示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii

 */
public class Solution_142 {

    /**
     * 方法一：直接用hash表来判断当前有无重复节点
     */
    public ListNode detectCycle(ListNode head) {
        if (head==null||head.next==null) return null;
        Set<ListNode> set = new HashSet<>();
        while (head!=null){
            if (set.contains(head)){
                return head;
            }else {
                set.add(head);
            }
            head = head.next;
        }
        return null;
    }

    /**
     * 方法二：双指针 当两个节点相遇时候，快指针移动到head 快慢同时跑，一定会在循环节点的初始入口相遇
     */
    public static ListNode detectCycle1(ListNode head) {
        if (head==null||head.next==null) return null;
        ListNode fast = head.next;
        ListNode slow = head;
        while (slow!=fast){
            if (fast==null||fast.next==null) return null;
            slow=slow.next;
            fast =fast.next.next;
        }
        fast = head;
        slow = slow.next;
        while (fast!=slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        ListNode a = ListNode.creat();
        System.out.println(detectCycle1(a).val);
    }
}
