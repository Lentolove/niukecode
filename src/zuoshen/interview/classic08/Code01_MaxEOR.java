package zuoshen.interview.classic08;

/**
 * 左神：求子数组的最大异或和
 * 题目：一个数组的异或和是指数组中所有的树异或在一起的结果，给定一个数组arr,
 * 求最大子数组异或和
 */
public class Code01_MaxEOR {

    /**
     * 暴力解：遍历所有可能的子数组：[i,j]，求子数组的异或和
     * 遍历复杂度：O(N^2)，求异或和：O(N)，总复杂度为：O(N^3)
     */
    public static int maxXorSubArray1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int ans = Integer.MIN_VALUE;
        int n = arr.length;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                //表示[i,j]子数组的异或和
                int cur = 0;
                for (int k = i; k <= j; k++) {
                    cur ^= arr[k];
                }
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }

    /**
     * 数组预处理，先求出[0,i]的异或和
     */
    public static int maxXorSubArray2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        // 准备一个前缀异或和数组eor
        // eor[i] = arr[0...i]的异或结果
        int n = arr.length;
        int[] eor = new int[n];
        eor[0] = arr[0];
        for (int i = 1; i < n; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int ans = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                ans = Math.max(ans, i == 0 ? eor[j] : eor[i - 1] ^ eor[j]);
            }
        }
        return ans;
    }

    //前缀树的节点类型，每个节点向下走只能走0/1两种
    //node.nexts[0] == null 0方向没路
    //node.nexts[0] != null 0方向有路
    public static class Node {
        Node[] nexts = new Node[2];
    }

    //基于本题的异或和的前缀树
    public static class NumbTrie {

        //头节点
        public Node head = new Node();

        // 把某个数字newNum加入到这棵前缀树里
        // num是一个32位的整数，所以加入的过程一共走32步
        public void add(int num) {
            //前缀树的遍历
            Node cur = head;
            //从最高位开始往低位遍历
            for (int move = 31; move >= 0; move--) {
                // 从高位到低位，取出每一位的状态，如果当前状态是0，
                // path(int) = 0,如果当前状态是1,path(int) = 1
                int path = ((num >> move) & 1);
                //判断当前节点是否有路可以走，没有就新建，有就复用
                cur.nexts[path] = cur.nexts[path] == null ? new Node(): cur.nexts[path];
                //指针移动
                cur = cur.nexts[path];
            }
        }

        /**
         * 该树结构手机了之前的异或和的结果构建了前缀树，现在为当前前缀和选择最佳路径
         */
        public int maxXor(int sum){
            return 0;
        }

    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {

        int[] arr = {3, -28, -29, 2};
        System.out.println(maxXorSubArray1(arr));
        System.out.println(maxXorSubArray2(arr));

//        int testTime = 500000;
//        int maxSize = 30;
//        int maxValue = 50;
//        boolean succeed = true;
//        for (int i = 0; i < testTime; i++) {
//            int[] arr = generateRandomArray(maxSize, maxValue);
//            int comp = maxXorSubArray1(arr);
//            int res = maxXorSubarray2(arr);
//            if (res != comp) {
//                succeed = false;
//                printArray(arr);
//                System.out.println(res);
//                System.out.println(comp);
//                break;
//            }
//        }
//        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
        //
        // // int[] arr = generateRandomArray(6, maxValue);
        // int[] arr = { 3, -28, -29, 2};
        //
        // for (int i = 0; i < arr.length; i++) {
        // System.out.println(arr[i] + " ");
        // }
        // System.out.println("=========");
        // System.out.println(maxXorSubarray(arr));
        // System.out.println((int) (-28 ^ -29));

    }
}
