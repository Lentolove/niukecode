import tsp.offer.TreeNode;

import java.util.*;

/**
 * leetcode:85 最大矩形 maximal-rectongle (Hard)
 */
public class Solution {


    public static void main(String[] args) {

//        System.out.println(isMatch("aab","c*a*b"));
        int[] nums = {1, 2, 3, 4};
//
//        int[][] mar ={{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//        int[] ints = spiralOrder(mar);
//        System.out.println();
//        for (int a:ints){
//            System.out.println(a);
//        }
//
//        Stack<Integer> stack = new Stack<>();
//        Integer peek = stack.peek();
//
//        Queue<Integer> queue = new LinkedList<>();
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);

//        TreeNode treeNode = TreeNode.creatTreeNode();
////        printNum(ints1);
//        System.out.println("jj");
//
//        String node = "[1,2,3,null,null,4,5]";
//
//        PriorityQueue<Integer> queue = new PriorityQueue<>(2, (o1, o2) -> 0);
//
//
//        PriorityQueue<String> queue1 = new PriorityQueue<>((a, b) -> (a + b).compareTo(b + a));
//
//
//        MedianFinder medianFinder = new MedianFinder();
//        System.out.println(medianFinder.findMedian());
//        medianFinder.addNum(1);
//        medianFinder.addNum(2);
//        System.out.println(medianFinder.findMedian());
//        medianFinder.addNum(3);
//        System.out.println(medianFinder.findMedian());

//        findContinuousSequence(15);

//        String s = "a good   example";
//        System.out.println(reverseWords(s));

