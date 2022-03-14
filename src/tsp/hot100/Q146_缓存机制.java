package tsp.hot100;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Q146_缓存机制 {

    /**
     * 题目要求：
     * 1.get操作和put操作的时间复杂度都为O(1)
     * 2.用双向链表来解决：在双向链表中，我们增加`Head` 和 `Tail` 哑节点，这样在添加节点和删除节点的时候就不需要检查相邻的节点是否存在
     */
    public static class LRUCache {

        static class LinkNode {
            int key;
            int value;
            LinkNode pre, next;

            public LinkNode() {
            }

            public LinkNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private int maxCapacity, size;

        private HashMap<Integer, LinkNode> map;

        private LinkNode head, tail;

        public LRUCache(int capacity) {
            this.maxCapacity = capacity;
            size = 0;
            map = new HashMap<>(capacity);
            head = new LinkNode();
            tail = new LinkNode();
            head.next = tail;
            tail.pre = head;
        }

        /**
         * get操作需要将当前节点移动至头部
         */
        public int get(int key) {
            LinkNode cur = map.get(key);
            if (cur == null) return -1;
            addToHead(cur);
            return cur.value;
        }

        public void put(int key, int value) {
            //1.首先看是否存在了
            LinkNode item = map.get(key);
            if (item == null) {
                LinkNode newNode = new LinkNode(key, value);
                map.put(key, newNode);
                addToHead(newNode);
                size++;
                if (size > maxCapacity) {
                    //删除尾节点
                    LinkNode tail = removeTail();
                    map.remove(tail.key);
                    size--;
                }
            } else {
                item.value = value;
                moveToHead(item);
            }
        }

        /**
         * 将当前节点移到head处
         */
        private void moveToHead(LinkNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void addToHead(LinkNode node) {
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }

        /**
         * 移除当前节点
         */
        private void removeNode(LinkNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        //移除尾节点
        private LinkNode removeTail() {
            LinkNode node = tail.pre;
            removeNode(node);
            return node;
        }

    }


    public static void main(String[] args) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(2, 2);
        Integer integer = map.get(1);
        System.out.println(integer);
    }

}


