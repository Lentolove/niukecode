package tsp.hot100;

public class Q34_在排序数组中查找元素的第一个和最后一个位置 {

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        System.out.println(binarySearch(nums,8));
        System.out.println(searchRight(nums,7));
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


    //二分法查找左边界
    public static int binarySearch(int[] nums,int target,boolean isLefe){
        int n = nums.length;
        int left = 0,right = n - 1;
        while(left < right){
            int mid = (left + right) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                if(isLefe){
                    right = mid;
                }else{
                    left = mid;
                }
            }
        }
        return left;
    }

    //二分法查找左边界
    public static int binarySearch(int[] nums,int target){
        int n = nums.length;
        int left = 0,right = n - 1;
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
        return left;
    }

    //查找右边界
    public static int binarySearchRight(int[] nums,int target){
        int n = nums.length;
        int left = 0,right = n - 1;
        while (left < right){
            int mid = (left + right) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                left = mid;
            }
        }
        return  left;
    }
}
