package zuoshen.interview.classic4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 左神：给定一个字符串数组，请返回频率最高的前 k 个字符串。
 * topK 问题，通常要想到堆结构
 */
public class Code06_TopKTimesString {

    public static class Node {
        public String str;
        public int times;

        public Node(String str, int times) {
            this.str = str;
            this.times = times;
        }
    }

    public static class NodeCompare implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            return o1.times - o2.times;
        }
    }

    /**
     * 打印 topK 词频的字符串
     */
    public static void printTopKString(String[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0 ) return;
        //1.借助HashMap来记录词频
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : arr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        //2.用堆来存储前k个词频的元素,所以用小顶堆，每次存放的都是前k大的
        k = Math.min(arr.length, k);
        PriorityQueue<Node> queue = new PriorityQueue<>(new NodeCompare());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(),entry.getValue());
            if (queue.size() < k){
                queue.add(node);
            }else {
                if (queue.peek().times < node.times){
                    queue.poll();
                    queue.add(node);
                }
            }
        }
        while (!queue.isEmpty()){
            System.out.println(queue.poll().str);
        }
    }


    public static String[] generateRandomArray(int len, int max) {
        String[] res = new String[len];
        for (int i = 0; i != len; i++) {
            res[i] = String.valueOf((int) (Math.random() * (max + 1)));
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs = {"abc","cba","iii","abc","cba","opop","iidod","abc"};
        printTopKString(strs,2);
    }

}
