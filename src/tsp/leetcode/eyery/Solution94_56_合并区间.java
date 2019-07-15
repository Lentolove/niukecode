package tsp.leetcode.eyery;

import java.util.*;

/**
 * 56:合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 */
class Solution56 {

    /**
     * 自己写的
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> ret = new ArrayList<>();
        int n = intervals.length;
        if (n == 0) return ret.toArray(new int[0][]);
        //按照数组的第一个元素的大小进行排序 a[0]-b[0] 从小到大，b[0]-a[0]从大到小
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] temp = {intervals[0][0], intervals[0][1]};
        for (int i = 0; i < n; i++) {
            if (temp[1] >= intervals[i][0]) {
                temp[0] = Math.min(temp[0], intervals[i][0]);
                temp[1] = Math.max(temp[1], intervals[i][1]);
            } else {
                ret.add(temp);
                temp = intervals[i];
            }
        }
        ret.add(temp);
        return ret.toArray(new int[0][]);
    }

    /**
     * leetcode 官方题解 方法一：数组排序 LinkedList 合并区间
     */
    public int[][] merge1(int[][] intervals) {
        LinkedList<int[]> ret = new LinkedList<>();
        int n = intervals.length;
        if (n == 0) return ret.toArray(new int[0][]);
        //按照数组的第一个元素的大小进行排序 a[0]-b[0] 从小到大，b[0]-a[0]从大到小
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            if (ret.isEmpty() || ret.getLast()[1] < intervals[i][0]) {
                ret.add(intervals[i]);
            } else {
                // 这个合并区间的用法 贼溜！！！！！！！！！！！
                ret.getLast()[1] = Math.max(ret.getLast()[1], intervals[i][1]);
            }
        }
        return ret.toArray(new int[0][]);
    }
}

class Solution56_niuke {

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        int n = intervals.size();
        if (n < 2) return intervals;
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start == o2.start) return o1.end - o2.end;
                return o1.start - o2.start;
            }
        });
        ArrayList<Interval> ret = new ArrayList<>();
        ret.add(intervals.get(0));
        for (int i = 1; i < n; i++) {
            if (ret.get(ret.size()-1).end>=intervals.get(i).start){
                ret.get(ret.size()-1).end = Math.max(intervals.get(i).end,ret.get(ret.size()-1).end);
            }else {
                ret.add(intervals.get(i));
            }
        }
        return ret;
    }

}

class Solution11 {
    private Map<Interval, List<Interval> > graph;
    private Map<Integer, List<Interval> > nodesInComp;
    private Set<Interval> visited;

    // return whether two intervals overlap (inclusive)
    private boolean overlap(Interval a, Interval b) {
        return a.start <= b.end && b.start <= a.end;
    }

    // build a graph where an undirected edge between intervals u and v exists
    // iff u and v overlap.
    private void buildGraph(List<Interval> intervals) {
        graph = new HashMap<>();
        for (Interval interval : intervals) {
            graph.put(interval, new LinkedList<>());
        }

        for (Interval interval1 : intervals) {
            for (Interval interval2 : intervals) {
                if (overlap(interval1, interval2)) {
                    graph.get(interval1).add(interval2);
                    graph.get(interval2).add(interval1);
                }
            }
        }
    }

    // merges all of the nodes in this connected component into one interval.
    private Interval mergeNodes(List<Interval> nodes) {
        int minStart = nodes.get(0).start;
        for (Interval node : nodes) {
            minStart = Math.min(minStart, node.start);
        }

        int maxEnd = nodes.get(0).end;
        for (Interval node : nodes) {
            maxEnd= Math.max(maxEnd, node.end);
        }

        return new Interval(minStart, maxEnd);
    }

    // use depth-first search to mark all nodes in the same connected component
    // with the same integer.
    private void markComponentDFS(Interval start, int compNumber) {
        Stack<Interval> stack = new Stack<>();
        stack.add(start);

        while (!stack.isEmpty()) {
            Interval node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);

                if (nodesInComp.get(compNumber) == null) {
                    nodesInComp.put(compNumber, new LinkedList<>());
                }
                nodesInComp.get(compNumber).add(node);

                for (Interval child : graph.get(node)) {
                    stack.add(child);
                }
            }
        }
    }

    // gets the connected components of the interval overlap graph.
    private void buildComponents(List<Interval> intervals) {
        nodesInComp = new HashMap();
        visited = new HashSet();
        int compNumber = 0;

        for (Interval interval : intervals) {
            if (!visited.contains(interval)) {
                markComponentDFS(interval, compNumber);
                compNumber++;
            }
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        buildGraph(intervals);
        buildComponents(intervals);

        // for each component, merge all intervals into one interval.
        List<Interval> merged = new LinkedList<>();
        for (int comp = 0; comp < nodesInComp.size(); comp++) {
            merged.add(mergeNodes(nodesInComp.get(comp)));
        }

        return merged;
    }
}


public class Solution94_56_合并区间 {

    public static void main(String[] args) {
        Solution56 solution56 = new Solution56();
        Solution56_niuke ss = new Solution56_niuke();

        int[][] a = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] b = {};
        int[][] merge = solution56.merge(b);

    }
}
