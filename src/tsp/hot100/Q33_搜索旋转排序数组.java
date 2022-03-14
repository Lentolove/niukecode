package tsp.hot100;

/**
 * Author : tsp TODO 注意点：查找到最小的值的索引，然后分区间二分
 * Time: 2022/2/15 20:26
 * Desc :整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q33_搜索旋转排序数组 {


    public static int search(int[] nums, int target) {
        int n = nums.length;
        if (nums[0] < nums[n - 1]) {
            if (nums[0] > target || nums[n - 1] < target) return -1;
            return binarySearch(nums, 0, n - 1, target);
        }
        //查找转折区间,即最小值的索引位置
        int l = 0, r = n - 1;
        int minIndex = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1]) {//找到转折点了
                 minIndex = mid + 1;
                 break;
            }else {
                //mid处在了升序区间内，逆转后又两个升序控件，判断是左边还是右边
                //5 6 7 8 1 2 3 4 找到 1 的下标
                if (nums[l] > nums[mid]){//中点⽐比左起点还⼩小 则左边上升的序列列⼀一定存在旋转
                    r = mid - 1;
                }else {
                    l = mid + 1;
                }
            }
        }
        if (nums[minIndex] <= target && nums[n - 1] >= target) return binarySearch(nums,minIndex,n - 1,target);
        return binarySearch(nums,0,minIndex - 1,target);
    }

    /**
     * 在有序区间内找目标值
     */
    public static int binarySearch(int[] nums, int start, int end, int target) {
        int l = start, r = end;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {5,6,7,8,1,2,3,4};
        System.out.println(search(arr,6));
    }

}
