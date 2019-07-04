package tsp.leetcode.dynamic_planning;

/**
 * 题目：candy
 * 题目描述：There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 */
public class Solution16 {


    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candys = new int[n];
        for (int i = 1; i < n; i++) {
            if (ratings[i]>ratings[i-1]){
                candys[i] = candys[i-1]+1;
            }
        }
        for (int i = n-2; i >=0; i--) {
            if (ratings[i]>ratings[i+1]&&candys[i]<=candys[i+1]){
                candys[i]=candys[i+1]+1;
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += candys[i];
        }
        return sum+n;
    }

    public static void main(String[] args) {
        int[] a = {4,2,3,4,1};
        Solution16 solution16 = new Solution16();
        int sout = solution16.candy(a);
        System.out.println(sout);
    }

}
