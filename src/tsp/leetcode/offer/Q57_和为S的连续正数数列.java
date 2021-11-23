package tsp.leetcode.offer;

import java.util.ArrayList;
import java.util.List;

public class Q57_和为S的连续正数数列 {


    public static void main(String[] args) {
        
    }


   static class Solution {

        //滑动窗口，双指针
        public int[][] findContinuousSequence(int target) {
            List<List<Integer>> list = new ArrayList<>();
            int sum = 0;
            int left = 1,right = 1;
            //只需要找到数组的一半就行，毕竟窗口中至少要有两个数
            while(left <= target / 2){
                if(sum > target){
                    sum -= left;
                    left++;
                }else if(sum < target){
                    sum += right;
                    right++;
                }else{
                    List<Integer> item = new ArrayList<>();
                    for(int i = left; i < right; i++){
                        item.add(i);
                    }
                    list.add(item);
                    sum -= left;
                    left++;
                }
            }
            int[][] ans = new int[list.size()][1];
            for(int i = 0; i < list.size();i++){
                List<Integer> item = list.get(i);
                int[] arr = new int[item.size()];
                for(int j = 0;j < arr.length; j++){
                    arr[i] = item.get(j);
                }
                ans[i] = arr;
            }
            return ans;
        }
    }
}
