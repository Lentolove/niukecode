package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/29 18:34
 * Desc :给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 *
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 *
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, 
 * skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 *  提示：
 *  listA 中节点数目为 m 
 *  listB 中节点数目为 n 
 *  1 <= m, n <= 3 * 10⁴ 
 *  1 <= Node.val <= 10⁵ 
 *  0 <= skipA <= m 
 *  0 <= skipB <= n 
 *  如果 listA 和 listB 没有交点，intersectVal 为 0 
 *  如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB] 
 *
 *  进阶：你能否设计一个时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方案？ 
 *  Related Topics 哈希表 链表 双指针 👍 1634 👎 0
 *
 * leetcode submit region begin(Prohibit modification and deletion)
 */
public class Problem_0160_IntersectionOfTwoLinkedLists {

    public static class ListNode {
        int val;
        ListNode next;
    }

    /**
     * 输出两个链表相交的节点，题目保证了两个量表都没有环
     * 思路：两个指着一起走
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p1 = headA,p2 = headB;
        while (p1 != p2){
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }
}
