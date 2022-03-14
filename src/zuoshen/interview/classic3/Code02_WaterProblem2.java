package zuoshen.interview.classic3;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 左神：二维蓄水池问题
 */
public class Code02_WaterProblem2 {

    /**
     * 封装二维矩阵的位置点左边及其值
     * 根据值来进行比较排序
     * node{value,row,col}
     */
    public static class Node {
        public int value;
        public int row;
        public int col;

        public Node(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }

    /**
     * 定义小根堆比较器，根据 value 值来进行比较
     */
    public static class NodeCompare implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    /**
     * 计算二维矩阵的蓄水量
     */
    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) return 0;
        int N = heightMap.length;
        int M = heightMap[0].length;
        //1.定义小堆，将四周加入到小堆中，并定义 flag 记录是否已经添加到小根堆中
        boolean[][] isEnter = new boolean[N][M];
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeCompare());
        //2.遍历四周加入
        for (int i = 0; i < M; i++) {//第一行和最后一行
            //第一行
            isEnter[0][i] = true;
            heap.add(new Node(heightMap[0][i], 0, i));
            //最后一行
            isEnter[N - 1][i] = true;
            heap.add(new Node(heightMap[N - 1][i], N - 1, i));
        }
        //加入第一列和最后一列
        for (int i = 1; i < N - 1; i++) {
            //第一列
            isEnter[i][0] = true;
            heap.add(new Node(heightMap[i][0], i, 0));
            //最后一列
            isEnter[i][M - 1] = true;
            heap.add(new Node(heightMap[i][M - 1], i, M - 1));
        }
        //开始计算蓄水量,每个位置蓄水量+water中
        int water = 0;
        //每个 node 弹出的时候(小根堆弹出的都是最小值),如果当前的node.value比 max 大，则更新 max
        int max = 0;
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            max = Math.max(max, cur.value);
            int r = cur.row;
            int c = cur.col;
            //判断临近的四个点是否满足蓄水条件，并添加到heap中，更新 isEnter，因为当前的边界最小值为 max,
            // 如果要加如的node的高度比max小，说明可以蓄水
            if (r > 0 && !isEnter[r - 1][c]) {
                //上方的点,加入heap中，并且判断当前位置是否可以蓄
                water += Math.max(0, max - heightMap[r - 1][c]);
                //加入到 heap 中
                isEnter[r - 1][c] = true;
                heap.add(new Node(heightMap[r - 1][c], r - 1, c));
            }
            if (r < N - 1 && !isEnter[r + 1][c]) {
                water += Math.max(0, max - heightMap[r + 1][c]);
                isEnter[r + 1][c] = true;
                heap.add(new Node(heightMap[r + 1][c], r + 1, c));
            }
            if (c > 0 && !isEnter[r][c - 1]) {
                water += Math.max(0, max - heightMap[r][c - 1]);
                isEnter[r][c - 1] = true;
                heap.add(new Node(heightMap[r][c - 1], r, c - 1));
            }
            if (c < M - 1 && !isEnter[r][c + 1]) {
                water += Math.max(0, max - heightMap[r][c + 1]);
                isEnter[r][c + 1] = true;
                heap.add(new Node(heightMap[r][c + 1], r, c + 1));
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {3,4,4,4},
                {3,2,1,4},
                {3,1,1,4},
                {3,4,4,4},
        };
        System.out.println(trapRainWater(arr));
    }

}
