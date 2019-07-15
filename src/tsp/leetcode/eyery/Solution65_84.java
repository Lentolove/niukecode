package tsp.leetcode.eyery;

import java.util.Stack;

/**
 * leetcode:84 矩状图中的最大矩形 largest-rectangle-in-histogram (Hard)
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 */
public class Solution65_84 {




    /**
     * 分治法：牛客网上没AC过（检查是否存在数组越界）
     * 通过观察，可以发现，最大面积矩形存在于以下几种情况：
     * 1.确定了最矮柱子以后，矩形的宽尽可能往两边延伸。
     * 2.在最矮柱子左边的最大面积矩形（子问题）。
     * 3.在最矮柱子右边的最大面积矩形（子问题）。
     */
    public int largestRectangleArea(int[] height) {
        return childAre(height,0,height.length-1);
    }

    public int childAre(int[] height,int start,int end){
        if (start>end) return 0;
        int minIndex = start; //最小高度的下标
        for (int i = start; i <=end ; i++) {
            if (height[minIndex]>height[i]) minIndex = i; //获取高度最小值得下标
        }
        return Math.max(height[minIndex]*(end-start+1),
                Math.max(childAre(height,start,minIndex-1),childAre(height,minIndex+1,end)));
    }

    /**
     * 借助栈
     */

    public int largestRectangleArea1(int[] height){
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxAre = 0;
        for (int i = 0; i < height.length; i++) {
            while (stack.peek()!=-1&&height[stack.peek()]>=height[i]){
                maxAre = Math.max(maxAre,height[stack.pop()]*(i-stack.peek()-1));
            }
            stack.push(i);
        }
        while (stack.peek()!=-1){
         maxAre = Math.max(maxAre,height[stack.pop()]*(height.length-1-stack.peek()));
        }
        return maxAre;
    }

    /**
     * 优化的暴力解法
     */
    public int largestRectangleArea2(int[] height) {
        int maxare= 0;
        for (int i = 0; i < height.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < height.length; j++) {
                minHeight = Math.min(minHeight,height[j]);
                maxare = Math.max(maxare,minHeight*(j-i+1));
            }
        }
        return maxare;
    }

        public static void main(String[] args) {
        int[] a ={2,1,5,6,2,3};
        Solution65_84 s = new Solution65_84();
        System.out.println(s.largestRectangleArea(a));
    }
}
