package tsp.offer;

public class Q1_旋转数组的最小值 {

    public static int minNumberInRotateArray(int [] array) {
        if (array==null||array.length<1) return 0;
        int str = 0;
        int end = array.length-1;

        for (int i = 0; i < array.length; i++) {
            if ((end-str)==1) return array[end];
            int mid  = (str+end)>>1;
            if (array[i]<array[mid]){
                str = mid;
            }else {
                end = mid;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] a={3,4,5,3,3};
        System.out.println(minNumberInRotateArray(a));
    }
}
