package tsp.hot100;


/**
 * Author : tsp
 * Time: 2022/2/23 20:09
 * Desc :
 */
public class Q221_最大正方形 {

    /**
     * 找出矩阵只包含1的最大正方形
     * 1.定义dp[i][j]表示以该位置为右下角的最大正方形边长
     * 2.该位置之和它的左，上，左上有关，因为左dp[i-1][j] 它
     */
    public static int maximalSquare(char[][] m) {
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,3,4,6,7,8,9};
        System.out.println(leftSearch(arr,12));
    }

    public static int leftSearch(int[] arr,int target){
        int n = arr.length;
        int l = 0,r = n - 1;
        int ans = -1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if (target <= arr[mid]){
                ans = mid;
                r = mid - 1;
            }else {
                l = mid +1;
            }
        }
        return ans;
    }


    static int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 缩小右边界
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (left >= nums.length || nums[left] != target) return -1;

        return left;
    }



}
