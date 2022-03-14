package zuoshen.interview.classic9;

/**
 * 左神：给定一个无序数组 arr,如果只能在一个子数组上进行排序从而让整体有序，
 * 返回需要排序的最短子数组的长度？
 */
public class Code04_MinLengthForSort {

    /**
     * 思路：分两次，从左往右记录右边有多少数是不需要排序的，从右往左记录左边不需要排序的为值的索引
     * 1.从左往右，定义变量 maxLeft：1.如果 maxLeft > a ,则打x,否则打√
     * 2.从右往左，定义变量 minRight: 1.如果 minRight < a,则打 x,否则打√
     */
    public static int getMinLength(int[] arr) {
        if (arr == null || arr.length == 1) return 0;
        int n = arr.length;
        int maxLeft = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i < n; i++) {
            if (maxLeft > arr[i]) {//出现逆序，打x
                noMaxIndex = i;
            } else {
                //从左往右正常顺序
                maxLeft = Math.max(maxLeft, arr[i]);
            }
        }
        //2.从右往左
        int minRight = arr[n - 1];
        int noMinIndex = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (minRight < arr[i]){//从右往左逆序了，打x
                noMinIndex = i;
            }else {
                minRight = Math.min(minRight,arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
        System.out.println(getMinLength(arr));

    }

}