        int[] numbers = getLeastNumbers(nums, 2);


    }

    //1.借助大堆，每次拿堆中的最大值
    public static int[] getLeastNumbers(int[] arr, int k) {
        int[] ans = new int[k];
        if (k == 0 || arr == null || arr.length < k) return ans;
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (a, b) -> b - a);
        for (int i = 0; i < k; i++) {
            queue.add(ans[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.add(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll();
        }
        return ans;
    }


    class MaxQueue {

        private Deque<Integer> queue, maxqueue;

        public MaxQueue() {
            //保存输入值
            queue = new LinkedList<>();
            //保存最大值
            maxqueue = new LinkedList<>();
        }

        public int max_value() {
            if (maxqueue.isEmpty()) return -1;
            return maxqueue.peekFirst();
        }

        public void push_back(int value) {
            //确保maxqueue 倒序排列的,这样每次弹出最大
            while (!maxqueue.isEmpty() && maxqueue.peekLast() < value) {
                maxqueue.pollLast();
            }
            maxqueue.addLast(value);
            queue.addLast(value);
        }

        public int pop_front() {
            if (maxqueue.peekFirst() == queue.peekFirst()) {
                maxqueue.pollFirst();
            }
            return queue.pollFirst();
        }
    }


    public static String reverseWords(String s) {
        String[] strs = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].equals("")) continue;
            sb.append(strs[i]).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * ⽅方法⼀一：滑动窗⼝口 左右指针
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 1;
        while (i < n && j < n) {
            //扩展窗⼝口
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);//j⼜又指针
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 假设是ASCII 128 编码
     * ⽤用整数数组替换hashmap
     */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128];
        //扩展[i,j]窗⼝口
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);//记录字符出现的最⼤大位置 j-i+1就是没有重复的⼦子串串⻓长度
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;


    }


    //滑动窗口
    public static int[][] findContinuousSequence(int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int sum = 0;
        int left = 1, right = 1;
        while (left <= target / 2) {
            //只能右移
            if (sum < target) {
                sum += right;
                right++;
            } else if (sum > target) {
                sum -= left;
                left++;
            } else {
                List<Integer> item = new ArrayList<>();
                for (int i = left; i < right; i++) {
                    item.add(i);
                }
                ans.add(item);
                sum -= left;
                left++;
            }
        }
        int[][] result = new int[ans.size()][1];
        for (int i = 0; i < ans.size(); i++) {
            List<Integer> list = ans.get(i);
            int[] item = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                item[j] = list.get(j);
            }
            result[i] = item;
        }
        return result;

    }

    public static int[][] findContinuousSequence1(int target) {
        int i = 1; // 滑动窗口的左边界
        int j = 1; // 滑动窗口的右边界
        int sum = 0; // 滑动窗口中数字的和
        List<int[]> res = new ArrayList<>();

        while (i <= target / 2) {
            if (sum < target) {
                // 右边界向右移动
                sum += j;
                j++;
            } else if (sum > target) {
                // 左边界向右移动
                sum -= i;
                i++;
            } else {
                // 记录结果
                int[] arr = new int[j - i];
                for (int k = i; k < j; k++) {
                    arr[k - i] = k;
                }
                res.add(arr);
                // 左边界向右移动
                sum -= i;
                i++;
            }
        }

        return res.toArray(new int[res.size()][]);
    }


    static class MedianFinder {

        /**
         * min 小顶堆，保存大于中位数字的数字
         * max 大顶堆，保存小于中位数字的数字
         * 确保 Math.ads(min - max) <= 1。
         * 当 length 为奇数时候，说明这个元素要添加到大堆中，但是为了确保大堆的元素都是小于小堆的，所以先把元素添加到小堆
         * 在将小堆的最小元素poll到大堆中
         */
        private PriorityQueue<Integer> min, max;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            //小堆
            min = new PriorityQueue<>();
            //大堆
            max = new PriorityQueue<>((a, b) -> b - a);
        }

        public void addNum(int num) {
            if (min.size() != max.size()) {
                //奇数,元素最终放到 max 中
                min.add(num);
                max.add(min.poll());
            } else {
                //偶数，元素最终需要被添加到 min 中
                max.add(num);
                min.add(max.poll());
            }
        }

        public double findMedian() {
            return min.size() != max.size() ? min.peek() : (min.peek() + max.peek() / 2.0);
        }
    }

    public static void printNum(int[] num) {
        for (int i : num) {
            System.out.println(i);
        }

    }


    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        //1.异常处理
        if (root == null) return "[]";
        //2.借助队列实现层序遍历。
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                sb.append(node + ",");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                sb.append("null,");
            }
        }
        //删除最后多余的，
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 1.特殊处理：data 为 "[]" ，返回 null
     * 2.对上面序列化的字符串进行切割处理。去掉前后[,],同时以","切割字符串 ,字符串数据遍历指针 i = 1，跟节点root = value[0]
     * 3.按照层序遍历构建二叉树
     * 1.节点处队列记 为 node
     * 2.构建 node 节点的左子节点的值为 value[i],入队里。同时 i++，继续操作右子节点。
     */
    public static TreeNode deserialize(String data) {
        //1.空数据判断
        if ("[]".equals(data)) return null;
        String[] str = data.substring(1, data.length() - 1).split(",");
        //开始构建二叉树的根节点。同时创建队列
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            //如果当前数组的值不为 null 就将它添加到 node的左子树中
            if (!"null".equals(str[i])) {
                node.left = new TreeNode(Integer.parseInt(str[i]));
                queue.add(node.left);
            }
            i++;
            //右子树
            if (!"null".equals(str[i])) {
                node.right = new TreeNode(Integer.parseInt(str[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    public static TreeNode deserialize1(String data) {
        if (data.equals("[]")) return null;
        String[] vals = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<TreeNode>() {{
            add(root);
        }};
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!vals[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.left);
            }
            i++;
            if (!vals[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    //层序遍历
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> item = new ArrayList<>(size);
            while (size > 0) {
                TreeNode node = queue.poll();
                size--;
                item.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            //根据是第奇数层还是偶数层，从而反转结果
            if (ans.size() % 2 == 1) Collections.reverse(item);
            ans.add(item);

        }
        return ans;
    }


    //二叉树的层序遍历，借助队列
    public static int[] levelOrder(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            //当前层有多少个节点
            int size = queue.size();
            while (size > 0) {
                size--;
                TreeNode node = queue.poll();
                if (node == null) continue;
                ans.add(node.val);
                //先添加左子树，再添加右子树
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        return result;
    }


    //常规思路，动态规划
    public static int cuttingRope(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        //定义 dp 数组
        int[] dp = new int[n + 1];
        //dp[1] = 1,dp[2] = 2,dp[3] = 3,取值问题，f(5) = max(f(1) * f(4),f(2) * f(3)), 从 f(4) 开始定义 dp 数组
        for (int i = 0; i < 4; i++) {
            dp[i] = i;
        }
        //从 dp[4] 开始计算
        for (int i = 4; i <= n; i++) {
            int max = 0;
            //因为 f(2) * f(3) = f(3) * f(2)
            for (int j = 1; j <= i / 2; j++) {
                max = Math.max(max, dp[j] * dp[i - j]);
            }
            dp[i] = max;
        }
        return dp[n];
    }

    //动态规划
    public static boolean isMatch(String s, String p) {
        int n1 = s.length();
        int n2 = p.length();
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        dp[0][0] = true;
        for (int i = 1; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                boolean first_match = p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.';
                if (j > 1 && p.charAt(j) == '*') {
                    dp[i][j] = dp[i][j - 2] || first_match && dp[i][j];
                } else {
                    dp[i][j] = first_match && dp[i - 1][j - 1];
                }
            }
        }
        return dp[n1][n2];
    }

    public static int int2Sum(int i) {
        int ans = 0;
        while (i > 0) {
            ans += i % 10;
            i /= 10;
        }
        return ans;
    }

    static class Solution1 {
        public int fib(int n) {
            if (n < 2) return n;
            if (n == 2) return 1;
            int one = 1;
            int two = 1;
            for (int i = 2; i < n; i++) {
                int temp = (one + two) % 1000000007;
                one = two;
                two = temp;
            }
            return two;
        }

        public int fib2(int n) {
            if (n < 2) return n;
            int a = 0, b = 1;
            while (n >= 2) {
                int temp = (a + b) % 1000000007;
                a = b;
                b = temp;
                n--;
            }
            return b;
        }
    }

    //首位双指针交换位置
    public static int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && (nums[left] & 1) == 1) {
                //奇数
                left++;
            }
            while (left < right && (nums[right] & 1) == 0) {
                //偶数
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        return nums;
    }


    //从外层往内层打印
    public static int[] spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] ans = new int[row * col];
        int count = 0;//打印指针
        //圈数
        int rip = (Math.min(row, col) + 1) / 2;
        for (int i = 0; i < rip; i++) {
            //从左往右
            for (int j = i; j < col - i; j++) ans[count++] = matrix[i][j];
            //从上往下
            for (int j = i + 1; j < row - i; j++) ans[count++] = matrix[j][col - i - 1];
            //从左往右
            for (int j = col - i - 2; j >= i && (row - i - 1) != i; j--) ans[count++] = matrix[row - i - 1][j];
            //从下往上
            for (int j = row - i - 2; j > i && (col - i - 1) != i; j--) ans[count++] = matrix[j][i];
        }
        return ans;
    }

}
