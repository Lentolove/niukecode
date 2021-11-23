package tsp.hot100;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Q146_缓存机制 {

    public static void main(String[] args) {

        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(2,2);
        Integer integer = map.get(1);
        System.out.println(integer);
    }

}

class LRUCache {

    private int maxCapactity;

    private LinkedHashMap<Integer,Integer> map;

    public LRUCache(int capacity) {
        this.maxCapactity = capacity;
        map = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }
        return map.get(key);
    }

    public void put(int key, int value) {
        if (map.size() < maxCapactity){
            map.put(key,value);
            return;
        }
    }
}
