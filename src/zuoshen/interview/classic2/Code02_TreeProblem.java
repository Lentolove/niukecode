package zuoshen.interview.classic2;

/**
 * 左神：二叉树的路径问题整理
 */
public class Code02_TreeProblem {


    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    private static int maxSum = Integer.MIN_VALUE;

    /**
     * 问题一：路劲必须从头节点出发，到叶节点截止，返回的最大路径和
     * 递归，在到达叶子结点的时候更新最大值，用一个成员变量更新最大值。
     */
    public static int maxPath(Node head) {
        if (head == null) return 0;
        maxSum = Integer.MIN_VALUE;
        maxPathDf(head, 0);
        return maxSum;
    }

    /**
     * pre 表示之前已经走过的和
     */
    public static void maxPathDf(Node node, int pre) {
        if (node.left == null && node.right == null) {
            maxSum = Math.max(maxSum, pre + node.value);
            return;
        }
        if (node.left != null) {
            maxPathDf(node.left, pre + node.value);
        }
        if (node.right != null) {
            maxPathDf(node.right, pre + node.value);
        }
    }

    /**
     * 问题二：可以从任何节点出发往下走，达到任何节点的最大值，可以不是叶节点，返回最大值
     * 树形DP框架：
     * 根据当前结果是否与 x 节点有关分两大类：
     * 1.与 x 无关，从左右孩子中拿最大值。
     * 2.与 x 有关
     * 2.1 包含 x 的整数最大值，左树最大值，右树最大值
     */
    public static int maxDis(Node node) {
        if (node == null) return 0;
        return process(node).allTreeMaxSum;
    }

    public static class Info {
        //整棵树的最大值
        int allTreeMaxSum;
        //包括头节点的最大值
        int fromHeadMaxSum;

        public Info(int allTreeMaxSum, int fromHeadMaxSum) {
            this.allTreeMaxSum = allTreeMaxSum;
            this.fromHeadMaxSum = fromHeadMaxSum;
        }
    }

    /**
     * 1) 与 x 无关的时候：1.左树上整体最大路径和 2.右树上整体最大路径和
     * 2) 与 x 有关的时候： 3. x 自己本身(不往左右走) 4.x往左走 5.x 往右走
     */
    public static Info process(Node x) {
        if (x == null) return null;
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.allTreeMaxSum;
        }
        int p3 = x.value;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = x.value + leftInfo.fromHeadMaxSum;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p5 = x.value + rightInfo.fromHeadMaxSum;
        }
        //整棵树的最大值：
        int allTreeMaxSum = Math.max(Math.max(Math.max(Math.max(p1, p2), p3), p4), p5);
        //包含当前节点的最大值
        int fromHeadMaxSum = Math.max(Math.max(p3, p4), p5);
        return new Info(allTreeMaxSum, fromHeadMaxSum);
    }

    /**
     * 问题三：路径可以从任何节点出发，到任何节点，返回最大路径和
     * 与问题2一样:只是多了一种情况：
     * 1) 与 x 无关的时候：1.左树上整体最大路径和 2.右树上整体最大路径和
     * 2) 与 x 有关的时候： 3. x 自己本身(不往左右走) 4.x往左走 5.x 往右走
     * 6. 穿过x,x左边+x右边+x本身
     */
    public static int anyMaxSum(Node node) {
        if (node == null) return 0;
        return process(node).allTreeMaxSum;
    }

    public static Info process1(Node x) {
        if (x == null) return null;
        Info leftInfo = process1(x.left);
        Info rightInfo = process1(x.right);
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.allTreeMaxSum;
        }
        int p3 = x.value;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = x.value + leftInfo.fromHeadMaxSum;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p5 = x.value + rightInfo.fromHeadMaxSum;
        }
        int p6 = Integer.MIN_VALUE;
        if (leftInfo != null && rightInfo != null) {
            p6 = x.value + leftInfo.fromHeadMaxSum + rightInfo.fromHeadMaxSum;
        }
        int allTreeMaxSum = Math.max(Math.max(Math.max(Math.max(Math.max(p1, p2), p3), p4), p5), p6);
        //从 x 开始往下的还是p3,p4,p5
        int fromHeadMaxSum = Math.max(Math.max(p3, p4), p5);
        return new Info(allTreeMaxSum, fromHeadMaxSum);
    }


    private static int maxPathSum = Integer.MIN_VALUE;

    public static int anyMaxSumToLeaf(Node node) {
        if (node == null) return 0;
        df(node);
        return maxPathSum;
    }

    /**
     * 问题四：从任意节点出发到叶节点的最大路径和
     */
    public static int df(Node node) {
        if (node.left == null && node.right == null) {
            //更新 maxPathSum
            maxPathSum = Math.max(maxSum, node.value);
            return node.value;
        }
        int nextMax = 0;
        if (node.left != null) {
            nextMax = df(node.left);
        }
        if (node.right != null) {
            nextMax = Math.max(nextMax, df(node.right));
        }
        int ans = nextMax + node.value;
        maxPathSum = Math.max(maxSum, ans);
        return ans;
    }


}
