package zuoshen.interview.classic5;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 左神：给定一堆信封，返回最大的嵌套层数
 * 题目：每个信封都有长和宽两个维度的数据，A信封如果想嵌套在B信封里，A信封的长宽都必须小于B信封
 * 如果给一批信封，返回最大的嵌套层数
 */
public class Code05_EnvelopesProblem {

    public static class Envelope {
        public int l;//长度
        public int h;//高度

        public Envelope(int l, int h) {
            this.l = l;
            this.h = h;
        }
    }

    //信封按照长度从小到大排序，长度相等的，高度从大到小排序
    public static class EnvelopeCompare implements Comparator<Envelope> {

        @Override
        public int compare(Envelope o1, Envelope o2) {
            return o1.l == o2.l ? o2.h - o1.h : o1.l - o2.l;
        }
    }

    /**
     * 最大信封嵌套层数：eg:(3,2),(2,4),(3,5),(1,2),(2,3)
     * 排序后：(1,2),(2,4),(2,3),(3,5),(3,2)
     * 高度：[2,4,3,5,2] => 等价于求该数组的最长递增子序列
     */
    public static int maxEnvelops(int[][] matrix) {
        Envelope[] envelopes = getSortEnvelopes(matrix);
        int n = matrix.length;
        //记住ends求最长递增子数组
        int[] ends = new int[n];
        int right = 0;//维持ends数组的边界
        ends[0] = envelopes[0].h;
        int l, r, m;
        for (int i = 1; i < n; i++) {
            l = 0;
            r = right;
            //二分查找ends数组左边界
            while (l <= r) {
                m = (l + r) / 2;
                if (envelopes[i].h > ends[m]) {
                    l = m + 1;
                } else {
                    r = r - 1;
                }
            }
            right = Math.max(right, l);
            ends[i] = envelopes[i].h;
        }
        //最终返回ends的最长长度
        return right + 1;
    }

    //返回排序好的信封列表：长从小到大，宽度从大到小
    public static Envelope[] getSortEnvelopes(int[][] matrix) {
        int n = matrix.length;
        Envelope[] ans = new Envelope[n];
        for (int i = 0; i < n; i++) {
            ans[i] = new Envelope(matrix[i][0], matrix[i][1]);
        }
        Arrays.sort(ans, new EnvelopeCompare());
        return ans;
    }

    public static void main(String[] args) {
        int[][] test = { { 3, 4 }, { 2, 3 }, { 4, 5 }, { 1, 3 }, { 2, 2 }, { 3, 6 }, { 1, 2 }, { 3, 2 }, { 2, 4 } };
        System.out.println(maxEnvelops(test));
    }

}
