package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q17_坐标移动 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null){
            int x = 0, y = 0;
            String[] line1 = str.split(";");
            for (int i = 0; i < line1.length; i++) {
                try {
                    int a = Integer.parseInt(line1[i].substring(1));
                    char c = line1[i].charAt(0);
                    if (c == 'A') {
                        x -= a;
                    } else if (c == 'D') {
                        x += a;
                    } else if (c == 'W') {
                        y += a;
                    } else {
                        y -= a;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            System.out.println(x + "," + y);
        }
    }
}
