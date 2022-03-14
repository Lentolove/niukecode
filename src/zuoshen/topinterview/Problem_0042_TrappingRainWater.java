package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/12 15:52
 * Desc :给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * 提示：
 * n == height.length
 * 1 <= n <= 2 * 10⁴
 * 0 <= height[i] <= 10⁵
 * <p>
 * Related Topics 栈 数组 双指针 动态规划 单调栈 👍 3203 👎 0
 */
public class Problem_0042_TrappingRainWater {

    /**
     * 思路：某个位置的雨水量，边界不用考虑，计算[1,n-2]每个位置的蓄水量。
     * 当前 i 位置蓄水量：由 leftMax 和 rightMax 来决定。
     * 思路1: 数组预处理技巧，提前准备好 left 和 right 数组记录最大值
     * height = [1,2,0,3,2,5], left = [1,2,2,3,3,3] right = [5,5,5,5,5,5,5]
     * 思路2：用有限变量记录 leftMax 和 rightMax，采用双指针的思想：
     * 1) leftMax = height[0] = 1, rightMax = height[n - 1] = 5
     * 2) l = 1, r = n - 2 开始：height[l] 的左侧最大值已经知道了,右侧最大值只知道是大于等于5的。
     * 3) if(leftMax <= rightMax),则边界依赖leftMax来计算蓄水量
     * 否则右边最大值是确定，左边的最大值一定是大于等于leftMax的。
     * 4) water += Math.max( max(leftMax,rightMax) - height[i],0)
     */
    public static int trap(int[] height) {
        //1.边界过滤
        if (height == null || height.length < 3) return 0;
        int n = height.length;
        int leftMax = height[0], rightMax = height[n - 1];
        int l = 1, r = n - 2;
        int water = 0;
        while (l <= r){// =号的情况: [3,1,4],初始l = 1,r = 1。走到一个位置
            if (leftMax <= rightMax){// leftMax = rightMax 两边都可以更新的
                water += Math.max(leftMax - height[l],0);
                leftMax = Math.max(leftMax,height[l++]);
            }else {
                water += Math.max(rightMax - height[r],0);
                rightMax = Math.max(rightMax,height[r--]);
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }

}
