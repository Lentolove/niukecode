package tsp.leetcode.tree;

import tsp.offer.TreeNode;

import java.util.*;

/**
 * 题目：路径综合-II
 * https://leetcode-cn.com/problems/path-sum-ii/
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class Solution37 {
    /**
     * 想半天没做出来
     */
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    ArrayList<Integer> item = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        if (root==null) return result;
        item.add(root.val);
        if (root.left==null&&root.right==null&&sum-root.val==0){
            result.add(new ArrayList<>(item));
        }
        /**
         *               5
         *              / \
         *             4   8
         *            /   / \
         *           11  13  4
         *          /  \    / \
         *         7    2  5   1
         *
         */
        pathSum(root.left,sum-root.val);//走到7这个点，左右子树都为0，sum不满足条件
        pathSum(root.right,sum-root.val);
        //每一次到根节点不符合则清空it
        item.remove(item.size()-1);  // 7这个节点不满足条件，回退到11节点，继续执行11.right,同时移除不满条件的叶节点7
        return result;
    }

    /**
     * 递归的第二种写法
     */
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> pathSum2(TreeNode root, int sum) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        paths(root, sum, list);
        return res;
    }
    private void paths(TreeNode root, int sum, ArrayList<Integer> list){
        //如果是个空树，直接返回null;
        if(root == null)
            return;
        if(root.left == null && root.right == null && sum - root.val == 0){
            list.add(root.val);
            res.add(new ArrayList<Integer>(list));
            list.remove(list.size() - 1);
            return;
        }
        list.add(root.val);
        paths(root.left, sum - root.val, list);
        paths(root.right, sum - root.val, list);
        list.remove(list.size() - 1); //每次递归不满足条件得回退
    }

    /**
     * 非递归深度遍历二叉树
     */
    public ArrayList<ArrayList<Integer>> pathSum1(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        if (root==null) return lists;
        ArrayList<TreeNode> quene = new ArrayList<>();
        quene.add(root);
        while (!quene.isEmpty()){
            TreeNode node = quene.remove(0);
            sum -= node.val;

        }

        return lists;
    }


    public ArrayList<ArrayList<Integer>> pathSum3(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> rs = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return rs;
        }
        TreeNode p = root;
        Stack<TreeNode> S = new Stack<TreeNode>();
        int curSum = 0;
        Map<TreeNode,ArrayList<Integer>> map1 = new HashMap<TreeNode,ArrayList<Integer>>();
        Map<TreeNode,Integer> map2 = new HashMap<TreeNode,Integer>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        while (S.isEmpty() == false || p != null) {
            if (p != null) {
                curSum += p.val;
                S.push(p);
                tmp.add(p.val);
                map1.put(p, new ArrayList<Integer>(tmp));
                map2.put(p, curSum);
                if (p.left == null && p.right == null && curSum == sum){
                    rs.add(map1.get(p));
                }
                p = p.left;
            } else{
                p = S.pop();
                tmp = map1.get(p);
                curSum = map2.get(p);
                p = p.right;
            }
        }
        return rs;
    }


    public static void main(String[] args) {
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(4);
        TreeNode d = new TreeNode(8);
        TreeNode e = new TreeNode(2);
        TreeNode f = new TreeNode(7);
        TreeNode g = new TreeNode(3);
        TreeNode h = new TreeNode(9);
        a.left=b;
        a.right=d;
        b.left=e;
        b.right=f;
        d.left=g;
        d.right=h;
        Solution37 so = new Solution37();
        ArrayList<ArrayList<Integer>> arrayLists = so.pathSum(a, 16);
        System.out.println(Arrays.asList(arrayLists));
    }
}
