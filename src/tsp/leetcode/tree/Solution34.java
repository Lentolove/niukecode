package tsp.leetcode.tree;


import tsp.offer.TreeLinkNode;

import java.util.LinkedList;

/**
 * 题目：填充每一个节点的下一个指针
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。初始状态下，所有 next 指针都被设置为 NULL。
 */


public class Solution34 {

    // 借助队列实现 层次遍历
    public void connect(TreeLinkNode root) {
        if (root==null) return;
        //借助队列实现层次遍历
        LinkedList<TreeLinkNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int sie = queue.size();
            while ((sie--)>0){
                TreeLinkNode node = queue.remove();
                if (sie>0){
                    node.next = queue.peek();//Retrieves, but does not remove, the head (first element) of this list.
                }
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
            }
        }
        return;
    }

    public void connect1(TreeLinkNode root) {
        while (root!=null){
            //tmpLevelFirst为新建立的每层第一个节点（为了方便后面遍历当前行节点）
            TreeLinkNode dumy = new TreeLinkNode(-1);
            TreeLinkNode cur = dumy;
            while (root!=null){
                if (root.left!=null){
                    cur.next = root.left;
                    cur = cur.next;
                }
                if (root.right!=null){
                    cur.next = root.right;
                    cur=cur.next;
                }
                root=root.next;
            }
            root = dumy.next;
        }
    }




}
