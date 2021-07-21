package tsp.offer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
 * 那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
 * 那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，
 * 使用GetMedian()方法获取当前读取数据的中位数。
 *
 *
 * 我们不需要把真个数组进行排序 只要能保证前半部分的数据小于后半部分的数据  就可以找出中间值了
 */
public class Q36_数据流中的中位数 {
    static int count = 0;
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();//默认创建的是最小堆
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(11, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            //创建最大堆  创建一个堆的默认初始大小是11
            return o2.compareTo(o1);
        }
    });


    public void Insert(Integer num) {
        count++;
        /**
         * 当count为奇数时 插入到最大堆
         */
        if ((count&1)==0){
            if (!maxHeap.isEmpty()&&num<maxHeap.peek()){ //大堆不为空 并且插入的数据比大堆小
                maxHeap.offer(num);//将数据插入到大堆中 并且拿出大堆中最大数字
                num = maxHeap.poll();
            }
            minHeap.offer(num);
        }else {
            if (!minHeap.isEmpty()&&num>minHeap.peek()){ //小堆的最小元素比插入的元素小
                minHeap.offer(num);
                num = minHeap.poll();
            }
            maxHeap.offer(num);
        }
    }

    public Double GetMedian() {
        if (count==0){
            throw new RuntimeException("GetMedian failed!");
        }
        double result;
        if ((count&1)==1){ //奇数 则中间数字位于大堆的堆顶 maxHeap.size()-minHeap.size()=1
            result = maxHeap.peek();
        }else {
            result = (minHeap.peek()+maxHeap.peek())/2.0;
        }
        return result;
    }
}
