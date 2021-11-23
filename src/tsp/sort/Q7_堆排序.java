package tsp.sort;

import static tsp.sort.Utils.swap;

public class Q7_堆排序 {


    private static void heap_sort(int[] a, int n) {
        buildMaxHeap(a, n);
        for (int i = n - 1; i > 0; i--) {
            swap(a, 0, i);
            heapify(a, 0, i);
        }
    }

    //1.构建最大堆
    private static void buildMaxHeap(int[] a, int n) {
        for (int i = (n - 1) / 2; i >= 0; i--) {
            heapify(a, i, n);
        }
    }

    private static void heapify(int[] a, int i, int n) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int lagest = i;
        if (left < n && a[left] > a[lagest]) lagest = left;//最小堆就改成<
        if (right < n && a[right] > a[lagest]) lagest = right;
        if (lagest != i) {
            swap(a, i, lagest);
            heapify(a, lagest, n);
        }
    }

}
