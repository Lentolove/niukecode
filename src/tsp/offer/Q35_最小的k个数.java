package tsp.offer;

/**
 * 输入n个整数，找出其中最小的K个数。
 * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 *
 *
 * 用最大堆保存这k个数，每次只和堆顶比，如果比堆顶小，删除堆顶，新数入堆。
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q35_最小的k个数 {

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input==null||k==0||k>input.length) return list;
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(k, (o1, o2) -> {
            return o2.compareTo(o1);//最大堆
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
class Solution {

    //快排
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k >= arr.length) return arr;
        return quickSort(arr,0,arr.length - 1,k);
    }

    //快排，找哨兵位置
    public int[] quickSort(int[] arr,int l,int r,int k){
        //以 l 为哨兵,进行排序，从右往左找到第一个比k晓得元素
        int i = l, j = r;
        while(i < j){
            //找需要排到左边的第一个数字，第一个比l小的
            while(i < j && arr[j] >= arr[l]) j--;
            //从左往右找第一个比 r 大的
            while(i < j && arr[i] <= arr[l]) i++;
            swap(arr,i,j);
        }
        swap(arr,i,l);
        //判断当前 i 的索引位置与 k 的关系
        if(i < k){
            //说明第 k + 1 小的元素在 i 在右子数组中
            return quickSort(arr,i + 1,r,k);
        }else if( i > k){
            //说明第 k + 1小的元素在 i的左边
            return quickSort(arr,l,i - 1,k);
        }else{
            //刚好
            return Arrays.copyOf(arr, k);
        }
    }

    public void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}