package tsp.sort;

public class Utils {


    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void printNum(int[] num) {
        for (int i : num) {
            System.out.print(i + "\t");
        }
    }

}
