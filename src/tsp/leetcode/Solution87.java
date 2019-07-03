package tsp.leetcode;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 说明：m 和 n 的值均不超过 100。
 * 示例 1:
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 */
public class Solution87 {


    /**
     * 不同路径
     *
     * 算法
     * 如果第一个格点 obstacleGrid[0,0] 是 1，说明有障碍物，那么机器人不能做任何移动，我们返回结果 0。
     * 否则，如果 obstacleGrid[0,0] 是 0，我们初始化这个值为 1 然后继续算法。
     * 遍历第一行，如果有一个格点初始值为 1 ，说明当前节点有障碍物，没有路径可以通过，设值为 0 ；否则设这个值是前一个节点的值 obstacleGrid[i,j] = obstacleGrid[i,j-1]。
     * 遍历第一列，如果有一个格点初始值为 1 ，说明当前节点有障碍物，没有路径可以通过，设值为 0 ；否则设这个值是前一个节点的值 obstacleGrid[i,j] = obstacleGrid[i-1,j]。
     * 现在，从 obstacleGrid[1,1] 开始遍历整个数组，如果某个格点初始不包含任何障碍物，就把值赋为上方和左侧两个格点方案数之和 obstacleGrid[i,j] = obstacleGrid[i-1,j] + obstacleGrid[i,j-1]。
     * 如果这个点有障碍物，设值为 0 ，这可以保证不会对后面的路径产生贡献。
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int rows = obstacleGrid.length;
        int clos = obstacleGrid[0].length;
        //如果起始位置为 1 则直接返回 0
        if (obstacleGrid[0][0]==1) return 0;
        //不重新构建动态规划的数组dp 直接操作原始数组
        obstacleGrid[0][0] = 1;
        //填满第一行和第一列
        for (int i = 1; i < clos; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i]==0&&obstacleGrid[0][i-1]==1?1:0);
        }
        for (int i = 1; i < rows; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0]==0&&obstacleGrid[i-1][0]==1?1:0);

        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < clos; j++) {
                if (obstacleGrid[i][j]==0){
                    obstacleGrid[i][j] = obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
                }else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        return obstacleGrid[rows-1][clos-1];
    }

    public static void main(String[] args) {
        System.out.println(0);
    }
}
