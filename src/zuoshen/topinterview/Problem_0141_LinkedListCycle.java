package zuoshen.topinterview;

import java.util.List;

/**
 * author : tsp
 * Date : 2022/3/27
 * DESC:给你一个链表的头节点 head ，判断链表中是否有环。如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，
 * 则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到
 * 链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目范围是 [0, 10⁴]
 * -10⁵ <= Node.val <= 10⁵
 * pos 为 -1 或者链表中的一个 有效索引 。
 * <p>
 * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
 * Related Topics 哈希表 链表 双指针 👍 1437 👎 0
 * <p>
 * <p>
 * leetcode submit region begin(Prohibit modification and deletion)
 */
public class Problem_0141_LinkedListCycle {


    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 判断一个链表是否有环
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        if (head.next == head) return true;
        //快慢指针
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next == null) return false;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = c;
        System.out.println(hasCycle(a));
    }

}
