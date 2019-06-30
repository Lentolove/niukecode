package tsp.offer;

public class Q12_青蛙变态台阶跳 {
    public static int JumpFloor(int target) {
        if (target<1) return 0;
//        if (target == 1) return 1;
//        return 2*JumpFloor(target-1);

        return 1<<(target-1);
    }

    /**
     * 非递归
     * @param target
     * @return
     */
    public static int JumpFloor1(int target) {
        if (target==0) return 0;
        int[] dp = new int[target+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <=target; i++) {
            dp[i] = 0;
            for (int j = 0; j <=i ; j++) {
                dp[i] += dp[j];
            }
        }
        return dp[target];
    }



    public static void main(String[] args) {
        System.out.println(JumpFloor(100));
    }
}
