package tsp.leetcode.eyery;

import java.util.ArrayList;
import java.util.List;

/**
 * 57:插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 示例 1:
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 */

class Solution57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ret = new ArrayList<>();
        int p = 0;
        //确定 newInterval 区间的 开始位置在 intervals的哪个区间手段之前
        while (p < intervals.length && newInterval[0] > intervals[p][1]) {
            ret.add(intervals[p++]);
        }
        int[] temp = {newInterval[0], newInterval[1]};
        // 确定 newInterval 区间的 末尾位置在 intervals的哪个区间的开头之后
        while (p < intervals.length && newInterval[1] >= intervals[p][0]) {//我的尾部>=你的头部要
            temp[0] = Math.min(temp[0],intervals[p][0]);
            temp[1] = Math.max(temp[1],intervals[p][1]);
            p++;
        }
        ret.add(temp);
        while (p<intervals.length){
            ret.add(intervals[p++]);
        }
        return ret.toArray(new int[0][]);
    }
}


class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}


public class Solution93_57_插入区间 {

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> ret = new ArrayList<>();
        int p =0;
        int len = intervals.size();
        while (p<len&&newInterval.start>intervals.get(p).end){
            ret.add(intervals.get(p++));
        }
        Interval temp = new Interval(newInterval.start,newInterval.end);
        while (p<len&&newInterval.end>=intervals.get(p).start){
            temp.start = Math.min(temp.start,intervals.get(p).start);
            temp.end = Math.max(temp.end,intervals.get(p).end);
            p++;
        }
        ret.add(temp);
        while (p<len){
            ret.add(intervals.get(p++));
        }
        return ret;
    }


    public static void main(String[] args) {
        Solution57 s = new Solution57();
        int[][] a = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] b = {4,8};
        int[][] insert = s.insert(a, b);
    }
}
