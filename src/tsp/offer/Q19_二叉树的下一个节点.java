package tsp.offer;

public class Q19_二叉树的下一个节点 {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode==null) return null;
        if (pNode.right!=null){
            pNode = pNode.right;
            while (pNode.left!=null){
                pNode =pNode.left;
            }
            return pNode;
        }
        while (pNode.next!=null){
            if (pNode.next.left == pNode) return pNode.next;
            pNode = pNode.next;
        }
        return null;
    }
}
