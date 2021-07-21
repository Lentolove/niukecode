package tsp.offer;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class Q21_调整数组顺序使奇数位于偶数前面 {



    public void reOrderArray1(int [] array) {

    }
    /**
     * 这个使得奇数和奇数 偶数和偶数之间的相对为止发生了改变
     * @param array
     */
    public static void reOrderArray(int [] array) {
        if (array==null||array.length==0) return;
        int p1 = 0;
        int p2 = array.length-1;
        while (p1<p2){
            //向后移动p1 直到遇到偶数
            while (p1<p2&&(array[p1]&1)!=0){
                p1++;
            }
            //移动p2指针  直到它指向奇数
            while (p1<p2&&(array[p2]&1)==0){
                p2--;
            }
            if (p1<p2){
                int temp = array[p1];
                array[p1]=array[p2];
                array[p2] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {2,3,6,4,8,9,2,0};
        reOrderArray(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
}
