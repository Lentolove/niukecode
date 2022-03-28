package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/24 20:02
 * Desc :
 */
public class Problem_0136_SingleNumber {

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int a : nums) {
            ans ^= a;
        }
        return ans;
    }
}
