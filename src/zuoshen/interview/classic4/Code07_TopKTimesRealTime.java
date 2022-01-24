package zuoshen.interview.classic4;

import java.util.HashMap;

/**
 * 左神：词频结构问题，自己改写堆结构.事先定义好K的大小
 * TopRecord{
 * TopRecord(int k);
 * void add(String str);
 * List<String> top()
 * }
 * 要求：add(String str) 方法复杂度为 O(logk)
 * top() 方法复杂度为 O(K)
 */
public class Code07_TopKTimesRealTime {


    public static class Node {
        public String str;
        public int times;

        public Node(String str, int times) {
            this.str = str;
            this.times = times;
        }
    }


    public static class TopRecord {
        private Node[] heap;
        private int heapSize;
        //记录 str 及 str 的次数
        private HashMap<String, Node> strNodeMap;
        //记录 str 在堆 heap 的位置 -1 表示不在堆中
        private HashMap<Node, Integer> nodeIndexMap;

        public TopRecord(int k) {
            heap = new Node[k];
            heapSize = 0;
            strNodeMap = new HashMap<>();
            nodeIndexMap = new HashMap<>();
        }

        public void add(String str) {
            Node curNode = null;
            int preIndex = -1;//记录 str 之前在堆上的位置，默认不在为 -1
            //1.首先在词频表上查询 str 出现的次数
            if (!strNodeMap.containsKey(str)) {//用户第一次加入
                curNode = new Node(str, 1);
                strNodeMap.put(str, curNode);
                //记录在堆中heap的位置,第一次添加为 - 1
                nodeIndexMap.put(curNode, -1);
            } else {
                //已经在词频表中记录过，获取它在堆中的位置，如果不在就位 - 1；
                curNode = strNodeMap.get(str);
                curNode.times++;
                preIndex = nodeIndexMap.get(curNode);
            }
            //preIndex = - 1 的可能情况，1.第一次加入 2.有可能是之前因为 times 小被从堆中踢出来，
            // 重新赋值为 -1 表示不在堆中
            if (preIndex == -1) {//不在堆中
                if (heapSize == heap.length) {//堆满了，需要判断当前加入的str的词频是否属于topK,只有times大于堆顶最小元素才添加
                    if (heap[0].times < curNode.times) {
                        //需要更新堆顶,就是放在heap[0]位置,把之前的节点删除，即赋值为-1
                        nodeIndexMap.put(heap[0],-1);
                        nodeIndexMap.put(curNode,0);
                        heap[0] = curNode;
                        heapify(0,heapSize++);
                    }
                }else {
                    //堆没有满，第一次加入
                    nodeIndexMap.put(curNode,heapSize);
                    heap[heapSize] = curNode;
                    //加入元素，执行heapInsert进行上升操作
                    heapInsert(heapSize++);
                }
            }else {
                //之前已经在堆中了,times变大了，执行heapify操作判断是否需要下沉
                heapify(preIndex,heapSize);
            }
        }

        /**
         * 打印前k个大小
         */
        public void printTopK(){
            for (int i = 0; i < heap.length; i++){
                if (heap[i] == null) break;
                System.out.print("Str: " + heap[i].str);
                System.out.println(" Times: " + heap[i].times);
            }
        }

        /**
         * 往堆的末尾添加元素，然后向上爬
         */
        private void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) / 2;
                if (heap[parent].times < heap[index].times) {
                    swap(index, parent);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        /**
         * 在堆上下沉的过程，与子节点比较是否需要下降
         */
        private void heapify(int index, int heapSize) {
            int l = index * 2 + 1;//左孩子index
            int r = l + 1;//右孩子
            int smallIndex = 0;//需要交换的孩子节点的index
            while (l < heapSize) {
                if (heap[index].times > heap[l].times) {
                    smallIndex = l;
                }
                if (r < heapSize && heap[smallIndex].times > heap[r].times) {
                    smallIndex = r;
                }
                if (smallIndex != index) {
                    //子节点比父节点小，父节点下沉
                    swap(index, smallIndex);
                } else {
                    break;
                }
                //交换完毕后，继续判断是否需要下沉
                index = smallIndex;
                l = index * 2 + 1;
                r = l + 1;
            }
        }

        /**
         * 交换过程不光 heap 数组要更换，记录节点的Map也要交换
         */
        public void swap(int i, int j) {
            //nodeIndexMap中记录的是节点在heap中的index
            nodeIndexMap.put(heap[i], j);
            nodeIndexMap.put(heap[j], i);
            //交换heap数组
            Node cur = heap[i];
            heap[i] = heap[j];
            heap[j] = cur;
        }

    }


    public static String[] generateRandomArray(int len, int max) {
        String[] res = new String[len];
        for (int i = 0; i != len; i++) {
            res[i] = String.valueOf((int) (Math.random() * (max + 1)));
        }
        return res;
    }

    public static void printArray(String[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TopRecord record = new TopRecord(2);
        record.add("zuo");
        record.printTopK();
        record.add("cheng");
        record.add("cheng");
        record.printTopK();
        record.add("Yun");
        record.add("Yun");
        record.printTopK();

    }

}
