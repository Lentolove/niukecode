package tsp.leetcode.links;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制带随机只指针的链表
 * <p>
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的深拷贝。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 */

class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        label = x;
    }

    ;

}

public class Solution_138 {

    /**
     * 方法一：回溯法
     */
    // HashMap which holds old nodes as keys and new nodes as its values.
    HashMap<RandomListNode, RandomListNode> visitHash = new HashMap<>();

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        // If we have already processed the current node, then we simply return the cloned version of it
        if (visitHash.containsKey(head)) {
            return visitHash.get(head);
        }
        //Create a new node with the value same as old node. (i.e. copy the node)
        RandomListNode node = new RandomListNode(head.label);
        /**
         * Save this value in the hash map. This is needed since there might be
         * loops during traversal due to randomness of random pointers and this would help us avoid them;
         *
         */
        visitHash.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }

    /**
     * 方法二：O(N)空间的迭代迭代
     */

    public RandomListNode copyRandomList1(RandomListNode head) {
        if (head == null) return null;
        RandomListNode oldNode = head;
        //创建新链表的头节点
        RandomListNode newNode = new RandomListNode(oldNode.label);
        visitHash.put(oldNode, newNode);
        while (oldNode != null) {
            newNode.random = getCloneNode(oldNode.random);
            newNode.next = getCloneNode(oldNode.next);
        }
        return visitHash.get(head);
    }

    private RandomListNode getCloneNode(RandomListNode node) {
        // 如果节点存在
        if (node != null) {
            //检测当前节点时候包含在字典中
            if (visitHash.containsKey(node)) {
                return visitHash.get(node);
            } else {
                visitHash.put(node, new RandomListNode(node.label));
                return visitHash.get(node);
            }
        }
        return null;
    }

    //写法二：
    public RandomListNode copyRandomList11(RandomListNode head) {
        if (head==null) return null;
        RandomListNode pNode = head,copyNode=null,copHead = null;
        Map<RandomListNode,RandomListNode> map = new HashMap<>();
        while (pNode!=null){
            RandomListNode node = new RandomListNode(pNode.label);
            if (pNode==head){
                copHead = copyNode = node;
            }else {
                copyNode.next = node;
                copyNode = copyNode.next;
            }
            map.put(pNode,copyNode);
            pNode = pNode.next;
        }

        for (Map.Entry<RandomListNode,RandomListNode> m: map.entrySet()) {
            m.getValue().random = map.get(m.getKey().random);
        }
        return copHead;
    }



        /**
         * 方法二：O(1)空间的迭代迭代
         */
    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null) return null;
        RandomListNode ptr = head;

        while (ptr != null) {
            RandomListNode newNode = new RandomListNode(ptr.label);
            /**
             * Inserting the cloned node just next to the original node.
             * If A->B->C is the original linked list,Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
             *
             */
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }
        /**
         * Now link the random pointers of the new nodes created.
         * Iterate the newly created list and use the original nodes' random pointers,
         * to assign references to random pointers for cloned nodes.。
         */
        ptr =head;
        while (ptr!=null){
            ptr.next.random = (ptr.random !=null)? ptr.random:null;
            ptr = ptr.next.next;
        }
        /**
         * Unweave the linked list to get back the original linked list and the cloned list.
         * i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
         */
        RandomListNode ptr_old_list = head; //
        RandomListNode ptr_new_list = head.next;
        RandomListNode new_head = head.next;
        while (ptr_old_list!=null){
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = ptr_new_list.next!=null? ptr_new_list.next:null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return new_head;
    }


}
