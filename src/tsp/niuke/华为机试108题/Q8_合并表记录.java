package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Q8_合并表记录 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            String[] strs = bf.readLine().split(" ");
            int a = Integer.parseInt(strs[0]);
            int b = Integer.parseInt(strs[1]);
            map.put(a,map.getOrDefault(a,0)+b);
        }
        for (Integer x : map.keySet() ) {
            System.out.println(x+" "+map.get(x));
        }
    }
}
