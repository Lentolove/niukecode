package tsp.hot100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q581_最短无序连续子数组 {


    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        int[] nums2 = {1, 2, 3, 4};
        int ans = findUnsortedSubarray(nums);
        System.out.println(ans);
    }

    //以Hash表来记录0~i的和，key = sum，value = 个数,有0~x的和为sum
    public int subarraySum(int[] nums, int k) {
        int count = 0,pre = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);//初始化时候，和为0的个数为1
        for(int i = 0; i < nums.length; i++){
            //统计0~i的和
            pre += nums[i];
            if(map.containsKey(pre - k)){
                count += map.get(pre - k);
            }
            map.put(pre,map.getOrDefault(pre,0) + 1);
        }
        return count;
    }

    //从左往右找升序，从右往左找降序
    public static int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int leftMax = nums[0];
        int rightMin = nums[len - 1];
        int start = 0,right = len -1;
        for(int i = 0; i < len; i++){
            //从左往右
            if(nums[i] < leftMax){
                //降序了
                start = i;
            }else{
                leftMax = nums[i];
            }
            if(nums[len - i - 1] > rightMin){
                right = len - i - 1;
            }else{
                rightMin = nums[len - i - 1];
            }
        }
        return right - start + 1;
    }

}
