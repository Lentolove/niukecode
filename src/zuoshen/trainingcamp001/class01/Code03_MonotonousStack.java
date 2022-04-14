package zuoshen.trainingcamp001.class01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Author : tsp
 * Time: 2022/4/14 18:20
 * Desc :
 */
public class Code03_MonotonousStack {


    /**
     * 单调栈问题：在数组中想找到一个数，左边和右边比这个数小、且离这个数最近的位置
     * 如果对每一个数都想求到这样的信息，能不能整体代价达到O(n)?
     * eg: arr = [3,1,4,5,3,7]
     * [[-1,1],[,-1,-1],[2,4],[4,-1]]
     * <p>
     * 思路：借助栈来实现，假定数组中不包含重复元素，步骤如下：
     * 1.栈内存储的是索引，保持栈内索引对应的值严格单调递增。
     * 2.当遇到一个元素 arr[i] > arr[stack.peek()]， 入栈。
     * 3.当遇到一个元素 arr[i] < arr[stack.peek]，此时栈内元素 j 出栈，左边比 j 小的在它的下面
     * 右边比 j 小的就是让它出栈的元素，这样就能拿到左边离它最近和右边离它最近的位置
     */
    public static int[][] getNearLessNoRepeat(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        int n = arr.length;
        int[][] result = new int[n][2];
        //栈中存放的索引，索引对应值保持单调递增
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                //当前元素使得栈内元素无法保持单调次增，则开始结算栈内元素
                int index = stack.pop();
                int preMin = stack.isEmpty() ? -1 : stack.peek();
                result[index][0] = preMin;
                result[index][1] = i;
            }
            //结算完成后，将当前元素入栈
            stack.add(i);
        }
        //最后再次结算栈内元素，没有元素让其弹出了，它的右边没有比它小的元素，否则它一定在之前的过程中被弹出计算了
        //eg:[1,2,3,4,5]
        while (!stack.isEmpty()) {
            int index = stack.pop();
            int preMin = stack.isEmpty() ? -1 : stack.peek();
            result[index][0] = preMin;
            result[index][1] = -1;
        }
        return result;
    }

    /**
     * 数组中存在重复的元素，单调栈
     * 1.此时栈中存放的不在是单个的元素索引，而是一个 List 集合，保存的是相同元素的索引值。
     * 2.当遇到较小值使得栈内元素弹出时，弹出的是一个 List 集合，它的前小为栈中前一个位置的 list 中的最后一个值
     */
    public static int[][] getNearLess(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        int n = arr.length;
        int[][] ans = new int[n][2];
        //保存的是相同值元素的索引
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> indexList = stack.pop();
                int preMinIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer index : indexList) {
                    ans[index][0] = preMinIndex;
                    ans[index][1] = i;
                }
            }
            //此时结算完成，加入当前元素，如果值相等，则直接加入栈顶list中，否则新建
            if (stack.isEmpty()) {
                List<Integer> item = new ArrayList<>();
                item.add(i);
                stack.add(item);
            } else {
                List<Integer> top = stack.peek();
                //判断是否可以合并到list中
                if (arr[top.get(0)] == arr[i]) {
                    top.add(i);
                } else {
                    List<Integer> item = new ArrayList<>();
                    item.add(i);
                    stack.add(item);
                }
            }
        }
        //最后结算 stack 中的元素
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            int preMinIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer index : list) {
                ans[index][0] = preMinIndex;
                ans[index][1] = -1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
//        int[] arr = {3, 1, 4, 5, 2, 7};
//        int[][] result = getNearLessNoRepeat(arr);
//        for (int[] ints : result) {
//            System.out.println(Arrays.toString(ints));
//        }

        int[] arr2 = {3,2,3,4,2,4,5};
        int[][] result2 = getNearLess(arr2);
        for (int[] ans : result2) {
            System.out.println(Arrays.toString(ans));
        }

    }

}
