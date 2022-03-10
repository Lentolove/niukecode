package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/9 16:30
 * Desc : 给定一个数组
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * 示例 1：
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 * 提示：
 * n == height.length
 * 2 <= n <= 10⁵
 * 0 <= height[i] <= 10⁴
 * <p>
 * Related Topics 贪心 数组 双指针 👍 3269 👎 0
 */

public class Problem_0011_ContainerWithMostWater {

    /**
     * [1,8,6,2,5,4,8,3,7]
     * [0,1,2,3,4,5,6,7,8] index Max = (8 - 1) * Math.min(7,8)
     * 思路：双指针，左右指针从两端开始出发，每次记录最大值，然后移动较短高度的指针
     */
    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) return 0;
        int n = height.length;
        int l = 0, r = n - 1;
        int ans = 0;
        while (l < r){
            ans = Math.max(ans,(r - l) * Math.min(height[l],height[r]));
            if (height[l] < height[r]){
                l++;
            }else {
                r--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(arr));
    }
}
