package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class Q19_简单错误记录 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();//记录 和 错误的次数,linedHashMap是有序的
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = bf.readLine()) != null) {
            String[] split = str.split(" ");
            String[] path = split[0].split("\\\\");
            String name = path[path.length - 1];
            if (name.length() > 16) name = name.substring(name.length() - 16);
            String key = name + " " + split[split.length - 1];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int size = map.size(), index = 0;
        for (String s : map.keySet()) {
            index++;
            if (size - index < 8) {//最后八个了
                System.out.println(s + " " + map.get(s));
            }
        }
    }
}
