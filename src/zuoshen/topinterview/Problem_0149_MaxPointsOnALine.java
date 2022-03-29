package zuoshen.topinterview;

import java.util.HashMap;
import java.util.Map;

/**
 * author : tsp
 * Date : 2022/3/27
 * DESC:给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * <p>
 * 示例 1：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 * <p>
 * 提示：
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -10⁴ <= xi, yi <= 10⁴
 * points 中的所有点 互不相同
 * <p>
 * Related Topics 几何 数组 哈希表 数学 👍 395 👎 0
 */
public class Problem_0149_MaxPointsOnALine {

    /**
     * 同一条直线压中的最多的点，两个点确定一条直线，斜率是固定的
     * 1.从前往后遍历，以当前点 a 为起始点，向后遍历到 b，a与b的情况
     * 2.重合，同x轴，同y轴，斜率相同。
     * 3.用HashMap来存储斜率，及其点的数量，key表示分子，value为HashMap：key为分子，value为点的个数
     */
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        int ans = 0;
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            //每一轮需要clear map 中的数据重新统计
            map.clear();
            int samePoint = 1;
            int sameX = 0;
            int sameY = 0;
            int line = 0;
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                //计算斜率
                int x = x2 - x1;
                int y = y2 - y1;
                if (x == 0 && y == 0) {
                    samePoint++;
                } else if (x == 0) {
                    sameX++;
                } else if (y == 0) {
                    sameY++;
                } else {
                    int gcd = gcd(x, y);
                    x /= gcd;
                    y /= gcd;
                    if (!map.containsKey(x)){//不存在的分子
                        map.put(x,new HashMap<>());
                    }
                    if (!map.get(x).containsKey(y)){
                        //当前分母不存在
                        map.get(x).put(y,0);
                    }
                    //当前斜率上存在的点的数量
                    map.get(x).put(y,map.get(x).get(y) + 1);
                    //当前斜率的线的点的个数
                    line = Math.max(line,map.get(x).get(y));
                }
            }
            //记录最大值
            int lineMax = Math.max(sameX, Math.max(sameY, line)) + samePoint;
            ans = Math.max(ans, lineMax);
        }
        return ans;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
