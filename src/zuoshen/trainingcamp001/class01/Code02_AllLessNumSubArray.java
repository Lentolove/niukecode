package zuoshen.trainingcamp001.class01;

import java.util.LinkedList;

/**
 * author : tsp
 * Date : 2022/4/13
 * DESC: 子数组达标问题，给定一个整型数组 arr，和一个正数 num，arr 中的某个子数组 sub，
 * 如果想达标，必须满足：sub 中的最大值  - sub 中的最小值 <= num，返回 arr 中达标子数组的数量。
 */
public class Code02_AllLessNumSubArray {

    /**
     * 1.本质上还是滑动窗口的思想
     */
    public static int getSum(int[] arr, int num) {
        if (arr == null || arr.length == 0) return 0;
        //1.定义双端队列，一个保存窗口的最大值，一个保存最小值，存的是arr中的索引
        LinkedList<Integer> qMin = new LinkedList<>();
        LinkedList<Integer> qMax = new LinkedList<>();
        //2.定义滑动窗口的指针
        int l = 0, r = 0;
        int n = arr.length;
        int ans = 0;
        while (l < n){
            //以当前l为开头,不断的以多功能 r 指针，直到区间 [l,r)不达标，退出，
            // 此时 [l,r-1]这个区间内是达标的，因为 r 是最后一个不达标的位置,由窗口达标规律可知，[l,r-1]达标，则子区间必达标
            while (r < n){
                //3.如果窗口不为null，则构建滑动窗口,保证 qMin 严格单调递增
                while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[r]){
                    //3.1当前元素 <= qMin窗口的结尾元素小，为了保证窗口中最小值的更新结构，将窗口中的值弹出，
                    // 相等也弹出，因为相等，下标更晚被弹出，在窗口中存活的事件更长
                    qMin.pollLast();
                }
                //3.2将当前元素添加到 qMin 中
                qMin.addLast(r);
                //4.如果窗口不为null，则构建滑动窗口,保证 qMax 严格单调递减，这样每次都能从对头中拿到最大值。
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[r]){
                    //窗口中下标对应的值：[5,4,2],此时来了一个 3，保证单调递减，就要弹出 2
                    qMax.pollLast();
                }
                qMax.addLast(r);
                //TODO 5.此时最小值窗口和最大值窗口结构已经更新好了，验证 [L，r)是否达标
                if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num){
                    //不达标，则退出当前r指针的移动，表明达标窗口已经到边界了
                    break;
                }
                //否则继续移动r指针，直到不满足达标为止
                r++;
            }
            //6.找到r能移动到的最远区间了，此时从l到r-1一共有 r - l 个子数组
            ans += r - l;
            //7.移动l指针，尝试下一个位置，同时更新双端队列窗口中的值，判断当前l处的元素有没有被移出
            if (qMin.peekFirst() == l){
                qMin.pollFirst();
            }
            if (qMax.peekFirst() == l){
                qMax.pollFirst();
            }
            //缩小窗口，l指针又移动
            l++;
        }
        return ans;
    }

    /**
     * 暴力测试代码
     */
    public static int getSum1(int[] arr,int num){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        int n = arr.length;
        for (int l = 0; l < n ; l++) {
            for (int r = l; r < n; r++) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int i = l; i <= r ; i++) {
                    min = Math.min(arr[i],min);
                    max = Math.max(max,arr[i]);
                }
                if (max - min <= num){
                    ans++;
                }

            }
        }
        return ans;
    }


    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] array = getRandomArray(30);
        int num = 5;
        System.out.println(getSum(array,num));
        System.out.println(getSum1(array,num));

    }

}
