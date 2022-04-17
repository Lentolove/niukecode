package zuoshen.trainingcamp001.class01;

import java.util.LinkedList;

/**
 * Author : tsp
 * Time: 2022/3/22 19:02
 * Desc :给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
 *  返回 滑动窗口中的最大值 。
 *
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 *  提示： 
 *
 *  1 <= nums.length <= 10⁵ 
 *  -10⁴ <= nums[i] <= 10⁴ 
 *  1 <= k <= nums.length 
 *  
 *  Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1536 👎 0
 */
public class Code01_SlidingWindowMaxArray {

    /**
     * 题目：
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 思路：
     * 1.用一个双端队列来维持窗口中的最大值，队列中存储的是数组的下标 index
     * 2.双端队列从左往右，保持单调递减，从尾部添加元素，从头部移除元素
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        //1.保存的数组的下标，保证元素单调递减的
        LinkedList<Integer> queue = new LinkedList<>();
        //2.保存窗口移动过程中的最大值
        int[] result = new int[n - k + 1];
        int intdex = 0;
        for (int r = 0; r < n; r++) { //让当前r位置的元素进窗口
            //3.从尾部添加，需要保证queue单调递减，如果当前要添加的元素比队尾的大，则弹出队尾元素
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[r]){
                //这里==也弹出，因为此时r元素出窗口的时间移动比之前的晚
                queue.pollLast();
            }
            //4.此时将当前元素添加到queue中，维持单调递减
            queue.addLast(r);
            //5.如果当前窗口没有形成w长度之前，不弹出数字，对头永远是当前窗口的最大值
            if (queue.peekFirst() == r - k){
                //当前窗口已经为 k + 1 了，需要将对头元素弹出去
                queue.pollFirst();
            }
            //6.以上窗口更新完了，在 0~k-1之前窗口还没构建完成，不更新数据，只打r到达了k-1位置以后
            if (r >= k - 1){
                result[intdex++] = nums[queue.peekFirst()];
            }
        }
        return result;
    }
}
