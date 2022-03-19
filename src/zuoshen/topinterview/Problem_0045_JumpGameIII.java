package zuoshen.topinterview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * author : tsp
 * Date : 2022/3/14
 * DESC: 跳跃问题进阶：：题意如下：给定一个数组 arr = [2,3,1,1,4]，机器人从第 start 个位置开始出发，最终来打 end 位置，
 * 机器人只能往左走和往右跳，跳的距离只能是 arr[i] 的距离，请返回从 start 位置到 end 位置最少的跳跃步骤，注意：start，end 索引隔 1
 * 如果不能跳到 end 位置，则返回 -1，否则返回最少跳跃步骤
 */
public class Problem_0045_JumpGameIII {


    /**
     * 跳跃问题
     *
     * @param arr   当前数组 arr[i] 表示它可以跳跃的距离
     * @param start 起始位置，从 1 ~ n
     * @param end   终止位置，从 1 ~ n
     */
    public static int minJump(int[] arr, int start, int end) {
        return 0;
    }

    /**
     * 最优思路：宽度优先遍历
     * 把当前来到的位置看做 node,当前的层数为 level ,往左跳:left,往右跳：right.
     * 1)注意：防止出现循环跳跃的问题，既 跳到2 -》3-》2这种会导致死循环，需要记录当前来过的位置，
     * 2)宽度优先遍历通常借助队列来实现，用一个 Map 来记录当前 node 位置的值，及其来到的层数，因为我们是把跳跃过程当做树的形式，
     * 只会往下一层跳跃，树的深度 + 1
     * [    2    2    2    ?     ]
     * [    1    3    5    7     ] 由三位置到达7位置，如果往左跳，在往右跳，则会出现死循环，一定要注意，因为我们的逻辑是往两边走，
     * 都考虑
     */
    public static int jump1(int[] arr, int start, int end) {
        if (start == end) return 0;
        int n = arr.length;
        //queue存储的是当前位置索引
        Queue<Integer> queue = new LinkedList<>();
        //key 表示当前数，value 表示它的层数
        HashMap<Integer, Integer> map = new HashMap<>();
        //初始位置为 start
        queue.add(start);
        map.put(start, 0);
        while (!queue.isEmpty()) {
            //当前来到的位置
            int curIndex = queue.poll();
            //当前层数，也就是跳跃的步骤数
            int level = map.get(curIndex);
            if (curIndex == end) {
                return level;
            }
            //向左跳，如果不越界的话
            int leftIndex = curIndex - arr[curIndex];
            int rightIndex = curIndex + arr[curIndex];
            if (leftIndex >= 0 && !map.containsKey(leftIndex)) {//左边没有越界,并且当前位置没来过
                map.put(leftIndex, level + 1);
                queue.add(leftIndex);
            }
            if (rightIndex < n && !map.containsKey(rightIndex)) {
                map.put(rightIndex, level + 1);
                queue.add(rightIndex);
            }
        }
        return -1;
    }


    /**
     * 宽度优先遍历，考虑用递归的思想，暴力尝试然后该动态规划。
     * 思想历程：
     * 1)暴力递归的参数：固定参数 arr，目标位置 end，可变参数：当前来到的位置 i
     * 2)但是请注意：树的遍历过程中，我们如何避免进入死循环，两个位置来回跳的问题，此时考虑用一个数组 walk 来记录当前位置是否已经来过了
     */
    public static int jump2Process(int[] arr, int end, int i, boolean[] walk) {
        //1.base case,表示无法到达
        if (i < 0 || i >= arr.length || walk[i]) {
            return -1;
        }
        if (i == end) {
            return 0;
        }
        //来到正常位置 i,把当前位置标为 true
        walk[i] = true;
        //继续往左往右
        int left = i - arr[i];
        int right = i + arr[i];
        //拿到往左走和往右走的结果
        int ans1 = jump2Process(arr, end, left, walk);
        int ans2 = jump2Process(arr, end, right, walk);
        //选择一个较好的选择
        int bestChose = -1;
        if (ans1 != -1 && ans2 != -1){
            bestChose = Math.min(ans1,ans2);
        }else if (ans1 != -1){
            bestChose = ans1;
        }else if (ans2 != -1){
            bestChose = ans2;
        }
        //如果经过上述所有过程，将诶过还是 -1，
        //回溯过程恢复现场
        walk[i] = false;
        if (bestChose == -1){
            return -1;
        }
        return bestChose + 1;
    }

    /**
     * TODO 出问题了 暂时没有看出来哪里有问题
     */
    public static int jump3Process(int[] arr, int end, int i, boolean[] walk) {
        //1.base case,表示无法到达
        if (i < 1 || i > arr.length || walk[i - 1]) {
            return -1;
        }
        if (i == end) {
            return 0;
        }
        //来到正常位置 i,把当前位置标为 true
        walk[i - 1] = true;
        //继续往左往右
        int left = i - arr[i - 1];
        int right = i + arr[i - 1];
        //拿到往左走和往右走的结果
        int ans1 = jump3Process(arr, end, left, walk);
        int ans2 = jump3Process(arr, end, right, walk);
        //选择一个较好的选择
        int bestChose = -1;
        if (ans1 != -1 && ans2 != -1){
            bestChose = Math.min(ans1,ans2);
        }else if (ans1 != -1){
            bestChose = ans1;
        }else if (ans2 != -1){
            bestChose = ans2;
        }
        //如果经过上述所有过程，将诶过还是 -1，
        //回溯过程恢复现场
        walk[i] = false;
        if (bestChose == -1){
            return -1;
        }
        return bestChose + 1;
    }

    public static void main(String[] args) {
//        int[] arr = {7, 6, 7,2, 3, 8, 2, 2, 7,7,4,3,6,5,1,0,2,0};
        int[] arr = {4,2,3,0,3,1,2};
        System.out.println(jump1(arr, 0, 3));
        System.out.println(jump3Process(arr, 1, 4,new boolean[arr.length]));
    }


}
