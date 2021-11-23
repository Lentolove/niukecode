package tsp.offer;

import java.util.LinkedList;
import java.util.Queue;

public class Q62_圆圈中最后剩下的数字 {


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lastRemaining(5,3));
    }

    static class Solution {

        //用双端队列
        public int lastRemaining(int n, int m) {
            Queue<Integer> queue = new LinkedList<>();
            int index = 0;
            while(index < n){
                queue.add(index);
                index++;
            }
            while(queue.size() > 1){
                index = 1;
                while(index < m){
                    queue.add(queue.poll());
                    index++;
                }
                queue.poll();
            }
            return queue.poll();
        }
    }
}
