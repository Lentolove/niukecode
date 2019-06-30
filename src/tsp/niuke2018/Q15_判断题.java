package tsp.niuke2018;



import java.util.Arrays;
import java.util.Scanner;


public class Q15_判断题 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println(count(a));
    }

    public static int count(int[] a){
        int[] b = Arrays.copyOf(a, a.length);
        Arrays.sort(b);
        int count =0;
        for (int i = 0; i < a.length; i++) {
            if (a[i]!=b[i]){
                count++;
            }
        }
        return count;
    }

}
