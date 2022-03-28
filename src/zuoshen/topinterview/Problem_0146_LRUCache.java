package zuoshen.topinterview;

import java.util.HashMap;

/**
 * Author : tsp
 * Time: 2022/3/24 18:39
 * Desc :
 */
public class Problem_0146_LRUCache {

    public static class LRUCache {

        private MyCache<Integer, Integer> cache;


        public LRUCache(int capacity) {
            cache = new MyCache<>(capacity);
        }

        /**
         * 对外只暴露 get 方法获取某个值，不存在则返回 null
         * 并且复杂度为O(1)
         */
        public int get(int key) {
            Integer ans = cache.get(key);
            return ans == null ? -1 : ans;
        }

        /**
         * 存入值
         */
        public void put(int key, int value) {
            cache.set(key, value);
        }
    }

    /**
     * 自定义Node结构，用来存放key和value
     * map中key存放的值key，值存放的是 key+value构成的Node节点
     * 1.可以通过key直接拿到当前Node节点，
     * 2.因为是双向链表，可以向Node节点的左右移动指针操作双向链表
     */
    private static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> pre, next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class NodeDoubleLinkedList<K, V> {
        private Node<K, V> head, tail;

        public NodeDoubleLinkedList() {
            head = null;
            tail = null;
        }

        /**
         * 向双向链表中添加节点
         */
        public void addNode(Node<K, V> cur) {
            if (cur == null) return;
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                //向双向链表的尾部添加节点
                tail.next = cur;
                cur.pre = tail;
                tail = cur;
            }
        }

        /**
         * 将节点移动到末尾，表示最新访问
         */
        public void moveNodeToTail(Node<K, V> cur) {
            if (cur == tail) return;
            //如果tail不是尾巴，则接在尾巴上
            if (head == cur) {
                //当前要移的节点是head,head往next移动，将head指针移动到末尾
                head = cur.next;
                head.pre = null;
            } else {
                //处于中间
                cur.pre.next = cur.next;
                cur.next.pre = cur.next;
            }
            //将cur指针移动到末尾
            cur.pre = tail;
            cur.next = null;
            tail.next = cur;
            tail = cur;
        }

        public Node<K, V> removeHead() {
            if (head == null) return null;
            Node<K, V> res = head;
            if (head == tail) {
                //只有一个节点
                head = null;
                tail = null;
            } else {
                head = res.next;
                res.next = null;
                head.pre = null;
            }
            return res;
        }
    }

    private static class MyCache<K, V> {
        private HashMap<K, Node<K, V>> keyNodeMap;
        private NodeDoubleLinkedList<K, V> nodelist;
        private final int capacity;

        public MyCache(int cap) {
            if (cap < 1) {
                throw new RuntimeException("you most create a cache with cap more than 0");
            }
            this.capacity = cap;
            keyNodeMap = new HashMap<>();
            nodelist = new NodeDoubleLinkedList<>();
        }

        public V get(K key) {
            if (keyNodeMap.containsKey(key)) {
                Node<K, V> res = keyNodeMap.get(key);
                //移动到链表tail后，表示最新访问的
                nodelist.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        public void set(K key, V value) {
            if (keyNodeMap.containsKey(key)) {
                Node<K, V> node = keyNodeMap.get(key);
                node.value = value;
                nodelist.moveNodeToTail(node);
            } else {
                //新添加的点，先判断LRU是否已满，满了就删除head
                if (keyNodeMap.size() == capacity) {
                    removeMostUnUsedCache();
                } else {
                    //创建新的节点到末尾
                    Node<K, V> newNode = new Node<>(key, value);
                    keyNodeMap.put(key, newNode);
                    nodelist.addNode(newNode);
                }
            }
        }

        /**
         * 移除最近最少使用的点
         */
        private void removeMostUnUsedCache() {
            Node<K, V> node = nodelist.removeHead();
            keyNodeMap.remove(node.key);
        }
    }


}
