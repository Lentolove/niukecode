package tsp.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 灰色代码是一个二进制数字系统，其中两个连续的值只在一个比特内不同。
 * 给定一个非负整数n表示代码中的总位数，打印灰码序列。灰码序列必须以0开头。
 * 例如，给定n=2，返回[0,1,3,2]。它的灰码序列是：对于给定的n，灰码序列不是唯一定义的。
 *  n = 3 [0,1,3,2,6,7,5,4]
 * 例如，[0,2,3,1]也是根据上述定义的有效灰码序列。
 * 目前，法官可以根据一个灰码序列来判断
 *
 *
 * 格雷码
 */
public class Solution60 {
    public static ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        for (int i = 0; i < n; i++) {
            int hight = 1<<i;
            for (int j = result.size()-1; j >=0 ; j--) {
                result.add(hight| result.get(j));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        ArrayList<Integer> arrayList = grayCode(n);
        System.out.println(Arrays.asList(arrayList));
    }
}
