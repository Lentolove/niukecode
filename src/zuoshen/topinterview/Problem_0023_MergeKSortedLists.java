package zuoshen.topinterview;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author : tsp
 * Time: 2022/3/10 20:01
 * Desc :
 * 给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * <p>
 * Related Topics 链表 分治 堆（优先队列） 归并排序 👍 1796 👎 0
 * <p>
 * leetcode submit region begin(Prohibit modification and deletion)
 */
public class Problem_0023_MergeKSortedLists {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 合并k个有序的链表，可以用小根堆去做，小根堆中存入都是每个链表的头节点
     * 每次弹出最小值，然后将当前节点的下一个值加入到小根堆中。
     * lists = [[1,4,5],[1,3,4],[2,6]]
     * TODO 还有借助合并K个数组的思路，采用分治的思想，链表两两merge
     * 方法一：借助小根堆来完成
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new NodeCompare());
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        //开始合并链表，直到queue为null
        while (!queue.isEmpty()) {
            ListNode item = queue.poll();
            cur.next = item;
            cur = cur.next;
            if (item.next != null) {
                queue.add(item.next);
            }
        }
        return dummy.next;
    }

    private static class NodeCompare implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }


}
