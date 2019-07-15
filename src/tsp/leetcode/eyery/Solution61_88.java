package tsp.leetcode.eyery;

import tsp.offer.TreeNode;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;

/**
 * 合并两个有序数组
 * leetcode:88 merge-sorted-array
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 */


public class Solution61_88 {

    /**
     * 方法一：合并后排序
     * 简单粗暴
     */
    public void merge(int A[], int m, int B[], int n) {
        System.arraycopy(B, 0, A, m, n);
        Arrays.sort(A);
    }

    /**
     * 方法二：双指针/从前往后
     * 一般而言，对于 有序数组 可以通过 双指针法 达到 O(M+N) 的时间复杂度
     * 最直接的算法实现是将指针 p1 置为 A 开头，p2 置为 B 开头，在每一步将最小值放入输出数组中。
     */
    public void merge1(int A[], int m, int B[], int n) {
        //设置一个空数组 用于存放排序后的值
        int[] temp = new int[m];
        System.arraycopy(A, 0, temp, 0, m);
        int p1 = 0, p2 = 0, p = 0; // p1 是temp的指针 p2是B的指针  p是A的指针
        while (p1 < m && p2 < n) {
            A[p++] = (temp[p1] < B[p2]) ? temp[p1++] : B[p2++];
        }

        if (p1 < m) {
            System.arraycopy(temp, p1, A, p1 + p2, m + n - p1 - p2);
        }
        if (p2 < n) {
            System.arraycopy(B, p2, A, p1 + p2, m + n - p1 - p2);
        }
    }

    /**
     * 方法三：双指针/从后往前
     */
    public void merge2(int[] A, int m, int[] B, int n) {
        if (A.length<1||B.length<1) return;
        int p1 = m-1;
        int p2 = n-1;
        int p = m + n - 1;
        while (p1>=0&&p2>=0){
            A[p--] = A[p1]<B[p2]?B[p2--]:A[p1--];
        }
        System.arraycopy(B,0,A,0,p2+1); //不包含尾
    }

}
