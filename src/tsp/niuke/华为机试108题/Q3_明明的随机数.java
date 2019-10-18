package tsp.niuke.华为机试108题;

import java.util.*;

public class Q3_明明的随机数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int a = sc.nextInt();
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < a; i++) {
                int b = sc.nextInt();
                set.add(b);
            }
            for (Integer i  : set) {
                System.out.println(i);
            }
        }
    }
}
