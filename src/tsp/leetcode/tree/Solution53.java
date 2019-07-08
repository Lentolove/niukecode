package tsp.leetcode.tree;

import tsp.offer.TreeNode;

import java.util.ArrayList;

/**
 * 不同的二叉搜索树II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * 示例:
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 */
public class Solution53 {

    public ArrayList<TreeNode> generateTrees(int n) {
        return generate_Trees(1,n);
    }

    private ArrayList<TreeNode> generate_Trees(int start,int end){
        ArrayList<TreeNode> all_trees = new ArrayList<>();
        if (start>end){
            all_trees.add(null);
            return all_trees;
        }
        //选择一个节点
        for (int i = start; i <=end ; i++) {
            // 求根结点i的左右子树集合
            ArrayList<TreeNode> left_trees = generate_Trees(start, i - 1);
            ArrayList<TreeNode> right_trees = generate_Trees(i+1, end);
            for (int j = 0; j < left_trees.size(); j++) {
                for (int k = 0; k < right_trees.size(); k++) {
                    // 将左右子树相互配对，每一个左子树都与所有右子树匹配，每一个右子树都与所有的左子树匹配
                    TreeNode root = new TreeNode(i);
                    root.left = left_trees.get(j);
                    root.right = right_trees.get(k);
                    all_trees.add(root);
                }
            }

        }
        return all_trees;
    }
}
