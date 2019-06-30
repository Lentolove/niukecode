package tsp.offer;


class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

public class Q30_复杂链表的复制 {

    public RandomListNode Clone(RandomListNode pHead){
        if (pHead==null) return null;
        RandomListNode pNode = pHead;
        while (pNode!=null){
            RandomListNode pClone = new RandomListNode(pNode.label);
            pClone.next=pNode.next;
            pNode.next = pClone;
            pNode=pClone.next;
        }

        pNode = pHead;
        while (pNode!=null){
            pNode.next.random = pNode.random ==null?null:pNode.random.next;
            pNode = pNode.next.next;
        }
        //3、拆分链表，将链表拆分为原链表和复制后的链表
        pNode = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (pNode!=null){
            RandomListNode cloneNode = pNode.next;
            pNode.next = cloneNode.next;
            cloneNode.next = cloneNode.next == null? null:cloneNode.next.next;
            pNode = pNode.next;
        }
        return  pCloneHead;
    }



    //第二步 设置复制节点的random指针
    public void setRandom(RandomListNode pHead){
        RandomListNode pNode = pHead;
        while (pNode!=null){
            RandomListNode pClone = pNode.next;
            if (pNode.random!=null){
                //              A->C->C' 复制出来的节点的random指针指向复制出来的C'
                pClone.random = pNode.random.next;
            }
            pNode = pClone.next;
        }


    }

    //第三步 把长链表拆分成两个链表 把奇数位置的节点用next连接起来就是原始链表
    public RandomListNode getCloneRandom(RandomListNode pHead){
        RandomListNode pNode = pHead;
        RandomListNode pCloneHead = null;
        RandomListNode pCloneNode = null;
        if (pNode!=null){
            pCloneHead = pCloneNode = pNode.next;
            pNode.next = pCloneNode.next;
            pNode = pNode.next;
        }
        while (pNode!=null){
            pCloneNode.next = pNode.next;
            pCloneNode = pCloneHead.next;
            pNode.next = pCloneHead.next;
            pNode = pNode.next;
        }
        return pCloneNode;
    }

}
