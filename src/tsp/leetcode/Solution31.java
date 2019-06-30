package tsp.leetcode;

import java.util.ArrayList;

public class Solution31 {

    /**
     *  动态规划，从底向上计算
     *  当前节点： triangle[i][j]+ Math.min(triangle[i+1][j],triangle[i+1][j+1]) 保存为当前节点的最小值
     *  修改了原输入
     */
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        for (int i = triangle.size()-2; i >=0 ; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) +
                        Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }
}
