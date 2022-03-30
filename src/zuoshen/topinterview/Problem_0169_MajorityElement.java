package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/30 19:42
 * Desc :给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1：
 * 输入：[3,2,3]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * 进阶：
 * <p>
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 * <p>
 * Related Topics 数组 哈希表 分治 计数 排序 👍 1376 👎 0
 */
public class Problem_0169_MajorityElement {

    /**
     * 题意：数组中个数查过 n/2的数，题目规定一定存在
     * 打靶子思想
     */
    public int majorityElement(int[] nums) {
        if (nums.length == 1) return nums[0];
        int hp = 0, num = 0;
        for (int a : nums) {
            if (hp == 0) {
                num = a;
                hp = 1;
            } else if (a != num) {
                hp--;
            } else {
                hp++;
            }
        }
        return num;
    }
}
