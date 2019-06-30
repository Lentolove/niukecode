package tsp.niuke2018;

import java.util.Arrays;
import java.util.Scanner;


public class Q11_疯狂队列 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = sc.nextInt();
        }
        System.out.println(getCrazyValue(h,n));
    }

    /**
     *
     *
     *  * 小易老师是非常严厉的,它会要求所有学生在进入教室前都排成一列,并且他要求学生按照身高不递减的顺序排列。有一次,n个学生在列队的时候,小易老师正好去卫生间了。学生们终于有机会反击了,于是学生们决定来一次疯狂的队列,他们定义一个队列的疯狂值为每对相邻排列学生身高差的绝对值总和。由于按照身高顺序排列的队列的疯狂值是最小的,他们当然决定按照疯狂值最大的顺序来进行列队。
     *  * 现在给出n个学生的身高,请计算出这些学生列队的最大可能的疯狂值。小易老师回来一定会气得半死。
     *  * 输入描述：输入包括两行,第一行一个整数n(1 ≤ n ≤ 50),表示学生的人数
     *  * 第二行为n个整数h[i](1 ≤ h[i] ≤ 1000),表示每个学生的身高
     *  * 输出描述：输出一个整数,表示n个学生列队可以获得的最大的疯狂值。
     *  * 如样例所示:
     *  * 当队列排列顺序是: 25-10-40-5-25, 身高差绝对值的总和为15+30+35+20=100。
     *  * 这是最大的疯狂值了。
     *  * 5
     *  * 5 10 25 40 25
     *  * 输出 100
     *
     *
     * [ 5,10,25,25,40]
     * step1: value = 40 - 5 = 35  (min,max)=(5,40) (min_index,max_index)=(1,3)
     * step2:  25          5    40      10
     *      max_index     min   max   min_index ->min =10 max = 25
     * */
    public static int getCrazyValue(int[] h,int n){
        Arrays.sort(h);
        int min = h[0];// 上一次加入疯狂队列的那个最小值
        int max = h[n-1];// 上一次加入疯狂队列的那个最大值
        int value = max-min;
        int min_index = 1;
        int max_index = n-2;
        while (min_index<max_index){
            value += max- h[min_index];
            value += h[max_index] - min;
            min = h[min_index++];
            max = h[max_index--];
        }
        //当加入最后一个数字的时候 此时min_index = max_index 将它放在合适的位置上
        value += Math.max(h[max_index]-min,max-h[min_index]);
        return value;
    }
}
