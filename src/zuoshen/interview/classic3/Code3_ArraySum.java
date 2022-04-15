package zuoshen.interview.classic3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 左神：给定一个有序数组，查找数组中累加和为 aim 的不同的x元组
 * 1. 找到两个元素的和为 aim 的 二元组
 * 2. 找到两个元素的和为 aim 的 三元组
 */
public class Code3_ArraySum {

    /**
     * 找到两个元素的和为 aim 的 二元组
     */
    public static List<int[]> getSumAimOf2(int[] arr, int aim) {
        List<int[]> ans = new ArrayList<>();
        if (arr == null || arr.length < 2) return ans;
        int n = arr.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int sum = arr[l] + arr[r];
            if (sum < aim) {
                l++;
            } else if (sum > aim) {
                r--;
            } else {
                //需要去重
                if (l == 0 || arr[l - 1] != arr[l]) {
                    ans.add(new int[]{arr[l++], arr[r--]});
                } else {
                    l++;
                }
            }
        }
        return ans;
    }

    /**
     * 左神：找到三个元素的和为 aim 的 三元组
     * 固定一个数字，剩下的为 aim - arr[i] 变成查找二元aim问题
     */
    public static List<int[]> getSumAimOf3(int[] arr, int aim) {
        List<int[]> ans = new ArrayList<>();
        if (arr == null || arr.length < 3) return ans;
        int n = arr.length;
        for (int i = 0; i < n - 2; i++) {
            if (i != 0 && arr[i] == arr[i - 1]) continue;
            int target = aim - arr[i];
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int sum = arr[l] + arr[r];
                if (sum < target) {
                    l++;
                } else if (sum > target) {
                    r--;
                } else {
                    if (l == 0 || arr[l - 1] != arr[l]) {
                        ans.add(new int[]{arr[i], arr[l++], arr[r--]});
                    } else {
                        l++;
                    }
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
//        int[] arr = {-15, -5, 1, 1, 2, 2, 3, 3, 5, 5, 6, 6, 7, 7, 8, 8, 10, 13};
//        List<int[]> sumAimOf2 = getSumAimOf2(arr, 9);
//        for (int[] item : sumAimOf2) {
//            System.out.println(Arrays.toString(item));
//        }
        int[] arr = {-15, -5, 1, 1, 2, 2, 3, 3, 5, 5, 6, 6, 7, 7, 8, 8, 10, 13};
        List<int[]> sumAimOf2 = getSumAimOf3(arr, 9);
        for (int[] item : sumAimOf2) {
            System.out.println(Arrays.toString(item));
        }
    }

}
