package tsp.niuke.华为机试108题;

import java.util.Scanner;

public class Q5_进制转换 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String s = sc.next();
            System.out.println(Integer.parseInt(s.substring(2),16));
        }
    }
}
