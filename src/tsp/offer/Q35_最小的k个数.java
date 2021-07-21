package tsp.offer;

/**
 * 输入n个整数，找出其中最小的K个数。
 * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 *
 *
 * 用最大堆保存这k个数，每次只和堆顶比，如果比堆顶小，删除堆顶，新数入堆。
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q35_最小的k个数 {

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input==null||k==0||k>input.length) return list;
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);//最大堆
            }
        });
        for (int i = 0; i < input.length; i++) {
            if (maxheap.size()!=k){
                maxheap.offer(input[i]);
            }else if (maxheap.peek()>input[i]){
                maxheap.poll();
                maxheap.offer(input[i]);
            }
        }
        for (Integer in :maxheap) {
            list.add(in);
        }

        return list;
    }
}
