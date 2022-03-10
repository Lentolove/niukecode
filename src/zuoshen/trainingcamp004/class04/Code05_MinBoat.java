package zuoshen.trainingcamp004.class04;

import java.util.Arrays;

/**
 * Author : tsp
 * Time: 2022/3/4 14:20
 * Desc : 课程十七-》 最少船只数量
 * 题目：给定一个数组 arr,长度为 N，且每个数都是正数，代表N个人的体重，在给定一个正数limit，
 * 代表一艘船的载重：坐船规则如下：
 * 1.每艘船最多只能载两人
 * 2.乘客的体重和不能超过 limit
 * 返回如果同时让N个人过河最少需要几条船
 */
public class Code05_MinBoat {

    /**
     * 思路：对arr进行排序，然后双指针从 > limit/2 位置为分界线向左右移动
     * eg: 1,1,3,3,4,4,5,5,5 | 6,6,6,7,7,7,9,9,9
     * 1.如果排序后体重的最大值超过 limit，则无方案,直接返回 -1
     * 2.定义L，R指针，以 arr[l] 来控制 arr[r],如果 arr[l] + arr[r] <= limit，则不断移动r指针，移动距离 rightUse
     * 说明当前 l 位置的数可以和 [r,r + rightUse) 共同乘坐一条船，l向左括 rightUse 个位置，
     * 由(l - rightUser,l] 来匹配 [r,r + rightUse)距离，需要穿 rightUse 只，如果左侧数据不够，则剩下的右侧部分每人都需要一艘船
     * 3.
     */
    public static int minBoat(int[] arr, int limit) {
        if (arr == null || arr.length == 0) return 0;
        //排序，O(nlogn)
        int n = arr.length;
        Arrays.sort(arr);
        if (arr[n - 1] > limit) return -1;//无方案
        //2.找到 limit / 2 边界,及找到第一个比 limit/2,
        int index = -1;
        //从后往前找比较好，不然出现重复的 limit/2
        //1,1,2,2,5,5 | 7,7,9 limit = 10
        for (int i = 0; i < n; i++) {
            if (arr[i] > limit / 2) {
                index = i;
                break;
            }
        }
        //2.可能第一个就大于limit/2,或者到最后也没找到大于 limit / 2 的
        if (index == 0) return n; //第一个就大于limit / 2
        if (index == -1) return (n + 1) / 2;//所有都小于limit / 2
        //3.在中间位置
        int l = index - 1;//[0,l] 都是小于等于 limit/2
        int r = index;//[r,n - 1] 都是大于 limit
        //4.记录左侧画x的位置
        int noUse = 0;
        while (l >= 0) {//根据左指针移动又指针
            int solve = 0;//记录当前l可以解决右侧多少个数(能坐一条船)
            while (r < n && (arr[l] + arr[r]) <= limit) {
                solve++;
                r++;
            }
            if (solve == 0) {//一个都没解决，说明l当前的数没法匹配后面的所有
                //说明当前的 l 位置无法找到一个 | 右边的去乘坐一条船,将当前 l 位置画 x,l指针继续往左移动
                noUse++;
                l--;
            } else {
                //找到可以匹配的区间,那么久从 <= l 区间也划分出等长与 solve 向匹配，可能会出现越界的情况,l指针左移动 solve位置
                l = Math.max(-1, l - solve);
                //如果等于 -1，则跳出循环，说明剩下的 [r,n-1]都需要一个人一条船,如果还有，则继续上述过程
            }
        }
        //现在统计总的船只数量
        int leftCount = index;// limit/2左侧区间的总数量
        int used = leftCount - noUse;//左侧无效的数字，就是左侧打对号的数字，可以与右侧进行匹配的船数
        //n - leftCount右侧数量，减去左侧已经匹配的 used 的数量，剩下的就是未匹配的
        int rightUnSolved = (n - leftCount) - used;
        //最后剩下的就是左侧为匹配上的数量 noUse，则他们两个人一条船，向上取证
        return rightUnSolved + used + ((noUse + 1) / 2);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3,4,4, 4, 4, 4, 5 };
        int weight = 6;
        System.out.println(minBoat(arr, weight));
    }

}
