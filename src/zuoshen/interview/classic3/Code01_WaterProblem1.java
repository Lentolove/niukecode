package zuoshen.interview.classic3;

/**
 * 左神：一维蓄水池问题
 * 题目：给定一个数组 arr ，数组的所有值非负数，将数组看成一个容器，请返回容器能装多少水？
 * eg: arr = [3,1,2,5,2,4] ,根据值画出直方图就是容器的形状，该容器可以装下 5 格水；
 * [arr = [4,5,1,3,2] ，该容器可以装下 2 格水
 */
public class Code01_WaterProblem1 {

    /**
     * 利用左右指针，及两个变量来更新左右的最大值
     */
    public static int waterStorage(int[] arr){
        if (arr == null || arr.length < 3) return 0;
        int n = arr.length;
        int leftMax = arr[0];
        int rightMax = arr[n - 1];
        int l = 1;
        int r = n - 2;
        int water = 0;
        while (l <= r){
            if (leftMax <= rightMax){
                //更新蓄水量,如果当前位置比 leftMax 高，那就蓄水， water += 0
                water += Math.max(leftMax - arr[l],0);
                //看是否需要更新 leftMax 的值,同时 l 指针右移动
                leftMax = Math.max(leftMax,arr[l++]);
            }else {
                water += Math.max(rightMax - arr[r],0);
                rightMax = Math.max(rightMax,arr[r--]);
            }
        }
        return water;
    }


    public static void main(String[] args) {
        int[] arr = {3,1,2,5,2,4};
        System.out.println(waterStorage(arr));
    }


}
