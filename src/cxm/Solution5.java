package cxm;


public class Solution5 {


    static int p = 1000000007;
    static int totalSum = 0;

    public static void main(String[] args) {
        int[] nums = { 3, 4, 5, 6};
        combine_increase(nums, 0, new int[4], 4, 4, nums.length);
        System.out.println(totalSum);

    }

    public static void combine_increase(int[] arr, int start, int[] result, int count, int NUM, int arr_len) {
        for (int i = start; i < arr_len + 1 - count; i++) {
            result[count - 1] = i;
            if (count - 1 == 0) {
                int j;
                int sum = 1;
                for (j = NUM - 1; j >= 0; j--) {
                    int a = arr[result[j]];
                    sum ^= (a % p);
                }
                sum ^= 1;
                totalSum += (sum % p);
            } else
                combine_increase(arr, i + 1, result, count - 1, NUM, arr_len);
        }
    }


}
