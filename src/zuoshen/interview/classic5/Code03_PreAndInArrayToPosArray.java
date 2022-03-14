package zuoshen.interview.classic5;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 左神：给定前序遍历和中序遍历，请返回后续遍历
 * 思路：分治法
 */
public class Code03_PreAndInArrayToPosArray {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 根据前序和中序的特点：
     * 前：根左右
     * 中：左根右
     */
    public static int[] preInToPos(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        int N = pre.length;
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            inMap.put(in[i], i);
        }
        int[] pos = new int[N];
        process(pre, 0, N - 1, in, 0, N - 1, pos, 0, N - 1, inMap);
        return pos;
    }

    public static void process(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3,
                               HashMap<Integer, Integer> inMap) {
        if (L1 > R1) {
            return;
        }
        if (L1 == R1) {
            pos[L3] = pre[L1];
            return;
        }
        pos[R3] = pre[L1];
        int mid = inMap.get(pre[L1]);
        int leftSize = mid - L2;
        process(pre, L1 + 1, L1 + leftSize, in, L2, mid - 1, pos, L3, L3 + leftSize - 1, inMap);
        process(pre, L1 + leftSize + 1, R1, in, mid + 1, R2, pos, L3 + leftSize, R3 - 1, inMap);
    }


    /**
     * 根据前序遍历和中序遍历构建二叉树
     */
    public static Node buildTree(int[] pre, int[] in) {
        int n = pre.length;
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inMap.put(in[i], i);
        }
        return processTree(0, 0, n - 1, pre, inMap);
    }


    public static Node processTree(int rootIndex, int leftBound, int rightBound, int[] pre, HashMap<Integer, Integer> map) {
        if (leftBound > rightBound) return null;
        //1.构建root节点
        Node head = new Node(pre[rootIndex]);
        int index = map.get(pre[rootIndex]);
        head.left = processTree(rootIndex + 1, leftBound, index - 1, pre, map);
        head.right = processTree(rootIndex + (index - leftBound) + 1, index + 1, rightBound, pre, map);
        return head;
    }


    public static void main(String[] args) {
//        Node head = new Node(6);
//        Node a = new Node(4);
//        Node b = new Node(8);
//        Node c = new Node(3);
//        Node d = new Node(5);
//        Node e = new Node(7);
//        Node f = new Node(9);
        int[] pre = {6, 4, 3, 5, 8, 7, 9};
        int[] in = {3, 4, 5, 6, 7, 8, 9};
        int[] pos = preInToPos(pre, in);
        System.out.println(Arrays.toString(pos));
    }

}
