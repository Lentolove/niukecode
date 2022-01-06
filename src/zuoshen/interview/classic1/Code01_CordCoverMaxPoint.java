package zuoshen.interview.classic1;
import java.util.Arrays;

/**
 * 左神：绳子能覆盖到的最多的点数
 * 题目描述：给定一个有序数组 arr, 从左往右一次表示 X 轴上从左往右点的位置，给定一个长度为 L 的绳子，最多能覆盖到几个点。
 * 包括边缘点
 */
public class Code01_CordCoverMaxPoint {

    /**
     * 思考：1.以绳子的末端来放在数轴上，从末端往左最多能覆盖到几个点。
     * 2.只用考虑数轴上的点，没有必要找不在数轴上的点。
     * 3.既然数组是有序的，当前位置为 i,往左能达到的最远距离是 left = arr[i] - L,查找 left 在[0..i]区间的的位置。
     * 4.对于左侧区间可以直接用二分。
     * 时间复杂度：O(nlogn)
     */
    public static int maxPoint1(int[] arr, int length) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr, i, arr[i] - length);
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }

    /**
     * 在区间 0 ~ r 中二分查找 value 的左边界，[1,3,5,8,10,13] 查找 11 返回的是 5
     */
    public static int nearestIndex(int[] arr, int r, int value) {
        int l = 0;
        //默认就是右端点的起始位置
        int index = r;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (value <= arr[mid]) {
                index = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return index;
    }

    /**
     * 思路二: 1.对于有单调性的问题，想到滑动窗口
     * 2.从0位置开始推动窗口，左指针left,right
     * 3.如果 arr[right] - arr[left] <= l,说明窗口还能继续扩大，right 指针++，直到超过 l.
     * 4.此时记录覆盖的最多点数。left指针还是向右移动
     * 时间复杂度：O(n)
     */
    public static int maxPoint2(int[] arr, int length) {
        int n = arr.length;
        int left = 0;
        int right = 0;
        int ans = 0;
        while (left < n) {
            while (right < n && arr[right] - arr[left] <= length) {
                right++;
            }
            //此时窗口已经超出了，记录最大值，并且left 指针右移动
            ans = Math.max(ans, right - (left++));
        }
        return ans;
    }

    /**
     * 生成长度为len，最大值为 max 的排序的数组
     */
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 10000;
        System.out.println("开始测试...");
        for (int i = 0; i < testTime; i++) {
            //绳子长度
            int L = (int)(Math.random() * max);
            int[] arr = generateArray(len,L);
            int ans1 = maxPoint1(arr,L);
            int ans2 = maxPoint2(arr,L);
            if (ans1 != ans2){
                System.out.println("出问题");
                break;
            }
        }
    }

}
