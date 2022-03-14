package zuoshen.jinjie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈和窗口及其更新结构
 */
public class Section1 {

    public static void main(String[] args) {
        int[] arr = {3, 3, 4, 2, 4, 3};
        int[][] nearLess = getNearLess(arr);
        int max = getSubarrayMaxSum(arr);
        System.out.println(max);
        for (int[] less : nearLess) {
            System.out.println(Arrays.toString(less));
            //[-1, 3]
            //[-1, 3]
            //[1, 3]
            //[-1, -1]
            //[3, 5]
            //[3, -1]
        }
    }

    /**
     * 返回数组中每个元素的左边左边离它最近的最小值及右边最近的最小值
     * 数组中可能出现重复的元素。借助栈，栈中存放的是当前元素的索引的集合。
     * 比如：[3,3,4,2,4,3,5,1,2]
     * 栈中存入{0,1} -> 3
     */
    public static int[][] getNearLess(int[] arr) {
        int n = arr.length;
        //1.二维数组中0位置表示左边索引，1位置表示右边索引
        int[][] ans = new int[n][2];
        //2.栈中的List<Integer> 存放的是位置，同样的值的索引，位置存储在一起，保持栈中索引对应的元素
        //值是由栈底到栈顶是单调递增的。
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            //3.当前加入的元素比栈顶小。需要处理站内元素了
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                //3.1 取出栈顶元素集合,现在要来处理该集合中元素的左右边界最小值问题
                List<Integer> posList = stack.pop();
                //3.2 如果当前 stack 还不为null，往下找一层，因为站内元素索引对应的元素值是单调递增的，
                // 我们只需要找下一层最晚加入的那个元素的索引即可
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                //3.3更新当前集合中左右边界
                for (int index : posList) {
                    ans[index][0] = leftLessIndex;
                    ans[index][1] = i;
                }
            }
            //4.如果当前要加入的元素和栈顶相等。
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                //当前元素比栈顶元素大
                List<Integer> item = new ArrayList<>();
                item.add(i);
                stack.add(item);
            }
        }
        //5，最后在处理stack中剩余的元素，剩余的元素说明右边没有元素比它小使得它弹出
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            //5.1 查找它的下一层是否存在，更新左边界
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (int index : list) {
                ans[index][0] = leftLessIndex;
                ans[index][1] = -1;
            }
        }
        return ans;
    }


    /**
     * 左神：给定一个只包含正整数数组arr，arr中任何一个子数组sub一定都可以算出(sub累加和) * (sub中的最小值)是多少，
     * 那么所有子数组中，这个值的最大值是多少？
     * 思路：
     * 1.以当前元素作为sub数组的最小值 ，往左右找边界，即当前sub中能向两边扩到多大。
     * 2.题目即可转成寻找每个元素的左边离它最近的最小值的索引，及右边离它最近的最小值的索引，然后中间即为累加和。
     * 3.对于累加和可以对数组预处理，sum[i] 表示前 i 个元素的和。
     */
    public static int getSubarrayMaxSum(int[] arr) {
        int n = arr.length;
        int[] sum = new int[n];
        //1.数组前缀和预处理
        for (int i = 0; i < n; i++) {
            sum[i] += arr[i];
            if (i > 0) {
                sum[i] += sum[i - 1];
            }
        }
        //2.同样的步骤，查找左右边界
        Stack<List<Integer>> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            //3.当前加入的元素小
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> list = stack.pop();
                //3.1 计算左边界
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer index : list) {
                    //右边界是 i ，左边界是 leftIndex
                    int subSum = sum[i - 1] - (leftLessIndex == -1 ? 0 : sum[leftLessIndex]);
                    max = Math.max(max, subSum * arr[index]);
                    break;
                }
            }
            //4.如果当前要加入的元素和栈顶相等。
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                //当前元素比栈顶元素大
                List<Integer> item = new ArrayList<>();
                item.add(i);
                stack.add(item);
            }
        }
        //5，最后在处理stack中剩余的元素，剩余的元素说明右边没有元素比它小使得它弹出
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            //5.1 查找它的下一层是否存在，更新左边界,因为此时右边已经没有比它小的值，只用关心左边界
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (int index : list) {
                int subSum = sum[n - 1] - (leftLessIndex == -1 ? 0 : sum[leftLessIndex]);
                max = Math.max(max, subSum * arr[index]);
                break;
            }
        }
        return max;
    }
}
