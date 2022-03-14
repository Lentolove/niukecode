package tsp.hot100;

/**
 * Author : tsp
 * Time: 2022/2/15 10:04
 * Desc :给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * <p>
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 返回容器可以储存的最大水量。
 * <p>
 * 说明：你不能倾斜容器。
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q11_盛最多水的容器 {

    /**
     * 思路一：暴力法-》每次都要找[0,i-1]位置的最大值，[i + 1,n]区间的最大值
     * 通过数组预处理，定义 left 和 right 数组
     * 时间复杂度 O(N),空间复杂度 O(N)
     * TODO 不是接雨水问题
     */
    public static int maxArea(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;
        //1.定义从左往右 left[i] 表示[0,i-1]的最大数
        int[] left = new int[n];
        int leftMax = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = leftMax;
            if (leftMax < height[i]) {
                leftMax = height[i];
            }
        }
        //2.定义从右往左的记录最大值的数组
        int[] right = new int[n];
        int rightMax = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = rightMax;
            if (rightMax < height[i]) {
                rightMax = height[i];
            }
        }
        //3.计算总蓄水量
        int sum = 0;
        for (int i = 1; i < n - 1; i++) {
            int a = Math.min(left[i], right[i]) - height[i];
            sum += Math.max(a, 0);
        }
        return sum;
    }

    /**
     * 思路：木桶原理，定义 left 和 right 指针
     * 1.定义左边最大值 leftMax 和 右边最大值 rightMax
     * 2.记录当前蓄水量：(r - l - 1) * min(leftMax,rightMax) 更新最大值
     * 3.哪边短就移动哪边的指针，增加桶的最短高度
     */
    public static int maxArea1(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        while (left < right) {
            //中间的区间 right - left
            ans = Math.max(ans, Math.min(height[left], height[right]) * (right - left));
            //1.左边小就移动左指针
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
//        System.out.println(maxArea(arr));
        System.out.println(maxArea1(arr));
    }

}
