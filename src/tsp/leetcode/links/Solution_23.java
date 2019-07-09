package tsp.leetcode.links;

import tsp.offer.ListNode;
import tsp.offer.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 示例:
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 */
public class Solution_23 {

    /**
     *  方法一：暴力法
     * 遍历所有链表，将所有节点的值放到一个数组中。
     * 将这个数组排序，然后遍历所有元素得到正确顺序的值。
     * 用遍历得到的值，创建一个新的有序链表。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ArrayList<Integer> nodes = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            ListNode head = lists[i];
            while (head!=null){
                nodes.add(head.val);
                head = head.next;
            }
        }

        return null;
    }

    /**
     * 方法二：优先队列
     */
    public ListNode mergeKLists1(ListNode[] lists) {

        if (lists.length <1) return null;
        ListNode dumy = new ListNode(-1);
        ListNode cur = dumy;
        int len = lists.length;
        //构造优先队列 默认是从小到大的排序
//        PriorityQueue<ListNode> pq = new PriorityQueue<>(len,Comparator.comparingInt(a->a.val));
        PriorityQueue<ListNode> pq = new PriorityQueue<>(len, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val; //从小到大  o2.val - o1.val 从大到小
            }
        });
        for (ListNode node:lists) {
            if (node!=null){
                pq.add(node); //空节点没必要添加进去
            }
        }
        while (!pq.isEmpty()){
            //优先队列非空才能出队
            ListNode node = pq.poll();
            // 当前节点的 next 指针指向出队元素
            cur.next = node;
            // 当前指针向前移动一个元素，指向了刚刚出队的那个元素
            cur = cur.next;
            if (cur.next!=null){
                pq.add(cur.next);
            }
        }
        return dumy.next;
    }



    /**
     * 方法三：分治法
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        int len = lists.length;
        if (len==0) return null;
        return mergeKLists(lists,0,len-1);
    }

    private ListNode mergeKLists(ListNode[] lists,int l,int r){
        //思考这里为什么取等于？这是因为根据下文对 mergeKLists 的递归调用情况，区间最窄的时候，只可能是左右端点重合
        if (l==r){
            return lists[l];
        }
        int mid = (r-l)/2+l;
        ListNode l1 = mergeKLists(lists,l,mid);
        ListNode l2 = mergeKLists(lists,mid+1,r);
        return mergeTwoListNode(l1,l2);
    }

    private ListNode mergeTwoListNode(ListNode l1,ListNode l2){
        if (l1==null){
            return l2;
        }
        if (l2==null) return l1;
        if (l1.val<l2.val){
            l1.next = mergeTwoListNode(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoListNode(l1,l2.next);
            return l2;
        }
    }







}
