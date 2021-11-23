package cxm;

public class Solution6 {

    public static void runPermutation(int[] a, int n) {
        if (null == a || a.length == 0 || n <= 0 || n > a.length)
            return;
        int[] b = new int[n];// 辅助空间，保存待输出组合数
        getCombination(a, n, 0, b, 0);
    }

    public static void getCombination(int[] a, int n, int begin, int[] b, int index) {
        if (n == 0) {// 如果够n个数了，输出b数组
            getAllPermutation(b, 0);// 得到b的全排列
            return;
        }

        //取n个数的所有情况
        for (int i = begin; i < a.length; i++) {
            b[index] = a[i];//取一个元素放到辅助数组中
            //取剩下元素的组合
            getCombination(a, n - 1, i + 1, b, index + 1);
        }
    }

    //全排列
    public static void getAllPermutation(int[] a, int index) {
        /* 与a的元素个数相同则输出 */
        if (index == a.length - 1) {
            for (int i = 0; i < a.length; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = index; i < a.length; i++) {
            swap(a, index, i);
            getAllPermutation(a, index + 1);
            swap(a, index, i);
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 4, 5, 6};
        runPermutation(nums,4);
    }

}
