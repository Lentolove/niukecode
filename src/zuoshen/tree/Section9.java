/*
 * @Author: your name
 * @Date: 2021-12-14 17:14:56
 * @LastEditTime: 2021-12-15 14:19:52
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: \niukecode\src\zuoshen\tree\Section9.java
 */
package zuoshen.tree;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Section9 {

    /************* 会议日程安排问题*********** */

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 左神：会议日程安排问题
     * 问题：一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目宣讲。
     * 给你每个项目的开始时间和结束时间，你来安排宣讲的日程，要求会议室进行宣讲的场数最多。
     */
    public static int bestArrange(Program[] list) {
        // 按照会议结束时间排序
        Arrays.sort(list, (a, b) -> a.end - b.end);
        // timeline表示来到的时间点
        int tiemLine = 0;
        int result = 0;
        for (int i = 0; i < list.length; i++) {
            if (tiemLine <= list[i].start) {
                result++;
                tiemLine += list[i].end;
            }
        }
        return result;
    }

    /************************* 居民楼路灯问题********************************* */
    /**
     * 左神:居民楼路灯问题
     */
    public int minLight(String road) {
        char[] str = road.toCharArray();
        // 出发位置
        int index = 0;
        // 放置的灯数量
        int light = 0;
        while (index < str.length) {
            // 1.当前位置是墙“X",则直接跳到下一个点
            if (str[index] == 'X') {
                index++;
            } else {
                // i 位置是 . 不管i+1是X还是.当前区域需要放灯
                light++;
                if (index + 1 == str.length) {
                    // 到末尾了，直接返回
                    break;
                } else {
                    // 如果i + 1位置是 x，则在i+2位置做决定
                    if (str[index + 1] == 'X') {
                        index += 2;
                    } else {
                        // i位置是. i+1也是. 那么不管i+2是什么，都在i+1位置放灯，到i+3去做决定
                        index += 3;
                    }
                }
            }
        }
        return light;
    }

/**************哈夫曼树问题-金条切割*************************** */
    /**
     * 左神：一根金条切成两半，是需要花费和长度值一样的铜板的。比如长度为20的金条，不管怎么切，
     * 都需要花费20个铜板。一群人想整分整块金条，怎么分最省铜板？
     */
    public int lessMoney(int[] arr){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int a : arr){
            queue.add(a);
        }
        int sum = 0;
        while(queue.size() > 1){
             // 每一次弹出两个数，合并成一个数,合成的数一定输非叶子节点
             int cur = queue.poll() + queue.poll();
             //把合成的累加到sum中去
             sum += cur;
             queue.add(sum);
        }
        return sum;
    }

/*****************项目花费和利润问题*************************** */

    class Programe{
        int c;//花费，4 
        int p;//利润，1，干完后手上5
        Programe(int p,int c){
            this.p = p;
            this.c = c;
        }
    }
    
    /**
     * 左神：项目花费和利润问题
     * 思路：将profits 和 capital组成一个整体进行排序。
     * @param k 总共可以做的项目次数
     * @param w 目前手上的初始本金
     * @param profits 每个项目的纯利润
     * @param capital 每个项目的花费
     */
    public int findMaxCapital(int k,int w,int[] profits,int[] capital){
        //1.由花费组织的小根堆
        PriorityQueue<Programe> minCost = new PriorityQueue<>((a,b) -> a.c - b.c);
        //2.由利润组织的大根堆，每次拿出最大利润
        PriorityQueue<Programe> maxProfit = new PriorityQueue<>((a,b) -> b.p - a.p);
        //先把所有的项目方法到小根堆中
        for(int i = 0; i < profits.length; i++){
            minCost.add(new Programe(profits[i], capital[i]));
        }
        //现在开始做项目，最多做k个项目，直到总本金不够下一个项目的花费
        for(int i = 0 ; i < k; i++){
            //小根堆不为空，且堆顶的花费被我当前启动资金cover住,这个项目我本金可以接下,我的本金w是可以累加的
            while(!minCost.isEmpty() && minCost.peek().c <= w){
                //将小根堆中的项目放入大根堆中，表示可以做，从中挑选出利润最大的
                maxProfit.add(minCost.poll());
            }
            //大根堆为null，表示无项目可做了，当前的本金不够项目花费了
            if(maxProfit.isEmpty()){
                return w;
            }
            //开始最项目，跟新我的w收益，直接将利润加到我们的w本金上，
            w += maxProfit.poll().p;
        }
        return w;
    }


}
