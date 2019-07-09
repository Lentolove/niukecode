package tsp.leetcode.links;

import tsp.offer.ListNode;
import tsp.offer.TreeNode;

import java.util.ArrayList;

/**
 * 题目：leetcode:109 有序链表转换为二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 */
public class Solution_109 {

    /**
     * 方法一： 递归
     * 根据有序数组创建二叉搜索书的思路，将链表分成两部分
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode preMid = preMid(head); // 中间节点的前一个节点
        ListNode mid = preMid.next;
        preMid.next = null; //断开链表
        TreeNode t = new TreeNode(mid.val); //中间节点作为树的根节点
        t.left = sortedListToBST(head); // 链表中间节点的前半部分 中间已经断开
        t.right = sortedListToBST(mid.next);//链表中间节点的后半部分 中间已经断开
        return t;

    }

    //找到链表的中间节点 快慢指针
    private static ListNode preMid(ListNode head) {
        ListNode slow = head, fast = head.next;
        ListNode pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }

    //牛客网的递归解法不通过，只给定了一个固定输出的查找二叉树，实际上可能有多个，所以上述方法没有AC牛客网
    public TreeNode sortedListToBST1(ListNode head) {
        return toBST(head, null);
    }

    //这个tail表示迭代过程中的终结点  左子树的tail=slow（中点）
    private TreeNode toBST(ListNode head, ListNode tail) {
        if (head == tail) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = toBST(head, slow);
        root.right = toBST(slow.next, tail);
        return root;
    }

    /**
     * 方法二: 思路与方法一相同，只是在找链表的中点的时候将链表的值先存在数组里
     * 这样就问题变成了 将有序数组转成二叉搜索书
     */


    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) return null;
        ArrayList<Integer> lists = new ArrayList<>();
        while (head != null) {
            lists.add(head.val);
            head = head.next;
        }
        return convertListToBFT(lists, 0, lists.size() - 1);
    }

    private TreeNode convertListToBFT(ArrayList<Integer> list, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = convertListToBFT(list, left, mid - 1);
        root.right = convertListToBFT(list, mid + 1, right);
        return root;
    }


    /**
     * 方法三: 中序遍历模拟
     * 中序遍历一棵二叉搜索树的结果是得到一个升序序列。
     */


    public TreeNode sortedListToBST3(ListNode head) {
        if (head == null) return null;
        return null;
    }


    public static void main(String[] args) {
        ListNode head = ListNode.creat();
        System.out.println(preMid(head).val);
    }
}
