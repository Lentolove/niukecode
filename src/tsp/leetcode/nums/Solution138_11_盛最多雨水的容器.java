package tsp.leetcode.nums;

/**
 * 11:盛最多雨水的容器
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 */
public class Solution138_11_盛最多雨水的容器 {


    /**
     * 双指针
     * 这题最关键的是两点，一是两边往中间找，二是每次放弃最短的版。
     */
    public static int maxArea(int[] height) {
        int max = 0,left = 0,right = height.length-1;
        while (left < right){
            max = Math.max(max,((right-left)*Math.min(height[left],height[right])));
            if (height[left] < height[right]){ //储水量取决于较短的一条边
                left++;
            }else {
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(a));
    }
}
