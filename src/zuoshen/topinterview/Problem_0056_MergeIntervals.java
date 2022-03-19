package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * author : tsp
 * Date : 2022/3/17
 * DESC:以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
 * 回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 提示：
 * 1 <= intervals.length <= 10⁴
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10⁴
 * <p>
 * Related Topics 数组 排序 👍 1375 👎 0
 */
public class Problem_0056_MergeIntervals {


    public static class Point {
        int start;
        int end;

        public Point(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class PointCompare implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            return o1.start - o2.start;
        }
    }

    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n == 0 || n == 1) return intervals;
        Point[] pointList = new Point[n];
        for (int i = 0; i < n; i++) {
            pointList[i] = new Point(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(pointList, new PointCompare());
        List<int[]> ans = new ArrayList<>();
        int s = pointList[0].start;
        int e = pointList[0].end;
        for (int i = 1; i < n; i++) {
            int s1 = pointList[i].start;
            int e1 = pointList[i].end;
            if (e >= s1) {
                e = Math.max(e,e1);
            }else {
                //收集之前的区间
                ans.add(new int[]{s,e});
                s = s1;
                e = e1;
            }
        }
        ans.add(new int[]{s,e});
        int[][] result = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        return result;
    }

}
