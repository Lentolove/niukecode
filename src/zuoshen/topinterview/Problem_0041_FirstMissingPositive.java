package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/11 16:59
 * Desc :给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= nums.length <= 5 * 10⁵
 * -2³¹ <= nums[i] <= 2³¹ - 1
 * <p>
 * Related Topics 数组 哈希表 👍 1380 👎 0
 */
public class Problem_0041_FirstMissingPositive {

    /**
     * 思路：题目要求O(N)时间复杂度，并且不能使用额外的空间。
     * 假定数组长度为 n,则期望的没有出现的最小的正整数为 n+1,期望数组为[1,2,3,4,5] n = 5,最小未出现的正整数为6
     * 1.定义两个指针 L = 0, R = n, 当前期望的没有出现的最小的正整数为 R + 1.
     * 2.L表示左边已经按照[1,2..]排好的部分，根据以下条件来移动L和R的指针
     * 1) nums[L] == L+1,那么L++
     * 2) nums[L] != L+1前提下，nums[L] > R,预期最小值为 R + 1,此时出现了 nums[L] > R，eg:R = 6,此时出现了一个 7
     * 那前面不可能出现1-6这五个数全部出现，将 swap(l,--r)
     * 3)nums[L] <= L, [1,2,3,3..] L = 3,此时 nums[L] = 3,已经出现过了，它只会导致我的期望值 R + 1 减小，
     * 因为出现重复了,这个数是垃圾数字。将 swap(l,--r),将最后的期望值--,将当前位置拿过来继续判断。
     * 4) nums[nums[L] - 1] = nums[L], 当前头顶上的位置元素为: nums[L]= 8，那它应该在数组中的index = 7位置，如果当前 nums[7] = 8 = nums[L],
     * 则说明当前元素出现重复了，这是一个垃圾数据，只会降低我的预期期望值，swap(l,--r)
     * 5) 当前位置 arr[L] - 1 = 4 = index,并且 arr[index] = l,当前遇到的 nums[L]= 8，index =  7 的位置刚好是 L，则交换过来，继续
     * [3,4,-1,1]
     */
    public int firstMissingPositive(int[] nums) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            if (nums[l] == l + 1) {
                l++;
            } else if (nums[l] <= l || nums[l] > r || nums[nums[l] - 1] == nums[l]) {
                //(3),(2),(4)
                swap(nums, l, --r);
            } else {
                //(5)
                swap(nums, l, nums[l] - 1);
            }
        }
        return l + 1;
    }


    /**
     * TODO  基于桶排序的思想
     */
    public int firstMissingPositive1(int[] nums){

        return 0;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
