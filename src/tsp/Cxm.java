package tsp;

import java.util.Arrays;

public class Cxm {


    public static void main(String[] args) {
        int[] nums = {8,8,8,8,8,8,10};
        System.out.println(search(nums,8));
        System.out.println(searchRight(nums,8));
    }


    //排序数组，二分法，找左边界
    public static int search(int[] nums, int target) {
        if(nums.length == 0) return 0;
        if(nums[0] > target || nums[nums.length - 1] < target) return 0;
        int left = 0,right = nums.length - 1;
        while(left < right){
            int mid = (left + right) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        int count = 0;
        for(int i = left;i < nums.length; i++){
            if(nums[i] == target){
                count++;
            }else{
                break;
            }
        }
        return count;
    }

    //排序数组，二分法，找右边界
    public static int searchRight(int[] nums, int target) {
        if(nums.length == 0) return 0;
        if(nums[0] > target || nums[nums.length - 1] < target) return 0;
        int left = 0,right = nums.length - 1;
        while(left < right){
            int mid = (left + right) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] <= target){
                left = mid + 1;
            }
        }
        return left;
    }
}
