package zuoshen.interview.classic9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 左神：给定两个有序数组 arr1 和 arr2，再给定一个整数 K，求两个数累加和最大的前
 * k 个，两个数必须分别来自 arr1 和 arr2
 */
public class Code01_TopKSumCrossTwoArrays {


    public static class Node {
        public int l1;//arr1的索引
        public int l2;//arr2的索引
        public int sum;//arr1[l1] + arr2[l2]的和

        public Node(int l1, int l2, int sum) {
            this.l1 = l1;
            this.l2 = l2;
            this.sum = sum;
        }
    }

    //按照sum排序的大根堆
    public static class NodeCompare implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }

    /**
     * 思路：两个数组有序，将 arr1 和 arr2 看成一张二维表。最大的值就是 arr1[n-1] + arr2[m - 1]
     * 1.类比于二维蓄水池问题的思路，既然是求topK问题，联想到用堆来解决。
     * 2.定义 Node(int l1,int l2,int sum)按照 sum 实现的大根堆，保存前 topK 个和。
     * 3.初始将最大位置的 Node(n-1,m-2,arr1[n-1] + arr2[m-1]) 加入到小根堆中。
     * 4.每次从小根堆中弹出的时候记录值，同时将Node的左边和上边的节点加入到小根堆中，并利用一个二维表来记录
     * 是否已经添加过。
     */
    public static int[] topKSum(int[] arr1, int[] arr2, int topK) {
        if (arr1 == null || arr2 == null || topK < 1) return new int[0];
        //数据过滤
        int n = arr1.length;
        int m = arr2.length;
        topK = Math.min(topK, n * m);
        //定义大根堆
        PriorityQueue<Node> maxHeap = new PriorityQueue<>(new NodeCompare());
        //定义flag表明哪些节点添加过，防止重复添加
        boolean[][] flag = new boolean[n][m];
        //1.先将最大值放入到大根堆中，然后依次加入它的附近点
        maxHeap.add(new Node(n - 1, m - 1, arr1[n - 1] + arr2[m - 1]));
        flag[n - 1][m - 1] = true;
        int[] ans = new int[topK];
        int ansIndex = 0, l1 = 0, l2 = 0;

        while (ansIndex != topK && !maxHeap.isEmpty()) {
            Node curNode = maxHeap.poll();
            ans[ansIndex++] = curNode.sum;
            l1 = curNode.l1;
            l2 = curNode.l2;
            //将左边和上方的点加入到堆中
            if (l1 > 0 && !flag[l1 - 1][l2]) {//上方
                flag[l1 - 1][l2] = true;
                maxHeap.add(new Node(l1 - 1, l2, arr1[l1 - 1] + arr2[l2]));
            }
            if (l2 > 0 && !flag[l1][l2 - 1]) {
                flag[l1][l2 - 1] = true;
                maxHeap.add(new Node(l1, l2 - 1, arr1[l1] + arr2[l2 - 1]));
            }
        }
        return ans;
    }

    // For test, this method is inefficient but absolutely right
    public static int[] topKSumTest(int[] arr1, int[] arr2, int topK) {
        int[] all = new int[arr1.length * arr2.length];
        int index = 0;
        for (int i = 0; i != arr1.length; i++) {
            for (int j = 0; j != arr2.length; j++) {
                all[index++] = arr1[i] + arr2[j];
            }
        }
        Arrays.sort(all);
        int[] res = new int[Math.min(topK, all.length)];
        index = all.length - 1;
        for (int i = 0; i != res.length; i++) {
            res[i] = all[index--];
        }
        return res;
    }

    public static int[] generateRandomSortArray(int len) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * 50000) + 1;
        }
        Arrays.sort(res);
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i != arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int a1Len = 5000;
        int a2Len = 4000;
        int k = 20000000;
        int[] arr1 = generateRandomSortArray(a1Len);
        int[] arr2 = generateRandomSortArray(a2Len);
        long start = System.currentTimeMillis();
        int[] res = topKSum(arr1, arr2, k);
        long end = System.currentTimeMillis();
        System.out.println(end - start + " ms");

        start = System.currentTimeMillis();
        int[] absolutelyRight = topKSumTest(arr1, arr2, k);
        end = System.currentTimeMillis();
        System.out.println(end - start + " ms");

        System.out.println(isEqual(res, absolutelyRight));

    }

}

