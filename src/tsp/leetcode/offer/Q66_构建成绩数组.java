package tsp.leetcode.offer;

public class Q66_构建成绩数组 {


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {1,2,3,4,5};
        solution.constructArr(a);
    }

    static class Solution {

        //维护左右两个dp数组，分别保存，dpL[i] 为  1 * 2* 3... i -1 的和
        //同理右边也为：dpR[i] 为 n * n- 2 * ... n - i + 1的和。
        //那么 B[i] = dpL[i] * dpR[i]
        public int[] constructArr(int[] a) {
            if(a == null || a.length == 0) return new int[0];
            int len = a.length;
            int[] dpL = new int[len];
            int[] dpR = new int[len];
            //构建左右dp数组
            dpL[0] = 1;
            dpR[len - 1] = 1;
            for(int i = 1; i < len ;i++ ){
                dpL[i] = dpL[i - 1] * a[i - 1];
            }
            for(int i = len - 2; i >=0;i--){
                dpR[i] = dpR[i + 1] * a[i + 1];
            }
            for(int i = 0; i < len;i++){
                a[i] = dpL[i] * dpR[i];
            }
            return a;
        }
    }
}
