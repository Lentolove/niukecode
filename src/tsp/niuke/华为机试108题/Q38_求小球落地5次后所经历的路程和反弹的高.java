package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q38_求小球落地5次后所经历的路程和反弹的高 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            double n = Double.parseDouble(str);
            System.out.println(getJourney(n));
            System.out.println(getTenthHigh(n));
        }

    }

    //统计出第5次落地时，共经过多少米
    private static double getJourney(double high) {
        return 2.875*high;
    }

    //统计出第5次反弹多高
    private static double getTenthHigh(double high) {
        return high/32;
    }
}
