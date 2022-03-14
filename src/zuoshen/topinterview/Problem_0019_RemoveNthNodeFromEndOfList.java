package zuoshen.topinterview;

import java.util.List;

/**
 * Author : tsp
 * Time: 2022/3/10 18:42
 * Desc :
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * <p>
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * Related Topics 链表 双指针 👍 1855 👎 0
 * <p>
 * leetcode submit region begin(Prohibit modification and deletion)
 */
public class Problem_0019_RemoveNthNodeFromEndOfList {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 移除链表的倒数第 n 个节点，n在链表的范围内，并且有可能是头节点
     * 用先后指针来走，并创建亚节点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode first = dummy, second = dummy;
        while (n > 0 && first != null) {
            n--;
            first = first.next;
        }
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        //断链
        second.next = second.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        ListNode node = removeNthFromEnd(a, 4);
        printListNode(node);
    }

    private static void printListNode(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
