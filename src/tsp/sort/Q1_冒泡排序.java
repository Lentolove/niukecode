package tsp.sort;

import static tsp.sort.Utils.printNum;
import static tsp.sort.Utils.swap;

/**
 * 冒泡排序算法
 * 平均时间复杂度： O(n^2)
 * 最好情况： O(n)
 * 最坏情况： O(n^2)
 * 空间复杂度：O(1)
 * 稳定
 */
public class Q1_冒泡排序 {

    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 6, 7, 2, 4, 5, 9, 2, 3, 1, 5};
        bubble_sort1(nums);
        printNum(nums);
    }

    /**
     * 1.把最大值通过冒泡的方式排到最后
     */
    public static void bubble_sort(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    /**
     * 2.优化后，增加一个 flag 判断，如果当前内层循环中已经不需要交换了，则直接停止冒泡了
     */
    public static void bubble_sort1(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    flag = true;
                    swap(a, j, j + 1);
                }
            }
            //如果这一轮冒泡都不需要交换位置，说明前面的数据就已经都排好了，直接退出当前循环
            if (!flag) break;
        }
    }



}
