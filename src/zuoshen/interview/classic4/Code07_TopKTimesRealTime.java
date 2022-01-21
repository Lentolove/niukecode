package zuoshen.interview.classic4;

import java.util.HashMap;

/**
 * 左神：词频结构问题，自己改写堆结构.事先定义好K的大小
 * TopRecord{
 *     TopRecord(int k);
 *     void add(String str);
 *     List<String> top()
 * }
 * 要求：add(String str) 方法复杂度为 O(logk)
 *      top() 方法复杂度为 O(K)
 */
public class Code07_TopKTimesRealTime {


    public static class Node{
        public String str;
        public int times;

        public Node(String str, int times) {
            this.str = str;
            this.times = times;
        }
    }


    public static class TopRecord{
        private Node[] heap;
        private int heapSize;
        //记录 str 及 str 的次数
        private HashMap<String,Node> strNodeMap;
        //记录 str 在堆 heap 的位置 -1 表示不在堆中
        private HashMap<String,Integer> nodeIndexMap;

        public TopRecord(int k){
            heap = new Node[k];
            heapSize = 0;
            strNodeMap = new HashMap<>();
            nodeIndexMap = new HashMap<>();
        }

        public void add(String str){
            Node curNode = null;
            int preIndex = -1;//记录 str 之前在堆上的位置，默认不在为 -1
            //1.首先在词频表上查询 str 出现的次数
            if (!strNodeMap.containsKey(str)){//用户第一次加入
                curNode = new Node(str,1);
                strNodeMap.put(str,curNode);
                //记录在堆中heap的位置,第一次添加为 - 1
                nodeIndexMap.put(str, - 1);
            }else {
                //已经在词频表中记录过，获取它在堆中的位置，如果不在就位 - 1；
                curNode = strNodeMap.get(str);
                curNode.times++;
                preIndex = nodeIndexMap.get(str);
            }
            //preIndex = - 1 的可能情况，1.第一次加入2.有可能是之前因为 time 小被从堆中踢出来，重新赋值为 -1 表示不在堆中
            if (preIndex == -1){//不在堆中
                if (heapSize == heap.length){//堆满了，需要判断当前加入的str的词频是否输入topK,只有times大于堆顶最小元素才添加
                    if (heap[0].times < curNode.times){
                        //需要更新堆顶
                    }
                }
            }
        }

        /**
         * 往堆的末尾添加元素，然后向上爬
         */
        private void heapInsert(int index,int heapSize){
            while (index != 0){
                int parent = (index - 1) / 2;
                if (heap[parent].times < heap[index].times){

                }
            }
        }

        public static void swap(int i,int j){

        }


    }



}
