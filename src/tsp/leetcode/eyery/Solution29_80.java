package tsp.leetcode.eyery;

import java.util.Arrays;

/**
 * leetcode:80 删除排序数组中的重复项 II  (Medium)
 *
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 示例 1:
 * 给定 nums = [1,1,1,2,2,3],
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 */
public class Solution29_80 {

    /**
     * 原地删除肯定是双指针，一个指向遍历的元素，一个指向可以写入的位置，后者的大小是小于等于前者的
     */
    public int removeDuplicates(int[] A) {
        if (A.length<=1) return A.length;
        int cp = 1;
        for (int i = 2; i < A.length; i++) {
            if (A[i]!= A[cp-1]){
                cp +=1; // cp指针先移动了.....
                A[cp] = A[i];
            }
        }
        System.out.println(Arrays.toString(A));
        return cp+1;
    }

    public static void main(String[] args) {
        Solution29_80 s = new Solution29_80();
        int[] a = {0,0,1,1,2,2,2,3};
        System.out.println(s.removeDuplicates(a));

    }
}

