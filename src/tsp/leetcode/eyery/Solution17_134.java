package tsp.leetcode.eyery;

/**
 * leetcode:134 加油站
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * 说明: 
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例 1:
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gas-station
 */
public class Solution17_134 {

    /**
     *
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int total_tank = 0, curr_tank = 0;
        int start_station = 0;
        for (int i = 0; i < n; i++) {
            total_tank += gas[i] - cost[i];
            curr_tank += gas[i] - cost[i];
            if (curr_tank < 0) { //如果当前油量小于0 说明无法到达下一个站 则重新选择新的起点站
                start_station = i + 1;
                curr_tank = 0;
            }
        }
        return total_tank >= 0 ? start_station : -1;
    }

    /**
     * 从start出发， 如果油量足够， 可以一直向后走 end++；  油量不够的时候，
     * start向后退  最终 start == end的时候，如果有解一定是当前 start所在位置
     */
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int start = gas.length-1;
        int end =0;
        int sum = gas[start]-cost[start];
        while (start>end){
            if (sum>=0){
                sum+=gas[end]-cost[end];
                end++;
            }else {
                --start;
                sum += gas[start]-cost[start];
            }
        }
        return sum>=0?start:-1;
    }
}