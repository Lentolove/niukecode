package tsp.offer;

public class Q14_duplicateInArray {

    public int duplicateInArray(int[] nums) {
        if (nums==null||nums.length<1) return -1;
        int str = 1;
        int end = nums.length-1;
        while (str<=end){
            int mid = (str+end)>>1;
            int count = coutRange(nums,str,mid);
            if (str ==end){
                if (count>1){
                    return str;
                }else {
                    break;
                }
            }
            if (count>(mid-str+1)){
                end = mid;
            }else {
                str = mid+1;
            }
        }
        return -1;
    }
    public int coutRange(int[] nums,int start,int end){
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>=start&&nums[i]<=end){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(3>>1);
    }
}
