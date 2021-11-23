package cxm;

import java.util.*;

public class Solution3 {

    static int p = 1000000007;

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int subsets = subsets(nums);
        System.out.println(subsets);
    }

    public static int subsets(int[] nums) {
        int ans = 0;
        int all_size = 1 << nums.length; // 1 << n = 2^n
        for (int i = 0; i < all_size; i++) {
            List<Integer> item = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    item.add(nums[j]);
                }
            }
            if (item.size() == 0) continue;
            int a = getTotal(item);
            ans += (a % p);
        }
        return ans;
    }

    public static int getTotal(List<Integer> item) {
        Collections.sort(item);
        if (item.size() == 1) {
            return item.get(0) * 2;
        } else {
            return item.get(0) + item.get(item.size() - 1);
        }
    }
}
