package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q22_汽水瓶 {

    //看别人思路，没两个汽水瓶换一个

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            int n = Integer.parseInt(str);
            if (n == 0) break;
            System.out.println(maxDrink(n));
        }
    }

    private static int maxDrink(int n) {
        int count = 0;
        while (n >= 2) {
            int a = n / 3;
            count += a;
            int mod = n % 3;
            n = a + mod;
            if (n == 2) {
                count++;
                break;
            }
        }
        return count;
    }
}

