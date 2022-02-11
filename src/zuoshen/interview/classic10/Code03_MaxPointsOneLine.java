package zuoshen.interview.classic10;

import java.util.HashMap;

/**
 * Author : tsp
 * Time: 2022/2/11 15:59
 * <p>
 * 左神：给定两个数字 arrX 和 arrY，长度都为 N，代表二维平面上有 N 个点，第 i 个点的
 * x 左边和 y 坐标分别对应 arrX[i] 和 arrY[i],返回求一条执行最多能穿过多少个点?
 */
public class Code03_MaxPointsOneLine {

    public static class Point {
        public int x;
        public int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    /**
     * 思路：分情况讨论两个点的情况
     * 1): a 与 b 重合 ： 定义变量 chong
     * 2): a 与 b 共 x 轴  ：定义变量 sameX
     * 3): a 与 b 共 y 轴  ：定义变量 sameY
     * 4): a 与 b 共斜率   : 定义斜率集合 map
     * map 中表示不同的斜率对应点的个数
     * 难点：斜率 (x1 - x2)/(y1- y2) 如何表示而不丢失精度，可不能直接用 double 去表示
     * 1.可以对斜率进行分数化简： 20/30 = 2/3，定义成字符串 2_3 表示，如果有负号的话统一提到前面 “-2_3"
     * 2.斜率化简后，对分子分母用 HashMap 存储表示，key 对应分子，value 一个 HashMap 表：key表示分母，value表示点个数
     * 3.求最大公约数用到欧几里得算法，gcd
     */
    public static int maxPoints(Point[] points) {
        if (points == null) return 0;
        if (points.length <= 2) return points.length;
        //map存储斜率，key 表示分子，value.key 表示分母，value.value 表示对应的点数个数
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < points.length; i++) {//以不同的点作为起点来计算
            map.clear();//每次都需要清空，因为是不同的起点， [a,b,c,d,e,f] (a,b),(a,c),(a,d),....,(b,c),(b,d)
            int samePoint = 1;
            int sameX = 0;
            int sameY = 0;
            int line = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[i].x - points[j].x;
                int y = points[i].y - points[j].y;
                if (x == 0 && y == 0) {//重合点
                    samePoint++;
                } else if (x == 0) {
                    sameX++;
                } else if (y == 0) {
                    sameY++;
                } else {
                    //分子分母都不为0，计算分子和分母的最大公约数
                    int gcd = gcd(x, y);
                    x /= gcd;//化简后的分子
                    y /= gcd;//化简后的分母
                    if (!map.containsKey(x)) {
                        map.put(x, new HashMap<>());
                    }
                    if (!map.get(x).containsKey(y)) {//不包含当前分母
                        map.get(x).put(y, 0);
                    }
                    //存在当前斜率，就让其点数+1
                    map.get(x).put(y, map.get(x).get(y) + 1);
                    //当前直线上有多少点
                    line = Math.max(line, map.get(x).get(y));
                }
            }
            //记录最大值
            result = Math.max(result, Math.max(Math.max(sameX, sameY), line) + samePoint);
        }
        return result;
    }

    /**
     * 求最大公约数：保证初始调用的时候，a和b不等于0
     */
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
