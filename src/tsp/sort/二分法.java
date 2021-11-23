package tsp.sort;

public class 二分法 {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println(search1(nums, 10));
    }



    public int missingNumber(int[] nums) {
        int left = 0,right = nums.length - 1;
        while(left < right){
            int mid = (left + right) / 2;
            if(nums[mid] == mid){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }

    //查找右边界
    public static int search1(int[] nums, int target) {
        int left = 0,right = nums.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] <= target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left;
    }

    //二分查找左边界点
    public static int search(int[] nums, int target) {
        if (nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) return 0;
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int count = 0;
        while (nums[left] == target) {
            count++;
            left++;
        }
        return count;
    }

}
