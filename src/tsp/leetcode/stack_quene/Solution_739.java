package tsp.leetcode.stack_quene;

import java.util.Arrays;
import java.util.Stack;

/**
 * leetcode:739 每日温度
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 */
public class Solution_739 {


    /**
     * 以某个元素为出发点，第一想法是将后续所有元素入栈，再遍历栈顶与当前元素比较。
     * 遍历过程中我们要找的是首个更大值的下标，某个大值后的较小值对我们毫无意义，剔除降序片段，
     * 所以我们的目标是 维护一个从栈顶至栈底递增的栈 。
     * 根据栈后进先出的特性，要从后往前遍历数组。目的是获取下标差值，所以栈保存下标而不是值。
     * 如果当前元素大于等于栈顶元素，则重复pop，直到栈顶元素大于当前元素，二者下标差值即为所求。
     * 如果栈为空，说明栈中没有大于当前元素的值，保存0。
     * 将当前元素（新的最小值）入栈。
     */
    public static int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] dist = new int[n];
        Stack<Integer> indexs = new Stack<>();
        for (int curIndex = 0; curIndex < n; curIndex++) {
            while (!indexs.isEmpty() && T[curIndex] > T[indexs.peek()]) {
                int preIndex = indexs.pop();
                dist[preIndex] = curIndex - preIndex;
            }
            indexs.add(curIndex);
        }
        return dist;
    }


    public static void main(String[] args) {
        int[] t = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] ints = dailyTemperatures(t);
        System.out.println(Arrays.toString(ints));
    }
}
